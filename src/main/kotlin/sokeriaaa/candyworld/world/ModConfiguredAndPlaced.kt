package sokeriaaa.candyworld.world

import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.util.random.SimpleWeightedRandomList
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.LakeFeature
import net.minecraft.world.level.levelgen.feature.configurations.*
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer
import net.minecraft.world.level.levelgen.placement.*
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.blocks.chocolate.ChocolateBarBlock.Companion.FACING
import sokeriaaa.candyworld.platform.registry.CandyWorldRegistry
import sokeriaaa.candyworld.registry.KeyObjectPair
import sokeriaaa.candyworld.registry.ModBlocks
import sokeriaaa.candyworld.registry.ModTags
import sokeriaaa.candyworld.world.feature.ConfiguredAndPlaced
import sokeriaaa.candyworld.world.feature.config.CandySpikeConfiguration
import sokeriaaa.candyworld.world.tree.placer.ChocolateFoliagePlacer
import sokeriaaa.candyworld.world.tree.placer.CottonCandyFoliagePlacer
import kotlin.random.Random
import kotlin.random.nextInt

object ModConfiguredAndPlaced {
    private val CRYSTALLIZED_SUGAR_COOKIE_ORE by lazy { ModBlocks.CRYSTALLIZED_SUGAR_COOKIE_ORE.value.defaultBlockState() }
    private val COOKIE_ORE by lazy { ModBlocks.COOKIE_ORE.value.defaultBlockState() }
    private val TELEPORT_ORE by lazy { ModBlocks.TELEPORTER_ORE.value.defaultBlockState() }
    private val SUGAR_SAND by lazy { ModBlocks.SUGAR_SAND.value.defaultBlockState() }
    private val CRYSTALLIZED_SUGAR by lazy { ModBlocks.CRYSTALLIZED_SUGAR.value.defaultBlockState() }
    private val CANDY_GRASS_BLOCK by lazy { ModBlocks.CANDY_GRASS_BLOCK.value.defaultBlockState() }
    private val CHOCOLATE_COVERED_WHITE_BROWNIE by lazy { ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.value.defaultBlockState() }
    private val MILK_BROWNIE by lazy { ModBlocks.MILK_BROWNIE_BLOCK.value.defaultBlockState() }
    private val WHITE_BROWNIE by lazy { ModBlocks.WHITE_BROWNIE_BLOCK.value.defaultBlockState() }
    private val DARK_BROWNIE by lazy { ModBlocks.DARK_BROWNIE_BLOCK.value.defaultBlockState() }
    private val COTTON_CANDY_PLANT by lazy { ModBlocks.COTTON_CANDY_PLANT.value.defaultBlockState() }
    private val COTTON_CANDY_BUSH by lazy { ModBlocks.COTTON_CANDY_BUSH.value.defaultBlockState() }

    private val WHITE_CANDY_CANE_BLOCK by lazy { ModBlocks.WHITE_CANDY_CANE_BLOCK.value.defaultBlockState() }
    private val WHITE_RED_CANDY_CANE_BLOCK by lazy { ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.value.defaultBlockState() }
    private val WHITE_GREEN_CANDY_CANE_BLOCK by lazy { ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.value.defaultBlockState() }

    private val COTTON_CANDY_LEAVES by lazy { ModBlocks.COTTON_CANDY_LEAVES.value.defaultBlockState() }

    private val WAFER_STICK_BLOCK by lazy { ModBlocks.WAFER_STICK_BLOCK.value.defaultBlockState() }
    private val MILK_CHOCOLATE_LEAVES by lazy { ModBlocks.MILK_CHOCOLATE_LEAVES.value.defaultBlockState() }
    private val WHITE_CHOCOLATE_LEAVES by lazy { ModBlocks.WHITE_CHOCOLATE_LEAVES.value.defaultBlockState() }
    private val DARK_CHOCOLATE_LEAVES by lazy { ModBlocks.DARK_CHOCOLATE_LEAVES.value.defaultBlockState() }

