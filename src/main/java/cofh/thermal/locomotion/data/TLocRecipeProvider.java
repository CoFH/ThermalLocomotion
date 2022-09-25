package cofh.thermal.locomotion.data;

import cofh.lib.data.RecipeProviderCoFH;
import cofh.lib.util.references.CoFHTags;
import cofh.thermal.lib.common.ThermalFlags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.lib.common.ThermalFlags.*;
import static cofh.thermal.lib.common.ThermalIDs.*;
import static cofh.thermal.locomotion.init.TLocIDs.*;

public class TLocRecipeProvider extends RecipeProviderCoFH {

    public TLocRecipeProvider(DataGenerator generatorIn) {

        super(generatorIn, ID_THERMAL);
        manager = ThermalFlags.manager();
    }

    @Override
    public String getName() {

        return "Thermal Locomotion: Recipes";
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        registerRailRecipes(consumer);
        registerCartRecipes(consumer);
    }

    // region HELPERS
    private void registerRailRecipes(Consumer<FinishedRecipe> consumer) {

        String rail = name(Items.RAIL);
        String activatorRail = name(Items.ACTIVATOR_RAIL);
        String detectorRail = name(Items.DETECTOR_RAIL);
        String poweredRail = name(Items.POWERED_RAIL);

        ShapedRecipeBuilder.shaped(BLOCKS.get(ID_CROSSOVER_RAIL))
                .define('I', Items.STICK)
                .define('X', Blocks.RAIL)
                .pattern("XI")
                .pattern("IX")
                .unlockedBy("has_" + rail, has(Items.RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BLOCKS.get(ID_PRISMARINE_RAIL), 6)
                .define('C', Items.PRISMARINE_CRYSTALS)
                .define('S', Items.PRISMARINE_SHARD)
                .define('X', Blocks.RAIL)
                .pattern("XSX")
                .pattern("XCX")
                .pattern("XSX")
                .unlockedBy("has_" + rail, has(Items.RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BLOCKS.get(ID_PRISMARINE_CROSSOVER_RAIL))
                .define('I', Items.STICK)
                .define('X', BLOCKS.get(ID_PRISMARINE_RAIL))
                .pattern("XI")
                .pattern("IX")
                .unlockedBy("has_" + name(BLOCKS.get(ID_PRISMARINE_RAIL)), has(ITEMS.get(ID_PRISMARINE_RAIL)))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BLOCKS.get(ID_PRISMARINE_ACTIVATOR_RAIL), 6)
                .define('C', Items.PRISMARINE_CRYSTALS)
                .define('S', Items.PRISMARINE_SHARD)
                .define('X', Blocks.ACTIVATOR_RAIL)
                .pattern("XSX")
                .pattern("XCX")
                .pattern("XSX")
                .unlockedBy("has_" + activatorRail, has(Items.ACTIVATOR_RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BLOCKS.get(ID_PRISMARINE_DETECTOR_RAIL), 6)
                .define('C', Items.PRISMARINE_CRYSTALS)
                .define('S', Items.PRISMARINE_SHARD)
                .define('X', Blocks.DETECTOR_RAIL)
                .pattern("XSX")
                .pattern("XCX")
                .pattern("XSX")
                .unlockedBy("has_" + detectorRail, has(Items.DETECTOR_RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BLOCKS.get(ID_PRISMARINE_POWERED_RAIL), 6)
                .define('C', Items.PRISMARINE_CRYSTALS)
                .define('S', Items.PRISMARINE_SHARD)
                .define('X', Blocks.POWERED_RAIL)
                .pattern("XSX")
                .pattern("XCX")
                .pattern("XSX")
                .unlockedBy("has_" + poweredRail, has(Items.POWERED_RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BLOCKS.get(ID_LUMIUM_RAIL), 6)
                .define('I', CoFHTags.Items.INGOTS_LUMIUM)
                .define('X', Blocks.RAIL)
                .pattern("XIX")
                .pattern("XIX")
                .pattern("XIX")
                .unlockedBy("has_" + rail, has(Items.RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BLOCKS.get(ID_LUMIUM_CROSSOVER_RAIL))
                .define('I', Items.STICK)
                .define('X', BLOCKS.get(ID_LUMIUM_RAIL))
                .pattern("XI")
                .pattern("IX")
                .unlockedBy("has_" + name(BLOCKS.get(ID_LUMIUM_RAIL)), has(ITEMS.get(ID_LUMIUM_RAIL)))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BLOCKS.get(ID_LUMIUM_ACTIVATOR_RAIL), 6)
                .define('I', CoFHTags.Items.INGOTS_LUMIUM)
                .define('X', Blocks.ACTIVATOR_RAIL)
                .pattern("XIX")
                .pattern("XIX")
                .pattern("XIX")
                .unlockedBy("has_" + activatorRail, has(Items.ACTIVATOR_RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BLOCKS.get(ID_LUMIUM_DETECTOR_RAIL), 6)
                .define('I', CoFHTags.Items.INGOTS_LUMIUM)
                .define('X', Blocks.DETECTOR_RAIL)
                .pattern("XIX")
                .pattern("XIX")
                .pattern("XIX")
                .unlockedBy("has_" + detectorRail, has(Items.DETECTOR_RAIL))
                .save(consumer);

        ShapedRecipeBuilder.shaped(BLOCKS.get(ID_LUMIUM_POWERED_RAIL), 6)
                .define('I', CoFHTags.Items.INGOTS_LUMIUM)
                .define('X', Blocks.POWERED_RAIL)
                .pattern("XIX")
                .pattern("XIX")
                .pattern("XIX")
                .unlockedBy("has_" + poweredRail, has(Items.POWERED_RAIL))
                .save(consumer);
    }

    private void registerCartRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(ITEMS.get(ID_UNDERWATER_CART))
                .define('C', Items.MINECART)
                .define('G', Tags.Items.GLASS)
                .define('P', Items.PRISMARINE)
                .pattern("GGG")
                .pattern("GCG")
                .pattern("PPP")
                .unlockedBy("has_" + name(Items.MINECART), has(Items.MINECART))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ITEMS.get(ID_SLIME_TNT_CART))
                .define('A', ITEMS.get(ID_SLIME_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_" + name(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_BASIC_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(ITEMS.get(ID_REDSTONE_TNT_CART))
                .define('A', ITEMS.get(ID_REDSTONE_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_" + name(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_BASIC_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(ITEMS.get(ID_GLOWSTONE_TNT_CART))
                .define('A', ITEMS.get(ID_GLOWSTONE_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_" + name(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_BASIC_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(ITEMS.get(ID_ENDER_TNT_CART))
                .define('A', ITEMS.get(ID_ENDER_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_" + name(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_BASIC_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(ITEMS.get(ID_PHYTO_TNT_CART))
                .define('A', ITEMS.get(ID_PHYTO_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_" + name(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_PHYTOGRO_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(ITEMS.get(ID_FIRE_TNT_CART))
                .define('A', ITEMS.get(ID_FIRE_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_" + name(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_ELEMENTAL_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(ITEMS.get(ID_EARTH_TNT_CART))
                .define('A', ITEMS.get(ID_EARTH_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_" + name(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_ELEMENTAL_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(ITEMS.get(ID_ICE_TNT_CART))
                .define('A', ITEMS.get(ID_ICE_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_" + name(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_ELEMENTAL_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(ITEMS.get(ID_LIGHTNING_TNT_CART))
                .define('A', ITEMS.get(ID_LIGHTNING_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_" + name(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_ELEMENTAL_EXPLOSIVES));

        ShapedRecipeBuilder.shaped(ITEMS.get(ID_NUKE_TNT_CART))
                .define('A', ITEMS.get(ID_NUKE_TNT))
                .define('B', Items.MINECART)
                .pattern("A")
                .pattern("B")
                .unlockedBy("has_" + name(Items.MINECART), has(Items.MINECART))
                .save(withConditions(consumer).flag(FLAG_NUCLEAR_EXPLOSIVES));
    }
    // endregion
}
