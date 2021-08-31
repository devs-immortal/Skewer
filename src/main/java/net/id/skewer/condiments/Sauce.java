package net.id.skewer.condiments;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.id.skewer.condiments.Condiment;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class Sauce extends Fluid implements Condiment {
    public FoodComponent component;
    public BiConsumer<PlayerEntity, Item> onConsumed;
    public Item bucketItem;

    public Sauce(FoodComponent component, Item bucketItem, BiConsumer<PlayerEntity, Item> onConsumed){
        this.component = component;
        this.onConsumed = onConsumed;
        this.bucketItem = bucketItem;
    }
    public Sauce(FoodComponent component, Item bucketItem){
        this(component, bucketItem, null);
    }
    public Sauce(FoodComponent component){
        this(component, null);
    }

    @Override
    public Item getBucketItem() {
        return bucketItem;
    }

    @Override
    public @Nullable FoodComponent getFoodComponent() {
        return component;
    }

    @Override
    public @Nullable BiConsumer<PlayerEntity, Item> getOnConsumed() {
        return onConsumed;
    }


    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return false;
    }

    @Override
    protected Vec3d getVelocity(BlockView world, BlockPos pos, FluidState state) {
        return Vec3d.ZERO;
    }

    @Override
    public int getTickRate(WorldView world) {
        return 0;
    }

    @Override
    protected float getBlastResistance() {
        return 0;
    }

    @Override
    public float getHeight(FluidState state, BlockView world, BlockPos pos) {
        return 0;
    }

    @Override
    public float getHeight(FluidState state) {
        return 0;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return null;
    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    @Override
    public int getLevel(FluidState state) {
        return 0;
    }

    @Override
    public VoxelShape getShape(FluidState state, BlockView world, BlockPos pos) {
        return null;
    }

}
