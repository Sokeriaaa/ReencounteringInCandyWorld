package sokeriaaa.candyworld.platform.neoforge.datagen.providers

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.FluidTagsProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.blocks.fluid.ModFluids
import sokeriaaa.candyworld.registry.ModTags
import java.util.concurrent.CompletableFuture

class CandyFluidTags(
    output: PackOutput,
    provider: CompletableFuture<HolderLookup.Provider>,
    existingFileHelper: ExistingFileHelper?,
) : FluidTagsProvider(
    output,
    provider,
    CandyWorld.MOD_ID,
    existingFileHelper,
) {

    override fun addTags(provider: HolderLookup.Provider) {
        this.tag(ModTags.CANDY).add(
            ModFluids.LIQUID_CHOCOLATE_SOURCE.value,
            ModFluids.LIQUID_CHOCOLATE_FLOWING.value,
            ModFluids.LIQUID_CANDY_SOURCE.value,
            ModFluids.LIQUID_CANDY_FLOWING.value,
        )
        this.tag(ModTags.C_FLUID_CHOCOLATE).add(
            ModFluids.LIQUID_CHOCOLATE_SOURCE.value,
            ModFluids.LIQUID_CHOCOLATE_FLOWING.value,
        )
    }
}