package sokeriaaa.candyworld

import net.minecraft.resources.ResourceLocation
import sokeriaaa.candyworld.blocks.fluid.ModFluids
import sokeriaaa.candyworld.registry.ModBiomes
import sokeriaaa.candyworld.registry.ModBlocks
import sokeriaaa.candyworld.registry.ModEntities
import sokeriaaa.candyworld.registry.ModItems
import sokeriaaa.candyworld.world.ModConfiguredAndPlaced
import sokeriaaa.candyworld.world.ModFeatures
import sokeriaaa.candyworld.world.ModFoliagePlacer

object CandyWorld {
    const val MOD_ID: String = "candyworld"

    @JvmStatic
    fun init() {
        // Write common init code here.
        ModFluids.register()
        ModItems.register()
        ModBlocks.register()
        ModEntities.register()
        ModFoliagePlacer.register()
        ModFeatures.register()
        ModConfiguredAndPlaced.register()
        ModBiomes.register()
    }

    fun id(path: String): ResourceLocation =
        ResourceLocation.fromNamespaceAndPath(MOD_ID, path)

}