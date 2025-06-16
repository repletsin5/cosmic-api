package io.github.puzzle.cosmic.api.entity.player;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.world.Chunk;
import finalforeach.cosmicreach.world.Zone;
import io.github.puzzle.cosmic.api.account.IAccount;
import io.github.puzzle.cosmic.api.block.IBlockPosition;
import io.github.puzzle.cosmic.api.entity.IEntity;
import io.github.puzzle.cosmic.api.item.IItemStack;
import io.github.puzzle.cosmic.api.util.IIdentifier;
import io.github.puzzle.cosmic.api.world.IChunk;
import io.github.puzzle.cosmic.api.world.IWorld;
import io.github.puzzle.cosmic.api.world.IZone;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
public interface IPlayer {

    /**
     * Gets the zone the player is in.
     * @return a {@link Zone}
     */
    Zone getZone();

    /**
     * Gets the player position as a blockPosition.
     * @return a {@link IBlockPosition}
     */
    BlockPosition getBlockPosition();


    /**
     * Checks if the player is loading.
     */
    boolean isLoading();

}
