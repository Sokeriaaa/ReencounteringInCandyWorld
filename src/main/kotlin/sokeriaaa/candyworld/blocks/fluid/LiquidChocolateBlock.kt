package sokeriaaa.candyworld.blocks.fluid

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.FluidTags
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.material.FlowingFluid
import sokeriaaa.candyworld.registry.ModBlocks

open class LiquidChocolateBlock(
    flowingFluid: FlowingFluid,
    properties: Properties,
) : LiquidBlock(flowingFluid, properties) {

    public override fun neighborChanged(
        blockState: BlockState,
        level: Level,
        pos: BlockPos,
        block: Block,
        blockPos2: BlockPos,
        isMoving: Boolean
    ) {
        this.checkForMixing(level, pos, blockState)
        super.neighborChanged(blockState, level, pos, block, blockPos2, isMoving)
    }

    protected fun checkForMixing(
        level: Level,
        pos: BlockPos,
        state: BlockState,
    ) {
        if (state.getValue(LEVEL) != 0) {
            return
        }
        var flag = false
        for (direction in Direction.entries) {
            if (direction !== Direction.DOWN) {
                val blockpos = pos.relative(direction)
                if (level.getFluidState(blockpos).`is`(FluidTags.WATER)) {
                    flag = true
                    break
                }
            }
        }
        if (flag) {
            level.setBlockAndUpdate(pos, ModBlocks.MILK_CHOCOLATE_BLOCK.value.defaultBlockState())
            this.triggerMixEffects(level, pos)
        }
    }

    protected fun triggerMixEffects(
        level: Level,
        pos: BlockPos,
    ) {
        val d0 = pos.x.toDouble()
        val d1 = pos.y.toDouble()
        val d2 = pos.z.toDouble()
        level.playSound(
            null,
            pos,
            SoundEvents.LAVA_EXTINGUISH,
            SoundSource.BLOCKS,
            0.5f,
            2.6f + (level.random.nextFloat() - level.random.nextFloat()) * 0.8f
        )

        repeat(8) {
            level.addParticle(
                ParticleTypes.LARGE_SMOKE,
                d0 + Math.random(),
                d1 + 1.2,
                d2 + Math.random(),
                0.0,
                0.0,
                0.0,
            )
        }
    }
}