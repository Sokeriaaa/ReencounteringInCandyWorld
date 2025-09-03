package sokeriaaa.candyworld.blocks

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.LeavesBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Fluids
import kotlin.math.min

abstract class CandyLeavesBlock(properties: Properties) : LeavesBlock(properties) {

    abstract fun isLog(state: BlockState): Boolean
    abstract fun isLeaves(state: BlockState): Boolean

    public override fun tick(
        state: BlockState,
        serverLevel: ServerLevel,
        blockPos: BlockPos,
        randomSource: RandomSource
    ) {
        serverLevel.setBlock(blockPos, myUpdateDistance(state, serverLevel, blockPos), 3)
    }

    public override fun updateShape(
        blockState: BlockState,
        direction: Direction,
        blockState2: BlockState,
        levelAccessor: LevelAccessor,
        blockPos: BlockPos,
        blockPos2: BlockPos
    ): BlockState {
        if (blockState.getValue(WATERLOGGED) as Boolean) {
            levelAccessor.scheduleTick(
                blockPos,
                Fluids.WATER,
                Fluids.WATER.getTickDelay(levelAccessor),
            )
        }

        val i = myGetDistanceAt(blockState2) + 1
        if (i != 1 || blockState.getValue(DISTANCE) as Int != i) {
            levelAccessor.scheduleTick(blockPos, this, 1)
        }

        return blockState
    }


    override fun getStateForPlacement(blockPlaceContext: BlockPlaceContext): BlockState {
        val fluidState = blockPlaceContext.level.getFluidState(blockPlaceContext.clickedPos)
        val blockState = this.defaultBlockState()
            .setValue(PERSISTENT, true)
            .setValue(
                WATERLOGGED,
                fluidState.type === Fluids.WATER
            )
        return myUpdateDistance(blockState, blockPlaceContext.level, blockPlaceContext.clickedPos)
    }

    private fun myUpdateDistance(
        blockState: BlockState,
        levelAccessor: LevelAccessor,
        blockPos: BlockPos,
    ): BlockState {
        var i = 7
        val mutableBlockPos = BlockPos.MutableBlockPos()

        for (direction in Direction.entries) {
            mutableBlockPos.setWithOffset(blockPos, direction)
            i = min(i, myGetDistanceAt(levelAccessor.getBlockState(mutableBlockPos)) + 1)
            if (i == 1) {
                break
            }
        }

        return blockState.setValue(DISTANCE, i)
    }

    private fun myGetDistanceAt(state: BlockState): Int {
        return if (isLog(state)) {
            0
        } else {
            if (isLeaves(state) && state.hasProperty(DISTANCE)) {
                state.getValue(DISTANCE)
            } else {
                7
            }
        }
    }

}