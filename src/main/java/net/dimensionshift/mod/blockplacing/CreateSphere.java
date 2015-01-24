package net.dimensionshift.mod.blockplacing;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CreateSphere {

	public static void createSphere(World world, int x, int y, int z, int r, boolean isfilled, Block block) {

		for (int x1 = -r; x1 < r; x1++) {
			for (int y1 = -r; y1 < r; y1++) {
				for (int z1 = -r; z1 < r; z1++) {
					double dist = MathHelper.sqrt_double((x1 * x1 + y1 * y1 + z1 * z1)); // Calculates
																							// the
																							// distance
					if (isfilled == true && dist < r) {
						world.setBlock(x + x1, y + y1, z + z1, block);
					} else if (isfilled == false) {
						if (dist == r || dist == r - 1 || dist == r + 1) {

							world.setBlock(x + x1, y + y1, z + z1, block);
						}
					}

				}
			}

		}

	}
}
