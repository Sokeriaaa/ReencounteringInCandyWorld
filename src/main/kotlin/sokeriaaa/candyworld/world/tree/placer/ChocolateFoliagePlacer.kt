package sokeriaaa.candyworld.world.tree.placer

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.LevelSimulatedReader
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType
import sokeriaaa.candyworld.registry.ModTags
import sokeriaaa.candyworld.world.ModFoliagePlacer
import kotlin.math.abs

open class ChocolateFoliagePlacer(
    radius: IntProvider,
    offset: IntProvider,
    private val trunkHeight: IntProvider
) : FoliagePlacer(radius, offset) {

    companion object {
        // 使用 MapCodec 而不是 Codec
        // Using MapCodec instead of Codec
        val CODEC: MapCodec<ChocolateFoliagePlacer> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                IntProvider.CODEC.fieldOf("radius").forGetter { it.radius },
                IntProvider.CODEC.fieldOf("offset").forGetter { it.offset },
                IntProvider.CODEC.fieldOf("trunk_height").forGetter { it.trunkHeight }
            ).apply(instance, ::ChocolateFoliagePlacer)
        }
    }

    override fun type(): FoliagePlacerType<*> {
        return ModFoliagePlacer.CHOCOLATE_FOLIAGE_PLACER.value
    }

    // 修正后的 createFoliage 方法签名
    // Corrected createFoliage method signature
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

        val height = 3

        // 生成树叶层
        // Generate foliage layers
        for (y in blockPos.y - 3 + height..blockPos.y + height) {
            val yPlusHeight = y - (blockPos.y + height)
            val l2 = 1 - yPlusHeight / 2
            for (x in blockPos.x - l2..blockPos.x + l2) {
                val j1 = x - blockPos.x
                for (z in blockPos.z - l2..blockPos.z + l2) {
                    val l1 = z - blockPos.z
                    if (shouldSkipLocation(random, j1, yPlusHeight, l1, l2, false)) {
                        val mutablePos = BlockPos.MutableBlockPos(x, y, z)

                        if (isAirOrLeaves(level, mutablePos)) {
                            // 使用 blockSetter 而不是直接设置方块
                            // Use blockSetter instead of directly setting blocks
                            blockSetter.set(mutablePos, leafState)
                        }
                    }
                }
            }
        }

        // 生成树干
        // Generate trunk
        for (j2 in 0 until height) {
            val abovePos = blockPos.above(j2)
            setLogBlock(level, blockSetter, abovePos, trunkState)
        }
    }

    private fun setLogBlock(
        level: LevelSimulatedReader,
        blockSetter: FoliageSetter,
        pos: BlockPos,
        trunkState: BlockState
    ) {
        if (isAirOrLeaves(level, pos)) {
            // 使用 blockSetter 设置树干方块
            // Use blockSetter to set trunk blocks
            blockSetter.set(pos, trunkState)
        }
    }

    private fun isAirOrLeaves(reader: LevelSimulatedReader, pos: BlockPos): Boolean {
        return reader.isStateAtPosition(pos) { state ->
            state.isAir || state.`is`(ModTags.CHOCOLATE_LEAVES)
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
        return abs(localX) != radius || abs(localZ) != radius ||
                (random.nextInt(2) != 0 && localY != 0)
    }
}