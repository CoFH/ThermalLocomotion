package cofh.thermal.locomotion.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cofh.lib.util.constants.ModIds.ID_THERMAL_LOCOMOTION;

@Mod.EventBusSubscriber (bus = Mod.EventBusSubscriber.Bus.MOD, modid = ID_THERMAL_LOCOMOTION)
public class TLocDataGen {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {

        DataGenerator gen = event.getGenerator();
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();

        TLocTagsProvider.Block blockTags = new TLocTagsProvider.Block(gen, exFileHelper);

        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new TLocTagsProvider.Item(gen, blockTags, exFileHelper));

        gen.addProvider(event.includeServer(), new TLocLootTableProvider(gen));
        gen.addProvider(event.includeServer(), new TLocRecipeProvider(gen));

        gen.addProvider(event.includeClient(), new TLocBlockStateProvider(gen, exFileHelper));
        gen.addProvider(event.includeClient(), new TLocItemModelProvider(gen, exFileHelper));
    }

}
