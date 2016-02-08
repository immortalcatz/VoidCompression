package turkey.voidCompression.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
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
				ItemStack stack = new ItemStack(VCBlocks.compressedBlock, 1);
				stack.stackTagCompound = (NBTTagCompound) stack0.stackTagCompound.copy();
				int compression = stack.stackTagCompound.getInteger("Compression") + 1;
				stack.stackTagCompound.setInteger("Compression", compression);
				String oldDisplay = stack.stackTagCompound.getString("Display");
				stack.stackTagCompound.setString("Display", CompressionHelper.tuples[compression-1] + oldDisplay.substring(oldDisplay.indexOf(" ")));
				return stack;
			}
			else if(stack0.getItem() instanceof ItemBlock)
			{
				ItemStack stack = new ItemStack(VCBlocks.compressedBlock, 1);
				stack.stackTagCompound = new NBTTagCompound();
				stack.stackTagCompound.setString("BlockID", Block.blockRegistry.getNameForObject(Block.getBlockFromItem(stack0.getItem())));
				stack.stackTagCompound.setInteger("Compression", 1);
				stack.stackTagCompound.setString("Display", CompressionHelper.tuples[0] + " Compressed " + stack0.getItem().getItemStackDisplayName(stack0));
				return stack;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getCompressedBlockName(ItemStack stack)
	{
		int i = stack.getItemDamage();

		List<String> stackname = stack.getTooltip(Minecraft.getMinecraft().thePlayer, Minecraft.getMinecraft().gameSettings.advancedItemTooltips);

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