package sokeriaaa.candyworld.blocks.cottoncandy

import net.minecraft.world.level.block.state.BlockState
import sokeriaaa.candyworld.blocks.CandyLeavesBlock
import sokeriaaa.candyworld.registry.ModBlocks
import sokeriaaa.candyworld.registry.ModTags

open class CottonCandyLeavesBlock(properties: Properties) : CandyLeavesBlock(properties) {

    override fun isLog(state: BlockState): Boolean {
        return state.`is`(ModBlocks.WHITE_CANDY_CANE_BLOCK.value)
    }

    override fun isLeaves(state: BlockState): Boolean {
        return state.`is`(ModTags.COTTON_CANDY_LEAVES)
    }

}