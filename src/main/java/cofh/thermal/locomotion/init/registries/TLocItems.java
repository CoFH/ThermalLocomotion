package cofh.thermal.locomotion.init.registries;

import cofh.thermal.core.common.config.ThermalCoreConfig;
import cofh.thermal.locomotion.common.entity.FluidMinecart;
import cofh.thermal.locomotion.common.item.EnergyMinecartItem;
import cofh.thermal.locomotion.common.item.FluidMinecartItem;
import cofh.thermal.locomotion.common.item.UnderwaterMinecartItem;
import net.minecraft.world.item.Item;

import java.util.function.IntSupplier;

import static cofh.lib.util.constants.ModIds.ID_THERMAL_LOCOMOTION;
import static cofh.thermal.core.init.registries.ThermalCreativeTabs.toolsTab;
import static cofh.thermal.core.util.RegistrationHelper.registerItem;
import static cofh.thermal.lib.util.ThermalAugmentRules.ENERGY_STORAGE_VALIDATOR;
import static cofh.thermal.locomotion.init.registries.TLocIDs.*;

public class TLocItems {

    private TLocItems() {

    }

    public static void register() {

        IntSupplier storageAugs = () -> ThermalCoreConfig.storageAugments;

        toolsTab(200, registerItem(ID_UNDERWATER_CART, () -> new UnderwaterMinecartItem(new Item.Properties()).setModId(ID_THERMAL_LOCOMOTION)));
        toolsTab(200, registerItem(ID_ENERGY_CART, () -> new EnergyMinecartItem(new Item.Properties()).setNumSlots(storageAugs).setAugValidator(ENERGY_STORAGE_VALIDATOR).setModId(ID_THERMAL_LOCOMOTION)));
        toolsTab(200, registerItem(ID_FLUID_CART, () -> new FluidMinecartItem(new Item.Properties()).setNumSlots(storageAugs).setAugValidator(FluidMinecart.AUG_VALIDATOR).setModId(ID_THERMAL_LOCOMOTION)));
    }

}
