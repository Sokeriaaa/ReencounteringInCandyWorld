package sokeriaaa.candyworld.world.tree.placer

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType
import sokeriaaa.candyworld.registry.ModTags
import sokeriaaa.candyworld.world.ModFoliagePlacer

open class CottonCandyFoliagePlacer(
    radius: IntProvider,
    offset: IntProvider,
    private val trunkHeight: IntProvider
) : FoliagePlacer(radius, offset) {

    companion object {
        // 使用 MapCodec 而不是 Codec
        // Using MapCodec instead of Codec
        val CODEC: MapCodec<CottonCandyFoliagePlacer> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                IntProvider.CODEC.fieldOf("radius").forGetter { it.radius },
                IntProvider.CODEC.fieldOf("offset").forGetter { it.offset },
                IntProvider.CODEC.fieldOf("trunk_height").forGetter { it.trunkHeight }
            ).apply(instance, ::CottonCandyFoliagePlacer)
        }
    }

    override fun type(): FoliagePlacerType<*> {
        return ModFoliagePlacer.COTTON_CANDY_FOLIAGE_PLACER.value
    }

    // 修正后的 createFoliage 方法
    // Corrected createFoliage method
    override fun createFoliage(
        level: LevelSimulatedReader,
        blockSetter: FoliageSetter,
        random: RandomSource,
        config: TreeConfiguration,
        maxFreeTreeHeight: Int,
        attachment: FoliageAttachment,
        foliageHeight: Int,
        foliageRadius: Int,
        offset: Int
    ) {
        val blockPos = attachment.pos()
        val leafState = config.foliageProvider.getState(random, blockPos)
        val trunkState = config.trunkProvider.getState(random, blockPos)

        var currentY = -2

        // 按照层次放置树叶
        // Place foliage by layers
        placeLayer1(level, blockSetter, blockPos.above(currentY++), leafState)
        placeLayer2(level, blockSetter, blockPos.above(currentY++), leafState)
        placeLayer3(level, blockSetter, blockPos.above(currentY++), leafState)
        placeLayer4(level, blockSetter, blockPos.above(currentY++), leafState)
        placeLayer4(level, blockSetter, blockPos.above(currentY++), leafState)
        placeLayer3(level, blockSetter, blockPos.above(currentY++), leafState)
        placeLayer2(level, blockSetter, blockPos.above(currentY++), leafState)
        placeLayer1(level, blockSetter, blockPos.above(currentY), leafState)

        // 生成树干
        // Generate trunk
        for (j2 in 0 until 5) {
            val abovePos = blockPos.above(j2)
            setLogBlock(level, blockSetter, abovePos, 0, 0, 0, trunkState)
        }
    }

    // 更新辅助方法以使用 FoliageSetter
    // Update helper methods to use FoliageSetter
    private fun placeLayer1(
        level: LevelSimulatedReader,
        blockSetter: FoliageSetter,
        pos: BlockPos,
        leafState: BlockState
    ) {
        for (x in -1..1) {
            for (z in -1..1) {
                setLeafBlock(level, blockSetter, pos, x, 0, z, leafState)
            }
        }
    }

    private fun placeLayer2(
        level: LevelSimulatedReader,
        blockSetter: FoliageSetter,
        pos: BlockPos,
        leafState: BlockState
    ) {
        placeLayerSquare(level, blockSetter, pos, leafState)
        setAir(level, blockSetter, pos, 2, 0, 2)
        setAir(level, blockSetter, pos, 2, 0, -2)
        setAir(level, blockSetter, pos, -2, 0, 2)
        setAir(level, blockSetter, pos, -2, 0, -2)
    }

    private fun placeLayer3(
        level: LevelSimulatedReader,
        blockSetter: FoliageSetter,
        pos: BlockPos,
        leafState: BlockState
    ) {
        placeLayerSquare(level, blockSetter, pos, leafState)
        setLeafBlock(level, blockSetter, pos, 3, 0, 0, leafState)
        setLeafBlock(level, blockSetter, pos, -3, 0, 0, leafState)
        setLeafBlock(level, blockSetter, pos, 0, 0, -3, leafState)
        setLeafBlock(level, blockSetter, pos, 0, 0, 3, leafState)
    }

    private fun placeLayer4(
        level: LevelSimulatedReader,
        blockSetter: FoliageSetter,
        pos: BlockPos,
        leafState: BlockState
    ) {
        placeLayerSquare(level, blockSetter, pos, leafState)
        for (i in -1..1) {
            setLeafBlock(level, blockSetter, pos, i, 0, 3, leafState)
            setLeafBlock(level, blockSetter, pos, i, 0, -3, leafState)
            setLeafBlock(level, blockSetter, pos, 3, 0, i, leafState)
            setLeafBlock(level, blockSetter, pos, -3, 0, i, leafState)
        }
    }

    private fun placeLayerSquare(
        level: LevelSimulatedReader,
        blockSetter: FoliageSetter,
        pos: BlockPos,
        leafState: BlockState
    ) {
        for (x in -2..2) {
            for (z in -2..2) {
                setLeafBlock(level, blockSetter, pos, x, 0, z, leafState)
            }
        }
    }

    private fun setLeafBlock(
        level: LevelSimulatedReader,
        blockSetter: FoliageSetter,
        pos: BlockPos,
        xOffset: Int,
        yOffset: Int,
        zOffset: Int,
        leafState: BlockState
    ) {
        val targetPos = pos.offset(xOffset, yOffset, zOffset)
        if (isAirOrLeaves(level, targetPos)) {
            blockSetter.set(targetPos, leafState)
        }
    }


    private fun setLogBlock(
        level: LevelSimulatedReader,
        blockSetter: FoliageSetter,
        pos: BlockPos,
        xOffset: Int,
        yOffset: Int,
        zOffset: Int,
        trunkState: BlockState
    ) {
        val mutablePos = BlockPos.MutableBlockPos()
        mutablePos.setWithOffset(pos, xOffset, yOffset, zOffset)
        if (isAirOrLeaves(level, mutablePos)) {
            blockSetter.set(mutablePos, trunkState)
        }
    }

    private fun setAir(
        level: LevelSimulatedReader,
        blockSetter: FoliageSetter,
        pos: BlockPos,
        xOffset: Int,
        yOffset: Int,
        zOffset: Int,
    ) {
        val mutablePos = BlockPos.MutableBlockPos()
        mutablePos.setWithOffset(pos, xOffset, yOffset, zOffset)
        if (isAirOrLeaves(level, mutablePos)) {
            blockSetter.set(mutablePos, Blocks.AIR.defaultBlockState())
        }
    }

    private fun isAirOrLeaves(reader: LevelSimulatedReader, pos: BlockPos): Boolean {
        return reader.isStateAtPosition(pos) { state ->
            state.isAir || state.`is`(ModTags.COTTON_CANDY_LEAVES)
        }
    }

    override fun foliageHeight(random: RandomSource, height: Int, config: TreeConfiguration): Int {
        return maxOf(4, height - trunkHeight.sample(random))
    }

    override fun foliageRadius(random: RandomSource, height: Int): Int {
        return radius.sample(random)
    }

    override fun shouldSkipLocation(
        random: RandomSource,
        localX: Int,
        localY: Int,
        localZ: Int,
        radius: Int,
        large: Boolean
    ): Boolean {
        return localX == radius && localZ == radius &&
                (random.nextInt(2) == 0 || localY == 0)
    }
}