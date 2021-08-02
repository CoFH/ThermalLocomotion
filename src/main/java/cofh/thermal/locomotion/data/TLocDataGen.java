package cofh.thermal.locomotion.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import static cofh.lib.util.constants.Constants.ID_THERMAL_LOCOMOTION;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ID_THERMAL_LOCOMOTION)
public class TLocDataGen {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {

        if (event.includeServer()) {
            registerServerProviders(event);
        }
        if (event.includeClient()) {
            registerClientProviders(event);
        }
    }

    private static void registerServerProviders(GatherDataEvent event) {

        DataGenerator gen = event.getGenerator();
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();

        TLocTagsProvider.Block blockTags = new TLocTagsProvider.Block(gen, exFileHelper);

        gen.addProvider(blockTags);
        gen.addProvider(new TLocTagsProvider.Item(gen, blockTags, exFileHelper));
        gen.addProvider(new TLocTagsProvider.Fluid(gen, exFileHelper));

        gen.addProvider(new TLocLootTableProvider(gen));
        gen.addProvider(new TLocRecipeProvider(gen));
    }

    private static void registerClientProviders(GatherDataEvent event) {

        DataGenerator gen = event.getGenerator();
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();

        gen.addProvider(new TLocBlockStateProvider(gen, exFileHelper));
        gen.addProvider(new TLocItemModelProvider(gen, exFileHelper));
    }

}
