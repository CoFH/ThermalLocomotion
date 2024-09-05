package cofh.thermal.locomotion.init.registries;

import cofh.lib.util.Utils;
import cofh.thermal.locomotion.common.entity.EnergyMinecart;
import cofh.thermal.locomotion.common.entity.FluidMinecart;
import cofh.thermal.locomotion.common.inventory.EnergyMinecartMenu;
import cofh.thermal.locomotion.common.inventory.FluidMinecartMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import java.util.function.Supplier;

import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.locomotion.init.registries.TLocIDs.ID_ENERGY_CART;
import static cofh.thermal.locomotion.init.registries.TLocIDs.ID_FLUID_CART;

public class TLocContainers {

    private TLocContainers() {

    }

    public static void register() {

    }

    public static final Supplier<MenuType<EnergyMinecartMenu>> ENERGY_CART_CONTAINER = CONTAINERS.register(ID_ENERGY_CART, () -> IMenuTypeExtension.create((windowId, inv, data) -> new EnergyMinecartMenu(windowId, inv, Utils.getEntityFromBuf(data, EnergyMinecart.class))));
    public static final Supplier<MenuType<FluidMinecartMenu>> FLUID_CART_CONTAINER = CONTAINERS.register(ID_FLUID_CART, () -> IMenuTypeExtension.create((windowId, inv, data) -> new FluidMinecartMenu(windowId, inv, Utils.getEntityFromBuf(data, FluidMinecart.class))));

}
