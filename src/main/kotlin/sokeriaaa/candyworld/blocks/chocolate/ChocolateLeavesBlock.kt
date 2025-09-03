package sokeriaaa.candyworld.blocks.chocolate

import net.minecraft.world.level.block.state.BlockState
import sokeriaaa.candyworld.blocks.CandyLeavesBlock
import sokeriaaa.candyworld.registry.ModBlocks
import sokeriaaa.candyworld.registry.ModTags

open class ChocolateLeavesBlock(properties: Properties) : CandyLeavesBlock(properties) {

    override fun isLog(state: BlockState): Boolean {
        return state.`is`(ModBlocks.WAFER_STICK_BLOCK.value)
    }

    override fun isLeaves(state: BlockState): Boolean {
        return state.`is`(ModTags.CHOCOLATE_LEAVES)
    }

}