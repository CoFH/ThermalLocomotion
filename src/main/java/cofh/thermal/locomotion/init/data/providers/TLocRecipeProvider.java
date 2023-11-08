package cofh.thermal.locomotion.init.data.providers;

import cofh.lib.init.data.RecipeProviderCoFH;
import cofh.lib.init.tags.ItemTagsCoFH;
import cofh.thermal.lib.util.ThermalFlags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.lib.util.ThermalFlags.*;
import static cofh.thermal.lib.util.ThermalIDs.*;
import static cofh.thermal.locomotion.init.registries.TLocIDs.*;
import static net.minecraft.data.recipes.RecipeCategory.MISC;
import static net.minecraft.data.recipes.RecipeCategory.TOOLS;

public class TLocRecipeProvider extends RecipeProviderCoFH {

    public TLocRecipeProvider(PackOutput output) {

        super(output, ID_THERMAL);
        manager = ThermalFlags.manager();
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        registerRailRecipes(consumer);
        registerCartRecipes(consumer);
    }

    // region HELPERS
    private void registerRailRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(MISC, BLOCKS.get(ID_CROSSOVER_RAIL))
                .define('I', Items.STICK)
                .define('X', Blocks.RAIL)
                .pattern("XI")
                .pattern("IX")
                .unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MISC, BLOCKS.get(ID_PRISMARINE_RAIL), 6)
                .define('C', Items.PRISMARINE_CRYSTALS)
                .define('S', Items.PRISMARINE_SHARD)
                .define('X', Blocks.RAIL)
                .pattern("XSX")
                .pattern("XCX")
                .pattern("XSX")
                .unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MISC, BLOCKS.get(ID_PRISMARINE_CROSSOVER_RAIL))
                .define('I', Items.STICK)
                .define('X', BLOCKS.get(ID_PRISMARINE_RAIL))
                .pattern("XI")
                .pattern("IX")
                .unlockedBy(getHasName(BLOCKS.get(ID_PRISMARINE_RAIL)), has(ITEMS.get(ID_PRISMARINE_RAIL)))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MISC, BLOCKS.get(ID_PRISMARINE_ACTIVATOR_RAIL), 6)
                .define('C', Items.PRISMARINE_CRYSTALS)
                .define('S', Items.PRISMARINE_SHARD)
                .define('X', Blocks.ACTIVATOR_RAIL)
                .pattern("XSX")
                .pattern("XCX")
                .pattern("XSX")
                .unlockedBy(getHasName(Items.ACTIVATOR_RAIL), has(Items.ACTIVATOR_RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MISC, BLOCKS.get(ID_PRISMARINE_DETECTOR_RAIL), 6)
                .define('C', Items.PRISMARINE_CRYSTALS)
                .define('S', Items.PRISMARINE_SHARD)
                .define('X', Blocks.DETECTOR_RAIL)
                .pattern("XSX")
                .pattern("XCX")
                .pattern("XSX")
                .unlockedBy(getHasName(Items.DETECTOR_RAIL), has(Items.DETECTOR_RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MISC, BLOCKS.get(ID_PRISMARINE_POWERED_RAIL), 6)
                .define('C', Items.PRISMARINE_CRYSTALS)
                .define('S', Items.PRISMARINE_SHARD)
                .define('X', Blocks.POWERED_RAIL)
                .pattern("XSX")
                .pattern("XCX")
                .pattern("XSX")
                .unlockedBy(getHasName(Items.POWERED_RAIL), has(Items.POWERED_RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MISC, BLOCKS.get(ID_LUMIUM_RAIL), 6)
                .define('I', ItemTagsCoFH.INGOTS_LUMIUM)
                .define('X', Blocks.RAIL)
                .pattern("XIX")
                .pattern("XIX")
                .pattern("XIX")
                .unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MISC, BLOCKS.get(ID_LUMIUM_CROSSOVER_RAIL))
                .define('I', Items.STICK)
                .define('X', BLOCKS.get(ID_LUMIUM_RAIL))
                .pattern("XI")
                .pattern("IX")
                .unlockedBy(getHasName(BLOCKS.get(ID_LUMIUM_RAIL)), has(ITEMS.get(ID_LUMIUM_RAIL)))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MISC, BLOCKS.get(ID_LUMIUM_ACTIVATOR_RAIL), 6)
                .define('I', ItemTagsCoFH.INGOTS_LUMIUM)
                .define('X', Blocks.ACTIVATOR_RAIL)
                .pattern("XIX")
                .pattern("XIX")
                .pattern("XIX")
                .unlockedBy(getHasName(Items.ACTIVATOR_RAIL), has(Items.ACTIVATOR_RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MISC, BLOCKS.get(ID_LUMIUM_DETECTOR_RAIL), 6)
                .define('I', ItemTagsCoFH.INGOTS_LUMIUM)
                .define('X', Blocks.DETECTOR_RAIL)
                .pattern("XIX")
                .pattern("XIX")
                .pattern("XIX")
                .unlockedBy(getHasName(Items.DETECTOR_RAIL), has(Items.DETECTOR_RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(MISC, BLOCKS.get(ID_LUMIUM_POWERED_RAIL), 6)
                .define('I', ItemTagsCoFH.INGOTS_LUMIUM)
                .define('X', Blocks.POWERED_RAIL)
                .pattern("XIX")
                .pattern("XIX")
                .pattern("XIX")
                .unlockedBy(getHasName(Items.POWERED_RAIL), has(Items.POWERED_RAIL))
                .save(consumer);
    }

    private void registerCartRecipes(Consumer<FinishedRecipe> consumer) {

        var reg = ITEMS;

        ShapedRecipeBuilder.shaped(TOOLS, ITEMS.get(ID_UNDERWATER_CART))
                .define('C', Items.MINECART)
                .define('G', Tags.Items.GLASS)
                .define('P', Items.PRISMARINE)
                .pattern("GGG")
                .pattern("GCG")
                .pattern("PPP")
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TOOLS, ITEMS.get(ID_ENERGY_CART))
                .define('C', Items.MINECART)
                .define('F', reg.get(ID_ENERGY_CELL_FRAME))
                .define('I', ItemTagsCoFH.INGOTS_LEAD)
                .define('R', Tags.Items.STORAGE_BLOCKS_REDSTONE)
                .pattern(" F ")
                .pattern("ICI")
                .pattern(" R ")
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TOOLS, ITEMS.get(ID_FLUID_CART))
                .define('C', Items.MINECART)
                .define('F', reg.get(ID_FLUID_CELL_FRAME))
                .define('I', ItemTagsCoFH.INGOTS_BRONZE)
                .define('R', reg.get("cured_rubber"))
                .pattern(" F ")
                .pattern("ICI")
                .pattern(" R ")
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .save(consumer);

        ShapedRecipeBuilder.shaped(TOOLS, ITEMS.get(ID_SLIME_TNT_CART))
                .define('A', ITEMS.get(ID_SLIME_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_BASIC_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(TOOLS, ITEMS.get(ID_REDSTONE_TNT_CART))
                .define('A', ITEMS.get(ID_REDSTONE_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_BASIC_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(TOOLS, ITEMS.get(ID_GLOWSTONE_TNT_CART))
                .define('A', ITEMS.get(ID_GLOWSTONE_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_BASIC_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(TOOLS, ITEMS.get(ID_ENDER_TNT_CART))
                .define('A', ITEMS.get(ID_ENDER_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_BASIC_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(TOOLS, ITEMS.get(ID_PHYTO_TNT_CART))
                .define('A', ITEMS.get(ID_PHYTO_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_PHYTOGRO_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(TOOLS, ITEMS.get(ID_FIRE_TNT_CART))
                .define('A', ITEMS.get(ID_FIRE_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_ELEMENTAL_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(TOOLS, ITEMS.get(ID_EARTH_TNT_CART))
                .define('A', ITEMS.get(ID_EARTH_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_ELEMENTAL_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(TOOLS, ITEMS.get(ID_ICE_TNT_CART))
                .define('A', ITEMS.get(ID_ICE_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_ELEMENTAL_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(TOOLS, ITEMS.get(ID_LIGHTNING_TNT_CART))
                .define('A', ITEMS.get(ID_LIGHTNING_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_ELEMENTAL_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(TOOLS, ITEMS.get(ID_NUKE_TNT_CART))
                .define('A', ITEMS.get(ID_NUKE_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_NUCLEAR_EXPLOSIVES));
    }
    // endregion
}
