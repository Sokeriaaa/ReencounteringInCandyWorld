package sokeriaaa.candyworld.entity.ai

import net.minecraft.world.entity.ai.goal.PanicGoal
import sokeriaaa.candyworld.entity.EasterChicken

open class EasterChickenPanicGoal(
    private val creature: EasterChicken,
    speedIn: Double,
) : PanicGoal(creature, speedIn) {

    override fun canUse(): Boolean {
        if (this.creature.explodeWhenDone) {
            return this.findRandomPosition()
        }
        return super.canUse()
    }

    override fun canContinueToUse(): Boolean {
        if (!this.creature.getNavigation().isDone) {
            return true
        }
        if (creature.explodeWhenDone) {
            this.creature.hurt(creature.damageSources().generic(), 0.0f)
        }
        return false
    }
}