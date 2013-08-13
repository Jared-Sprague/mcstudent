package com.caramelcode.mcstudent.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.caramelcode.mcstudent.forge.MCStudentModInfo;
import com.mcf.davidee.guilib.basic.BasicScreen;
import com.mcf.davidee.guilib.basic.OverlayScreen;

public abstract class MCStudentPopup extends BasicScreen {

	private static final ResourceLocation TEXTURE = new ResourceLocation(
			MCStudentModInfo.ID.toLowerCase(), "textures/gui/window.png");

	public static final int WIDTH = 256, HEIGHT = 129;

	public MCStudentPopup(GuiScreen parent) {
		super(parent);
	}

	@Override
	protected void unhandledKeyTyped(char c, int code) {
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawBackground() {
		super.drawBackground();
		drawGradientRect(0, 0, width, height, -1072689136, -804253680);
		int x = (width - WIDTH) / 2, y = (height - HEIGHT) / 2;
		mc.renderEngine.func_110577_a(TEXTURE);
		GL11.glColor4f(1, 1, 1, 1);
		drawTexturedModalRect(x, y, 0, 0, WIDTH, HEIGHT);
	}

	@Override
	protected void createGui() {
	}

	@Override
	protected void reopenedGui() {
	}
}
