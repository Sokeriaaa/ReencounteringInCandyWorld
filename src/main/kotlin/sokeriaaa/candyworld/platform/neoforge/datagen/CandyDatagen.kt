package sokeriaaa.candyworld.platform.neoforge.datagen

import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.common.data.ExistingFileHelper
import net.neoforged.neoforge.data.event.GatherDataEvent
import sokeriaaa.candyworld.platform.neoforge.datagen.providers.*
import sokeriaaa.candyworld.platform.neoforge.datagen.providers.client.CandyLanguage

@EventBusSubscriber
object CandyDatagen {

    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val lookupProvider = event.lookupProvider
        val output = generator.packOutput
        val helper: ExistingFileHelper? = event.existingFileHelper

        if (event.includeServer()) {
            generator.apply {
                addProvider(true, CandyLootTables(output, lookupProvider))
                addProvider(true, CandyRecipes(output, lookupProvider))
                addProvider(true, CandyFluidTags(output, lookupProvider, helper))
                val blockTagsProvider = CandyBlockTags(output, lookupProvider, helper)
                addProvider(true, blockTagsProvider)
                addProvider(true, CandyItemTags(output, lookupProvider, blockTagsProvider.contentsGetter(), helper))
                addProvider(true, CandyDatapackBuiltinEntries(output, lookupProvider))
            }
        }
        if (event.includeClient()) {
            generator.apply {
                addProvider(true, CandyLanguage(output))
            }
        }
    }

}