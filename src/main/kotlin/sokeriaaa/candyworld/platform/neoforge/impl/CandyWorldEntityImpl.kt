package sokeriaaa.candyworld.platform.neoforge.impl

import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level
import net.neoforged.neoforge.event.EventHooks
import sokeriaaa.candyworld.platform.entity.CandyWorldEntity

class CandyWorldEntityImpl : CandyWorldEntity {
    override fun canEntityGrief(
        level: Level,
        entity: Entity
    ): Boolean {
        return EventHooks.canEntityGrief(level, entity)
    }
}