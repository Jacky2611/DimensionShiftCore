package net.dimensionshift.mod.gui;

import net.dimensionshift.mod.DimensionShift;
import net.dimensionshift.mod.container.ContainerSimpleController;
import net.dimensionshift.mod.container.ContainerSimpleTeleporter;
import net.dimensionshift.mod.container.ContainerTeleporter;
import net.dimensionshift.mod.tileentity.TileEntitySimpleController;
import net.dimensionshift.mod.tileentity.TileEntitySimpleTeleporter;
import net.dimensionshift.mod.tileentity.TileEntityTeleporter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity != null) {
			switch (ID) {
				case DimensionShift.guiIdSimpleController:
					if (entity instanceof TileEntitySimpleController) {
						return new ContainerSimpleController(player.inventory, (TileEntitySimpleController) entity);
					}
				case DimensionShift.guiIdSimpleTeleporter:
					if (entity instanceof TileEntitySimpleTeleporter) {
						return new ContainerSimpleTeleporter(player.inventory, (TileEntitySimpleTeleporter) entity);
					}
				case DimensionShift.guiIdTeleporter:
					if (entity instanceof TileEntityTeleporter) {
						return new ContainerTeleporter(player.inventory, (TileEntityTeleporter) entity);
					}

			}

		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		if (entity != null) {
			switch (ID) {
				case DimensionShift.guiIdSimpleController:
					if (entity instanceof TileEntitySimpleController) {
						return new GuiSimpleController(player.inventory, (TileEntitySimpleController) entity);
					}
				case DimensionShift.guiIdSimpleTeleporter:
					if (entity instanceof TileEntitySimpleTeleporter) {
						return new GuiSimpleTeleporter(player.inventory, (TileEntitySimpleTeleporter) entity);
					}
				case DimensionShift.guiIdTeleporter:
					if (entity instanceof TileEntityTeleporter) {
						return new GuiTeleporter(player.inventory, (TileEntityTeleporter) entity);
					}

			}

		}

		return null;
	}

}
