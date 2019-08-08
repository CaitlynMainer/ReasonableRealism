package com.draco18s.industry.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class PoweredRailBridgeBlock extends PoweredRailBlock {
	protected static final VoxelShape THICK_FLAT_AABB = Block.makeCuboidShape(0.0D, -2.0D, 0.0D, 16.0D, 2.0D, 16.0D);

	public PoweredRailBridgeBlock() {
		super(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.7F).sound(SoundType.METAL));

	}

	@Override
	public boolean isFlexibleRail(BlockState state, IBlockReader world, BlockPos pos) {
		return false;
	}

	@Override
	public boolean canMakeSlopes(BlockState state, IBlockReader world, BlockPos pos) {
		return false;
	}

	/*@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		if(world.getBlockState(pos.down()).isSideSolid(world, pos.down(), Direction.UP) ||
				this.isRailBlock(world, pos.down()) || this.isRailBlock(world, pos.up())) {
			return false;
		}
		return true;
	}*/

	@Override
	@Deprecated
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		if (!world.isRemote) {
			//TODO
			//if(!canPlaceBlockAt(world, pos)) {
			//this.dropBlockAsItem(world, pos, state, 0);
			world.destroyBlock(pos, true);
			//}
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return THICK_FLAT_AABB;
	}

	@Override
	@Deprecated
	public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return THICK_FLAT_AABB;
	}

	@Override
	@Deprecated
	public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		//TODO validate this
		if(reader instanceof IWorld) {
			IWorld iworld = (IWorld)reader;
			PlayerEntity p = iworld.getClosestPlayer(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5, 1.5, false);
			if(p == null) {
				p = iworld.getClosestPlayer(pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5, 1.5, false);
			}
			if(p == null) {
				p = iworld.getClosestPlayer(pos.getX()+0.5, pos.getY()+2, pos.getZ()+0.5, 1.5, false);
			}
			if(p != null && p.isSneaking()) {
				return THICK_FLAT_AABB;
			}
		}
		return VoxelShapes.empty();
	}
}
