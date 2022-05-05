package cofh.thermal.locomotion.item;

import cofh.core.item.MinecartItemCoFH;
import cofh.thermal.locomotion.entity.FluidMinecartEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

import static cofh.lib.util.references.CoreReferences.HOLDING;

public class FluidMinecartItem extends MinecartItemCoFH {

    public FluidMinecartItem(Properties builder) {

        super(FluidMinecartEntity::new, builder);
        setEnchantability(10);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

        return enchantment == HOLDING;
    }

}
