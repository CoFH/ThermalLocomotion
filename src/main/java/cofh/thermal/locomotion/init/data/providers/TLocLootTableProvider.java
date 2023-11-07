package cofh.thermal.locomotion.init.data.providers;

import cofh.lib.init.data.LootTableProviderCoFH;
import cofh.thermal.locomotion.init.data.tables.TLocBlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

public class TLocLootTableProvider extends LootTableProviderCoFH {

    public TLocLootTableProvider(PackOutput output) {

        super(output, List.of(
                new SubProviderEntry(TLocBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }

}
