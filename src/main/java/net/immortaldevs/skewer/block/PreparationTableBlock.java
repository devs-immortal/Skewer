package net.immortaldevs.skewer.block;

import net.immortaldevs.skewer.block.entity.PreparationTableBlockEntity;
import net.immortaldevs.skewer.component.SkeweredFoodComponents;
import net.immortaldevs.skewer.item.SkewerItems;
import net.immortaldevs.skewer.tag.SkewerItemTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PreparationTableBlock extends BlockWithEntity {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    protected static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 0, 0, 16, 4, 16),
            Block.createCuboidShape(0, 10, 0, 16, 16, 16),
            Block.createCuboidShape(1, 4, 1, 15, 10, 15));

    public PreparationTableBlock(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state,
                              World world,
                              BlockPos pos,
                              PlayerEntity player,
                              Hand hand,
                              BlockHitResult hit) {
        ItemStack heldStack = player.getStackInHand(hand);
        if (!(world.getBlockEntity(pos) instanceof PreparationTableBlockEntity blockEntity)) {
            return ActionResult.PASS;
        }

        if (heldStack.isOf(SkewerItems.KNIFE)) {
            if (world.isClient) return ActionResult.CONSUME;

            blockEntity.assembleKebab(player, hand);
            return ActionResult.SUCCESS;
        }

        if (heldStack.isIn(SkewerItemTags.SKEWERS)) {
            if (world.isClient) return ActionResult.CONSUME;

            blockEntity.addSkewer(player, hand);
            return ActionResult.SUCCESS;
        }

        //TODO taking items out of the table

        if (SkeweredFoodComponents.contains(heldStack.getItem())) {
            return !world.isClient && blockEntity.addFood(player, hand)
                    ? ActionResult.SUCCESS
                    : ActionResult.CONSUME;
        }

        return ActionResult.FAIL;
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = super.getPlacementState(ctx);
        if (state == null) return null;
        return state.with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PreparationTableBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }
}
