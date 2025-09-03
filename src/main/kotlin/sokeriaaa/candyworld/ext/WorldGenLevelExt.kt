package sokeriaaa.candyworld.ext

import net.minecraft.core.BlockPos
import net.minecraft.world.level.WorldGenLevel

fun WorldGenLevel.isAreaLoaded(center: BlockPos, range: Int): Boolean {
    return this.hasChunksAt(
        center.offset(-range, -range, -range),
        center.offset(range, range, range),
    )
}