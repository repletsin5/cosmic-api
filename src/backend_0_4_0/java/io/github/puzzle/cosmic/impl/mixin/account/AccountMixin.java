package io.github.puzzle.cosmic.impl.mixin.account;

import finalforeach.cosmicreach.GameSingletons;
import finalforeach.cosmicreach.accounts.Account;
import finalforeach.cosmicreach.networking.server.ServerSingletons;
import io.github.puzzle.cosmic.api.account.IAccount;
import io.github.puzzle.cosmic.api.entity.player.IPlayer;
import io.github.puzzle.cosmic.util.annotation.Internal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Internal
@Mixin(Account.class)
public abstract class AccountMixin implements IAccount {

    @Unique
    private final transient Account puzzleLoader$account = IAccount.as(this);

    @Override
    public String pGetUsername() {
        return puzzleLoader$account.getUsername();
    }

    @Override
    public String pGetUniqueId() {
        return puzzleLoader$account.getUniqueId();
    }

    public IPlayer pGetPlayer() {
        if (GameSingletons.isHost && GameSingletons.isClient) {
            GameSingletons.client().getAccount();
        }
        return IPlayer.as(GameSingletons.getPlayerFromAccount(IAccount.as(this)));
    }

    public boolean pIsOperator() {
        return GameSingletons.isHost && GameSingletons.isClient || ServerSingletons.OP_LIST.hasAccount(IAccount.as(this));
    }
}
