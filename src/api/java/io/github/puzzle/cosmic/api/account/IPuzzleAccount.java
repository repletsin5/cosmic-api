package io.github.puzzle.cosmic.api.account;

import io.github.puzzle.cosmic.api.entity.player.IPuzzlePlayer;
import io.github.puzzle.cosmic.util.annotation.compile.ApiGen;

/**
 *
 * @author Mr_Zombii
 * @since 0.3.26
 */
@ApiGen("Account")
public interface IPuzzleAccount {

    /**
     * Gets the display name of the account
     */
    String pGetDisplayName();

    /**
     * Gets the username of the account.
     */
    String pGetUsername();

    /**
     * Gets the unique id of the account
     */
    String pGetUniqueId();

    /**
     * Sets the username of the account.
     */
    void pSetUsername(String username);

    /**
     * Sets the unique id of the account.
     */
    void pSetUniqueId(String uniqueId);

    /**
     * Returns the account type prefix.
     */
    String pGetPrefix();

    /**
     * Gets the debug string for the account.
     */
    String pGetDebugString();

    /**
     * Checks the ability to save the account.
     */
    boolean pCanSave();

    /**
     * Checks if the type of account is allowed on a server.
     */
    boolean pIsAllowed();

    /**
     * Checks if an operator player owns this account.
     */
    boolean pIsOperator();

    /**
     * Gets the player who owns this account.
     */
    IPuzzlePlayer pGetPlayer();

}
