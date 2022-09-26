package cofh.thermal.locomotion.item;

import cofh.core.item.MinecartItemCoFH;
import cofh.thermal.locomotion.entity.FluidMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;

import static cofh.core.util.references.CoreReferences.HOLDING;

public class FluidMinecartItem extends MinecartItemCoFH {

    public FluidMinecartItem(Properties builder) {

        super(FluidMinecart::new, builder);
        setEnchantability(10);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

        return enchantment == HOLDING;
    }

}
