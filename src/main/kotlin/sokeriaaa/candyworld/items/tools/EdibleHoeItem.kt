package sokeriaaa.candyworld.items.tools

import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.HoeItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Tier
import net.minecraft.world.item.UseAnim
import net.minecraft.world.level.Level

open class EdibleHoeItem(
    tier: Tier,
    properties: Properties,
) : HoeItem(tier, properties), EdibleToolItem {

    ///////////////////////////////////////////////////////////////////////////
    // Food implementation
    ///////////////////////////////////////////////////////////////////////////
    override fun getUseDuration(itemStack: ItemStack, livingEntity: LivingEntity): Int {
        return 32
    }

    override fun getUseAnimation(stack: ItemStack): UseAnim {
        return UseAnim.EAT
    }

    override fun use(
        level: Level,
        player: Player,
        interactionHand: InteractionHand,
    ): InteractionResultHolder<ItemStack> {
        return super.onItemRightClick(level, player, interactionHand)
    }

    override fun finishUsingItem(
        itemStack: ItemStack,
        level: Level,
        livingEntity: LivingEntity,
    ): ItemStack {
        return super.onItemUseFinish(itemStack, level, livingEntity)
    }
}