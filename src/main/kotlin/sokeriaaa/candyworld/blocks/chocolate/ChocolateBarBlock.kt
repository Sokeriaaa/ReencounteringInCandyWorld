package sokeriaaa.candyworld.blocks.chocolate

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

open class ChocolateBarBlock(properties: Properties) : Block(properties) {

    init {
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH))
    }

    public override fun getShape(
        blockState: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
        collisionContext: CollisionContext
    ): VoxelShape {
        return when (blockState.getValue(FACING)) {
            Direction.NORTH, Direction.SOUTH -> SHAPE_NS
            else -> SHAPE_EW
        }
    }

    public override fun neighborChanged(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        block: Block,
        blockPos2: BlockPos,
        isMoving: Boolean
    ) {
        if (!this.canSurvive(state, level, pos)) {
            level.destroyBlock(pos, true)
        }
        super.neighborChanged(state, level, pos, block, blockPos2, isMoving)
    }

    public override fun canSurvive(
        state: BlockState,
        levelReader: LevelReader,
        pos: BlockPos,
    ): Boolean {
        val blockPos = pos.below()
        if (state.block === this) {
            // Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return levelReader.getBlockState(blockPos).isFaceSturdy(levelReader, pos, Direction.UP)
        }
        return super.canSurvive(state, levelReader, pos)
    }

    protected override fun createBlockStateDefinition(
        builder: StateDefinition.Builder<Block, BlockState>,
    ) {
        builder.add(FACING)
    }

    override fun getStateForPlacement(blockPlaceContext: BlockPlaceContext): BlockState? {
        return this.defaultBlockState().setValue(FACING, blockPlaceContext.horizontalDirection)
    }

    companion object {
        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        protected val SHAPE_NS: VoxelShape = box(2.5, 0.0, 5.5, 13.5, 14.5, 10.5)
        protected val SHAPE_EW: VoxelShape = box(5.5, 0.0, 2.5, 10.5, 14.5, 13.5)
    }
}