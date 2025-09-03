package sokeriaaa.candyworld.blocks.cottoncandy

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.BushBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import sokeriaaa.candyworld.registry.ModBlocks

open class CottonCandyPlantBlock(properties: Properties) : BushBlock(properties) {
    override fun codec(): MapCodec<CottonCandyPlantBlock> {
        return CODEC
    }

    override fun getShape(
        blockState: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
        collisionContext: CollisionContext,
    ): VoxelShape {
        return SHAPE
    }

    protected override fun mayPlaceOn(
        state: BlockState,
        blockGetter: BlockGetter,
        blockPos: BlockPos,
    ): Boolean {
        return state.`is`(ModBlocks.CANDY_GRASS_BLOCK.value)
                || state.`is`(ModBlocks.MILK_BROWNIE_BLOCK.value)
                || state.`is`(ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.value)
                || state.`is`(ModBlocks.WHITE_BROWNIE_BLOCK.value)
                || state.`is`(ModBlocks.DARK_CANDY_GRASS_BLOCK.value)
                || state.`is`(ModBlocks.DARK_BROWNIE_BLOCK.value) ||
                super.mayPlaceOn(state, blockGetter, blockPos)
    }

    companion object {
        val CODEC: MapCodec<CottonCandyPlantBlock> = simpleCodec { CottonCandyPlantBlock(it) }
        val SHAPE: VoxelShape = box(2.5, 0.0, 2.5, 13.5, 15.0, 13.5)

    }
}