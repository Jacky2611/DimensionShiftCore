package net.dimensionshift.mod.blockplacing;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3d;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class GetClosestBlockInRadiusRelativeToStartPoint {
	//public static final Vec3Pool myPool = new Vec3Pool(-1, -1);

	public static List<Vector3d> getBlocks(World world, int x, int y, int z, int r, Block block) {

		List<Vector3d> list = new ArrayList<Vector3d>();

		for (int x1 = -r; x1 < r; x1++) {
			for (int y1 = -r; y1 < r; y1++) {
				for (int z1 = -r; z1 < r; z1++) {
					double dist = MathHelper.sqrt_double((x1 * x1 + y1 * y1 + z1 * z1)); // Calculates

					// the distance
					if (dist < r) {
						if (block == world.getBlock(x + x1, y + y1, z + z1)) {
							list.add(new Vector3d(x1, y1, z1));

						}
					}
				}

			}

		}

		return list;
	}
}
