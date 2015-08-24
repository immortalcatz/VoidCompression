package turkey.voidCompression.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import turkey.voidCompression.blocks.CompressedBlock;
import turkey.voidCompression.util.CompressionHelper;

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
		int i = itemstack.getItemDamage();
		Block compressed = ((CompressedBlock) super.field_150939_a).getBlock();
		return CompressionHelper.getInstance().getBlockName(new ItemStack(compressed, 1, i));
	}
}