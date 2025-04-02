package io.github.puzzle.cosmic.BlockEntity;

import com.github.puzzle.game.util.BlockUtil;
import finalforeach.cosmicreach.blockentities.BlockEntity;
import finalforeach.cosmicreach.blockentities.BlockEntityCreator;
import finalforeach.cosmicreach.blocks.Block;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.util.Identifier;
import finalforeach.cosmicreach.world.BlockSetter;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.block.IBlockEntity;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.impl.data.point.DataPointManifest;

public class AbstractCosmicBlockEntity extends BlockEntity implements IBlockEntity {

    public static Identifier id;
    public Zone zone;
    public int x, y, z;

    public static void register() {
        BlockEntityCreator.registerBlockEntityCreator(id.toString(), (block, zone, x, y, z) -> new AbstractCosmicBlockEntity(zone, x, y, z));
    }

    public AbstractCosmicBlockEntity(Zone zone, int x, int y, int z) {
        super(zone, x, y, z);

        this.zone = zone;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String getBlockEntityId() {
        return id.toString();
    }
}