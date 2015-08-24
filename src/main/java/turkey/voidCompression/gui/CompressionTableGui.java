package turkey.voidCompression.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import turkey.voidCompression.VCCore;
import turkey.voidCompression.blocks.containers.CompressionTableContainer;
import turkey.voidCompression.blocks.tileEntities.CompressionTableTileEntity;

public class CompressionTableGui extends GuiContainer
{

	public static ResourceLocation texture = new ResourceLocation(VCCore.MODID + ":textures/gui/compression_table.png");

	public CompressionTableGui(InventoryPlayer inventory, CompressionTableTileEntity tileEntity, World world)
	{
		super(new CompressionTableContainer(inventory, world));
		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		this.fontRendererObj.drawString("Compression Table", 8, 6, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
}