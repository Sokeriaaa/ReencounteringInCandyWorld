package sokeriaaa.candyworld.world.feature

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
import sokeriaaa.candyworld.blocks.gummy.GummyWormBlock
import sokeriaaa.candyworld.enums.EnumGummy
import sokeriaaa.candyworld.registry.ModBlocks

open class GummyWormFeature(
    codec: Codec<NoneFeatureConfiguration>,
) : Feature<NoneFeatureConfiguration>(codec) {
    override fun place(
        featurePlaceContext: FeaturePlaceContext<NoneFeatureConfiguration>
    ): Boolean {
        val worldGenLevel = featurePlaceContext.level()
        val blockPos = featurePlaceContext.origin()
        val randomSource = featurePlaceContext.random()
        val surfacePos: BlockPos = worldGenLevel.getHeightmapPos(
            Heightmap.Types.WORLD_SURFACE_WG,
            blockPos,
        )

        if (worldGenLevel.getBlockState(surfacePos.below()).block is GummyWormBlock) {
            return false
        }

        val state: BlockState
        when (EnumGummy.random(randomSource)) {
            EnumGummy.ORANGE -> state = ORANGE_GUMMY_WORM
            EnumGummy.YELLOW -> state = YELLOW_GUMMY_WORM
            EnumGummy.WHITE -> state = WHITE_GUMMY_WORM
            EnumGummy.GREEN -> state = GREEN_GUMMY_WORM
            else -> state = RED_GUMMY_WORM
        }

        val r = randomSource.nextInt(3)
        return when (r) {
            0 -> generateWormFlat(worldGenLevel, surfacePos, randomSource.nextInt(10) + 7, state, randomSource)
            1 -> generateWormStraight(
                worldGenLevel,
                surfacePos,
                randomSource.nextInt(12) + 6,
                randomSource.nextInt(4) + 3,
                state
            )

            2 -> generateWormArc(worldGenLevel, surfacePos, state, randomSource)
            else -> false
        }
    }

    private fun generateWormStraight(
        worldGenLevel: WorldGenLevel,
        position: BlockPos,
        below: Int,
        above: Int,
        state: BlockState
    ): Boolean {
        for (i in -below..<above) {
            worldGenLevel.setBlock(
                position.above(i),
                state.setValue(BlockStateProperties.AXIS, Direction.Axis.Y),
                2 or 16,
            )
        }
        return true
    }

    private fun generateWormArc(
        worldGenLevel: WorldGenLevel,
        position: BlockPos,
        state: BlockState,
        randomSource: RandomSource,
    ): Boolean {
        var state = state
        val height = randomSource.nextInt(2) + 2
        val startDepth = randomSource.nextInt(4) + 4
        val direction: Direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource)

        state = state.setValue(BlockStateProperties.AXIS, Direction.Axis.Y)
        var pos = position.below(startDepth)
        for (i in 0..height + startDepth) {
            pos = pos.above()
            worldGenLevel.setBlock(pos, state, 2 or 16)
        }

        state = state.setValue(BlockStateProperties.AXIS, direction.axis)
        for (i in 0..2 + randomSource.nextInt(2)) {
            if (worldGenLevel.isAreaLoaded(pos.relative(direction), 2)) {
                pos = pos.relative(direction)
                worldGenLevel.setBlock(pos, state, 2 or 16)
            } else {
                break
            }
        }

        state = state.setValue(BlockStateProperties.AXIS, Direction.Axis.Y)
        while (isAirOrLiquid(worldGenLevel, pos.below())) {
            pos = pos.below()
            worldGenLevel.setBlock(pos, state, 2 or 16)
        }
        for (i in 0..4 + randomSource.nextInt(4)) {
            pos = pos.below()
            worldGenLevel.setBlock(pos, state, 2 or 16)
        }
        return true
    }

    private fun generateWormFlat(
        worldGenLevel: WorldGenLevel,
        position: BlockPos,
        length: Int,
        state: BlockState,
        randomSource: RandomSource,
    ): Boolean {
        var pos = position.above()
        var direction: Direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource)
        var lastTurnDir = 0
        var hasTurned = false
        var i = 0
        while (i <= length) {
            worldGenLevel.setBlock(pos, state.setValue(BlockStateProperties.AXIS, direction.axis), 2 or 16)

            // randomly change direction
            if (hasTurned) {
                hasTurned = false
            } else if (randomSource.nextInt(3) == 0) {
                direction = direction.clockWise
                if (lastTurnDir == 1 || (lastTurnDir == 0 && randomSource.nextBoolean())) {
                    direction = direction.clockWise.clockWise
                    lastTurnDir = -1
                } else {
                    lastTurnDir = 1
                }
                hasTurned = true
            }

            if (!worldGenLevel.isAreaLoaded(pos.relative(direction), 2)) {
                break
            }


            // fall
            while (isAirOrLiquid(worldGenLevel, pos.below())) {
                pos = pos.below()
                worldGenLevel.setBlock(pos, state.setValue(BlockStateProperties.AXIS, Direction.Axis.Y), 2 or 16)
                i++
            }

            // climb
            while (!isAirOrLiquid(worldGenLevel, pos.relative(direction))) {
                pos = pos.above()
                if (!isAirOrLiquid(worldGenLevel, pos)) {
                    return true
                }
                worldGenLevel.setBlock(pos, state.setValue(BlockStateProperties.AXIS, Direction.Axis.Y), 2 or 16)
                i++
            }

            pos = pos.relative(direction)
            i++
        }
        return true
    }

    private fun isAirOrLiquid(
        worldGenLevel: WorldGenLevel,
        pos: BlockPos,
    ): Boolean {
        return isAir(worldGenLevel, pos)
                || !worldGenLevel.getFluidState(pos).isEmpty
    }

    fun isAir(
        worldGenLevel: WorldGenLevel,
        pos: BlockPos,
    ): Boolean {
        return worldGenLevel.isStateAtPosition(pos, BlockState::isAir)
    }

    companion object {
        private val RED_GUMMY_WORM = ModBlocks.RED_GUMMY_WORM_BLOCK.value.defaultBlockState()
        private val ORANGE_GUMMY_WORM = ModBlocks.ORANGE_GUMMY_WORM_BLOCK.value.defaultBlockState()
        private val YELLOW_GUMMY_WORM = ModBlocks.YELLOW_GUMMY_WORM_BLOCK.value.defaultBlockState()
        private val WHITE_GUMMY_WORM = ModBlocks.WHITE_GUMMY_WORM_BLOCK.value.defaultBlockState()
        private val GREEN_GUMMY_WORM = ModBlocks.GREEN_GUMMY_WORM_BLOCK.value.defaultBlockState()
    }
}