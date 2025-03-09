package com.github.puzzle.buildsrx.transformers;

import com.github.puzzle.buildsrx.GameScanner;
import io.github.puzzle.cosmic.util.APISide;
import io.github.puzzle.cosmic.util.annotation.compile.Alternative;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;
import io.github.puzzle.cosmic.util.annotation.compile.EnvOnly;
import io.github.puzzle.cosmic.util.annotation.compile.SourceOnly;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Objects;

public class ClassPreProcessor extends AbstractClassTransformer {

    private final APISide side;

    public ClassPreProcessor(APISide side) {
        this.side = side;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (Objects.equals(descriptor, SourceOnly.class.descriptorString()))
            MemberRemoveClassTransformer.classesToRemove.add(className);

        return new ClassAnnotationScanner(descriptor, super.visitAnnotation(descriptor, visible));
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        return new FieldScanner(super.visitField(access, name, descriptor, signature, value), name);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        return new MethodScanner(super.visitMethod(access, name, descriptor, signature, exceptions), name, descriptor);
    }

    public class FieldScanner extends FieldVisitor {

        public String name;

        protected FieldScanner(FieldVisitor methodVisitor, String name) {
            super(Opcodes.ASM9, methodVisitor);
            this.name = name;
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            if (Objects.equals(descriptor, SourceOnly.class.descriptorString()))
                MemberRemoveClassTransformer.fieldsToRemove.add(className+name);

            return new FieldAnnotationScanner(className + name, descriptor, super.visitAnnotation(descriptor, visible));
        }
    }

    public class FieldAnnotationScanner extends AnnotationVisitor {

        String descriptor;
        String field;

        protected FieldAnnotationScanner(String field, String descriptor, AnnotationVisitor visitor) {
            super(Opcodes.ASM9);

            this.descriptor = descriptor;
            this.field = field;
            av = visitor;
        }

        @Override
        public void visit(String name, Object value) {
            if (Objects.equals(descriptor, EnvOnly.class.descriptorString())) {
                if (name.equals("value")) {
                    APISide s = (APISide) value;
                    if (s != side && side != null)
                        MemberRemoveClassTransformer.fieldsToRemove.add(field);
                }
            }
            super.visit(name, value);
        }
    }

    public class MethodScanner extends MethodVisitor {

        public String name;
        public String descriptor;

        protected MethodScanner(MethodVisitor methodVisitor, String name, String descriptor) {
            super(Opcodes.ASM9, methodVisitor);
            this.name = name;
            this.descriptor = descriptor;
        }

        @Override
        public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
            if (Objects.equals(descriptor, SourceOnly.class.descriptorString()))
                MemberRemoveClassTransformer.methodsToRemove.add(className + name + this.descriptor);

            return new MethodAnnotationScanner(className + name + this.descriptor, descriptor, super.visitAnnotation(descriptor, visible));
        }
    }

    public class MethodAnnotationScanner extends AnnotationVisitor {

        String descriptor;
        String method;

        protected MethodAnnotationScanner(String method, String descriptor, AnnotationVisitor visitor) {
            super(Opcodes.ASM9);

            this.descriptor = descriptor;
            this.method = method;
            av = visitor;
        }

        @Override
        public void visit(String name, Object value) {
            if (Objects.equals(descriptor, EnvOnly.class.descriptorString())) {
                if (name.equals("value")) {
                    APISide s = (APISide) value;
                    if (s != side && side != null)
                        MemberRemoveClassTransformer.methodsToRemove.add(method);
                }
            }
            super.visit(name, value);
        }
    }

    public class ClassAnnotationScanner extends AnnotationVisitor {

        String descriptor;

        protected ClassAnnotationScanner(String descriptor, AnnotationVisitor visitor) {
            super(Opcodes.ASM9);

            this.descriptor = descriptor;
            av = visitor;
        }

        @Override
        public void visit(String name, Object value) {
            if (Objects.equals(descriptor, EnvOnly.class.descriptorString())) {
                if (name.equals("value")) {
                    APISide s = (APISide) value;
                    if (s != side && side != null)
                        MemberRemoveClassTransformer.classesToRemove.add(className);
                }
            }

            if (Objects.equals(descriptor, Alternative.class.descriptorString())){
                if (name.equals("value")) {
                    String redirection = GameScanner.findClassByNameNoDupes((String) value).replaceAll("\\.", "/");
                    RedirectClassTransformer.redirections.put(className, redirection);
                    RedirectClassTransformer.redirections.put("L" + className + ";", "L" + redirection + ";");
                }
            }
            if (Objects.equals(descriptor, ApiGen.class.descriptorString())){
                if (name.equals("value")) {
                    ApiClassTransformer.apiGenerationMap.put(className, GameScanner.findClassByNameNoDupes((String) value).replaceAll("\\.", "/"));
                }
            }
            super.visit(name, value);
        }
    }

}
