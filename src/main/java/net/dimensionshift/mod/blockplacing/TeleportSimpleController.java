package net.dimensionshift.mod.blockplacing;

import java.util.List;

import net.dimensionshift.mod.world.dimensions.teleporter.TeleporterStandart;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TeleportSimpleController extends Thread {
	/*
	 * private World world; private World goalWorld; private Integer x; private
	 * Integer y; private Integer z; private int r;
	 */
	// for testing only
	private static TileEntity entityTmp2;

	static EntityPlayer player;

	public static void start(World world, World goalWorld, int x, int y, int z) {
		/*
		 * this.goalWorld=xworld; this.world=world; this.r = 30; this.x=x;
		 * this.y=y; this.z=z; this.player=player;
		 */
		int r = 30;
		int yMovement = 0;
		// }

		// public void run(){

		TileEntity entityTmp;
		NBTTagCompound compoundBlock = new NBTTagCompound();
		NBTTagCompound compoundEntity = new NBTTagCompound();
		Integer metadata;

		for (int x1 = -r; x1 < r; x1++) {
			for (int y1 = -r; y1 < r; y1++) {
				for (int z1 = -r; z1 < r; z1++) {
					double dist = MathHelper.sqrt_double((x1 * x1 + y1 * y1 + z1 * z1)); // Calculates
																							// the
																							// distance
					if (dist > r) continue;

					Block blockatcords = world.getBlock(x + x1, y + y1, z + z1);

					// getting required infomations

					metadata = world.getBlockMetadata(x + x1, y + y1, z + z1);
					entityTmp = world.getTileEntity(x + x1, y + y1, z + z1);

					// tile entity wird vor�bergehend abgespeichert
					if (entityTmp != null) {
						entityTmp.writeToNBT(compoundBlock);
					}

					if (blockatcords != goalWorld.getBlock(x + x1, y + y1 + yMovement, z + z1)) {
						// PLACING:

						// setting stone for torches and other blocks
						// if (blockatcords.isOpaqueCube()==false &&
						// goalWorld.getBlock(x+x1, y+y1+yMovement-1, z+z1)!=
						// world.getBlock(x+x1, y+y1-1, z+z1)) {
						// goalWorld.setBlock(x+x1, y+y1+yMovement-1, z+z1,
						// Blocks.stone);
						// }

						goalWorld.setBlock(x + x1, y + y1 + yMovement, z + z1, blockatcords);

					}

					// setting new cords for tileentity
					entityTmp2 = goalWorld.getTileEntity(x + x1, y + y1 + yMovement, z + z1);
					if (entityTmp != null) {
						entityTmp2.readFromNBT(compoundBlock);
						entityTmp2.xCoord = x + x1;
						entityTmp2.yCoord = y + y1 + yMovement;
						entityTmp2.zCoord = z + z1;
					}

					goalWorld.setBlockMetadataWithNotify(x + x1, y + y1 + yMovement, z + z1, metadata, 2);
					goalWorld.setTileEntity(x + x1, y + y1 + yMovement, z + z1, entityTmp2);

				}
			}
			// try {
			// sleep(70);
			// } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}

		// ENTITY
		player = world.getClosestPlayer(x, y, z, 999999);
		List<Entity> entityList = world.getEntitiesWithinAABBExcludingEntity(player, AxisAlignedBB.getBoundingBox(x - r, y - r, z - r, x + r, y + r, z + r));

		int numberOfEntities = entityList.size();

		// List<Entity> entityListFinal=entityList;

		System.out.println("List lenght " + numberOfEntities);

		if (numberOfEntities > 0 && entityList != null) {

			for (int i = 0; i < entityList.size(); i++) {
				Entity entity = entityList.get(i);
				if (entity.getDistance(x, y, z) > r) {
					entityList.remove(entity);
				}
			}

			int counterEnt = 0;
			for (Entity entity2 : entityList) {
				if (entity2 != null && !(entity2 instanceof EntityPlayer)) {

					NBTTagCompound compound2 = new NBTTagCompound();
					entity2.writeToNBT(compound2);
					compound2.setString("id", EntityList.getEntityString(entity2));
					System.out.println(compound2.getString("id"));
					compoundEntity.setTag("entityAt" + counterEnt, compound2);

					world.removeEntity(entity2);

					counterEnt++;

				}
			}
			compoundEntity.setInteger("numberofentities", counterEnt);

			int counterEnt2 = compoundEntity.getInteger("numberofentities");
			for (int i = 0; i < counterEnt2; i++) {
				try {
					Entity entity2 = EntityList.createEntityFromNBT(compoundEntity.getCompoundTag("entityAt" + i), world);
					entity2.setPosition(entity2.posX, entity2.posY + yMovement, entity2.posZ);
					goalWorld.spawnEntityInWorld(entity2);
				} catch (Exception e) {

				} finally {

				}
			}
		}

		System.out.println("DONE");

		// get dimension id
		int dim = 0;
		if (world.provider.dimensionId == 0) {
			dim = 9;
		}

		List<EntityPlayer> playerList = world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(x - r, y - r, z - r, x + r, y + r, z + r));

		int numberOfPlayers = playerList.size();

		// List<Entity> entityListFinal=entityList;

		System.out.println("Player List lenght " + numberOfPlayers);

		if (numberOfPlayers > 0 && playerList != null) {

			for (int i = 0; i < playerList.size(); i++) {
				EntityPlayer player1 = playerList.get(i);
				if (player1.getDistance(x, y, z) > r) {
					playerList.remove(player1);
				}
			}

			for (EntityPlayer entityPlayer : playerList) {
				if (entityPlayer != null && (entityPlayer instanceof EntityPlayer)) {

					NBTTagCompound tag = entityPlayer.getEntityData();
					tag.setInteger("UsedTeleporter", 5);
					TeleporterStandart.teleport(entityPlayer, dim, false);

				}
			}

		}
		// setting air
		// CreateSphere.createSphere(world, x, y, z, r, true, Blocks.air);
	}

}
