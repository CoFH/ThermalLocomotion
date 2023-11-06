package cofh.thermal.locomotion.init;

import cofh.thermal.core.entity.explosive.DetonateUtils;
import cofh.thermal.locomotion.entity.EnergyMinecart;
import cofh.thermal.locomotion.entity.FluidMinecart;
import cofh.thermal.locomotion.entity.UnderwaterMinecart;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.RegistryObject;

import static cofh.thermal.core.ThermalCore.ENTITIES;
import static cofh.thermal.core.util.RegistrationHelper.registerTNTMinecart;
import static cofh.thermal.lib.common.ThermalCreativeTabs.toolsTab;
import static cofh.thermal.lib.common.ThermalFlags.*;
import static cofh.thermal.lib.common.ThermalIDs.*;
import static cofh.thermal.locomotion.init.TLocIDs.*;

public class TLocEntities {

    private TLocEntities() {

    }

    public static void register() {

        toolsTab(201, registerTNTMinecart(ID_FIRE_TNT_CART, ID_FIRE_TNT, DetonateUtils::fire, getFlag(FLAG_ELEMENTAL_EXPLOSIVES)));
        toolsTab(201, registerTNTMinecart(ID_ICE_TNT_CART, ID_ICE_TNT, DetonateUtils::ice, getFlag(FLAG_ELEMENTAL_EXPLOSIVES)));
        toolsTab(201, registerTNTMinecart(ID_LIGHTNING_TNT_CART, ID_LIGHTNING_TNT, DetonateUtils::lightning, getFlag(FLAG_ELEMENTAL_EXPLOSIVES)));
        toolsTab(201, registerTNTMinecart(ID_EARTH_TNT_CART, ID_EARTH_TNT, DetonateUtils::earth, getFlag(FLAG_ELEMENTAL_EXPLOSIVES)));

        toolsTab(201, registerTNTMinecart(ID_ENDER_TNT_CART, ID_ENDER_TNT, DetonateUtils::ender, getFlag(FLAG_BASIC_EXPLOSIVES)));
        toolsTab(201, registerTNTMinecart(ID_GLOWSTONE_TNT_CART, ID_GLOWSTONE_TNT, DetonateUtils::glow, getFlag(FLAG_BASIC_EXPLOSIVES)));
        toolsTab(201, registerTNTMinecart(ID_REDSTONE_TNT_CART, ID_REDSTONE_TNT, DetonateUtils::redstone, getFlag(FLAG_BASIC_EXPLOSIVES)));
        toolsTab(201, registerTNTMinecart(ID_SLIME_TNT_CART, ID_SLIME_TNT, DetonateUtils::slime, getFlag(FLAG_BASIC_EXPLOSIVES)));

        toolsTab(201, registerTNTMinecart(ID_PHYTO_TNT_CART, ID_PHYTO_TNT, DetonateUtils::phyto, getFlag(FLAG_PHYTOGRO_EXPLOSIVES)));
        toolsTab(201, registerTNTMinecart(ID_NUKE_TNT_CART, ID_NUKE_TNT, DetonateUtils::nuke, getFlag(FLAG_NUCLEAR_EXPLOSIVES)));
    }

    public static final RegistryObject<EntityType<UnderwaterMinecart>> UNDERWATER_CART = ENTITIES.register(ID_UNDERWATER_CART, () -> EntityType.Builder.<UnderwaterMinecart>of(UnderwaterMinecart::new, MobCategory.MISC).sized(0.98F, 0.7F).build(ID_UNDERWATER_CART));
    public static final RegistryObject<EntityType<EnergyMinecart>> ENERGY_CART = ENTITIES.register(ID_ENERGY_CART, () -> EntityType.Builder.<EnergyMinecart>of(EnergyMinecart::new, MobCategory.MISC).sized(0.98F, 0.7F).build(ID_ENERGY_CART));
    public static final RegistryObject<EntityType<FluidMinecart>> FLUID_CART = ENTITIES.register(ID_FLUID_CART, () -> EntityType.Builder.<FluidMinecart>of(FluidMinecart::new, MobCategory.MISC).sized(0.98F, 0.7F).build(ID_FLUID_CART));

}
