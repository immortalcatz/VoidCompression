package turkey.voidCompression.blocks;

import net.minecraft.block.Block;
import turkey.voidCompression.blocks.tileEntities.CompressedBlockTileEntity;
import turkey.voidCompression.blocks.tileEntities.CompressionTableTileEntity;
import turkey.voidCompression.item.CompressedItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class VCBlocks
{
	public static Block compressionTable;
	public static Block compressedBlock;

	public static void loadBlocks()
	{	
		compressionTable = new CompressionTable();
		compressedBlock = new CompressedBlock();
		
		GameRegistry.registerBlock(compressedBlock, CompressedItemBlock.class, "Compresssed Block");
		GameRegistry.registerTileEntity(CompressedBlockTileEntity.class, compressedBlock.getUnlocalizedName());

		GameRegistry.registerBlock(compressionTable, "Compression_Table");
		GameRegistry.registerTileEntity(CompressionTableTileEntity.class, compressionTable.getUnlocalizedName());


	}
}