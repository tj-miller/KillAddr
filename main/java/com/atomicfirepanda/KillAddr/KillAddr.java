package com.atomicfirepanda.KillAddr;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = KillAddr.MODID, version = KillAddr.VERSION)
public class KillAddr
{
    public static final String MODID = "killaddr";
    public static final String VERSION = "1.0";
   
    
    PlayerDeathEventHandler pdhandler = new PlayerDeathEventHandler();
    
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		MinecraftForge.EVENT_BUS.register(pdhandler);
       
    }
    
    
    
}
