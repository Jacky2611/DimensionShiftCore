package net.dimensionshift.mod;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraftforge.fml.common.FMLLog;

public class DimensionShiftLogHelper extends FMLLog{
private static Logger logger = Logger.getLogger(DimensionShift.MODID);

public static void init() {
	//logger.setParent();
}

public static void log(Level logLevel, String message) {
logger.log(logLevel, message);
}
}