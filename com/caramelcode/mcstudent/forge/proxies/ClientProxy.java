package com.caramelcode.mcstudent.forge.proxies;

import net.minecraftforge.common.MinecraftForge;

import com.caramelcode.mcstudent.forge.MCStudentEventSounds;
import com.caramelcode.mcstudent.forge.MCStudentTickHandler;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;


public class ClientProxy extends CommonProxy {
	@Override
	public void initRenderers() {

	}

	@Override
	public void initSounds() {
		MinecraftForge.EVENT_BUS.register(new MCStudentEventSounds());
	}

	@Override
	public void initTicks() {
		TickRegistry.registerScheduledTickHandler(new MCStudentTickHandler(), Side.CLIENT);
	}
}
