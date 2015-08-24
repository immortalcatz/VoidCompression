package turkey.voidCompression.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import turkey.voidCompression.blocks.CompressedBlock;
import turkey.voidCompression.blocks.VCBlocks;
import turkey.voidCompression.item.CompressedItemBlock;

public class CompressionHelper
{
	public static String[] tuples = new String[] { "Single", "Double", "Triple", "Quadruple", "Quintuple", "Sextuple", "Septuple", "Octuple", "Nonuple", "Decuple" };
	private static final CompressionHelper instance = new CompressionHelper();

	public static final CompressionHelper getInstance()
	{
		return instance;
	}

	public ItemStack getCompressedResult(InventoryCrafting craftingMatrix, World world)
	{
		ItemStack stack0 = craftingMatrix.getStackInSlot(0);
		if(stack0 != null)
		{
			for(int i = 0; i < craftingMatrix.getSizeInventory(); i++)
			{
				if(craftingMatrix.getStackInSlot(i) == null)
					return null;
				if(!stack0.isItemEqual(craftingMatrix.getStackInSlot(i)))
					return null;
			}

			if(stack0.getItem() instanceof CompressedItemBlock)
			{
				ItemStack stack = new ItemStack(VCBlocks.compressedBlockFromBlock.get(((CompressedBlock) ((CompressedItemBlock) stack0.getItem()).field_150939_a).getBlock()), 1);
				stack.setItemDamage(stack0.getItemDamage() + 1);
				return stack;
			}
			else if(stack0.getItem() instanceof ItemBlock)
			{
				ItemStack stack = new ItemStack(VCBlocks.compressedBlockFromBlock.get(Block.getBlockFromItem(stack0.getItem())), 1);
				stack.setItemDamage(stack0.getItemDamage());
				return stack;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getCompressedBlockName(ItemStack stack)
	{
		int i = stack.getItemDamage();
		Block compressed = ((CompressedBlock) ((ItemBlock) stack.getItem()).field_150939_a).getBlock();
		
		ItemStack compressedStack = new ItemStack(compressed);

		List<String> stackname = compressedStack.getTooltip(Minecraft.getMinecraft().thePlayer, Minecraft.getMinecraft().gameSettings.advancedItemTooltips);

		if(stackname == null)
			stackname = new ArrayList<String>();

		if(stackname.size() == 0)
			stackname.add(stack.getUnlocalizedName());

		if(stackname.get(0) == null || stackname.get(0).equals(""))
			stackname.set(0, stack.getUnlocalizedName());
		
		return CompressionHelper.tuples[i] + " Compressed " + stackname.get(0);
	}

	@SuppressWarnings("unchecked")
	public String getBlockName(ItemStack stack)
	{
		int i = stack.getItemDamage();
		Block compressed = ((ItemBlock) stack.getItem()).field_150939_a;
		
		ItemStack compressedStack = new ItemStack(compressed);

		List<String> stackname = compressedStack.getTooltip(Minecraft.getMinecraft().thePlayer, Minecraft.getMinecraft().gameSettings.advancedItemTooltips);

		if(stackname == null)
			stackname = new ArrayList<String>();

		if(stackname.size() == 0)
			stackname.add(stack.getUnlocalizedName());

		if(stackname.get(0) == null || stackname.get(0).equals(""))
			stackname.set(0, stack.getUnlocalizedName());
		
		return CompressionHelper.tuples[i] + " Compressed " + stackname.get(0);
	}
}