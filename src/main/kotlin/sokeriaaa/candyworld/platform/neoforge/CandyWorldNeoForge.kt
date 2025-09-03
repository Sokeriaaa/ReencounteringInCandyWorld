package sokeriaaa.candyworld.platform.neoforge

import net.minecraft.world.level.levelgen.SurfaceRules
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.configs.CandyConfig
import sokeriaaa.candyworld.platform.Platform
import sokeriaaa.candyworld.platform.neoforge.impl.CandyWorldRegistryImpl
import sokeriaaa.candyworld.registry.ModBiomes
import sokeriaaa.candyworld.world.region.ChocolateForestRegion
import sokeriaaa.candyworld.world.region.CottonCandyPlainsRegion
import sokeriaaa.candyworld.world.region.GummySwampRegion
import sokeriaaa.candyworld.world.surface.ModSurfaceRules
import terrablender.api.Regions
import terrablender.api.SurfaceRuleManager

@Mod(CandyWorld.MOD_ID)
class CandyWorldNeoForge(modEventBus: IEventBus, modContainer: ModContainer) {
    init {
        // Run our common setup.
        CandyWorld.init()
        // Register the commonSetup method for modloading
        modEventBus.addListener<FMLCommonSetupEvent>(::commonSetup)
        // Register the Deferred Register to the mod event bus so objects get registered
        (Platform.registry as CandyWorldRegistryImpl).registerAllDeferredTo(modEventBus)
        modEventBus.register(CandyWorldEventBus::class.java)
//        NeoForge.EVENT_BUS.register(this)
    }


    private fun commonSetup(event: FMLCommonSetupEvent) {
        event.enqueueWork {
            // TerraBlender - Regions
            Regions.register(
                ChocolateForestRegion(
                    CandyWorld.id(ModBiomes.CHOCOLATE_FOREST_BUILDER.path),
                    CandyConfig.COMMON.weightChocolateForest,
                )
            )
            Regions.register(
                CottonCandyPlainsRegion(
                    CandyWorld.id(ModBiomes.COTTON_CANDY_PLAINS_BUILDER.path),
                    CandyConfig.COMMON.weightCottonCandyPlains,
                )
            )
            Regions.register(
                GummySwampRegion(
                    CandyWorld.id(ModBiomes.GUMMY_SWAMP_BUILDER.path),
                    CandyConfig.COMMON.weightGummySwamp,
                )
            )
            // TerraBlender - SurfaceRules
            SurfaceRuleManager.addSurfaceRules(
                SurfaceRuleManager.RuleCategory.OVERWORLD,
                CandyWorld.MOD_ID,
                SurfaceRules.sequence(
                    ModSurfaceRules.CANDY_SURFACE_RULES,
                    ModSurfaceRules.CHOCOLATE_SURFACE_RULES,
                    ModSurfaceRules.GUMMY_SURFACE_RULES,
                )
            )
        }
    }

}
