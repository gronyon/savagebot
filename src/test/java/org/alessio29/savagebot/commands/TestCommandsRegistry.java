package org.alessio29.savagebot.commands;

import org.alessio29.savagebot.apiActions.admin.HelpAction;
import org.alessio29.savagebot.internal.commands.CommandRegistry;
import org.alessio29.savagebot.internal.commands.Commands;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class TestCommandsRegistry {
    @Before
    public void setup() {
        CommandRegistry.getInstance().reset();
        Commands.registerDefaultCommands();
    }

    @Test
    public void testDefaultCommandsAreRegistered() {
        List<String> commands = new HashSet<>(CommandRegistry.getInstance().getRegisteredCommands())
                .stream()
                .map(cmd -> cmd.getCategory() + ":" + cmd.getName())
                .sorted()
                .collect(Collectors.toList());
        Assert.assertEquals(
                "[" +
                        "ADMIN:info, ADMIN:ping, ADMIN:prefix, " +
                        "BENNIES:benny, BENNIES:hat, BENNIES:pocket, BENNIES:use, " +
                        "CARDS:deal, CARDS:put, CARDS:show, CARDS:shuffle, " +
                        "CHARACTERS:list, CHARACTERS:remove, " +
                        "DICE:r, DICE:rh, DICE:rs, " +
                        "INFO:help, INFO:invite, " +
                        "INITIATIVE:card, INITIATIVE:di, INITIATIVE:drop, INITIATIVE:fight, INITIATIVE:init, INITIATIVE:round, " +
                        "STATES:state, "+
                        "TOKENS:clear, TOKENS:give, TOKENS:take" +
                        "]",
                commands.toString()
        );
    }

    @Ignore
    @Test
    public void testHelpText() {
        Assert.assertEquals(
                "__**CARDS category**__\n" +
                        "!card <character_name>; aliases: !cd\n" +
//                        "!deal [<card_count>]; aliases: !dl\n" +
                        "!draw [<card_count>] [<user>]; aliases: !dw\n" +
                        "!open [<card_count>]; aliases: !op\n" +
                        "!show; aliases: !sh\n" +
                        "!shuffle\n" +
                        "\n" +
                        "__**DICE category**__\n" +
                        "!r <expression1> ... <expressionN> \n" +
                        "!rh <expression_1> ... <expressionN>\n" +
                        "!rs [<heading_1>] <expression_1> ... [<heading_N>] <expression_N>\n" +
                        "\n" +
                        "__**BENNIES category**__\n" +
                        "!benny <character_name>\n" +
                        "!hat [fill]\n" +
                        "!pocket <character_name>\n" +
                        "!use <benny_color> <character_name>\n" +
                        "\n" +
                        "__**INITIATIVE category**__\n" +
                        "!di <character_name1> [<modifiers_1>] ... <character_nameN> [<modifiers_N>]\n" +
                        "!drop <character_name>\n"+
                        "!fight; aliases: !f\n" +
                        "!init\n" +
                        "!round [+] [-<char_name>]; aliases: !rd\n" +
                        "\n" +
                        "__**INFO category**__\n" +
                        "!help [<command> or <category>]\n" +
                        "!invite\n" +
                        "\n" +
                        "__**ADMIN category**__\n" +
                        "!info password\n" +
                        "!ping\n" +
                        "!prefix [<character>]\n" +
                        "\n" +
                        "__**TOKENS category**__\n" +
                        "!clear <character_name>/all\n" +
                        "!give <character_name> [<amount of tokens>]\n" +
                        "!take <character_name> [<amount of tokens>]\n" +
                        "!tokens\n" +
                        "\n" +
                        "For more details, use `!help <command>` or see https://github.com/alessio29/savagebot/blob/master/README.md",
                HelpAction.getBriefHelpForAllCommands()
        );
    }

    @Test
    public void testRegisteredParsingCommands() {
        List<String> commands = new HashSet<>(CommandRegistry.getInstance().getRegisteredParsingCommands())
                .stream()
                .map(Object::toString)
                .sorted()
                .collect(Collectors.toList());
        //noinspection ArraysAsListWithZeroOrOneArgument
        Assert.assertEquals(
                Arrays.asList(
                        "ParsingMethodCommand{ method: org.alessio29.savagebot.commands.DiceCommands::parseAndRollDice; methodOwner: null}"
                ),
                commands
        );
    }
}
