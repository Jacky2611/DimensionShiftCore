package net.dimensionshift.mod.item;

import java.util.List;

import net.dimensionshift.mod.DimensionShift;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemDimensionIdentificationCrystal extends Item {

	public ItemDimensionIdentificationCrystal(int maxStackSize, String name) {
		setMaxStackSize(maxStackSize);
		setCreativeTab(DimensionShift.tabDimensionShift);
		setUnlocalizedName(name);
		setTextureName(DimensionShift.MODID + ":" + name);
	}

	@Override
	public boolean onItemUseFirst(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		itemStack.stackTagCompound = new NBTTagCompound();
		itemStack.stackTagCompound.setString("name", world.provider.getDimensionName());
		itemStack.stackTagCompound.setInteger("id", world.provider.dimensionId);
		return true;
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
		if (itemStack.stackTagCompound != null) {
			String name = itemStack.stackTagCompound.getString("name");
			list.add(I18n.format("item.itemDimensionIdentificationCrystal.desc.destination", new Object[0]) + ": " + name);

		}
	}
}