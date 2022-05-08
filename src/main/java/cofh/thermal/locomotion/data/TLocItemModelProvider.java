package cofh.thermal.locomotion.data;

import cofh.lib.data.ItemModelProviderCoFH;
import cofh.lib.util.DeferredRegisterCoFH;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.locomotion.init.TLocIDs.*;

public class TLocItemModelProvider extends ItemModelProviderCoFH {

    public TLocItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_THERMAL, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        DeferredRegisterCoFH<Item> reg = ITEMS;

        generated(reg.getSup(ID_UNDERWATER_CART));
        generated(reg.getSup(ID_ENERGY_CART));
        generated(reg.getSup(ID_FLUID_CART));

        generated(reg.getSup(ID_SLIME_TNT_CART));
        generated(reg.getSup(ID_REDSTONE_TNT_CART));
        generated(reg.getSup(ID_GLOWSTONE_TNT_CART));
        generated(reg.getSup(ID_ENDER_TNT_CART));

        generated(reg.getSup(ID_PHYTO_TNT_CART));

        generated(reg.getSup(ID_FIRE_TNT_CART));
        generated(reg.getSup(ID_EARTH_TNT_CART));
        generated(reg.getSup(ID_ICE_TNT_CART));
        generated(reg.getSup(ID_LIGHTNING_TNT_CART));

        generated(reg.getSup(ID_NUKE_TNT_CART));
    }

    @Override
    public String getName() {

        return "Thermal Locomotion: Item Models";
    }

}
