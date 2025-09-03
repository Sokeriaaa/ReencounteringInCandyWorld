package sokeriaaa.candyworld.blocks.candysoil

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import sokeriaaa.candyworld.registry.ModBlocks

open class CandyGrassBlock(properties: Properties) : Block(properties) {

    public override fun randomTick(
        state: BlockState,
        serverLevel: ServerLevel,
        pos: BlockPos,
        randomSource: RandomSource
    ) {
        if (!serverLevel.isClientSide) {
            // TODO Deprecated
            if (!serverLevel.isAreaLoaded(pos, 3)) {
                return
            }

            // grass block is converted to brownie
            if (
                serverLevel.getLightEmission(pos.above()) < 4
                && serverLevel.getBlockState(pos.above()).getLightBlock(serverLevel, pos.above()) > 2
            ) {
                if (state.`is`(ModBlocks.CANDY_GRASS_BLOCK.value)) {
                    serverLevel.setBlockAndUpdate(pos, ModBlocks.MILK_BROWNIE_BLOCK.value.defaultBlockState())
                } else if (state.`is`(ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.value)) {
                    serverLevel.setBlockAndUpdate(pos, ModBlocks.WHITE_BROWNIE_BLOCK.value.defaultBlockState())
                } else {
                    serverLevel.setBlockAndUpdate(pos, ModBlocks.DARK_BROWNIE_BLOCK.value.defaultBlockState())
                }
            } else {
                if (serverLevel.getLightEmission(pos.above()) >= 9) {
                    // grass tries to spread 3 times
                    repeat(3) {
                        val blockpos = pos.offset(
                            randomSource.nextInt(3) - 1,
                            randomSource.nextInt(5) - 3,
                            randomSource.nextInt(3) - 1
                        )

                        // block not loaded
                        // TODO Deprecated
                        if (blockpos.y in 0..<256 && !serverLevel.hasChunkAt(blockpos)) {
                            return
                        }

                        val blockState: BlockState = serverLevel.getBlockState(blockpos.above())
                        val blockState2: BlockState = serverLevel.getBlockState(blockpos)

                        // block is valid
                        if (serverLevel.getLightEmission(blockpos.above()) >= 4
                            && blockState.getLightBlock(serverLevel, pos.above()) <= 2
                        ) {
                            if (blockState2.`is`(ModBlocks.MILK_BROWNIE_BLOCK.value)) {
                                serverLevel.setBlockAndUpdate(
                                    blockpos,
                                    ModBlocks.CANDY_GRASS_BLOCK.value.defaultBlockState()
                                )
                            } else if (blockState2.`is`(ModBlocks.WHITE_BROWNIE_BLOCK.value)) {
                                serverLevel.setBlockAndUpdate(
                                    blockpos,
                                    ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.value.defaultBlockState()
                                )
                            } else {
                                serverLevel.setBlockAndUpdate(
                                    blockpos,
                                    ModBlocks.DARK_CANDY_GRASS_BLOCK.value.defaultBlockState()
                                )
                            }
                        }
                    }
                }
            }
        }
    }

//    override fun isValidBonemealTarget(
//        levelReader: LevelReader,
//        blockPos: BlockPos,
//        blockState: BlockState
//    ): Boolean {
//        return levelReader.getBlockState(blockPos.above()).isAir
//    }
//
//    override fun isBonemealSuccess(
//        level: Level,
//        randomSource: RandomSource,
//        pos: BlockPos,
//        state: BlockState
//    ): Boolean {
//        return true
//    }
//
//    override fun performBonemeal(
//        serverLevel: ServerLevel,
//        randomSource: RandomSource,
//        pos: BlockPos,
//        blockState: BlockState
//    ) {
//        serverLevel.setBlockAndUpdate(pos.above(), ModBlocks.COTTON_CANDY_PLANT.value.defaultBlockState())
//    }
}