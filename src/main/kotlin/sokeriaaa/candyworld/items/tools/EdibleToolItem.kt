package sokeriaaa.candyworld.items.tools

import net.minecraft.core.component.DataComponents
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.food.FoodProperties
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

/**
 * 可食用工具
 * Edible tools
 */
interface EdibleToolItem {

    private val ItemStack.foodProperties: FoodProperties?
        get() = get(DataComponents.FOOD)

    fun calculateHealAmount(stack: ItemStack): Int {
        return ((stack.foodProperties?.nutrition ?: 0) *
                ((stack.maxDamage - stack.damageValue) / stack.maxDamage.toFloat())).toInt()
    }

    fun onItemRightClick(
        worldIn: Level,
        playerIn: Player,
        handIn: InteractionHand,
    ): InteractionResultHolder<ItemStack> {
        val stack: ItemStack = playerIn.getItemInHand(handIn)
        val foodProperties = stack.foodProperties
        if (foodProperties == null) {
            return InteractionResultHolder.pass(playerIn.getItemInHand(handIn))
        } else {
            if (playerIn.canEat(foodProperties.canAlwaysEat()) && playerIn.isShiftKeyDown) {
                playerIn.startUsingItem(handIn)
                return InteractionResultHolder.consume(stack)
            } else {
                return InteractionResultHolder.fail(stack)
            }
        }
    }

    fun onItemUseFinish(
        stack: ItemStack,
        worldIn: Level,
        entityLiving: LivingEntity
    ): ItemStack {
        return if (stack.foodProperties == null) {
            stack
        } else {
            entityLiving.eat(worldIn, stack)
        }
    }


}