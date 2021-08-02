package cofh.thermal.locomotion.data;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.locomotion.init.TLocIDs.*;

public class TLocTagsProvider {

    public static class Block extends BlockTagsProvider {

        public Block(DataGenerator gen, ExistingFileHelper existingFileHelper) {

            super(gen, ID_THERMAL, existingFileHelper);
        }

        @Override
        public String getName() {

            return "Thermal Locomotion: Block Tags";
        }

        @Override
        protected void registerTags() {

            getOrCreateBuilder(BlockTags.RAILS).add(
                    BLOCKS.get(ID_CROSSOVER_RAIL),

                    BLOCKS.get(ID_PRISMARINE_RAIL),
                    BLOCKS.get(ID_PRISMARINE_CROSSOVER_RAIL),
                    BLOCKS.get(ID_PRISMARINE_POWERED_RAIL),
                    BLOCKS.get(ID_PRISMARINE_ACTIVATOR_RAIL),
                    BLOCKS.get(ID_PRISMARINE_DETECTOR_RAIL),

                    BLOCKS.get(ID_LUMIUM_RAIL),
                    BLOCKS.get(ID_LUMIUM_CROSSOVER_RAIL),
                    BLOCKS.get(ID_LUMIUM_POWERED_RAIL),
                    BLOCKS.get(ID_LUMIUM_ACTIVATOR_RAIL),
                    BLOCKS.get(ID_LUMIUM_DETECTOR_RAIL)
            );
        }

    }

    public static class Item extends ItemTagsProvider {

        public Item(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {

            super(gen, blockTagProvider, ID_THERMAL, existingFileHelper);
        }

        @Override
        public String getName() {

            return "Thermal Locomotion: Item Tags";
        }

        @Override
        protected void registerTags() {

        }

    }

    public static class Fluid extends FluidTagsProvider {

        public Fluid(DataGenerator gen, ExistingFileHelper existingFileHelper) {

            super(gen, ID_THERMAL, existingFileHelper);
        }

        @Override
        public String getName() {

            return "Thermal Locomotion: Fluid Tags";
        }

        @Override
        protected void registerTags() {

        }

    }

}
