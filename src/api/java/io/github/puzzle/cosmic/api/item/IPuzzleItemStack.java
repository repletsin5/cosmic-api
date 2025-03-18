package io.github.puzzle.cosmic.api.item;

import com.badlogic.gdx.math.Vector3;
import finalforeach.cosmicreach.savelib.crbin.CRBinDeserializer;
import finalforeach.cosmicreach.savelib.crbin.CRBinSerializer;
import finalforeach.cosmicreach.savelib.crbin.ICRBinSerializable;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockPosition;
import io.github.puzzle.cosmic.api.block.IPuzzleBlockState;
import io.github.puzzle.cosmic.api.data.point.IDataPointManifest;
import io.github.puzzle.cosmic.api.entity.IPuzzleEntity;
import io.github.puzzle.cosmic.api.entity.player.IPuzzlePlayer;
import io.github.puzzle.cosmic.api.world.IPuzzleZone;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("ItemStack")
public interface IPuzzleItemStack extends ICRBinSerializable {

    IPuzzleItemStack pCopy();
    IPuzzleItem pGetItem();
    void pSetItem(IPuzzleItem item);
    void pCycleSwapGroupItem();

    IPuzzleEntity pSpawnItemEntityAt(IPuzzleZone zone, Vector3 pos);
    void pSpawnItemEntityAt(IPuzzleBlockPosition position);

    boolean pUseItem(IPuzzleItemSlot slot, IPuzzlePlayer player, IPuzzleBlockPosition position);

    int pGetDurability();
    int pGetMaxDurability();
    boolean pHasDurability();

    void pDamage(int damage);
    boolean pIsBroken();

    boolean pCanTargetBlockForBreaking(IPuzzleBlockState state);
    boolean pIsEffectiveBreaking(IPuzzleBlockState state);
    float pGetEffectiveBreakingSpeed();

    IPuzzleItemStack pSetAmount(int amount);

    String pGetName();

    IDataPointManifest pGetPointManifest();
    void pSetPointManifest(IDataPointManifest manifest);

    // ICRBinSerializable.java methods

    @Override
    void read(CRBinDeserializer crBinDeserializer);

    @Override
    void write(CRBinSerializer crBinSerializer);
}
