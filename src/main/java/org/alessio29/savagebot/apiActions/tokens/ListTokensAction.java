package org.alessio29.savagebot.apiActions.tokens;

import org.alessio29.savagebot.apiActions.IBotAction;
import org.alessio29.savagebot.characters.Character;
import org.alessio29.savagebot.characters.Characters;
import org.alessio29.savagebot.internal.IMessageReceived;
import org.alessio29.savagebot.internal.builders.ReplyBuilder;
import org.alessio29.savagebot.internal.commands.CommandExecutionResult;

import java.util.Set;

public class ListTokensAction implements IBotAction {

    private static final int NAME_SIZE = 15;
    private static final int TOKEN_SIZE = 5;

    public CommandExecutionResult doAction(IMessageReceived message, String[] args) {

        Set<Character> chars = Characters.getCharactersWithTokens(message.getGuildId(), message.getChannelId());
        if (chars.isEmpty()) {
            return new CommandExecutionResult("No characters with tokens defined!",1);
        }
        ReplyBuilder replyBuilder = new ReplyBuilder();
        replyBuilder.blockQuote().
                rightPad("NAME", NAME_SIZE).
                tab().
                rightPad("TOKENS", TOKEN_SIZE).
                newLine();
        for (Character chr : chars ) {
            replyBuilder.rightPad(chr.getName(), NAME_SIZE).
                    tab().
                    rightPad(String.valueOf(chr.getTokens()), TOKEN_SIZE).
                    newLine();
        }
        replyBuilder.blockQuote();
        return new CommandExecutionResult(replyBuilder.toString(), 2);
    }
}