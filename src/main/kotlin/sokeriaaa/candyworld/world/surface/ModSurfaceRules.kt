package sokeriaaa.candyworld.world.surface

import net.minecraft.world.level.levelgen.Noises
import net.minecraft.world.level.levelgen.SurfaceRules
import net.minecraft.world.level.levelgen.placement.CaveSurface
import sokeriaaa.candyworld.enums.EnumGummy
import sokeriaaa.candyworld.registry.ModBiomes
import sokeriaaa.candyworld.registry.ModBlocks

object ModSurfaceRules {
    private val RED_GUMMY by lazy { SurfaceRules.state(ModBlocks.RED_GUMMY_BLOCK.value.defaultBlockState()) }
    private val ORANGE_GUMMY by lazy { SurfaceRules.state(ModBlocks.ORANGE_GUMMY_BLOCK.value.defaultBlockState()) }
    private val YELLOW_GUMMY by lazy { SurfaceRules.state(ModBlocks.YELLOW_GUMMY_BLOCK.value.defaultBlockState()) }
    private val WHITE_GUMMY by lazy { SurfaceRules.state(ModBlocks.WHITE_GUMMY_BLOCK.value.defaultBlockState()) }
    private val GREEN_GUMMY by lazy { SurfaceRules.state(ModBlocks.GREEN_GUMMY_BLOCK.value.defaultBlockState()) }
    private val HARDENED_RED_GUMMY by lazy { SurfaceRules.state(ModBlocks.RED_HARDENED_GUMMY_BLOCK.value.defaultBlockState()) }
    private val HARDENED_ORANGE_GUMMY by lazy { SurfaceRules.state(ModBlocks.ORANGE_HARDENED_GUMMY_BLOCK.value.defaultBlockState()) }
    private val HARDENED_YELLOW_GUMMY by lazy { SurfaceRules.state(ModBlocks.YELLOW_HARDENED_GUMMY_BLOCK.value.defaultBlockState()) }
    private val HARDENED_WHITE_GUMMY by lazy { SurfaceRules.state(ModBlocks.WHITE_HARDENED_GUMMY_BLOCK.value.defaultBlockState()) }
    private val HARDENED_GREEN_GUMMY by lazy { SurfaceRules.state(ModBlocks.GREEN_HARDENED_GUMMY_BLOCK.value.defaultBlockState()) }

    private val CANDY_GRASS by lazy { SurfaceRules.state(ModBlocks.CANDY_GRASS_BLOCK.value.defaultBlockState()) }
    private val MILK_BROWNIE by lazy { SurfaceRules.state(ModBlocks.MILK_BROWNIE_BLOCK.value.defaultBlockState()) }
    private val CHOCOLATE_COVERED_WHITE_BROWNIE by lazy { SurfaceRules.state(ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.value.defaultBlockState()) }
    private val WHITE_BROWNIE by lazy { SurfaceRules.state(ModBlocks.WHITE_BROWNIE_BLOCK.value.defaultBlockState()) }
    private val DARK_BROWNIE by lazy { SurfaceRules.state(ModBlocks.DARK_BROWNIE_BLOCK.value.defaultBlockState()) }

