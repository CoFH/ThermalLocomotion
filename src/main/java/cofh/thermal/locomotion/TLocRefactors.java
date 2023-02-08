package cofh.thermal.locomotion;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkHooks;

public class TLocRefactors {

    private TLocRefactors() {

    }

    //    public static final EntityDataSerializer<FluidStack> FLUID_STACK = new EntityDataSerializer<>() {
    //
    //        @Override
    //        public void write(FriendlyByteBuf buf, FluidStack fluid) {
    //
    //            buf.writeFluidStack(fluid);
    //        }
    //
    //        @Override
    //        public FluidStack read(FriendlyByteBuf buf) {
    //
    //            return buf.readFluidStack();
    //        }
    //
    //        @Override
    //        public FluidStack copy(FluidStack stack) {
    //
    //            return stack.copy();
    //        }
    //    };

    public static void openEntityScreen(ServerPlayer player, MenuProvider containerSupplier, Entity entity) {

        NetworkHooks.openScreen(player, containerSupplier, buf -> buf.writeVarInt(entity.getId()));
    }

    public static <E extends Entity> E getEntityFromBuf(FriendlyByteBuf buf, Class<E> type) {

        if (buf == null) {
            throw new IllegalArgumentException("Null packet buffer.");
        }
        return DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> {
            if (Minecraft.getInstance().level == null) {
                throw new IllegalStateException("Client world is null.");
            }
            int entityId = buf.readVarInt();
            Entity e = Minecraft.getInstance().level.getEntity(entityId);
            if (type.isInstance(e)) {
                // noinspection unchecked
                return (E) e;
            }
            throw new IllegalStateException("Client could not locate entity (id: " + entityId + ")  for entity container or the entity was of an invalid type. This is likely caused by a mod breaking client side entity lookup.");
        });
    }

}
