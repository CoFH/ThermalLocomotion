package cofh.thermal.locomotion.inventory.container;

import cofh.lib.inventory.container.ContainerCoFH;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;

import javax.annotation.Nullable;

public class FluidMinecartContainer extends ContainerCoFH {

    public FluidMinecartContainer(@Nullable ContainerType<?> type, int id, PlayerInventory inventory, PlayerEntity player) {

        super(type, id, inventory, player);

        bindPlayerInventory(inventory);
    }

    @Override
    protected int getMergeableSlotCount() {

        return 0;
    }

    @Override
    public boolean stillValid(PlayerEntity p_75145_1_) {

        return false;
    }

}
