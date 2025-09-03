package sokeriaaa.candyworld.registry

import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.Carvers
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.*
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.configs.CandyConfig
import sokeriaaa.candyworld.platform.registry.CandyWorldRegistry
import sokeriaaa.candyworld.world.ModConfiguredAndPlaced

object ModBiomes {
    val COTTON_CANDY_PLAINS: ResourceKey<Biome> = create("cotton_candy_plains")
    val CHOCOLATE_FOREST: ResourceKey<Biome> = create("chocolate_forest")
    val GUMMY_SWAMP: ResourceKey<Biome> = create("gummy_swamp")

    val COTTON_CANDY_PLAINS_BUILDER = biomeBuilder("cotton_candy_plains") {
        { placedFeatures, worldCarvers ->
            Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.3f)
                .temperature(0.8f)
                .specialEffects(
                    BiomeSpecialEffects.Builder()
                        .fogColor(0xC0D8FF)
                        .waterColor(0x3F76E4)
                        .waterFogColor(0x050533)
                        .skyColor(0xFFAAEE)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .build(),
                )
                .mobSpawnSettings(
                    MobSpawnSettings.Builder()
                        .apply {
                            BiomeDefaultFeatures.plainsSpawns(this)
                            if (CandyConfig.COMMON.weightCottonCandySheep > 0) {
                                addSpawn(
                                    MobCategory.CREATURE,
                                    MobSpawnSettings.SpawnerData(
                                        ModEntities.COTTON_CANDY_SHEEP.value,
                                        CandyConfig.COMMON.weightCottonCandySheep,
                                        3,
                                        6,
                                    )
                                )
                            }
                        }
                        .build()
                )
                .generationSettings(
                    BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
                        .addDefaultCandyGeneration()
                        .addFeature(
                            GenerationStep.Decoration.VEGETAL_DECORATION,
                            ModConfiguredAndPlaced.PATCH_COTTON_CANDY.placed.key,
                        )
                        .addFeature(
                            GenerationStep.Decoration.VEGETAL_DECORATION,
                            ModConfiguredAndPlaced.COTTON_CANDY.placed.key,
                        )
                        .addFeature(
                            GenerationStep.Decoration.VEGETAL_DECORATION,
                            ModConfiguredAndPlaced.PATCH_CAVE_CANDY_CANE.placed.key,
                        )
                        .addFeature(
                            GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                            ModConfiguredAndPlaced.SUGAR_SPIKE.placed.key,
                        )
                        .addFeature(
                            GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                            ModConfiguredAndPlaced.MILK_CHOCOLATE_SPIKE.placed.key,
                        )
                        .build()
                )
        }
    }
    val CHOCOLATE_FOREST_BUILDER = biomeBuilder("chocolate_forest") {
        { placedFeatures, worldCarvers ->
            Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.3f)
                .temperature(0.8f)
                .specialEffects(
                    BiomeSpecialEffects.Builder()
                        .fogColor(0xC0D8FF)
                        .waterColor(0x3F76E4)
                        .waterFogColor(0x050533)
                        .skyColor(0xFFDDAA)
                        .build(),
                )
                .mobSpawnSettings(
                    MobSpawnSettings.Builder()
                        .apply {
                            BiomeDefaultFeatures.plainsSpawns(this)
                            if (CandyConfig.COMMON.weightEasterChicken > 0) {
                                addSpawn(
                                    MobCategory.CREATURE,
                                    MobSpawnSettings.SpawnerData(
                                        ModEntities.EASTER_CHICKEN.value,
                                        CandyConfig.COMMON.weightEasterChicken,
                                        3,
                                        7,
                                    )
                                )
                            }
                        }
                        .build()
                )
                .generationSettings(
                    BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
                        .addDefaultCandyGeneration()
                        .addFeature(
                            GenerationStep.Decoration.VEGETAL_DECORATION,
                            ModConfiguredAndPlaced.PATCH_CHOCOLATE_MUSHROOM.placed.key,
                        )
                        .addFeature(
                            GenerationStep.Decoration.VEGETAL_DECORATION,
                            ModConfiguredAndPlaced.PATCH_CHOCOLATE_BAR.placed.key,
                        )
                        .addFeature(
                            GenerationStep.Decoration.VEGETAL_DECORATION,
                            ModConfiguredAndPlaced.CHOCOLATE.placed.key,
                        )
                        .addFeature(
                            GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                            ModConfiguredAndPlaced.PATCH_CAVE_CHOCOLATE_BAR.placed.key,
                        )
                        .addFeature(
                            GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                            ModConfiguredAndPlaced.CHOCOLATE_SPIKE.placed.key,
                        )
                        .build()
                )
        }
    }
    val GUMMY_SWAMP_BUILDER = biomeBuilder("gummy_swamp") {
        { placedFeatures, worldCarvers ->
            Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.9f)
                .specialEffects(
                    BiomeSpecialEffects.Builder()
                        .fogColor(0xC0D8FF)
                        .waterColor(0x617B64)
                        .waterFogColor(0x232317)
                        .skyColor(0xA3FBFF)
                        .build(),
                )
                .mobSpawnSettings(
                    MobSpawnSettings.Builder()
                        .apply {
                            BiomeDefaultFeatures.plainsSpawns(this)
                            if (CandyConfig.COMMON.weightGummyMouse > 0) {
                                addSpawn(
                                    MobCategory.CREATURE,
                                    MobSpawnSettings.SpawnerData(
                                        ModEntities.GUMMY_MOUSE.value,
                                        CandyConfig.COMMON.weightGummyMouse,
                                        4,
                                        10,
                                    )
                                )
                            }
                            if (CandyConfig.COMMON.weightGummyBear > 0) {
                                addSpawn(
                                    MobCategory.CREATURE,
                                    MobSpawnSettings.SpawnerData(
                                        ModEntities.GUMMY_BEAR.value,
                                        CandyConfig.COMMON.weightGummyBear,
                                        4,
                                        10,
                                    )
                                )
                            }
                        }
                        .build()
                )
                .generationSettings(
                    BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
                        .addDefaultCandyGeneration()
                        .addFeature(
                            GenerationStep.Decoration.VEGETAL_DECORATION,
                            ModConfiguredAndPlaced.GUMMY_WORM.placed.key,
                        )
                        .build()
                )
        }
    }

    private fun BiomeGenerationSettings.Builder.addDefaultCandyGeneration(): BiomeGenerationSettings.Builder {
        return this
            // Common
            .apply {
                BiomeDefaultFeatures.addDefaultOres(this)
                BiomeDefaultFeatures.addDefaultSoftDisks(this)
            }
            .addCarver(GenerationStep.Carving.AIR, Carvers.CAVE)
            .addCarver(GenerationStep.Carving.AIR, Carvers.CAVE_EXTRA_UNDERGROUND)
            .addCarver(GenerationStep.Carving.AIR, Carvers.CANYON)
            // Candy world
            .addFeature(GenerationStep.Decoration.LAKES, ModConfiguredAndPlaced.LAKE_CHOCOLATE.placed.key)
            .addFeature(GenerationStep.Decoration.LAKES, ModConfiguredAndPlaced.LAKE_CANDY.placed.key)
            .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModConfiguredAndPlaced.ORE_MILK_BROWNIE.placed.key)
            .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModConfiguredAndPlaced.ORE_WHITE_BROWNIE.placed.key)
            .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModConfiguredAndPlaced.ORE_DARK_BROWNIE.placed.key)
            .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModConfiguredAndPlaced.ORE_TELEPORT.placed.key)
            .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModConfiguredAndPlaced.ORE_SUGAR_SAND.placed.key)
            .addFeature(
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ModConfiguredAndPlaced.ORE_MILK_BROWNIE_OVERWORLD.placed.key
            )
            .addFeature(
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ModConfiguredAndPlaced.ORE_WHITE_BROWNIE_OVERWORLD.placed.key
            )
            .addFeature(
                GenerationStep.Decoration.UNDERGROUND_ORES,
                ModConfiguredAndPlaced.ORE_DARK_BROWNIE_OVERWORLD.placed.key
            )
            .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModConfiguredAndPlaced.ORE_SUGAR_BLOCK.placed.key)
            .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModConfiguredAndPlaced.ORE_COOKIE.placed.key)
            .addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModConfiguredAndPlaced.ORE_SUGAR_COOKIE.placed.key)
    }

    fun register() {
        CandyWorldRegistry {
            registerBiome(CHOCOLATE_FOREST, CHOCOLATE_FOREST_BUILDER)
            registerBiome(COTTON_CANDY_PLAINS, COTTON_CANDY_PLAINS_BUILDER)
            registerBiome(GUMMY_SWAMP, GUMMY_SWAMP_BUILDER)
        }
    }

    fun create(
        name: String,
    ): ResourceKey<Biome> {
        return ResourceKey.create(Registries.BIOME, CandyWorld.id(name))
    }

    private fun biomeBuilder(
        path: String,
        supplier: () -> CandyBiomeBuilder,
    ) = RegistryObject(
        path = path,
        lazy = lazy(supplier),
    )

}

typealias CandyBiomeBuilder = (HolderGetter<PlacedFeature>, HolderGetter<ConfiguredWorldCarver<*>>) -> Biome.BiomeBuilder