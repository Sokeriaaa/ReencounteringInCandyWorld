package sokeriaaa.candyworld.blocks.fluid

import net.minecraft.world.item.Rarity
import net.minecraft.world.level.material.FlowingFluid
import net.minecraft.world.level.material.Fluid
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.platform.Platform
import sokeriaaa.candyworld.platform.fluid.CandyWorldFluid
import sokeriaaa.candyworld.platform.registry.CandyWorldRegistry
import sokeriaaa.candyworld.registry.ModBlocks
import sokeriaaa.candyworld.registry.ModItems
import sokeriaaa.candyworld.registry.RegistryObject

object ModFluids {

    val FLUID_CHOCOLATE_PROPERTIES: CandyWorldFluid.Attributes = CandyWorldFluid.Attributes(
        path = "fluid_chocolate",
        sourceFluid = { LIQUID_CHOCOLATE_SOURCE.value },
        flowingFluid = { LIQUID_CHOCOLATE_FLOWING.value },
        bucketItem = { ModItems.LIQUID_CHOCOLATE_BUCKET.value },
        block = { ModBlocks.LIQUID_CHOCOLATE_BLOCK.value },
        stillTexture = CandyWorld.id("fluid/liquid_chocolate_still"),
        flowingTexture = CandyWorld.id("fluid/liquid_chocolate_flow"),
        density = 1030,
        temperature = 315,
        rarity = Rarity.COMMON,
    )
    val FLUID_CANDY_PROPERTIES: CandyWorldFluid.Attributes = CandyWorldFluid.Attributes(
        path = "fluid_candy",
        sourceFluid = { LIQUID_CANDY_SOURCE.value },
        flowingFluid = { LIQUID_CANDY_FLOWING.value },
        bucketItem = { ModItems.LIQUID_CANDY_BUCKET.value },
        block = { ModBlocks.LIQUID_CANDY_BLOCK.value },
        stillTexture = CandyWorld.id("fluid/liquid_candy_still"),
        flowingTexture = CandyWorld.id("fluid/liquid_candy_flow"),
        lightLevel = 12,
        density = 2000,
        temperature = 1000,
        viscosity = 3000,
        rarity = Rarity.COMMON,
    )

    val LIQUID_CHOCOLATE_SOURCE: RegistryObject<FlowingFluid> = fluid("fluid_chocolate_source") {
        Platform.fluid.createSourceFluid(FLUID_CHOCOLATE_PROPERTIES)
    }

    val LIQUID_CHOCOLATE_FLOWING: RegistryObject<FlowingFluid> = fluid("fluid_chocolate_flowing") {
        Platform.fluid.createFlowingFluid(FLUID_CHOCOLATE_PROPERTIES)
    }

    val LIQUID_CANDY_SOURCE: RegistryObject<FlowingFluid> = fluid("fluid_candy_source") {
        Platform.fluid.createSourceFluid(FLUID_CANDY_PROPERTIES)
    }

    val LIQUID_CANDY_FLOWING: RegistryObject<FlowingFluid> = fluid("fluid_candy_flowing") {
        Platform.fluid.createFlowingFluid(FLUID_CANDY_PROPERTIES)
    }

    fun register() {
        CandyWorldRegistry {
            registerFluidType(FLUID_CHOCOLATE_PROPERTIES)
            registerFluidType(FLUID_CANDY_PROPERTIES)
            registerFluid(LIQUID_CHOCOLATE_SOURCE)
            registerFluid(LIQUID_CHOCOLATE_FLOWING)
            registerFluid(LIQUID_CANDY_SOURCE)
            registerFluid(LIQUID_CANDY_FLOWING)
        }
    }

    private inline fun <reified T : Fluid> fluid(
        path: String,
        noinline supplier: () -> T,
    ) = RegistryObject(
        path = path,
        lazy = lazy(supplier),
    )
}