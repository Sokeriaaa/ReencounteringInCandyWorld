package sokeriaaa.candyworld.entity.ai

import net.minecraft.core.BlockPos
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate
import sokeriaaa.candyworld.platform.Platform
import sokeriaaa.candyworld.registry.ModBlocks
import java.util.*
import java.util.function.Predicate
import kotlin.math.max

open class EatCandyGrassGoal(
    private val mob: Mob,
) : Goal() {

    private val entityWorld: Level = mob.level()
    private var eatingGrassTimer: Int = 0

    init {
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP))
    }

    override fun canUse(): Boolean {
        return if (mob.getRandom().nextInt(if (this.mob.isBaby) 50 else 1000) != 0) {
            false
        } else {
            val blockPos: BlockPos = this.mob.blockPosition()
            if (IS_TALL_GRASS.test(this.entityWorld.getBlockState(blockPos))) {
                true
            } else {
                this.entityWorld.getBlockState(blockPos.below()).`is`(ModBlocks.CANDY_GRASS_BLOCK.value)
            }
        }
    }

    override fun start() {
        this.eatingGrassTimer = 40
        this.entityWorld.broadcastEntityEvent(this.mob, 10.toByte())
        this.mob.getNavigation().stop()
    }

    override fun stop() {
        this.eatingGrassTimer = 0
    }

    override fun canContinueToUse(): Boolean {
        return this.eatingGrassTimer > 0
    }

    /**
     * Number of ticks since the entity started to eat grass
     */
    fun getEatingGrassTimer(): Int {
        return this.eatingGrassTimer
    }

    override fun tick() {
        this.eatingGrassTimer = max(0, this.eatingGrassTimer - 1)

        if (this.eatingGrassTimer == 4) {
            val blockPos: BlockPos = this.mob.blockPosition()

            if (IS_TALL_GRASS.test(this.entityWorld.getBlockState(blockPos))) {
                if (Platform.entity.canEntityGrief(this.entityWorld, this.mob)) {
                    this.entityWorld.destroyBlock(blockPos, false)
                }

                this.mob.ate()
            } else {
                val blockPos1: BlockPos = blockPos.below()

                if (this.entityWorld.getBlockState(blockPos1).`is`(ModBlocks.CANDY_GRASS_BLOCK.value)) {
                    if (Platform.entity.canEntityGrief(this.entityWorld, this.mob)) {
                        // sound played / particles
                        this.entityWorld.levelEvent(
                            2001,
                            blockPos1,
                            Block.getId(ModBlocks.COTTON_CANDY_LEAVES.value.defaultBlockState())
                        )
                        // block replacement
                        this.entityWorld.setBlock(blockPos1, ModBlocks.MILK_BROWNIE_BLOCK.value.defaultBlockState(), 2)
                    }

                    this.mob.ate()
                }
            }
        }
    }

    companion object {
        private val IS_TALL_GRASS: Predicate<BlockState> =
            BlockStatePredicate.forBlock(ModBlocks.COTTON_CANDY_PLANT.value)
    }

}