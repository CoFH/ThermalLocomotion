package cofh.thermal.locomotion.init;

import cofh.thermal.lib.common.ThermalItemGroups;
import cofh.thermal.locomotion.item.UnderwaterMinecartItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import static cofh.lib.util.constants.Constants.ID_THERMAL_LOCOMOTION;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.locomotion.init.TLocIDs.ID_UNDERWATER_CART;

public class TLocItems {

    private TLocItems() {

    }

    public static void register() {

        CreativeModeTab group = ThermalItemGroups.THERMAL_TOOLS;

        ITEMS.register(ID_UNDERWATER_CART, () -> new UnderwaterMinecartItem(new Item.Properties().tab(group)).setModId(ID_THERMAL_LOCOMOTION));
        //        ITEMS.register(ID_ENERGY_CART, () -> new EnergyMinecartItem(new Item.Properties().tab(group)).setModId(ID_THERMAL_LOCOMOTION));
        //        ITEMS.register(ID_FLUID_CART, () -> new FluidMinecartItem(new Item.Properties().tab(group)).setModId(ID_THERMAL_LOCOMOTION));
    }

}