    private val MILK_CHOCOLATE_MUSHROOM by lazy { ModBlocks.MILK_CHOCOLATE_MUSHROOM.value.defaultBlockState() }
    private val WHITE_CHOCOLATE_MUSHROOM by lazy { ModBlocks.WHITE_CHOCOLATE_MUSHROOM.value.defaultBlockState() }
    private val DARK_CHOCOLATE_MUSHROOM by lazy { ModBlocks.DARK_CHOCOLATE_MUSHROOM.value.defaultBlockState() }
    private val MILK_CHOCOLATE_BAR by lazy { ModBlocks.MILK_CHOCOLATE_BAR_BLOCK.value.defaultBlockState() }
    private val WHITE_CHOCOLATE_BAR by lazy { ModBlocks.WHITE_CHOCOLATE_BAR_BLOCK.value.defaultBlockState() }
    private val DARK_CHOCOLATE_BAR by lazy { ModBlocks.DARK_CHOCOLATE_BAR_BLOCK.value.defaultBlockState() }
    private val MILK_CHOCOLATE_BLOCK by lazy { ModBlocks.MILK_CHOCOLATE_BLOCK.value.defaultBlockState() }
    private val WHITE_CHOCOLATE_BLOCK by lazy { ModBlocks.WHITE_CHOCOLATE_BLOCK.value.defaultBlockState() }
    private val DARK_CHOCOLATE_BLOCK by lazy { ModBlocks.DARK_CHOCOLATE_BLOCK.value.defaultBlockState() }

    private val LIQUID_CHOCOLATE by lazy { ModBlocks.LIQUID_CHOCOLATE_BLOCK.value.defaultBlockState() }
    private val LIQUID_CANDY by lazy { ModBlocks.LIQUID_CANDY_BLOCK.value.defaultBlockState() }

