package sokeriaaa.candyworld.platform.neoforge

import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.SpawnPlacements
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent
import sokeriaaa.candyworld.registry.ModEntities


object CandyWorldEventBus {

    @SubscribeEvent
    @JvmStatic
    fun onRegisterSpawnPlacementsEvent(event: RegisterSpawnPlacementsEvent) {
        ModEntities.registerSpawnPlacements {
            event.register(
                it.entityType as EntityType<Entity>,
                it.spawnPlacementType,
                it.heightMapType,
                it.spawnPredicate as SpawnPlacements.SpawnPredicate<Entity>,
                RegisterSpawnPlacementsEvent.Operation.OR
            )
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun onEntityAttributeCreationEvent(event: EntityAttributeCreationEvent) {
        ModEntities.registerEntityAttributes { entityType, attributes ->
            event.put(entityType as EntityType<out LivingEntity>, attributes.build())
        }
    }
}