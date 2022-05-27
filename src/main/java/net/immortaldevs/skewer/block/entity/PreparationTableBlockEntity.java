package net.immortaldevs.skewer.block.entity;

import net.immortaldevs.sar.api.ComponentCollection;
import net.immortaldevs.skewer.component.CondimentComponent;
import net.immortaldevs.skewer.component.SkewerCondimentComponents;
import net.immortaldevs.skewer.component.SkeweredFoodComponent;
import net.immortaldevs.skewer.component.SkeweredFoodComponents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;

public class PreparationTableBlockEntity extends BlockEntity {
    protected final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(18, ItemStack.EMPTY);

    public PreparationTableBlockEntity(BlockPos pos, BlockState state) {
        super(SkewerBlockEntityTypes.PREPARATION_TABLE, pos, state);
    }

    public boolean assembleKebab(PlayerEntity player, Hand hand) {
        ItemStack kebab = this.inventory.get(16).copy();
        kebab.setCount(1);

        if (kebab.isEmpty()) return false;
        ComponentCollection skeweredFoods = kebab.getComponents("foods");

        boolean success = false;
        var context = new SkewerConstructionContext() {
            private int index;
            private double outputCount = 0.0;
            private double outputMultiplier = 1.0;
            private double outputModifier = 1.0;

            @Override
            public ItemStack getFood() {
                return PreparationTableBlockEntity.this.inventory.get(this.index);
            }

            @Override
            public void setFood(ItemStack food) {
                PreparationTableBlockEntity.this.inventory.set(this.index, food);
            }

            @Override
            public ItemStack getSkewer() {
                return kebab;
            }

            @Override
            public PlayerEntity getPlayer() {
                return player;
            }

            @Override
            public Hand getHand() {
                return hand;
            }

            @Override
            public double getOutputCount() {
                return this.outputCount;
            }

            @Override
            public void setOutputCount(double outputCount) {
                this.outputCount = outputCount;
            }

            @Override
            public double getOutputMultiplier() {
                return this.outputMultiplier;
            }

            @Override
            public void setOutputMultiplier(double outputMultiplier) {
                this.outputMultiplier = outputMultiplier;
            }

            @Override
            public double getOutputModifier() {
                return this.outputModifier;
            }

            @Override
            public void setOutputModifier(double outputModifier) {
                this.outputModifier = outputModifier;
            }
        };

        for (context.index = 15; context.index >= 0; context.index--) {
            SkeweredFoodComponent component = SkeweredFoodComponents.get(context.getFood().getItem());
            success |= component != null && component.applyTo(skeweredFoods, context);
        }

        if (success) {
            player.getStackInHand(hand).damage(1, player, p -> {});

            if (!player.getAbilities().creativeMode) {
                int output = Math.min(this.inventory.get(16).getCount(),
                        (int) Math.ceil(context.outputCount * context.outputMultiplier));

                this.inventory.get(16).decrement(output);
                kebab.setCount(output);
            }

            this.inventory.set(17, kebab);
        }

        this.updateListeners();
        return success;
    }

    public void addSkewer(PlayerEntity player, Hand hand) {
        ItemStack held = player.getStackInHand(hand);
        ItemStack stored = this.inventory.get(16);

        if (player.getAbilities().creativeMode) {
            if (ItemStack.canCombine(stored, held)) this.inventory.set(16, ItemStack.EMPTY);
            else this.inventory.set(16, held.copy());
        } else if (ItemStack.canCombine(stored, held)
                && stored.getCount() < stored.getMaxCount()) {
            int change = Math.min(stored.getMaxCount() - stored.getCount(), held.getCount());
            stored.increment(change);
            held.decrement(change);
        } else {
            this.inventory.set(16, held);
            player.setStackInHand(hand, stored);
        }

        this.updateListeners();
    }

    public boolean addFood(PlayerEntity player, Hand hand) {
        for (int i = 0; i < 16; i++) {
            if (this.inventory.get(i).isEmpty()) {
                ItemStack held = player.getStackInHand(hand);
                if (player.getAbilities().creativeMode) {
                    ItemStack stack = held.copy();
                    stack.setCount(1);
                    this.inventory.set(i, stack);
                } else this.inventory.set(i, held.split(1));

                this.updateListeners();
                return true;
            }
        }

        return false;
    }

    public boolean applyCondiment(PlayerEntity player, Hand hand) {
        ItemStack held = player.getStackInHand(hand);
        ItemStack kebab = this.inventory.get(17);
        CondimentComponent condiment = SkewerCondimentComponents.get(held.getItem());
        boolean changed = condiment != null && condiment.applyTo(kebab.getComponents("condiments"),
                held, kebab, player, hand);
        this.updateListeners();
        return changed;
    }

    public boolean canTakeItem() {
        for (int i = 0; i < 17; i++) {
            ItemStack stack = this.inventory.get(i);
            if (!stack.isEmpty()) return true;
        }

        return false;
    }

    public @Nullable ItemStack takeItem(PlayerEntity player) {
        if (player.isSneaking()) {
            for (int i = 16; i >= 0; i--) {
                ItemStack stack = this.takeItem(i);
                if (stack != null) return stack;
            }

            return null;
        } else {
            for (int i = 15; i >= 0; i--) {
                ItemStack stack = this.takeItem(i);
                if (stack != null) return stack;
            }

            return this.takeItem(16);
        }
    }

    protected @Nullable ItemStack takeItem(int index) {
        ItemStack stack = this.inventory.get(index);
        if (stack.isEmpty()) return null;
        this.inventory.set(index, ItemStack.EMPTY);
        this.updateListeners();
        return stack;
    }

    public ItemStack getSkewer() {
        return this.inventory.get(16);
    }

    public ItemStack getFood(int index) {
        if (index >= 0 && index < 16) return this.inventory.get(index);
        throw new IndexOutOfBoundsException("Index " + index + " out of bounds. Must be between 0 and 16");
    }

    public ItemStack getKebab() {
        return this.inventory.get(17);
    }

    public void clearKebab() {
        this.inventory.set(17, ItemStack.EMPTY);
        this.updateListeners();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inventory.clear();
        Inventories.readNbt(nbt, this.inventory);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory, true);
    }

    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return Inventories.writeNbt(new NbtCompound(), this.inventory);
    }

    @SuppressWarnings("ConstantConditions")
    private void updateListeners() {
        this.markDirty();
        this.world.updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }

    @SuppressWarnings("unused")
    public interface SkewerConstructionContext {
        ItemStack getFood();

        void setFood(ItemStack food);

        ItemStack getSkewer();

        PlayerEntity getPlayer();

        Hand getHand();

        double getOutputCount();

        void setOutputCount(double outputCount);

        double getOutputMultiplier();

        void setOutputMultiplier(double outputMultiplier);

        double getOutputModifier();

        void setOutputModifier(double outputModifier);
    }
}
