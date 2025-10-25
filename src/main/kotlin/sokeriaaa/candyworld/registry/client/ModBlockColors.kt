package sokeriaaa.candyworld.registry.client

import net.minecraft.client.color.block.BlockColor
import net.minecraft.world.level.block.Block
import sokeriaaa.candyworld.registry.ModBlocks

object ModBlockColors {

    fun registerBlockColors(
        registerBlockColor: (BlockColor, Array<Block>) -> Unit,
    ) {
        registerBlockColor(
            simpleBlockColor(0xff4530),
            arrayOf(
                ModBlocks.RED_GUMMY_BLOCK.value,
                ModBlocks.RED_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.RED_GUMMY_WORKBENCH.value,
                ModBlocks.RED_GUMMY_WORM_BLOCK.value,
            ),
        )
        registerBlockColor(
            simpleBlockColor(0xff9b4f),
            arrayOf(
                ModBlocks.ORANGE_GUMMY_BLOCK.value,
                ModBlocks.ORANGE_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.ORANGE_GUMMY_WORKBENCH.value,
                ModBlocks.ORANGE_GUMMY_WORM_BLOCK.value,
            ),
        )
        registerBlockColor(
            simpleBlockColor(0xffe563),
            arrayOf(
                ModBlocks.YELLOW_GUMMY_BLOCK.value,
                ModBlocks.YELLOW_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.YELLOW_GUMMY_WORKBENCH.value,
                ModBlocks.YELLOW_GUMMY_WORM_BLOCK.value,
            ),
        )
        registerBlockColor(
            simpleBlockColor(0xfffeb0),
            arrayOf(
                ModBlocks.WHITE_GUMMY_BLOCK.value,
                ModBlocks.WHITE_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.WHITE_GUMMY_WORKBENCH.value,
                ModBlocks.WHITE_GUMMY_WORM_BLOCK.value,
            ),
        )
        registerBlockColor(
            simpleBlockColor(0x80e22b),
            arrayOf(
                ModBlocks.GREEN_GUMMY_BLOCK.value,
                ModBlocks.GREEN_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.GREEN_GUMMY_WORKBENCH.value,
                ModBlocks.GREEN_GUMMY_WORM_BLOCK.value,
            ),
        )
    }

    private fun simpleBlockColor(color: Int): BlockColor = BlockColor { _, _, _, _ -> color }
}