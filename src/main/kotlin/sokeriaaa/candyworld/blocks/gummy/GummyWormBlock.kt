package sokeriaaa.candyworld.blocks.gummy

import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SlimeBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.EnumProperty

open class GummyWormBlock(properties: Properties) : SlimeBlock(properties) {

    init {
        this.registerDefaultState(super.defaultBlockState().setValue(AXIS, Direction.Axis.X))
    }

    protected override fun createBlockStateDefinition(
        builder: StateDefinition.Builder<Block, BlockState>,
    ) {
        builder.add(AXIS)
    }

    override fun getStateForPlacement(blockPlaceContext: BlockPlaceContext): BlockState? {
        return this.defaultBlockState().setValue(AXIS, blockPlaceContext.clickedFace.axis)
    }

    companion object {
        val AXIS: EnumProperty<Direction.Axis> = BlockStateProperties.AXIS
    }
}