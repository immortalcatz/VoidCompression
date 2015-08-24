package turkey.voidCompression.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import turkey.voidCompression.VCCore;
import turkey.voidCompression.blocks.tileEntities.CompressionTableTileEntity;
import turkey.voidCompression.gui.VCGuiHandler;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CompressionTable extends BlockContainer
{
	
    @SideOnly(Side.CLIENT)
    private IIcon topIcon;

	public CompressionTable()
	{
		super(Material.ground);
		super.setHardness(3.5f);
		super.setHarvestLevel("pickaxe", 0);
		super.setResistance(10);
		super.setBlockName("Compression_Table");
		super.setCreativeTab(VCCore.modTab);
		super.setBlockTextureName(VCCore.MODID + ":compression_table");
	}
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int p_149691_2_)
    {
        return side == 1 ? this.topIcon : (side == 0 ? Blocks.planks.getBlockTextureFromSide(side) : this.blockIcon);
    }
	
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
        this.topIcon = iconRegister.registerIcon(this.getTextureName() + "_top");
    }

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new CompressionTableTileEntity();
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(world.isRemote || player.isSneaking())
		{
			return false;
		}
		else
		{
			FMLNetworkHandler.openGui(player, VCCore.instance, VCGuiHandler.COMPRESSION_TABLE_ID, world, x, y, z);
			return true;
		}
	}

}