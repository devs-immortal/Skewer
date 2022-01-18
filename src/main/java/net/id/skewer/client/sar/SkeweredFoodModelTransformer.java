package net.id.skewer.client.sar;

import net.immortaldevs.sar.base.client.modifier.ItemModelModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;

import java.util.Random;

public final class SkeweredFoodModelTransformer {
    public static ItemModelModifier transform(int position, Random random, ItemStack stack, ItemModelModifier wrapped) {
        float offset = position * 0.0425f + random.nextFloat(0.495f, 0.505f);
        float z = random.nextFloat(0.48f, 0.52f);
        Quaternion quaternion = new Quaternion(0, 1.570796f, 0, false);
        quaternion.hamiltonProduct(new Quaternion(
                random.nextFloat(-0.05f, 0.05f) - 0.785398f,
                random.nextFloat(-0.05f, 0.05f),
                random.nextFloat(-3.141f, 3.141f), false));

        return (s, randomSupplier, context) -> {
            context.pushTransform(quad -> {
                Vec3f work = new Vec3f();

                for (int i = 0; i < 4; i++) {
                    quad.copyPos(i, work);
                    work.add(-0.5f, -0.5f, -0.5f);
                    work.rotate(quaternion);
                    work.scale(0.75f);
                    work.add(offset, offset, z);
                    quad.pos(i, work);
                }

                return true;
            });

            wrapped.emitQuads(stack, randomSupplier, context);
            context.popTransform();
        };
    }
}
