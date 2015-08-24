package turkey.voidCompression;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigLoader
{
	private static Configuration config;
	private static final String genCat = "General Settings";

	public static void loadConfigSettings(File file)
	{
		config = new Configuration(file);
		config.load();

		String[] defaults = new String[]{"minecraft:dirt", "minecraft:cobblestone"};
		VCSettings.compressedBlocks = config.getStringList("Compressed Blocks", genCat, defaults, "Add in the blocks that you would like to be able to be compressed here. Format: modID:BlockName");
		
		config.save();
	}
}