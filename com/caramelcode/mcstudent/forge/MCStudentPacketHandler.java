package com.caramelcode.mcstudent.forge;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class MCStudentPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if (packet.data != null && packet.data.length == 1 && packet.data[0] == 1) {
			ArrayList<ItemStack> itemListToSend = new ArrayList<ItemStack>();
			EntityPlayerMP thePlayer = (EntityPlayerMP) player;

			thePlayer.inventory.addItemStackToInventory(new ItemStack(Item.diamond));

			for (int i = 0; i < thePlayer.openContainer.inventorySlots.size(); ++i) {
				itemListToSend.add(((Slot) thePlayer.openContainer.inventorySlots.get(i))
						.getStack());
			}

			thePlayer.sendContainerAndContentsToPlayer(thePlayer.openContainer, itemListToSend);
		}
	}

}
