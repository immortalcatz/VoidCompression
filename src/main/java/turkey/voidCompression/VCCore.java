package turkey.voidCompression;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import org.apache.logging.log4j.Logger;

import turkey.voidCompression.blocks.VCBlocks;
import turkey.voidCompression.gui.VCGuiHandler;
import turkey.voidCompression.proxy.CommonProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = VCCore.MODID, version = VCCore.VERSION, name = VCCore.NAME)
public class VCCore
{
	public static final String MODID = "voidcompression";
	public static final String VERSION = "@VERSION@";
	public static final String NAME = "Void Compression";

	@Instance(value = MODID)
	public static VCCore instance;
	@SidedProxy(clientSide = "turkey.voidCompression.proxy.ClientProxy", serverSide = "turkey.voidCompression.proxy.CommonProxy")
	public static CommonProxy proxy;
	public static CreativeTabs modTab = new CreativeTabs(MODID)
	{
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(VCBlocks.compressionTable);
		}
	};
	public static Logger logger;

	@EventHandler
	public void init(FMLInitializationEvent event)
	{

	}

	@EventHandler
	public void load(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
		
		ConfigLoader.loadConfigSettings(event.getSuggestedConfigurationFile());

		proxy.registerRenderings();

		NetworkRegistry.INSTANCE.registerGuiHandler(this, new VCGuiHandler());
		FMLCommonHandler.instance().bus().register(new UpdateNotificationHandler());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		VCBlocks.loadBlocks();
		
		CraftingRecipies.loadRecipies();
	}
}