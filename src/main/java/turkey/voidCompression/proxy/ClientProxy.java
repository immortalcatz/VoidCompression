package turkey.voidCompression.proxy;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import turkey.voidCompression.VCSettings;
import turkey.voidCompression.blocks.VCBlocks;
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
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(VCBlocks.compressedBlock), new RenderCompressedBlock());
		VCSettings.renderID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(VCSettings.renderID, new RenderCompressedBlock());
	}
}
