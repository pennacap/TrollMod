package org.pennacap.hypixelflip;

import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.UUID;

@Mod(
        modid = Hypixelflip.MOD_ID,
        name = Hypixelflip.MOD_NAME,
        version = Hypixelflip.VERSION,
        clientSideOnly = true

)
public class Hypixelflip {
    public UUID api_key;
    public static final String MOD_ID = "hypixelflip";
    public static final String MOD_NAME = "HypixelFlip";
    public static final String VERSION = "1.0-SNAPSHOT";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static Hypixelflip INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }



    public boolean isSkyblock(){
        Minecraft mc = Minecraft.getMinecraft();

        if (mc != null && mc.world != null && mc.player != null) {
            if (mc.isSingleplayer() || mc.player.getServerBrand() == null ||
                    !mc.player.getServerBrand().toLowerCase().contains("hypixel")) {

                return false;
            }

            Scoreboard scoreboard = mc.world.getScoreboard();
            ScoreObjective sidebarObjective = scoreboard.getObjectiveInDisplaySlot(1);
            if (sidebarObjective != null) {
                String objectiveName = sidebarObjective.getDisplayName().replaceAll("(?i)\\u00A7.", "");

                if (objectiveName.startsWith("SKYBLOCK")) {

                    return true;
                }

            }
            return false;
        }
       return false;

    }
    @SubscribeEvent
    public void onChat(ServerChatEvent event) {
        System.out.println(event.getMessage());


    }
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event){
        if(event.phase != TickEvent.Phase.START) return;
        //if (!isSkyblock()||ConfigFile.apiKey==null){
        //    return;
        //}


    }
    @SubscribeEvent
    public void onLoad(WorldEvent.Load event){
        //if (isSkyblock()){
            if (ConfigFile.apiKey==null){
                Minecraft.getMinecraft().player.sendChatMessage("\u00a7AError: API key is not set. Set it in the config if you have one or run /api new");
                return;
            }


        //}
    }
}
