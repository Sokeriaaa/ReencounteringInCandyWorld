package sokeriaaa.candyworld.blocks

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import sokeriaaa.candyworld.configs.CandyConfig

open class StackableBlock(
    properties: Properties,
    val checkStackableConfig: Boolean = true,
    val checkRecursiveConfig: Boolean = true,
) : Block(properties) {
    public override fun neighborChanged(
        blockState: BlockState,
        level: Level,
        blockPos: BlockPos,
        block: Block,
        blockPos2: BlockPos,
        isMoving: Boolean
    ) {
        if (!this.canSurvive(blockState, level, blockPos)) {
            level.destroyBlock(blockPos, true)
        }
        super.neighborChanged(blockState, level, blockPos, block, blockPos2, isMoving)
    }

    public override fun updateShape(
        stateIn: BlockState,
        direction: Direction,
        blockState2: BlockState,
        levelAccessor: LevelAccessor,
        blockPos: BlockPos,
        blockPos2: BlockPos
    ): BlockState {
        return if (!stateIn.canSurvive(levelAccessor, blockPos)) {
            Blocks.AIR.defaultBlockState()
        } else {
            super.updateShape(stateIn, direction, blockState2, levelAccessor, blockPos, blockPos2)
        }
    }

    public override fun canSurvive(
        blockState: BlockState,
        levelReader: LevelReader,
        pos: BlockPos,
    ): Boolean {
        if (checkStackableConfig) {
            if (!CandyConfig.COMMON.stackableTreeTrunks) {
                return super.canSurvive(blockState, levelReader, pos)
            }
            return levelReader.getBlockState(pos.below())
                .isFaceSturdy(levelReader, pos.below(), Direction.UP)
                    || levelReader.getBlockState(pos.below())
                .block is StackableBlock
        } else {
            return super.canSurvive(blockState, levelReader, pos)
        }
    }
}