package turkey.voidCompression;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import turkey.voidCompression.blocks.VCBlocks;
import cpw.mods.fml.common.registry.GameRegistry;


public class CraftingRecipies
{

	public static void loadRecipies()
	{
		GameRegistry.addShapedRecipe(new ItemStack(VCBlocks.compressionTable, 1), "WPW","PLP", "WPW", 'W', new ItemStack(Blocks.planks), 'L', new ItemStack(Blocks.log), 'P', new ItemStack(Blocks.piston));
	}
}
