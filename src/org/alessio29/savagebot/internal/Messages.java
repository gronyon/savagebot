package org.alessio29.savagebot.internal;

import java.util.List;

import org.alessio29.savagebot.characters.CharacterInitCache;
import org.alessio29.savagebot.characters.CharacterInitiative;
import org.alessio29.savagebot.dice.DiceRollResult;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;

public class Messages {

	public static void showOrder(MessageReceivedEvent event) {
		
		StringBuilder reply = new StringBuilder();
		if (!CharacterInitCache.getCharacters(event.getGuild()).isEmpty()) {
			for (CharacterInitiative c : CharacterInitCache.getCharacters(event.getGuild())) {
				reply.append(c.getName()).append(" - ").append(c.getCard().toString()).append("\n");
			}
		} else {
			reply.append("��� ������� ����!");
		}
		IChannel ch = event.getChannel();
		ch.sendMessage(reply.toString());
	}

	
	public static void showError(String message, MessageReceivedEvent event )  {
		event.getChannel().sendMessage(message);	
	}
	
	public static void showMessage(String message, MessageReceivedEvent event )  {
		event.getChannel().sendMessage(message);	
	}
	
	public static String bold(String message) {
		return "**"+message+"**";
	}
	
	public static String italic(String message) {
		return "*"+message+"*";
	}	
	
	public static String createStringRepresentation(List<DiceRollResult> rollResults) {
		String result = "";
		for (DiceRollResult roll : rollResults) {
			result = result.concat(toStr(roll));
		}
		return result;
	}

	private static String toStr(DiceRollResult roll) {
		
		if (!roll.getOriginal().trim().isEmpty()) {
			return roll.getOriginal();
		}
		String dieCode="";
		if (!roll.getDieCode().trim().isEmpty()) {
			dieCode=roll.getDieCode()+"=";
		}
		String details="";
		if (!roll.getDetails().trim().isEmpty() && (roll.getDetails().contains("+") || roll.getDetails().contains(";") )) {
			details=roll.getDetails()+"=";
		} 
		return dieCode.concat(details).concat(bold(roll.getResult()+" "));
	}
}
