package com.caramelcode.mcstudent.forge;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class MCStudentEventSounds {
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		try {
			event.manager.addSound(MCStudentModInfo.ID.toLowerCase() + ":correct1.ogg");
			event.manager.addSound(MCStudentModInfo.ID.toLowerCase() + ":correct2.ogg");
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
