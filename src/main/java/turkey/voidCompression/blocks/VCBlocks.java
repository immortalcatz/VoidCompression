package turkey.voidCompression.blocks;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import turkey.voidCompression.VCSettings;
import turkey.voidCompression.blocks.tileEntities.CompressionTableTileEntity;
import turkey.voidCompression.item.CompressedItemBlock;
import turkey.voidCompression.util.CompressionHelper;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.registry.GameRegistry;

public class VCBlocks
{
	public static Block compressionTable;

	public static HashMap<Block, Block> compressedBlockFromBlock = Maps.newHashMap();
	public static HashMap<Block, Block> blockfromCompressedBlock = Maps.newHashMap();

	public static void loadBlocks()
	{	
		compressionTable = new CompressionTable();

		for(String blockRaw: VCSettings.compressedBlocks)
		{
			Block b = GameRegistry.findBlock(blockRaw.substring(0, blockRaw.indexOf(":")), blockRaw.substring(blockRaw.indexOf(":") + 1));
			if(b != null)
			{
				CompressedBlock cb = new CompressedBlock(b);
				compressedBlockFromBlock.put(b, cb);
				blockfromCompressedBlock.put(cb, b);
				GameRegistry.registerBlock(cb, CompressedItemBlock.class, CompressionHelper.getInstance().getBlockName(new ItemStack(b)).toLowerCase().replaceAll(" ", "_"));
			}
		}

		GameRegistry.registerBlock(compressionTable, "Compression_Table");
		GameRegistry.registerTileEntity(CompressionTableTileEntity.class, compressionTable.getUnlocalizedName());


	}
}