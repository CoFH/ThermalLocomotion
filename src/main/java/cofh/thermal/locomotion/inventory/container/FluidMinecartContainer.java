package cofh.thermal.locomotion.inventory.container;

import cofh.lib.inventory.container.ContainerCoFH;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

import javax.annotation.Nullable;

public class FluidMinecartContainer extends ContainerCoFH {

    public FluidMinecartContainer(@Nullable MenuType<?> type, int id, Inventory inventory, Player player) {

        super(type, id, inventory, player);

        bindPlayerInventory(inventory);
    }

    @Override
    protected int getMergeableSlotCount() {

        return 0;
    }

    @Override
    public boolean stillValid(Player player) {

        return false;
    }

}
