package turkey.voidCompression.proxy;

import turkey.voidCompression.VCSettings;
import turkey.voidCompression.render.RenderCompressedBlock;
import cpw.mods.fml.client.registry.RenderingRegistry;




public class ClientProxy extends CommonProxy
{

	@Override
	public boolean isClient()
	{
		return true;
	}

	public void registerGuis()
	{

	}

	public void registerRenderings()
	{
		VCSettings.renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(VCSettings.renderID, new RenderCompressedBlock());
	}
}
