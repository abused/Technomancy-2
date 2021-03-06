package theflogat.technomancy.common.blocks.bloodmagic.dynamos;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import theflogat.technomancy.common.blocks.base.BlockDynamoBase;
import theflogat.technomancy.common.tiles.bloodmagic.dynamos.TileBloodDynamo;
import theflogat.technomancy.lib.Names;
import theflogat.technomancy.lib.Ref;
import theflogat.technomancy.lib.RenderIds;
import theflogat.technomancy.lib.compat.BloodMagic;

public class BlockBloodDynamo extends BlockDynamoBase {

	public BlockBloodDynamo() {
		setUnlocalizedName(Ref.getId(Names.bloodDynamo));
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isTranslucent(IBlockState state) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.getHeldItem(hand) != null) {
			TileBloodDynamo tile = (TileBloodDynamo)world.getTileEntity(pos);
			if(player.getHeldItem(hand).getItem()==BloodMagic.divinationSigil){
				if(!world.isRemote) {
					player.sendMessage(new TextComponentString("Energy: " + tile.getEnergyStored(null) + "/" + tile.getMaxEnergyStored(null)));
					player.sendMessage(new TextComponentString("Blood: " + tile.liquid + "/" + TileBloodDynamo.capacity));
				}
				return true;
			}else if(player.getHeldItem(hand).getItem()==BloodMagic.bucketLife){
				if(tile.emptyBucket()) {
					player.inventory.mainInventory.set(player.inventory.currentItem, new ItemStack(Items.BUCKET));
					return true;
				}
			}
		}
		return super.onBlockActivated(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public TileEntity createNewTileEntity(World w, int meta) {
		return new TileBloodDynamo();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
}