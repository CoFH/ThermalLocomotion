package cofh.thermal.locomotion.init;

import cofh.thermal.locomotion.entity.EnergyMinecart;
import cofh.thermal.locomotion.entity.FluidMinecart;
import cofh.thermal.locomotion.entity.UnderwaterMinecartEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.locomotion.init.TLocIDs.*;

@ObjectHolder (ID_THERMAL)
public class TLocReferences {

    private TLocReferences() {

    }

    // region ENTITIES
    @ObjectHolder (ID_UNDERWATER_CART)
    public static final EntityType<UnderwaterMinecartEntity> UNDERWATER_CART_ENTITY = null;

    @ObjectHolder (ID_ENERGY_CART)
    public static final EntityType<EnergyMinecart> ENERGY_CART_ENTITY = null;

    @ObjectHolder (ID_FLUID_CART)
    public static final EntityType<FluidMinecart> FLUID_CART_ENTITY = null;
    // endregion

    // region ITEMS
    @ObjectHolder (ID_UNDERWATER_CART)
    public static final Item UNDERWATER_CART_ITEM = null;

    @ObjectHolder (ID_ENERGY_CART)
    public static final Item ENERGY_CART_ITEM = null;

    @ObjectHolder (ID_FLUID_CART)
    public static final Item FLUID_CART_ITEM = null;
    // endregion
}
