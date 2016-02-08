package turkey.voidCompression.blocks.tileEntities;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class CompressedBlockTileEntity extends TileEntity
{
	private Block block;
	private int compressionLevel = -1;

	public CompressedBlockTileEntity()
	{

	}

	public Block getBlockCompressed()
	{
		return this.block;
	}

	public void setBlockCompressed(Block block)
	{
		this.block = block;
	}

	public int getCompressedLevel()
	{
		return this.compressionLevel;
	}

	public void setCompressedLevel(int level)
	{
		this.compressionLevel = level;
	}

	public boolean isValid()
	{
		return(this.block != null && this.compressionLevel != -1);
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.block = Block.getBlockFromName(nbt.getString("BlockID"));
		this.compressionLevel = nbt.getInteger("CompressionLevel");
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setString("BlockID", Block.blockRegistry.getNameForObject(this.block));
		nbt.setInteger("CompressionLevel", this.compressionLevel);
	}
}
