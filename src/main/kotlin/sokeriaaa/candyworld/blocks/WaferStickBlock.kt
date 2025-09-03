package sokeriaaa.candyworld.blocks

import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape

open class WaferStickBlock(properties: Properties) : StackableBlock(properties) {

    private val _shape: VoxelShape = sequenceOf(
        box(3.0, 0.0, 13.0, 13.0, 16.0, 16.0),
        box(0.0, 0.0, 0.0, 3.0, 16.0, 16.0),
        box(13.0, 0.0, 0.0, 16.0, 16.0, 16.0),
        box(3.0, 0.0, 0.0, 13.0, 16.0, 3.0)
    ).reduce { v1, v2 -> Shapes.or(v1, v2) }

    override fun getShape(
        blockState: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
        collisionContext: CollisionContext
    ): VoxelShape {
        return _shape
    }
}