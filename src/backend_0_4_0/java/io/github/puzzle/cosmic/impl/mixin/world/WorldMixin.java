package io.github.puzzle.cosmic.impl.mixin.world;

import finalforeach.cosmicreach.world.World;
import finalforeach.cosmicreach.world.Zone;
import finalforeach.cosmicreach.worldgen.ZoneGenerator;
import io.github.puzzle.cosmic.api.world.IPuzzleWorld;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Collection;
import java.util.Set;

@Internal
@Mixin(World.class)
public class WorldMixin implements IPuzzleWorld {

    @Unique
    private final transient World puzzleLoader$world = IPuzzleWorld.as(this);

    @Unique
    private final transient IZoneMap puzzleLoader$zoneMap = new IZoneMap() {
        @Override
        public IPuzzleZone get(String s) {
            return IPuzzleZone.as(puzzleLoader$world.getZoneIfExists(s));
        }

        @Override
        public IPuzzleZone getOrCreate(String s) {
            return IPuzzleZone.as(puzzleLoader$world.getZoneCreateIfNull(s));
        }

        @Override
        public void put(IPuzzleZone iPuzzleZone) {
            puzzleLoader$world.addZone(iPuzzleZone.as());
        }

        @Override
        public void put(String s, ZoneGenerator zoneGenerator) {
            puzzleLoader$world.addNewZone(s, zoneGenerator);
        }

        @Override
        public Set<String> getKeys() {
            return puzzleLoader$world.getZoneIds();
        }

        @Override
        public Collection<Zone> getValues() {
            return puzzleLoader$world.getZones();
        }
    };

    @Override
    public String pGetDisplayName() {
        return puzzleLoader$world.getDisplayName();
    }

    @Override
    public IZoneMap pGetZoneMap() {
        return puzzleLoader$zoneMap;
    }

    @Override
    public String pGetAbsolutePath() {
        return puzzleLoader$world.getFullSaveFolder();
    }

    @Override
    public String pGetFolderName() {
        return puzzleLoader$world.getWorldFolderName();
    }

    @Override
    public long pGetDayNumber() {
        return puzzleLoader$world.getDayNumber();
    }

    @Override
    public boolean pCanEnter() {
        return  puzzleLoader$world.canEnter();
    }

    @Override
    public long pGetCurrentWorldTick() {
        return puzzleLoader$world.getCurrentWorldTick();
    }

    @Override
    public IPuzzleZone pGetDefaultZone() {
        return IPuzzleZone.as(puzzleLoader$world.getDefaultZone());
    }
}
