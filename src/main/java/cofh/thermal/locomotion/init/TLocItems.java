package cofh.thermal.locomotion.init;

import cofh.thermal.core.config.ThermalCoreConfig;
import cofh.thermal.lib.common.ThermalItemGroups;
import cofh.thermal.locomotion.entity.FluidMinecart;
import cofh.thermal.locomotion.item.EnergyMinecartItem;
import cofh.thermal.locomotion.item.FluidMinecartItem;
import cofh.thermal.locomotion.item.UnderwaterMinecartItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.IntSupplier;

import static cofh.lib.util.constants.ModIds.ID_THERMAL_LOCOMOTION;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.lib.common.ThermalAugmentRules.ENERGY_STORAGE_VALIDATOR;
import static cofh.thermal.locomotion.init.TLocIDs.*;

public class TLocItems {

    private TLocItems() {

    }

    public static void register() {

        CreativeModeTab group = ThermalItemGroups.THERMAL_TOOLS;

        IntSupplier storageAugs = () -> ThermalCoreConfig.storageAugments;

        ITEMS.register(ID_UNDERWATER_CART, () -> new UnderwaterMinecartItem(new Item.Properties().tab(group)).setModId(ID_THERMAL_LOCOMOTION));
        ITEMS.register(ID_ENERGY_CART, () -> new EnergyMinecartItem(new Item.Properties().tab(group)).setNumSlots(storageAugs).setAugValidator(ENERGY_STORAGE_VALIDATOR).setModId(ID_THERMAL_LOCOMOTION));
        ITEMS.register(ID_FLUID_CART, () -> new FluidMinecartItem(new Item.Properties().tab(group)).setNumSlots(storageAugs).setAugValidator(FluidMinecart.AUG_VALIDATOR).setModId(ID_THERMAL_LOCOMOTION));
    }

}
