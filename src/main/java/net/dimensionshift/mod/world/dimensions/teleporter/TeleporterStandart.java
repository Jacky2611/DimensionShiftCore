package net.dimensionshift.mod.world.dimensions.teleporter;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class TeleporterStandart extends Teleporter {

	private final WorldServer worldServerInstance;
	private boolean topBlock;

	public TeleporterStandart(WorldServer world, boolean topBlock) {
		super(world);
		this.worldServerInstance = world;
		this.topBlock = topBlock;
	}

	@Override
	public void placeInPortal(Entity pEntity, double p2, double p3, double p4, float p5) {
		int i = MathHelper.floor_double(pEntity.posX);
		int j = MathHelper.floor_double(pEntity.posY);
		int k = MathHelper.floor_double(pEntity.posZ);
		this.worldServerInstance.getBlock(i, j, k); // dummy load to maybe gen
													// chunk

		int height = 100;
		if (topBlock) {
			height = this.worldServerInstance.getHeightValue(i, k);// spawn
																	// player on
																	// heighest
																	// Block
		} else {
			height = j;// spawn Player on same height
		}

		pEntity.setPosition(i, height, k);
	}

	public static void teleport(EntityPlayer player, int dim, boolean topBlock) {

		MinecraftServer mServer = MinecraftServer.getServer();
		Side sidex = FMLCommonHandler.instance().getEffectiveSide();
		if (sidex == Side.SERVER) {
			if (player instanceof EntityPlayerMP) {
				EntityPlayerMP playerMP = (EntityPlayerMP) player;
				if (player.ridingEntity == null && player.riddenByEntity == null && player instanceof EntityPlayer) {
					FMLCommonHandler.instance().getMinecraftServerInstance();
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, dim, new net.dimensionshift.mod.world.dimensions.teleporter.TeleporterStandart(mServer.worldServerForDimension(dim), topBlock));

				}

			}
		}
	}

}