    val CANDY_SURFACE_RULES: SurfaceRules.RuleSource by lazy {
        SurfaceRules.ifTrue(
            SurfaceRules.isBiome(ModBiomes.COTTON_CANDY_PLAINS),
            SurfaceRules.sequence(
                // 规则1：地表层（1层）
                // Rule 1: Surface layer (1 layer)
                SurfaceRules.ifTrue(
                    SurfaceRules.ON_FLOOR,
                    SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                            SurfaceRules.waterBlockCheck(-1, 0),
                            CANDY_GRASS,
                        ),
                        MILK_BROWNIE,
                    )
                ),
                // 规则2：地下浅层（1-3层）
                // Rule 2: Underground shallow layers (1-3 layers)
                SurfaceRules.ifTrue(
                    SurfaceRules.UNDER_FLOOR, // 在地表下方生成
                    SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                            SurfaceRules.stoneDepthCheck(3, false, CaveSurface.FLOOR),
                            MILK_BROWNIE
                        ),
                    )
                ),
            )
        )
    }
    val CHOCOLATE_SURFACE_RULES: SurfaceRules.RuleSource by lazy {
        SurfaceRules.ifTrue(
            SurfaceRules.isBiome(ModBiomes.CHOCOLATE_FOREST),
            SurfaceRules.sequence(
                // 规则1：地表层（1层）
                // Rule 1: Surface layer (1 layer)
                SurfaceRules.ifTrue(
                    SurfaceRules.ON_FLOOR,
                    SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                            SurfaceRules.waterBlockCheck(-1, 0),
                            CHOCOLATE_COVERED_WHITE_BROWNIE,
                        ),
                        WHITE_BROWNIE,
                    )
                ),
                // 规则2：地下浅层（1-3层）
                // Rule 2: Underground shallow layers (1-3 layers)
                SurfaceRules.ifTrue(
                    SurfaceRules.UNDER_FLOOR, // 在地表下方生成
                    SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                            SurfaceRules.stoneDepthCheck(3, false, CaveSurface.FLOOR),
                            WHITE_BROWNIE
                        ),
                    )
                ),
            )
        )
    }
    val GUMMY_SURFACE_RULES: SurfaceRules.RuleSource by lazy {
        SurfaceRules.ifTrue(
            SurfaceRules.isBiome(ModBiomes.GUMMY_SWAMP),
            SurfaceRules.sequence(
                // 规则1：地表层（1层）
                // Rule 1: Surface layer (1 layer)
                SurfaceRules.ifTrue(
                    SurfaceRules.ON_FLOOR,
                    SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                            SurfaceRules.waterBlockCheck(-1, 0),
                            gummySurfaceSelector(0),
                        ),
                        gummySurfaceSelector(1),
                    )
                ),
                // 规则2：地下浅层（1-3层）
                // Rule 2: Underground shallow layers (1-3 layers)
                SurfaceRules.ifTrue(
                    SurfaceRules.UNDER_FLOOR, // 在地表下方生成
                    SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                            SurfaceRules.stoneDepthCheck(3, false, CaveSurface.FLOOR),
                            gummySurfaceSelector(1),
                        ),
                    )
                ),
            )
        )
    }

    @JvmStatic
    private lateinit var gummyMap: Map<EnumGummy, List<SurfaceRules.RuleSource>>

    private fun gummySurfaceSelector(
        index: Int,
    ): SurfaceRules.RuleSource {
        if (!::gummyMap.isInitialized) {
            gummyMap = mapOf(
                EnumGummy.RED to listOf(
                    RED_GUMMY,
                    HARDENED_RED_GUMMY,
                    RED_GUMMY
                ),
                EnumGummy.ORANGE to listOf(
                    ORANGE_GUMMY,
                    HARDENED_ORANGE_GUMMY,
                    ORANGE_GUMMY
                ),
                EnumGummy.YELLOW to listOf(
                    YELLOW_GUMMY,
                    HARDENED_YELLOW_GUMMY,
                    YELLOW_GUMMY
                ),
                EnumGummy.WHITE to listOf(
                    WHITE_GUMMY,
                    HARDENED_WHITE_GUMMY,
                    WHITE_GUMMY
                ),
                EnumGummy.GREEN to listOf(
                    GREEN_GUMMY,
                    HARDENED_GREEN_GUMMY,
                    GREEN_GUMMY
                ),
            )
        }
        return SurfaceRules.sequence(
            // 红色软糖
            SurfaceRules.ifTrue(
                SurfaceRules.noiseCondition(Noises.SURFACE, -1.0, -0.53828),
                gummyMap[EnumGummy.RED]!![index]
            ),
            // 橙色软糖
            SurfaceRules.ifTrue(
                SurfaceRules.noiseCondition(Noises.SURFACE, -0.53828, -0.17197),
                gummyMap[EnumGummy.ORANGE]!![index]
            ),
            // 黄色软糖
            SurfaceRules.ifTrue(
                SurfaceRules.noiseCondition(Noises.SURFACE, -0.17197, 0.17197),
                gummyMap[EnumGummy.YELLOW]!![index]
            ),
            // 白色软糖
            SurfaceRules.ifTrue(
                SurfaceRules.noiseCondition(Noises.SURFACE, 0.17197, 0.53828),
                gummyMap[EnumGummy.WHITE]!![index]
            ),
            // 绿色软糖
            SurfaceRules.ifTrue(
                SurfaceRules.noiseCondition(Noises.SURFACE, 0.53828, 1.0),
                gummyMap[EnumGummy.GREEN]!![index]
            ),
            // 默认使用红色软糖
            gummyMap[EnumGummy.RED]!![index]
        )
    }

}