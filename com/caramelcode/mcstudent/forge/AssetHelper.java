package com.caramelcode.mcstudent.forge;

import net.minecraft.util.ResourceLocation;

public class AssetHelper {
	private static final String ASSET_DIR = MCStudentModInfo.ID.toLowerCase();
	public static final String SOUND_CORRECT = ASSET_DIR + ":correct";

	public static ResourceLocation getTexture(String textureType, String fileName) {
		return new ResourceLocation(ASSET_DIR, "textures/" + textureType + "/" + fileName);
	}
}
