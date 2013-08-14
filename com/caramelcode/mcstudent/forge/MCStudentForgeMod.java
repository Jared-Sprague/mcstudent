package com.caramelcode.mcstudent.forge;

import com.caramelcode.mcstudent.forge.proxies.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = MCStudentModInfo.ID, name = MCStudentModInfo.NAME, version = MCStudentModInfo.VERSION)
@NetworkMod(channels = { MCStudentModInfo.CHANNEL }, clientSideRequired = true, serverSideRequired = false, packetHandler = MCStudentPacketHandler.class)
public class MCStudentForgeMod {
	private static final String PL = MCStudentModInfo.PROXY_LOCATION;
	@SidedProxy(clientSide = PL + ".ClientProxy", serverSide = PL + ".CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		proxy.initRenderers();
		proxy.initSounds();
		proxy.initTicks();
		ConfigHelper.init(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {

	}
}