    // 糖果世界
    // Candy world
    val ORE_MILK_BROWNIE = configuredAndPlaced(
        path = "ore_milk_brownie",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.ORE,
                OreConfiguration(CustomFillerType.SUGAR, MILK_BROWNIE, 25),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                orePlacements(16)
            )
        },
    )
    val ORE_WHITE_BROWNIE = configuredAndPlaced(
        path = "ore_white_brownie",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.ORE,
                OreConfiguration(CustomFillerType.SUGAR, WHITE_BROWNIE, 25),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                orePlacements(16)
            )
        },
    )
    val ORE_DARK_BROWNIE = configuredAndPlaced(
        path = "ore_dark_brownie",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.ORE,
                OreConfiguration(CustomFillerType.SUGAR, DARK_BROWNIE, 25),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                orePlacements(16)
            )
        },
    )
    val ORE_SUGAR_COOKIE = configuredAndPlaced(
        path = "ore_sugar_cookie",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.ORE,
                OreConfiguration(CustomFillerType.SUGAR, CRYSTALLIZED_SUGAR_COOKIE_ORE, 3),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                orePlacements(80)
            )
        },
    )
    val ORE_TELEPORT = configuredAndPlaced(
        path = "ore_teleport",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.ORE,
                OreConfiguration(CustomFillerType.SUGAR, TELEPORT_ORE, 3),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                orePlacements(Random.nextInt(6..10))
            )
        },
    )
    val ORE_SUGAR_SAND = configuredAndPlaced(
        path = "ore_sugar_sand",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.ORE,
                OreConfiguration(CustomFillerType.SUGAR, SUGAR_SAND, 20),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                orePlacements(8)
            )
        },
    )

    // 主世界
    // Overworld
    val ORE_MILK_BROWNIE_OVERWORLD = configuredAndPlaced(
        path = "ore_milk_brownie_overworld",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.ORE,
                OreConfiguration(
                    TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                    MILK_BROWNIE,
                    25,
                ),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                oreOverworldPlacements(0, 40, 1)
            )
        },
    )
    val ORE_WHITE_BROWNIE_OVERWORLD = configuredAndPlaced(
        path = "ore_white_brownie_overworld",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.ORE,
                OreConfiguration(
                    TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                    WHITE_BROWNIE,
                    25
                ),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                oreOverworldPlacements(0, 64, 1)
            )
        },
    )
    val ORE_DARK_BROWNIE_OVERWORLD = configuredAndPlaced(
        path = "ore_dark_brownie_overworld",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.ORE,
                OreConfiguration(
                    TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                    DARK_BROWNIE,
                    25
                ),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                oreOverworldPlacements(0, 25, 1)
            )
        },
    )
    val ORE_SUGAR_BLOCK = configuredAndPlaced(
        path = "ore_sugar_block",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.ORE,
                OreConfiguration(
                    TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                    CRYSTALLIZED_SUGAR,
                    20
                ),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                oreOverworldPlacements(0, 30, 2)
            )
        },
    )
    val ORE_COOKIE = configuredAndPlaced(
        path = "ore_cookie",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.ORE,
                OreConfiguration(
                    TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                    COOKIE_ORE,
                    20
                ),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                oreOverworldPlacements(0, 45, 50)
            )
        },
    )

    // 通用
    // General
    val GUMMY_WORM = configuredAndPlaced(
        path = "gummy_worm",
        configuredSupplier = {
            ConfiguredFeature(
                ModFeatures.GUMMY_WORM.value,
                NoneFeatureConfiguration()
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome(),
                )
            )
        },
    )
    val PATCH_COTTON_CANDY = configuredAndPlaced(
        path = "patch_cotton_candy",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockConfiguration(
                        WeightedStateProvider(
                            SimpleWeightedRandomList.builder<BlockState>()
                                .add(COTTON_CANDY_PLANT, 1)
                                .add(COTTON_CANDY_BUSH, 2)
                        ),
                    ),
                    listOf(),
                    10,
                ),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                listOf(
                    // 噪声
                    // noise
                    NoiseThresholdCountPlacement.of(-0.8, 5, 10),
                    // 在xz平面内扩散
                    // Spread in the xz plain
                    InSquarePlacement.spread(),
                    // 按照高度图放置（对应原版的 HEIGHTMAP_SQUARE）
                    // Place according to heightmap (corresponds to HEIGHTMAP_SQUARE in the original version)
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    // 只在合适的生物群系中生成
                    // Only spawns in suitable biomes
                    BiomeFilter.biome(),
                )
            )
        },
    )
    val COTTON_CANDY = configuredAndPlaced(
        path = "cotton_candy",
        configuredSupplier = {
            ConfiguredFeature(
                ModFeatures.CANDY_TREE.value,
                TreeConfiguration.TreeConfigurationBuilder(
                    // 树干方块提供器
                    // Trunk block provider
                    BlockStateProvider.simple(WHITE_CANDY_CANE_BLOCK),
                    // 直树干放置器：基础高度5，随机高度A=2，随机高度B=1
                    // Straight trunk placer: baseHeight=5, heightRandA=2, heightRandB=1
                    StraightTrunkPlacer(5, 2, 1),
                    BlockStateProvider.simple(COTTON_CANDY_LEAVES),
                    // 自定义树叶放置器
                    // Custom foliage placer
                    CottonCandyFoliagePlacer(
                        radius = ConstantInt.of(2),
                        offset = ConstantInt.of(0),
                        trunkHeight = ConstantInt.of(1),
                    ),
                    // 两层特征大小：限制值=1，下层尺寸=0，上层尺寸=1
                    // Two layers feature size: limit=1, lowerSize=0, upperSize=1
                    TwoLayersFeatureSize(1, 0, 1),
                ).ignoreVines().build(),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 1)) +
                        PlacementUtils.filteredByBlockSurvival(ModBlocks.COTTON_CANDY_SAPLING.value),
            )
        },
    )
    val PATCH_CHOCOLATE_MUSHROOM = configuredAndPlaced(
        path = "patch_chocolate_mushroom",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockConfiguration(
                        WeightedStateProvider(
                            SimpleWeightedRandomList.builder<BlockState>()
                                .add(MILK_CHOCOLATE_MUSHROOM, 1)
                                .add(WHITE_CHOCOLATE_MUSHROOM, 1)
                                .add(DARK_CHOCOLATE_MUSHROOM, 1)
                        ),
                    ),
                    listOf(),
                    40,
                ),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                listOf(
                    // 噪声
                    // noise
                    NoiseThresholdCountPlacement.of(-0.8, 5, 10),
                    // 在xz平面内扩散
                    // Spread in the xz plain
                    InSquarePlacement.spread(),
                    // 按照高度图放置（对应原版的 HEIGHTMAP_SQUARE）
                    // Place according to heightmap (corresponds to HEIGHTMAP_SQUARE in the original version)
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    // 只在合适的生物群系中生成
                    // Only spawns in suitable biomes
                    BiomeFilter.biome(),
                )
            )
        },
    )
    val PATCH_CHOCOLATE_BAR = configuredAndPlaced(
        path = "patch_chocolate_bar",
        configuredSupplier = {
            ConfiguredFeature(
                ModFeatures.RANDOM_ROTATED_PATCH.value,
                FeatureUtils.simplePatchConfiguration(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockConfiguration(
                        WeightedStateProvider(
                            SimpleWeightedRandomList.builder<BlockState>()
                                .add(MILK_CHOCOLATE_BAR.setValue(FACING, Direction.NORTH), 1)
                                .add(MILK_CHOCOLATE_BAR.setValue(FACING, Direction.SOUTH), 1)
                                .add(MILK_CHOCOLATE_BAR.setValue(FACING, Direction.WEST), 1)
                                .add(MILK_CHOCOLATE_BAR.setValue(FACING, Direction.EAST), 1)
                                .add(WHITE_CHOCOLATE_BAR.setValue(FACING, Direction.NORTH), 1)
                                .add(WHITE_CHOCOLATE_BAR.setValue(FACING, Direction.SOUTH), 1)
                                .add(WHITE_CHOCOLATE_BAR.setValue(FACING, Direction.WEST), 1)
                                .add(WHITE_CHOCOLATE_BAR.setValue(FACING, Direction.EAST), 1)
                                .add(DARK_CHOCOLATE_BAR.setValue(FACING, Direction.NORTH), 1)
                                .add(DARK_CHOCOLATE_BAR.setValue(FACING, Direction.SOUTH), 1)
                                .add(DARK_CHOCOLATE_BAR.setValue(FACING, Direction.WEST), 1)
                                .add(DARK_CHOCOLATE_BAR.setValue(FACING, Direction.EAST), 1)
                        ),
                    ),
                    listOf(),
                    20,
                ),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                listOf(
                    // 噪声
                    // noise
                    NoiseThresholdCountPlacement.of(-0.8, 5, 10),
                    // 在xz平面内扩散
                    // Spread in the xz plain
                    InSquarePlacement.spread(),
                    // 按照高度图放置（对应原版的 HEIGHTMAP_SQUARE）
                    // Place according to heightmap (corresponds to HEIGHTMAP_SQUARE in the original version)
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    // 只在合适的生物群系中生成
                    // Only spawns in suitable biomes
                    BiomeFilter.biome(),
                )
            )
        },
    )
    val PATCH_CAVE_CHOCOLATE_BAR = configuredAndPlaced(
        path = "patch_cave_chocolate_bar",
        configuredSupplier = {
            ConfiguredFeature(
                ModFeatures.RANDOM_ROTATED_PATCH.value,
                FeatureUtils.simplePatchConfiguration(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockConfiguration(
                        WeightedStateProvider(
                            SimpleWeightedRandomList.builder<BlockState>()
                                .add(MILK_CHOCOLATE_BAR.setValue(FACING, Direction.NORTH), 1)
                                .add(MILK_CHOCOLATE_BAR.setValue(FACING, Direction.SOUTH), 1)
                                .add(MILK_CHOCOLATE_BAR.setValue(FACING, Direction.WEST), 1)
                                .add(MILK_CHOCOLATE_BAR.setValue(FACING, Direction.EAST), 1)
                                .add(WHITE_CHOCOLATE_BAR.setValue(FACING, Direction.NORTH), 1)
                                .add(WHITE_CHOCOLATE_BAR.setValue(FACING, Direction.SOUTH), 1)
                                .add(WHITE_CHOCOLATE_BAR.setValue(FACING, Direction.WEST), 1)
                                .add(WHITE_CHOCOLATE_BAR.setValue(FACING, Direction.EAST), 1)
                                .add(DARK_CHOCOLATE_BAR.setValue(FACING, Direction.NORTH), 1)
                                .add(DARK_CHOCOLATE_BAR.setValue(FACING, Direction.SOUTH), 1)
                                .add(DARK_CHOCOLATE_BAR.setValue(FACING, Direction.WEST), 1)
                                .add(DARK_CHOCOLATE_BAR.setValue(FACING, Direction.EAST), 1)
                        ),
                    ),
                    listOf(),
                    20,
                ),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                listOf(
                    // 噪声
                    // noise
                    NoiseThresholdCountPlacement.of(-0.8, 5, 10),
                    // 在xz平面内扩散
                    // Spread in the xz plain
                    InSquarePlacement.spread(),
                    // 使用适用于洞穴环境的高度图
                    // Use heightmap suitable for cave environments
                    PlacementUtils.HEIGHTMAP_TOP_SOLID,
                    // 限制生成的最低和最高高度（Y坐标）
                    // Restrict the minimum and maximum height (Y coordinate) for generation
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(62)),
                    // 只在合适的生物群系中生成
                    // Only spawns in suitable biomes
                    BiomeFilter.biome(),
                )
            )
        },
    )
    val CHOCOLATE = configuredAndPlaced(
        path = "chocolate",
        configuredSupplier = {
            ConfiguredFeature(
                ModFeatures.CANDY_TREE.value,
                TreeConfiguration.TreeConfigurationBuilder(
                    // 树干方块提供器
                    // Trunk block provider
                    BlockStateProvider.simple(WAFER_STICK_BLOCK),
                    // 直树干放置器：基础高度5，随机高度A=2，随机高度B=1
                    // Straight trunk placer: baseHeight=5, heightRandA=2, heightRandB=1
                    StraightTrunkPlacer(5, 2, 1),
                    WeightedStateProvider(
                        SimpleWeightedRandomList.builder<BlockState>()
                            .add(MILK_CHOCOLATE_LEAVES, 1)
                            .add(WHITE_CHOCOLATE_LEAVES, 1)
                            .add(DARK_CHOCOLATE_LEAVES, 1)
                    ),
                    // 自定义树叶放置器
                    // Custom foliage placer
                    ChocolateFoliagePlacer(
                        radius = ConstantInt.of(2),
                        offset = ConstantInt.of(0),
                        trunkHeight = ConstantInt.of(1),
                    ),
                    // 两层特征大小：限制值=1，下层尺寸=0，上层尺寸=1
                    // Two layers feature size: limit=1, lowerSize=0, upperSize=1
                    TwoLayersFeatureSize(1, 0, 1),
                ).ignoreVines().build(),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 1)) +
                        PlacementUtils.filteredByBlockSurvival(ModBlocks.CHOCOLATE_SAPLING.value),
            )
        },
    )
    val PATCH_CAVE_CANDY_CANE = configuredAndPlaced(
        path = "patch_cave_candy_cane",
        configuredSupplier = {
            ConfiguredFeature(
                ModFeatures.CANDY_CANE.value,
                FeatureUtils.simplePatchConfiguration(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockConfiguration(
                        WeightedStateProvider(
                            SimpleWeightedRandomList.builder<BlockState>()
                                .add(WHITE_RED_CANDY_CANE_BLOCK, 1)
                                .add(WHITE_GREEN_CANDY_CANE_BLOCK, 2)
                        ),
                    ),
                    listOf(),
                    16,
                ),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                listOf(
                    // 噪声
                    // noise
                    NoiseThresholdCountPlacement.of(-0.8, 5, 10),
                    // 在xz平面内扩散
                    // Spread in the xz plain
                    InSquarePlacement.spread(),
                    // 使用适用于洞穴环境的高度图
                    // Use heightmap suitable for cave environments
                    PlacementUtils.HEIGHTMAP_TOP_SOLID,
                    // 限制生成的最低和最高高度（Y坐标）
                    // Restrict the minimum and maximum height (Y coordinate) for generation
                    HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(62)),
                    // 只在合适的生物群系中生成
                    // Only spawns in suitable biomes
                    BiomeFilter.biome(),
                )
            )
        },
    )
    val SUGAR_SPIKE = configuredAndPlaced(
        path = "sugar_spike",
        configuredSupplier = {
            ConfiguredFeature(
                ModFeatures.CANDY_SPIKE.value,
                Configs.DEFAULT_SUGAR_CONFIG,
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome(),
                )
            )
        },
    )
    val MILK_CHOCOLATE_SPIKE = configuredAndPlaced(
        path = "milk_chocolate_spike",
        configuredSupplier = {
            ConfiguredFeature(
                ModFeatures.CANDY_SPIKE.value,
                Configs.DEFAULT_MILK_CHOCOLATE_CONFIG,
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome(),
                )
            )
        },
    )
    val CHOCOLATE_SPIKE = configuredAndPlaced(
        path = "chocolate_spike",
        configuredSupplier = {
            ConfiguredFeature(
                ModFeatures.CANDY_SPIKE.value,
                Configs.DEFAULT_CHOCOLATE_CONFIG,
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                listOf(
                    InSquarePlacement.spread(),
                    PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                    BiomeFilter.biome(),
                )
            )
        },
    )
    val LAKE_CHOCOLATE = configuredAndPlaced(
        path = "lake_chocolate",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.LAKE,
                LakeFeature.Configuration(
                    BlockStateProvider.simple(LIQUID_CANDY),
                    BlockStateProvider.simple(CRYSTALLIZED_SUGAR),
                ),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                listOf(
                    // 生成频率，数值越大越稀有
                    // Rarity, the larger the value, the rarer it is
                    RarityFilter.onAverageOnceEvery(4),
                    // 生成频率，数值越大越稀有
                    // Rarity, the larger the value, the rarer it is
                    InSquarePlacement.spread(),
                    // 使用均匀高度分布而不是高度图投射
                    // Use uniform height distribution instead of heightmap projection
                    HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(320)
                    ),
                    // 生物群系过滤器
                    // Biome filter
                    BiomeFilter.biome()
                )
            )
        },
    )
    val LAKE_CANDY = configuredAndPlaced(
        path = "lake_candy",
        configuredSupplier = {
            ConfiguredFeature(
                Feature.LAKE,
                LakeFeature.Configuration(
                    BlockStateProvider.simple(LIQUID_CHOCOLATE),
                    BlockStateProvider.simple(CRYSTALLIZED_SUGAR),
                ),
            )
        },
        placedSupplier = {
            PlacedFeature(
                Holder.direct(it),
                listOf(
                    // 生成频率，数值越大越稀有
                    // Rarity, the larger the value, the rarer it is
                    RarityFilter.onAverageOnceEvery(4),
                    // 生成频率，数值越大越稀有
                    // Rarity, the larger the value, the rarer it is
                    InSquarePlacement.spread(),
                    // 使用均匀高度分布而不是高度图投射
                    // Use uniform height distribution instead of heightmap projection
                    HeightRangePlacement.uniform(
                        VerticalAnchor.absolute(-64),
                        VerticalAnchor.absolute(320)
                    ),
                    // 生物群系过滤器
                    // Biome filter
                    BiomeFilter.biome()
                )
            )
        },
    )

    private fun orePlacements(count: Int) = listOf(
        // 每个区块尝试生成的次数
        // The number of attempts to generate each chunk.
        CountPlacement.of(count),
        // 在区块内水平扩散
        // Horizontal spread within the chunk.
        InSquarePlacement.spread(),
        // 高度范围 (注意1.18后世界高度变化)
        // Height range (note that the world height has changed since 1.18)
        HeightRangePlacement.uniform(
            // 底部，对应-64
            // Bottom
            VerticalAnchor.bottom(),
            // 绝对高度64
            // Absolute height
            VerticalAnchor.absolute(64),
        ),
        // 生物群系过滤器
        // Biome filter
        BiomeFilter.biome()
    )

    private fun oreOverworldPlacements(
        min: Int,
        max: Int,
        count: Int
    ) = listOf(
        // 每个区块尝试生成的次数
        // The number of attempts to generate each chunk.
        CountPlacement.of(count),
        // 在区块内水平扩散
        // Horizontal spread within the chunk.
        InSquarePlacement.spread(),
        // 对应 TopSolidRangeConfig(0, 20, 40)
        HeightRangePlacement.triangle(
            // 最小高度
            // Minimal height
            VerticalAnchor.absolute(min),
            // 最大高度
            // Maximum height
            VerticalAnchor.absolute(max),
        ),
        // 生物群系过滤器
        // Biome filter
        BiomeFilter.biome()
    )

    fun register() {
        CandyWorldRegistry.Companion {
            registerConfiguredAndPlacedFeature(ORE_MILK_BROWNIE)
            registerConfiguredAndPlacedFeature(ORE_WHITE_BROWNIE)
            registerConfiguredAndPlacedFeature(ORE_DARK_BROWNIE)
            registerConfiguredAndPlacedFeature(ORE_SUGAR_COOKIE)
            registerConfiguredAndPlacedFeature(ORE_TELEPORT)
            registerConfiguredAndPlacedFeature(ORE_SUGAR_SAND)

            registerConfiguredAndPlacedFeature(ORE_MILK_BROWNIE_OVERWORLD)
            registerConfiguredAndPlacedFeature(ORE_WHITE_BROWNIE_OVERWORLD)
            registerConfiguredAndPlacedFeature(ORE_DARK_BROWNIE_OVERWORLD)
            registerConfiguredAndPlacedFeature(ORE_SUGAR_BLOCK)
            registerConfiguredAndPlacedFeature(ORE_COOKIE)

            registerConfiguredAndPlacedFeature(GUMMY_WORM)
            registerConfiguredAndPlacedFeature(PATCH_COTTON_CANDY)
            registerConfiguredAndPlacedFeature(COTTON_CANDY)
            registerConfiguredAndPlacedFeature(PATCH_CHOCOLATE_MUSHROOM)
            registerConfiguredAndPlacedFeature(PATCH_CHOCOLATE_BAR)
            registerConfiguredAndPlacedFeature(PATCH_CAVE_CHOCOLATE_BAR)
            registerConfiguredAndPlacedFeature(CHOCOLATE)
            registerConfiguredAndPlacedFeature(PATCH_CAVE_CANDY_CANE)
            registerConfiguredAndPlacedFeature(SUGAR_SPIKE)
            registerConfiguredAndPlacedFeature(MILK_CHOCOLATE_SPIKE)
            registerConfiguredAndPlacedFeature(CHOCOLATE_SPIKE)
            registerConfiguredAndPlacedFeature(LAKE_CHOCOLATE)
            registerConfiguredAndPlacedFeature(LAKE_CANDY)
        }
    }

    private inline fun <reified FC : FeatureConfiguration, reified F : Feature<FC>> configuredAndPlaced(
        path: String,
        noinline configuredSupplier: () -> ConfiguredFeature<FC, F>,
        noinline placedSupplier: (ConfiguredFeature<FC, F>) -> PlacedFeature,
    ): ConfiguredAndPlaced {
        val configuredLazy = lazy(configuredSupplier)
        return ConfiguredAndPlaced(
            configuredLazy = lazy {
                KeyObjectPair(
                    path = path,
                    lazy = configuredLazy,
                    createKey = {
                        ResourceKey.create(
                            Registries.CONFIGURED_FEATURE,
                            CandyWorld.id(it)
                        )
                    },
                )
            },
            placedLazy = lazy {
                KeyObjectPair(
                    path = path,
                    lazy = lazy { placedSupplier(configuredLazy.value) },
                    createKey = {
                        ResourceKey.create(
                            Registries.PLACED_FEATURE,
                            CandyWorld.id(it)
                        )
                    },
                )
            },
        )
    }

    object CustomFillerType {
        val SUGAR: RuleTest = TagMatchTest(ModTags.SUGAR)
    }

    private object Configs {
        // Candy Plains
        val DEFAULT_SUGAR_CONFIG: CandySpikeConfiguration = CandySpikeConfiguration.Builder(
            SimpleStateProvider.simple(CRYSTALLIZED_SUGAR)
        ).minLength(3)
            .maxLength(8)
            .chance(8)
            .whitelist(setOf(CANDY_GRASS_BLOCK, MILK_BROWNIE))
            .build()

        val DEFAULT_MILK_CHOCOLATE_CONFIG: CandySpikeConfiguration = CandySpikeConfiguration.Builder(
            SimpleStateProvider.simple(MILK_CHOCOLATE_BLOCK)
        ).minLength(5)
            .maxLength(12)
            .chance(24)
            .whitelist(setOf(CANDY_GRASS_BLOCK, MILK_BROWNIE))
            .build()

        // Chocolate Forest
        val DEFAULT_CHOCOLATE_CONFIG: CandySpikeConfiguration = CandySpikeConfiguration.Builder(
            WeightedStateProvider(
                SimpleWeightedRandomList.builder<BlockState>()
                    .add(MILK_CHOCOLATE_BLOCK, 1)
                    .add(WHITE_CHOCOLATE_BLOCK, 1)
                    .add(DARK_CHOCOLATE_BLOCK, 1)
            ),
        ).minLength(3)
            .maxLength(24)
            .chance(16)
            .whitelist(setOf(CHOCOLATE_COVERED_WHITE_BROWNIE, WHITE_BROWNIE))
            .build()
    }

}