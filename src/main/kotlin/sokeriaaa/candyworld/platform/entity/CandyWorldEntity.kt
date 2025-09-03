package sokeriaaa.candyworld.platform.entity

import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level

interface CandyWorldEntity {
    fun canEntityGrief(level: Level, entity: Entity): Boolean
}