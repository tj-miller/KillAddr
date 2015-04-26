package com.atomicfirepanda.KillAddr;

import java.util.List;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PlayerDeathEventHandler {
	
	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent event)
    {

    	if(event.entityLiving instanceof EntityPlayer)
    	{

    		Entity killerEntity;
    		String killerName = "";
    			
    		EntityPlayer killedEntity = (EntityPlayer)event.entityLiving;
    		String killedName = killedEntity.getDisplayName();
    		
    		EntityPlayer lastAttacker = null;
    		
    		//if the source of the damage is unknown or environment (fire, wither, drown, etc)  
    		if(event.source.getEntity() == null)
    		{
    			try{
    				if(event.entityLiving.getLastAttacker() instanceof EntityPlayer)
    				{
    					lastAttacker = (EntityPlayer)event.entityLiving.getLastAttacker();
    					killerEntity = lastAttacker;
    					killerName = "Last Attacker";
    				}
    				else{
    					killerEntity = null;
    					killerName = "The Environment";
    				}
    			}
    			catch(Exception e)
    			{
    				killerEntity = null;
    				killerName = "The Environment";
    			}
    			
    		}
    		else{
    			//if the source of the damage is known
    			killerEntity = event.source.getEntity();
    			
    			
    			try{
    				//get the player killer
    				EntityPlayer killerPlayer = (EntityPlayer)event.source.getEntity();
    				killerName = killerPlayer.getDisplayName().toString();	
    				
    				//send that player a message
    				IChatComponent killerChat = new ChatComponentText("You killed " + killedName  + ".");
        			killerPlayer.addChatMessage(killerChat);
            		killerChat = new ChatComponentText("You gain KillScore.");
            		killerPlayer.addChatMessage(killerChat);
    			}
    			catch (Exception e){
    				//if the player does not exist or is a mob
    				
    				killerEntity = event.source.getEntity();
    				killerName = killerEntity.getClass().getSimpleName().toString();
    				System.out.println("Killed by non-player: " + killerName);
    			}

    		}
    		
    		//if the source.getEntity is null AND there is a last known attacker, set the killer back to the proper player
    		if(killerName=="Last Attacker" && lastAttacker != null)
    		{
    			killerEntity = lastAttacker;
    			killerName = lastAttacker.getDisplayName().toString();
    		}
    	
    		IChatComponent killedChat = new ChatComponentText(killerName + " has killed you.");
    		killedEntity.addChatMessage(killedChat);
    		killedChat = new ChatComponentText("You forfeit KillScore.");
    		killedEntity.addChatMessage(killedChat);
 
    		return;
    	}
    }

}
