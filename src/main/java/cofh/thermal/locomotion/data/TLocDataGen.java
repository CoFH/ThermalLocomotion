package cofh.thermal.locomotion.data;

import cofh.thermal.locomotion.data.providers.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
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
        PackOutput output = gen.getPackOutput();
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();

        TLocTagsProvider.Block blockTags = new TLocTagsProvider.Block(output, event.getLookupProvider(), exFileHelper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new TLocTagsProvider.Item(output, event.getLookupProvider(), blockTags.contentsGetter(), exFileHelper));

        gen.addProvider(event.includeServer(), new TLocLootTableProvider(output));
        gen.addProvider(event.includeServer(), new TLocRecipeProvider(output));

        gen.addProvider(event.includeClient(), new TLocBlockStateProvider(output, exFileHelper));
        gen.addProvider(event.includeClient(), new TLocItemModelProvider(output, exFileHelper));
    }

}
