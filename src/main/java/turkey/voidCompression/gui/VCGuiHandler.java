package turkey.voidCompression.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import turkey.voidCompression.blocks.containers.CompressionTableContainer;
import turkey.voidCompression.blocks.tileEntities.CompressionTableTileEntity;
import cpw.mods.fml.common.network.IGuiHandler;

public class VCGuiHandler implements IGuiHandler
{
	public final static int COMPRESSION_TABLE_ID = 0;
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if (tileEntity != null)
		{
			switch (ID)
			{
			case COMPRESSION_TABLE_ID:
				if (tileEntity instanceof CompressionTableTileEntity)
				{
					return new CompressionTableGui(player.inventory, (CompressionTableTileEntity) tileEntity, world);
				}
				break;
			default:
				return null;
			}
		}
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if (tileEntity != null)
		{
			switch (ID)
			{
			case COMPRESSION_TABLE_ID:
				if (tileEntity instanceof CompressionTableTileEntity)
				{
					return new CompressionTableContainer(player.inventory, world);
				}
				break;
			default:
				return null;
			}
		}
		return null;
	}
}
