package cofh.thermal.locomotion.init.data.providers;

import cofh.lib.init.data.ItemModelProviderCoFH;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.locomotion.init.registries.TLocIDs.*;

public class TLocItemModelProvider extends ItemModelProviderCoFH {

    public TLocItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {

        super(output, ID_THERMAL, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        var reg = ITEMS;

        generated(reg.getSup(ID_UNDERWATER_CART));
        generated(reg.getSup(ID_ENERGY_CART));

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

}