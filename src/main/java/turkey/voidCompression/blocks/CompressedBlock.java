package turkey.voidCompression.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import turkey.voidCompression.VCCore;
import turkey.voidCompression.VCSettings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CompressedBlock extends Block
{
	@SideOnly(Side.CLIENT)
	private IIcon[] compressionLevels = new IIcon[8];

	private final Block block;

	public CompressedBlock(Block block)
	{
		super(Material.rock);
		this.block = block;
		super.setCreativeTab(VCCore.modTab);
		super.setHardness(1.5F);
		super.setResistance(10.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		compressionLevels[0] = register.registerIcon(VCCore.MODID + ":compressionOne");
		compressionLevels[1] = register.registerIcon(VCCore.MODID + ":compressionTwo");
		compressionLevels[2] = register.registerIcon(VCCore.MODID + ":compressionThree");
		compressionLevels[3] = register.registerIcon(VCCore.MODID + ":compressionFour");
		compressionLevels[4] = register.registerIcon(VCCore.MODID + ":compressionFive");
		compressionLevels[5] = register.registerIcon(VCCore.MODID + ":compressionSix");
		compressionLevels[6] = register.registerIcon(VCCore.MODID + ":compressionSeven");
		compressionLevels[7] = register.registerIcon(VCCore.MODID + ":compressionEight");
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

	@SideOnly(Side.CLIENT)
	public IIcon getBlockIcon(int side, int meta)
	{
		return block.getIcon(side, meta);
	}

	public Block getBlock()
	{
		return block;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType()
	{
		return VCSettings.renderID;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		for(int i = 0; i < 8; ++i)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	public int damageDropped(int meta)
	{
		return meta;
	}
}