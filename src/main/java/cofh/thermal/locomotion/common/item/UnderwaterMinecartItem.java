package cofh.thermal.locomotion.common.item;

import cofh.core.common.item.MinecartItemCoFH;
import cofh.thermal.locomotion.common.entity.UnderwaterMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

public class UnderwaterMinecartItem extends MinecartItemCoFH {

    public UnderwaterMinecartItem(Properties builder) {

        super(UnderwaterMinecart::new, builder);
        setEnchantability(10);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {

        return enchantment == Enchantments.RESPIRATION;
    }

}
