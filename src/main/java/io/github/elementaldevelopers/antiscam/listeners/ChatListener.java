package io.github.elementaldevelopers.antiscam.listeners;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.elementaldevelopers.antiscam.utils.APIGrabber;
import io.github.elementaldevelopers.antiscam.utils.MessageChecker;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ChatListener {
	private ArrayList<String> scammerlist = new ArrayList<String>();
	public JsonObject json;
	public boolean isworking = true;
	static private JsonParser parser = new JsonParser();
	public ChatListener(String json) {
		if (json == "Error") {
			this.isworking = false;
		}
		this.json = parser.parse(json).getAsJsonObject();
	}

	@SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
		char character = '\u00A7';
        String message = event.message.getUnformattedText();
        message = message.replaceAll(character + ".", "");
        String IGN = MessageChecker.checkMessage(message);
        if (IGN == "Not a chat message" || IGN == null) return;
        String uuid = APIGrabber.getUUID(IGN);
        if (IGN == "Error happened") return;
        if (IGN == "IGN Not Valid") 
        	System.err.println("IGN Not working, fix");
        if (json.get(IGN) != null) {
        	event.setCanceled(true);
        }
    }
}
