package io.github.elementaldevelopers.antiscam;

import io.github.elementaldevelopers.antiscam.listeners.ChatListener;
import io.github.elementaldevelopers.antiscam.utils.APIGrabber;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = AntiScam.MODID, version = AntiScam.VERSION)
public class AntiScam {
    public static final String MODID = "antiscam";
    public static final String VERSION = "0.0.1";
    
    @EventHandler
    public void init(FMLInitializationEvent event){
	    ChatListener chat = new ChatListener(APIGrabber.getData());
	    if (chat.isworking) {
	    	MinecraftForge.EVENT_BUS.register(chat);
	    }
    }

}
