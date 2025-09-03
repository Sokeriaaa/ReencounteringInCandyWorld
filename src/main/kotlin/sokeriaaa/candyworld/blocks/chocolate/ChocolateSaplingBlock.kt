package sokeriaaa.candyworld.blocks.chocolate

import net.minecraft.core.BlockPos
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.SaplingBlock
import net.minecraft.world.level.block.grower.TreeGrower
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.Fluid
import sokeriaaa.candyworld.registry.ModBlocks

open class ChocolateSaplingBlock(
    treeGrower: TreeGrower,
    properties: Properties,
) : SaplingBlock(treeGrower, properties) {

    init {
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0))
    }

    public override fun canBeReplaced(blockState: BlockState, fluid: Fluid): Boolean {
        return false
    }

    protected override fun mayPlaceOn(state: BlockState, blockGetter: BlockGetter, blockPos: BlockPos): Boolean {
        return state.`is`(ModBlocks.CANDY_GRASS_BLOCK.value)
                || state.`is`(ModBlocks.MILK_BROWNIE_BLOCK.value)
                || state.`is`(ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.value)
                || state.`is`(ModBlocks.WHITE_BROWNIE_BLOCK.value)
                || state.`is`(ModBlocks.DARK_CANDY_GRASS_BLOCK.value)
                || state.`is`(ModBlocks.DARK_BROWNIE_BLOCK.value)
                || super.mayPlaceOn(state, blockGetter, blockPos)
    }
}