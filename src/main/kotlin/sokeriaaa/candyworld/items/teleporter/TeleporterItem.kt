package sokeriaaa.candyworld.items.teleporter

import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

open class TeleporterItem(properties: Properties) : Item(properties) {

    override fun isFoil(itemStack: ItemStack): Boolean {
        return true
    }

    override fun finishUsingItem(
        stack: ItemStack,
        level: Level,
        livingEntity: LivingEntity,
    ): ItemStack {
        // TODO
        return stack
    }

    override fun use(
        level: Level,
        player: Player,
        interactionHand: InteractionHand,
    ): InteractionResultHolder<ItemStack> {
        val itemStack = player.getItemInHand(interactionHand)
        player.startUsingItem(interactionHand)
        return InteractionResultHolder.consume(itemStack)
    }
}