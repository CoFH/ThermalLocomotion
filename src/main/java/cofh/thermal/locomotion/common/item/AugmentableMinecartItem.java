package cofh.thermal.locomotion.common.item;

import cofh.core.common.entity.AbstractMinecartCoFH;
import cofh.core.common.item.IAugmentableItem;
import cofh.core.common.item.MinecartItemCoFH;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntSupplier;

import static cofh.thermal.lib.util.ThermalAugmentRules.FILTER_VALIDATOR;
import static cofh.thermal.lib.util.ThermalAugmentRules.UPGRADE_VALIDATOR;

public class AugmentableMinecartItem extends MinecartItemCoFH implements IAugmentableItem {

    protected IntSupplier numSlots = () -> 0;
    protected BiPredicate<ItemStack, List<ItemStack>> augValidator = (e, f) -> true;

    public AugmentableMinecartItem(IMinecartFactory<? extends AbstractMinecartCoFH> factory, Properties builder) {

        super(factory, builder);
    }

    public AugmentableMinecartItem setNumSlots(IntSupplier numSlots) {

        this.numSlots = numSlots;
        return this;
    }

    public AugmentableMinecartItem setAugValidator(BiPredicate<ItemStack, List<ItemStack>> augValidator) {

        this.augValidator = augValidator;
        return this;
    }

    // region IAugmentableItem
    @Override
    public int getAugmentSlots(ItemStack augmentable) {

        return numSlots.getAsInt();
    }

    @Override
    public boolean hasUpgradeSlot() {

        return true;
    }

    @Override
    public boolean hasFilterSlot() {

        return true;
    }

    @Override
    public boolean validAugment(int index, ItemStack augmentable, ItemStack augment, List<ItemStack> augments) {

        if (index == 0) {
            if (hasUpgradeSlot()) {
                return UPGRADE_VALIDATOR.test(augment, augments);
            } else if (hasFilterSlot()) {
                return FILTER_VALIDATOR.test(augment, augments);
            }
        } else if (index == 1) {
            if (hasUpgradeSlot() && hasFilterSlot()) {
                return FILTER_VALIDATOR.test(augment, augments);
            }
        }
        return augValidator.test(augment, augments);
    }

    @Override
    public void updateAugmentState(ItemStack augmentable, List<ItemStack> augments) {

    }
    // endregion
}
