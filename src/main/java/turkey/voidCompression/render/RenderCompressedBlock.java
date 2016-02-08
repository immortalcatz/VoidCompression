package turkey.voidCompression.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import turkey.voidCompression.VCSettings;
import turkey.voidCompression.blocks.VCBlocks;
import turkey.voidCompression.blocks.tileEntities.CompressedBlockTileEntity;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderCompressedBlock implements ISimpleBlockRenderingHandler, IItemRenderer
{
	private static RenderItem renderItem = new RenderItem();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if(te == null || !(te instanceof CompressedBlockTileEntity))
			return false;
		if(!((CompressedBlockTileEntity) te).isValid())
			return false;
		renderer.setOverrideBlockTexture(((CompressedBlockTileEntity) te).getBlockCompressed().getBlockTextureFromSide(0));
		renderer.renderStandardBlock(block, x, y, z);
		renderer.setOverrideBlockTexture(block.getIcon(0, ((CompressedBlockTileEntity) te).getCompressedLevel() - 1));
		renderer.renderStandardBlock(block, x, y, z);
		renderer.clearOverrideBlockTexture();
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return VCSettings.renderID;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return type == ItemRenderType.INVENTORY || type == ItemRenderType.EQUIPPED;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack stack, Object... data)
	{
		if(stack.stackTagCompound == null || !stack.stackTagCompound.hasKey("BlockID"))
			return;
		Block block = Block.getBlockFromName(stack.stackTagCompound.getString("BlockID"));
		RenderHelper.enableGUIStandardItemLighting();
		if(type == ItemRenderType.INVENTORY)
		{
			GL11.glPushMatrix();
			renderItem.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, new ItemStack(block), 0, 0);
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glScalef(1.01f, 1.01f, 1.01f);
			renderItem.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, new ItemStack(Item.getItemFromBlock(VCBlocks.compressedBlock)), 0, 0);
			GL11.glPopMatrix();
		}
		else if(type == ItemRenderType.EQUIPPED)
		{
			RenderBlocks blockrender = (RenderBlocks) data[0];
			/*blockrender.setOverrideBlockTexture(((CompressedBlockTileEntity) te).getBlockCompressed().getBlockTextureFromSide(0));
			blockrender.renderBlockAsItem(block, p_147800_2_, p_147800_3_);.renderStandardBlock(block, x, y, z);
			blockrender.setOverrideBlockTexture(block.getIcon(0, ((CompressedBlockTileEntity) te).getCompressedLevel() - 1));
			blockrender.renderStandardBlock(block, x, y, z);
			blockrender.clearOverrideBlockTexture();*/
		}
	}
}