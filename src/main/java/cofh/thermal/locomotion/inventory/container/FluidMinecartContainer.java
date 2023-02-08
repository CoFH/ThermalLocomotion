package cofh.thermal.locomotion.inventory.container;

import cofh.core.inventory.container.ContainerCoFH;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.locomotion.entity.FluidMinecart;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import static cofh.thermal.locomotion.init.TLocContainers.FLUID_CART_CONTAINER;

public class FluidMinecartContainer extends ContainerCoFH {

    public final FluidMinecart cart;

    public FluidMinecartContainer(int id, Inventory inventory, FluidMinecart cart) {

        super(FLUID_CART_CONTAINER.get(), id, inventory, inventory.player);

        this.cart = cart;

        InvWrapperCoFH cartInv = new InvWrapperCoFH(this.cart.getItemInv());

        addSlot(new SlotCoFH(cartInv, 0, 35, 35));
        addSlot(new SlotCoFH(cartInv, 1, 125, 35));

        bindAugmentSlots(cartInv, 2, this.cart.augSize());
        bindPlayerInventory(inventory);
    }

    @Override
    protected int getMergeableSlotCount() {

        return cart.invSize();
    }

    @Override
    public boolean stillValid(Player player) {

        return cart.isAlive();
    }

}
