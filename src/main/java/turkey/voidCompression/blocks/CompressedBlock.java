package turkey.voidCompression.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import turkey.voidCompression.VCCore;
import turkey.voidCompression.VCSettings;
import turkey.voidCompression.blocks.tileEntities.CompressedBlockTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CompressedBlock extends Block implements ITileEntityProvider
{
	@SideOnly(Side.CLIENT)
	private IIcon[] compressionLevels;


	public CompressedBlock()
	{
		super(Material.rock);
		super.setCreativeTab(VCCore.modTab);
		super.setHardness(1.5F);
		super.setResistance(10.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		compressionLevels = new IIcon[10];
		compressionLevels[0] = register.registerIcon(VCCore.MODID + ":compressionOne");
		compressionLevels[1] = register.registerIcon(VCCore.MODID + ":compressionTwo");
		compressionLevels[2] = register.registerIcon(VCCore.MODID + ":compressionThree");
		compressionLevels[3] = register.registerIcon(VCCore.MODID + ":compressionFour");
		compressionLevels[4] = register.registerIcon(VCCore.MODID + ":compressionFive");
		compressionLevels[5] = register.registerIcon(VCCore.MODID + ":compressionSix");
		compressionLevels[6] = register.registerIcon(VCCore.MODID + ":compressionSeven");
		compressionLevels[7] = register.registerIcon(VCCore.MODID + ":compressionEight");
		compressionLevels[7] = register.registerIcon(VCCore.MODID + ":compressionEight");
		compressionLevels[8] = register.registerIcon(VCCore.MODID + ":compressionEight");
		compressionLevels[9] = register.registerIcon(VCCore.MODID + ":compressionEight");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		return compressionLevels[world.getBlockMetadata(x, y, z)];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return compressionLevels[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return VCSettings.renderID;
	}

	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new CompressedBlockTileEntity();
	}
}