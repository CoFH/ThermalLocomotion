//package cofh.thermal.locomotion.data;
//
//import net.minecraft.core.HolderLookup;
//import net.minecraft.data.PackOutput;
//import net.minecraft.data.tags.BlockTagsProvider;
//import net.minecraft.data.tags.ItemTagsProvider;
//import net.minecraft.data.tags.TagsProvider;
//import net.minecraft.tags.BlockTags;
//import net.minecraftforge.common.data.ExistingFileHelper;
//
//import static cofh.lib.util.constants.ModIds.ID_THERMAL;
//import static cofh.thermal.core.ThermalCore.BLOCKS;
//import static cofh.thermal.locomotion.init.TLocIDs.*;
//
//public class TLocTagsProvider {
//
//    public static class Block extends BlockTagsProvider {
//
//        public Block(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
//
//            super(output, lookupProvider, ID_THERMAL, existingFileHelper);
//        }
//
//        @Override
//        public String getName() {
//
//            return "Thermal Locomotion: Block Tags";
//        }
//
//        @Override
//        protected void addTags(HolderLookup.Provider pProvider) {
//
//            tag(BlockTags.RAILS).add(
//                    BLOCKS.get(ID_CROSSOVER_RAIL),
//
//                    BLOCKS.get(ID_PRISMARINE_RAIL),
//                    BLOCKS.get(ID_PRISMARINE_CROSSOVER_RAIL),
//                    BLOCKS.get(ID_PRISMARINE_POWERED_RAIL),
//                    BLOCKS.get(ID_PRISMARINE_ACTIVATOR_RAIL),
//                    BLOCKS.get(ID_PRISMARINE_DETECTOR_RAIL),
//
//                    BLOCKS.get(ID_LUMIUM_RAIL),
//                    BLOCKS.get(ID_LUMIUM_CROSSOVER_RAIL),
//                    BLOCKS.get(ID_LUMIUM_POWERED_RAIL),
//                    BLOCKS.get(ID_LUMIUM_ACTIVATOR_RAIL),
//                    BLOCKS.get(ID_LUMIUM_DETECTOR_RAIL)
//            );
//        }
//
//    }
//
//    public static class Item extends ItemTagsProvider {
//
//        public Item(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagsProvider.TagLookup<net.minecraft.world.level.block.Block>> pBlockTags, ExistingFileHelper existingFileHelper) {
//
//            super(pOutput, pLookupProvider, pBlockTags, ID_THERMAL, existingFileHelper);
//        }
//
//        @Override
//        public String getName() {
//
//            return "Thermal Locomotion: Item Tags";
//        }
//
//        @Override
//        protected void addTags(HolderLookup.Provider pProvider) {
//
//        }
//
//    }
//
//}
