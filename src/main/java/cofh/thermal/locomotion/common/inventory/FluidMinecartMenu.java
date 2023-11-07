package cofh.thermal.locomotion.common.inventory;

import cofh.core.common.inventory.ContainerMenuCoFH;
import cofh.lib.common.inventory.SlotCoFH;
import cofh.lib.common.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.locomotion.common.entity.FluidMinecart;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import static cofh.thermal.locomotion.init.registries.TLocContainers.FLUID_CART_CONTAINER;

public class FluidMinecartMenu extends ContainerMenuCoFH {

    public final FluidMinecart cart;

    public FluidMinecartMenu(int id, Inventory inventory, FluidMinecart cart) {

        super(FLUID_CART_CONTAINER.get(), id, inventory, inventory.player);

        this.cart = cart;

        InvWrapperCoFH cartInv = new InvWrapperCoFH(this.cart.getItemInv());

        addSlot(new SlotCoFH(cartInv, 0, 26, 35));
        addSlot(new SlotCoFH(cartInv, 1, 134, 35));

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
