package io.github.puzzle.cosmic.api.entity.player;

import com.badlogic.gdx.math.Vector3;
import io.github.puzzle.cosmic.api.account.IPuzzleAccount;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockPosition;
import io.github.puzzle.cosmic.api.entity.IPuzzleEntity;
import io.github.puzzle.cosmic.api.item.IPuzzleItemStack;
import io.github.puzzle.cosmic.api.util.IPuzzleIdentifier;
import io.github.puzzle.cosmic.api.world.IPuzzleChunk;
import io.github.puzzle.cosmic.api.world.IPuzzleWorld;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Player")
public interface IPuzzlePlayer {

    IPuzzleEntity pGetEntity();

    IPuzzlePlayerEntity pGetPlayerEntity();

    Vector3 pGetViewOffset();
    void pProneCheck(IPuzzleZone zone);
    void pCrouchCheck(IPuzzleZone zone);

    void pRespawn(IPuzzleWorld world);
    void pRespawn(IPuzzleZone zone);

    void pSetPosition(float x, float y, float z);

    IPuzzleZone pGetZone();
    IPuzzleChunk pGetChunk(IPuzzleWorld world);

    short pGetBlockLight(IPuzzleWorld world);
    int pGetSkyLight(IPuzzleWorld world);

    void pSpawnDroppedItem(IPuzzleWorld world, IPuzzleItemStack itemStack);

    IPuzzleBlockPosition pGetBlockPosition();
    Vector3 pGetPosition();

    boolean pIsLoading();

    default void pSetZone(IPuzzleZone zone) {
        pSetZone(zone.pGetId());
    }

    default void pSetZone(IPuzzleIdentifier zoneId) {
        pSetZone(zoneId.asString());
    }

    void pSetZone(String zoneId);

    boolean pIsDead();

    IPuzzleAccount pGetAccount();
    String pGetUsername();

}
