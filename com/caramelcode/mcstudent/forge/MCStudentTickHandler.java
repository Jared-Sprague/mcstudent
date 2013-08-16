package com.caramelcode.mcstudent.forge;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import com.caramelcode.mcstudent.gui.QuestionPopup;

import cpw.mods.fml.common.IScheduledTickHandler;
import cpw.mods.fml.common.TickType;

public class MCStudentTickHandler implements IScheduledTickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.CLIENT))) {

			GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;

			if (guiscreen != null) {
				onTickInGUI(guiscreen);
			} else {
				onTickInGame();
			}
		}
	}

	private void onTickInGame() {
		Minecraft mc = Minecraft.getMinecraft();
		mc.displayGuiScreen(new QuestionPopup());
	}

	private void onTickInGUI(GuiScreen guiscreen) {
	}

	/**
	 * Returns the list of ticks this tick handler is interested in receiving at
	 * the minute
	 */
	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return this.getClass().getName();
	}

	@Override
	public int nextTickSpacing() {
		int ticks = ConfigHelper.ONE_MINUTE_TICKS * ConfigHelper.getQuestionMinutes();
		
		if (ConfigHelper.isDebug()) {
			ticks = ConfigHelper.DEBUG_TICK_SPACING;
		}
		
		return ticks;
	}
}
