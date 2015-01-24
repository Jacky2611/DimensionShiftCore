package net.dimensionshift.mod.item;

import net.dimensionshift.mod.api.ICustomItemBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class CustomItemBlock extends ItemBlock{

	public CustomItemBlock(Block block) {
		super(block);
		if(block instanceof ICustomItemBlock){
			this.setMaxStackSize(((ICustomItemBlock)block).getStackSize());
		}
	}

}
