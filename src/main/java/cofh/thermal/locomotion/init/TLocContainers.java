package cofh.thermal.locomotion.init;

import cofh.lib.util.Utils;
import cofh.thermal.locomotion.entity.EnergyMinecart;
import cofh.thermal.locomotion.entity.FluidMinecart;
import cofh.thermal.locomotion.inventory.container.EnergyMinecartContainer;
import cofh.thermal.locomotion.inventory.container.FluidMinecartContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.RegistryObject;

import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.locomotion.init.TLocIDs.ID_ENERGY_CART;
import static cofh.thermal.locomotion.init.TLocIDs.ID_FLUID_CART;

public class TLocContainers {

    private TLocContainers() {

    }

    public static void register() {

    }

    public static final RegistryObject<MenuType<EnergyMinecartContainer>> ENERGY_CART_CONTAINER = CONTAINERS.register(ID_ENERGY_CART, () -> IForgeMenuType.create((windowId, inv, data) -> new EnergyMinecartContainer(windowId, inv, Utils.getEntityFromBuf(data, EnergyMinecart.class))));
    public static final RegistryObject<MenuType<FluidMinecartContainer>> FLUID_CART_CONTAINER = CONTAINERS.register(ID_FLUID_CART, () -> IForgeMenuType.create((windowId, inv, data) -> new FluidMinecartContainer(windowId, inv, Utils.getEntityFromBuf(data, FluidMinecart.class))));

}
