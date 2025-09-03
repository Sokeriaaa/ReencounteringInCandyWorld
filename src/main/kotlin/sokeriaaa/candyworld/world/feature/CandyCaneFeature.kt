package sokeriaaa.candyworld.world.feature

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration

open class CandyCaneFeature(
    codec: Codec<RandomPatchConfiguration>,
) : RandomPatchFeature(codec) {
    override fun place(
        config: RandomPatchConfiguration,
        level: WorldGenLevel,
        chunkGenerator: ChunkGenerator,
        random: RandomSource,
        origin: BlockPos
    ): Boolean {
        // 检查基础方块是否适合
        // Check if the base block fits
        val belowPos = origin.below()
        if (!level.getBlockState(belowPos).isSolid) {
            return false
        }

        // 尝试从 RandomPatchConfiguration 中获取 PlacedFeature，然后获取其配置
        // Try to get the PlacedFeature from RandomPatchConfiguration, then get its configuration
        val placedFeatureHolder = config.feature
        val placedFeature = placedFeatureHolder.value()

        // 关键：获取 PlacedFeature 所持有的 ConfiguredFeature
        // Key: Get the ConfiguredFeature held by the PlacedFeature
        val configuredFeature = placedFeature.feature().value()

        // 检查是否是 SimpleBlockFeature 及其配置
        // Check if it's a SimpleBlockFeature and its configuration
        if (configuredFeature.feature() != SIMPLE_BLOCK) {
            return false // 或者处理其他类型的特征
        }

        // 现在可以安全地获取 SimpleBlockConfiguration
        // Now we can safely get the SimpleBlockConfiguration
        val simpleBlockConfig = configuredFeature.config() as SimpleBlockConfiguration

        // 从 SimpleBlockConfiguration 的 BlockStateProvider 中获取一个随机方块状态（用于整个柱子）
        // Get a random block state from the SimpleBlockConfiguration's BlockStateProvider (for the entire pillar)
        val blockState: BlockState = simpleBlockConfig.toPlace().getState(random, origin)

        // 随机确定柱子高度 (例如 1-16 格)
        // Randomly determine the height of the pillar (e.g., 1-16 blocks)
        val height = random.nextInt(16) + 1

        // 生成糖果柱子
        // Generate pillar
        for (i in 0 until height) {
            val currentPos = origin.above(i)
            level.setBlock(currentPos, blockState, 2)
        }

        return true
    }
}