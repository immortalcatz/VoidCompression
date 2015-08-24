package turkey.voidCompression.neiHook;

import turkey.voidCompression.VCCore;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIVoidCompressionConfig implements IConfigureNEI
{
	@Override
	public void loadConfig()
	{
		API.registerRecipeHandler(new CompressionTableRecipieHandler());
	    API.registerUsageHandler(new CompressionTableRecipieHandler());
	}

	@Override
	public String getName()
	{
		return "Void Compression";
	}

	@Override
	public String getVersion()
	{
		return VCCore.MODID;
	}
}