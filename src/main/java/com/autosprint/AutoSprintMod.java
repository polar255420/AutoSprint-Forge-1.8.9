package com.autosprint;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(
    modid = AutoSprintMod.MODID,
    name = AutoSprintMod.NAME,
    version = AutoSprintMod.VERSION,
    clientSideOnly = true,
    acceptedMinecraftVersions = "[1.8.9]"
)
public class AutoSprintMod {
    public static final String MODID = "autosprint";
    public static final String NAME = "AutoSprint";
    public static final String VERSION = "1.2.0";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;

        boolean shouldSprint = false;

        if (player != null && mc.theWorld != null && mc.currentScreen == null
                && player.movementInput != null && player.getHealth() > 0.0F) {

            shouldSprint =
                player.movementInput.moveForward >= 0.8F
                && !player.isSneaking()
                && !player.isRiding()
                && !player.isUsingItem()
                && !player.capabilities.isFlying
                && (player.capabilities.allowFlying || player.getFoodStats().getFoodLevel() > 6);
        }

        KeyBinding.setKeyBindState(mc.gameSettings.keyBindSprint.getKeyCode(), shouldSprint);
    }
}