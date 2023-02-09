package cofh.thermal.locomotion.inventory.container;

import cofh.core.inventory.container.ContainerCoFH;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.locomotion.entity.EnergyMinecart;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import static cofh.thermal.locomotion.init.TLocContainers.ENERGY_CART_CONTAINER;

public class EnergyMinecartContainer extends ContainerCoFH {

    public final EnergyMinecart cart;

    public EnergyMinecartContainer(int id, Inventory inventory, EnergyMinecart cart) {

        super(ENERGY_CART_CONTAINER.get(), id, inventory, inventory.player);

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
