package sokeriaaa.candyworld.world.feature

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import sokeriaaa.candyworld.world.feature.config.CandySpikeConfiguration

// 糖晶尖刺Feature
// Sugar crystal spike feature
open class CandySpikeFeature(
    codec: Codec<CandySpikeConfiguration>,
) : Feature<CandySpikeConfiguration>(codec) {

    override fun place(context: FeaturePlaceContext<CandySpikeConfiguration>): Boolean {
        val level = context.level()
        val random = context.random()
        val origin = context.origin()
        val config = context.config()

        // 获取尖刺方块状态
        // Get spike block state
        val blockState = config.stateProvider.getState(random, origin)

        // 确定生成位置（是否投射到地表）
        // Determine generation position (whether to project to surface)
        val targetPos = if (config.project) {
            level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, origin)
        } else {
            origin
        }

        val mutablePos = BlockPos.MutableBlockPos()
        var placedAny = false

        // 在16x16区域生成尖刺
        // Generate spikes in 16x16 area
        for (x in 0 until 16) {
            for (z in 0 until 16) {
                mutablePos.setWithOffset(targetPos, x, 0, z)

                // 从顶部向下扫描
                // Scan from top to bottom
                var blocksToPlace = 0
                for (y in level.maxBuildHeight downTo level.minBuildHeight) {
                    mutablePos.y = y
                    val currentState = level.getBlockState(mutablePos)

                    // 检查是否在白名单中且满足生成几率
                    // Check if in whitelist and meets generation chance
                    if (config.whitelist.contains(currentState) && random.nextInt(config.chance) == 0) {
                        // 确定要放置的尖刺长度
                        // Determine the length of spike to place
                        blocksToPlace = random.nextInt(config.maxLength - config.minLength + 1) + config.minLength
                    }
                    // 如果需要放置尖刺且当前位置不在白名单中
                    // If need to place spike and current position is not in whitelist
                    else if (blocksToPlace > 0 && !config.whitelist.contains(currentState)) {
                        // 检查是否可替换或黑名单
                        // Check if replaceable or in blacklist
                        val canPlace = (config.canReplace && currentState.canBeReplaced()) ||
                                (!config.blacklist.contains(currentState))

                        if (canPlace) {
                            level.setBlock(mutablePos, blockState, 2)
                            placedAny = true
                            blocksToPlace--
                        }
                    }
                }
            }
        }

        return placedAny
    }
}