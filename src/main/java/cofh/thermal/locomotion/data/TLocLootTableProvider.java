package cofh.thermal.locomotion.data;

import cofh.lib.data.LootTableProviderCoFH;
import net.minecraft.data.DataGenerator;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.locomotion.init.TLocIDs.*;

public class TLocLootTableProvider extends LootTableProviderCoFH {

    public TLocLootTableProvider(DataGenerator gen) {

        super(gen);
    }

    @Override
    public String getName() {

        return "Thermal Locomotion: Loot Tables";
    }

    @Override
    protected void addTables() {

        var regBlocks = BLOCKS;
        var regItems = ITEMS;

        createSimpleDropTable(regBlocks.get(ID_CROSSOVER_RAIL));

        createSimpleDropTable(regBlocks.get(ID_PRISMARINE_RAIL));
        createSimpleDropTable(regBlocks.get(ID_PRISMARINE_CROSSOVER_RAIL));
        createSimpleDropTable(regBlocks.get(ID_PRISMARINE_POWERED_RAIL));
        createSimpleDropTable(regBlocks.get(ID_PRISMARINE_ACTIVATOR_RAIL));
        createSimpleDropTable(regBlocks.get(ID_PRISMARINE_DETECTOR_RAIL));

        createSimpleDropTable(regBlocks.get(ID_LUMIUM_RAIL));
        createSimpleDropTable(regBlocks.get(ID_LUMIUM_CROSSOVER_RAIL));
        createSimpleDropTable(regBlocks.get(ID_LUMIUM_POWERED_RAIL));
        createSimpleDropTable(regBlocks.get(ID_LUMIUM_ACTIVATOR_RAIL));
        createSimpleDropTable(regBlocks.get(ID_LUMIUM_DETECTOR_RAIL));
    }

}
