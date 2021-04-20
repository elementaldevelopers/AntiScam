package io.github.elementaldevelopers.antiscam.utils;


import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageChecker {
	public static final Pattern guildregex = Pattern.compile("Guild > (\\[MVP\\+\\+]|\\[MVP\\+]|\\[MVP]|\\[VIP\\+]|\\[VIP])? .+ (\\[.+])*: .+");
	public static final Pattern partyregex = Pattern.compile("Party > (\\[MVP\\+\\+]|\\[MVP\\+]|\\[MVP]|\\[VIP\\+]|\\[VIP])? .+: .+");
	public static final Pattern allregex = Pattern.compile("^(?:\\[.*?\\] )*([^:]{1,16}):");
	public static final Pattern fromregex = Pattern.compile("From (\\[MVP\\+\\+]|\\[MVP\\+]|\\[MVP]|\\[VIP\\+]|\\[VIP])? ?.+: .+");
	public static final Pattern hasrank = Pattern.compile("(\\[MVP\\+\\+]|\\[MVP\\+]|\\[MVP]|\\[VIP\\+]|\\[VIP])");
	private static String extractIGN(String message, String whichone) {
		Matcher m = hasrank.matcher(message);
		String[] list = message.split(" ");
		boolean hasrankbool = m.find();
		if (whichone == "guild" || whichone ==  "party") {
			if (hasrankbool) {
				return list[3];
			}
			return list[2];
		}
		if (whichone == "all") {
			String[] l = message.split("(\\[.*?])");
			if (hasrankbool) {
				message = l[1];
			} else {
				message = l[0];
			}
			
			message = message.split(":")[0];
			message = message.replace(" ", "");
			return message;
		}
		if (whichone == "from") {
			if (hasrankbool) {
				return list[2];
			}
			return list[1];
		}
		throw new IllegalArgumentException();
	}
	public static String checkMessage(String message) {
		Matcher m1 = guildregex.matcher(message);
		Matcher m2 = partyregex.matcher(message);
		Matcher m3 = allregex.matcher(message);
		Matcher m4 = fromregex.matcher(message);
		if (m1.matches()) {
			return extractIGN(message, "guild");
		} else if (m2.matches()) {
			return extractIGN(message, "party");
		} else if (m3.find()) {
			return extractIGN(message, "all");
		} else if (m4.matches()) {
			return extractIGN(message, "from");
		}
		return "Not a chat message";
	}


}
