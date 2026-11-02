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

        Minecraft mc = Minecraft.func_71410_x();
        EntityPlayerSP player = mc.field_71439_g;

        boolean shouldSprint = false;

        if (player != null && mc.field_71441_e != null && mc.field_71462_r == null
                && player.field_71158_b != null && player.func_110143_aJ() > 0.0F) {

            shouldSprint =
                player.field_71158_b.field_78900_b >= 0.8F
                && !player.func_70093_af()
                && !player.func_70115_ae()
                && !player.func_71039_bw()
                && !player.field_71075_bZ.field_75100_b
                && (player.field_71075_bZ.field_75101_c || player.func_71024_bL().func_75116_a() > 6);
        }

        KeyBinding.func_74510_a(mc.field_71474_y.field_151444_V.func_151463_i(), shouldSprint);
    }
}