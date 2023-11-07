package cofh.thermal.locomotion.init.data.providers;

import cofh.lib.init.data.BlockStateProviderCoFH;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class TLocBlockStateProvider extends BlockStateProviderCoFH {

    public TLocBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {

        super(output, ID_THERMAL, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

    }

}
