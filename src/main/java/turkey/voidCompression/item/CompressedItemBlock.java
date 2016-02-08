package turkey.voidCompression.item;

import turkey.voidCompression.blocks.tileEntities.CompressedBlockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CompressedItemBlock extends ItemBlock
{
	public CompressedItemBlock(Block b)
	{
		super(b);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int meta)
	{
		return meta;
	}

	@Override
	public String getItemStackDisplayName(ItemStack itemstack)
	{
		String displayName = "Name Error";
		if(itemstack.stackTagCompound != null && itemstack.stackTagCompound.hasKey("Display"))
			displayName = itemstack.stackTagCompound.getString("Display");
		return displayName;
	}

	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if(super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata))
		{
			TileEntity ent = world.getTileEntity(x, y, z);
			if(ent instanceof CompressedBlockTileEntity)
			{
				CompressedBlockTileEntity compressed = (CompressedBlockTileEntity) ent;
				if(stack.stackTagCompound == null || !stack.stackTagCompound.hasKey("BlockID") || !stack.stackTagCompound.hasKey("Compression"))
				{
					world.setBlockToAir(x, y, z);
					return false;
				}
				compressed.setBlockCompressed(Block.getBlockFromName(stack.stackTagCompound.getString("BlockID")));
				compressed.setCompressedLevel(stack.stackTagCompound.getInteger("Compression"));
			}
			return true;
		}
		return false;
	}

	public boolean isValid(ItemStack stack)
	{
		return (stack.stackTagCompound != null && stack.stackTagCompound.hasKey("BlockID") && stack.stackTagCompound.hasKey("Compression"));
	}
}