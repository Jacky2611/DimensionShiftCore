package net.dimensionshift.mod.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;

public class EnchantmentDimensionTravelingStabilisation extends Enchantment {

	public EnchantmentDimensionTravelingStabilisation(int id, int rarity) {
		super(id, new ResourceLocation("unbreaking"), rarity, EnumEnchantmentType.ARMOR);
		this.setName("dimensionTravelingStabilisation");
	}

	// Min XP Level is 5
	@Override
	public int getMinEnchantability(int par1) {

		return 5 + (par1 - 1) * 10;
	}

	// Max XP Level
	@Override
	public int getMaxEnchantability(int par1) {
		return this.getMinEnchantability(par1) + 20;
	}

	// Has 4 levels
	@Override
	public int getMinLevel() {
		return 1;
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}
}
