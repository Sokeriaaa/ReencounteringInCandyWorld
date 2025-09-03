package sokeriaaa.candyworld.blocks.workbench

import net.minecraft.core.BlockPos
import net.minecraft.network.chat.Component
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleMenuProvider
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.ContainerLevelAccess
import net.minecraft.world.inventory.CraftingMenu
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.CraftingTableBlock
import net.minecraft.world.level.block.state.BlockState

open class ModCraftingTableBlock(properties: Properties) : CraftingTableBlock(properties) {

    override fun getMenuProvider(state: BlockState, level: Level, pos: BlockPos): MenuProvider {
        return SimpleMenuProvider(
            { containerId, playerInventory, _ ->
                val access = ContainerLevelAccess.create(level, pos)
                object : CraftingMenu(
                    containerId,
                    playerInventory,
                    access,
                ) {
                    override fun stillValid(player: Player): Boolean {
                        return stillValid(access, player, this@ModCraftingTableBlock)
                    }
                }
            },
            CONTAINER_TITLE,
        )
    }

    companion object {
        private val CONTAINER_TITLE: Component = Component.translatable("container.crafting")
    }
}