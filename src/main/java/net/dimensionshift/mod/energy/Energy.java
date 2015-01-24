package net.dimensionshift.mod.energy;

import java.util.ArrayList;
import java.util.List;

import net.dimensionshift.mod.DimensionShift;
import net.minecraft.block.Block;

public class Energy {

	public static final List<Block> getTechnicBlocks() {
		List<Block> technicBlocks = new ArrayList<Block>();
		technicBlocks.add(DimensionShift.blockWireBasic);

		technicBlocks.add(DimensionShift.blockSimpleControllerIdle);
		technicBlocks.add(DimensionShift.blockSimpleControllerActive);

		technicBlocks.add(DimensionShift.blockTeleporterIdle);
		technicBlocks.add(DimensionShift.blockTeleporterActive);

		return technicBlocks;
	}

	public static boolean isItemFuel(Block block) {
		return (getTechnicBlocks().contains(block));
	}

}
