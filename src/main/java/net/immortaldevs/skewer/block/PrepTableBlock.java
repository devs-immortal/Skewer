package net.immortaldevs.skewer.block;

import net.immortaldevs.skewer.blockentity.PrepTableEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class PrepTableBlock extends BlockWithEntity {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public static final VoxelShape COLLISION;

    protected PrepTableBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return Optional.ofNullable(world.getBlockEntity(pos)).map(blockEntity -> ((PrepTableEntity) blockEntity).handleInteraction(player.getStackInHand(hand), player, hand, hit)).orElse(super.onUse(state, world, pos, player, hand, hit));
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PrepTableEntity prepTable) {
                ItemScatterer.spawn(world, pos, prepTable.getItems());
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), prepTable.getBody());
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), prepTable.getOutput());
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COLLISION;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PrepTableEntity(pos, state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    static {
        var base = Block.createCuboidShape(0, 0, 0, 16, 4, 16);
        var top = Block.createCuboidShape(0, 10, 0, 16, 16, 16);
        var body = Block.createCuboidShape(1, 4, 1, 15, 10, 15);
        body = VoxelShapes.union(body, base);
        COLLISION = VoxelShapes.union(body, top);
    }
}
