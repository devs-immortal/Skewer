package net.immortaldevs.skewer.block;

import net.immortaldevs.skewer.item.SkewerItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class GarlicCropBlock extends CropBlock {
    public GarlicCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxAge() {
        return 3;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);

        // If surrounded by gravel, let it grow faster.
        final int[] gravelSpots = {0};
        // check the block under the farmland
        gravelSpots[0] = world.getBlockState(pos.down().down()).isOf(Blocks.GRAVEL) ? 1 : 0;
        // now check the other blocks
        BlockPos.iterateOutwards(pos, 1, 1, 1).iterator().forEachRemaining(check -> {
            if(world.isWater(check)) {
                gravelSpots[0]++;
            }
        });
        // More gravel spots -> more likely to grow faster.
        // No gravel : 1x speed. 26 gravel : 2x speed
        if(random.nextInt(25) < gravelSpots[0]) {
            super.randomTick(state, world, pos, random);
        }
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return SkewerItems.GARLIC;
    }
}
