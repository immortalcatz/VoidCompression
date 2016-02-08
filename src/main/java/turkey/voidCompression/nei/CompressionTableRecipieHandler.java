package turkey.voidCompression.nei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import turkey.voidCompression.VCCore;
import turkey.voidCompression.blocks.VCBlocks;
import turkey.voidCompression.gui.CompressionTableGui;
import turkey.voidCompression.item.CompressedItemBlock;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class CompressionTableRecipieHandler extends TemplateRecipeHandler
{

	@Override
	public String getRecipeName()
	{
		return "Compression Table";
	}

	@Override
	public String getGuiTexture()
	{
		return VCCore.MODID + ":textures/gui/compression_table.png";
	}

	@Override
	public Class<? extends GuiContainer> getGuiClass()
	{
		return CompressionTableGui.class;
	}

	@Override
	public String getOverlayIdentifier()
	{
		return "CompressionTable";
	}

	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		if(result == null)
			return;
		
		if(!(result.getItem() instanceof CompressedItemBlock))
			return;
		
		List<PositionedStack> input = new ArrayList<PositionedStack>();

		for(int l = 0; l < 3; ++l)
		{
			for(int i1 = 0; i1 < 3; ++i1)
			{
				if(result.getItemDamage() - 1 >= 0)
					input.add(new PositionedStack(new ItemStack(result.getItem(), 1, result.getItemDamage() - 1), 25 + i1 * 18, 6 + l * 18));
				else
					input.add(new PositionedStack(new ItemStack(VCBlocks.blockfromCompressedBlock.get(Block.getBlockFromItem(result.getItem()))), 25 + i1 * 18, 6 + l * 18));
			}
		}

		CompressionRecipe res = new CompressionRecipe(input, result);
		arecipes.add(res);

		input = new ArrayList<PositionedStack>();
		if(result.getItemDamage() + 1 < 8)
			input.add(new PositionedStack(new ItemStack(result.getItem(), 1, result.getItemDamage() + 1), 25, 6));
		else
			return;

		ItemStack result2 = result.copy();
		result2.stackSize = 9;

		res = new CompressionRecipe(input, result2);
		arecipes.add(res);
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient)
	{		
		if(!(ingredient.getItem() instanceof CompressedItemBlock) && VCBlocks.compressedBlockFromBlock.get(Block.getBlockFromItem(ingredient.getItem())) == null)
			return;
		
		List<PositionedStack> input;

		if(ingredient.getItemDamage() + 1 < 8)
		{
			input = new ArrayList<PositionedStack>();
			for(int l = 0; l < 3; ++l)
			{
				for(int i1 = 0; i1 < 3; ++i1)
				{
					input.add(new PositionedStack(ingredient, 25 + i1 * 18, 6 + l * 18));
				}
			}
			CompressionRecipe res = new CompressionRecipe(input, new ItemStack(ingredient.getItem(), 1, ingredient.getItemDamage() + 1));
			arecipes.add(res);
		}

		input = new ArrayList<PositionedStack>();
		ItemStack result = ingredient.copy();
		
		if(ingredient.getItemDamage() + 1 < 8)
			result = new ItemStack(ingredient.getItem(), 1, ingredient.getItemDamage() + 1);
		else
			return;

		
		result.stackSize = 9;
		
		input.add(new PositionedStack(ingredient, 25, 6));

		CompressionRecipe res2 = new CompressionRecipe(input, result);
		arecipes.add(res2);
	}

	public class CompressionRecipe extends TemplateRecipeHandler.CachedRecipe
	{

		private List<PositionedStack> input;
		private PositionedStack output;
		private int energy;

		public int getEnergy()
		{
			return energy;
		}

		@Override
		public PositionedStack getResult()
		{
			return output;
		}

		@Override
		public List<PositionedStack> getIngredients()
		{
			return input;
		}

		public CompressionRecipe(List<PositionedStack> ingredients, ItemStack result)
		{
			this.input = ingredients;

			if(result != null)
				this.output = new PositionedStack(result, 119, 24);
		}

	}

}
