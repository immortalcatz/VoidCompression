package turkey.voidCompression;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigLoader
{
	private static Configuration config;
	//private static final String genCat = "General Settings";

	public static void loadConfigSettings(File file)
	{
		config = new Configuration(file);
		config.load();
		
		config.save();
	}
}