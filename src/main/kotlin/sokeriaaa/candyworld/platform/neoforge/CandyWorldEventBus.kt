package sokeriaaa.candyworld.platform.neoforge

import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.SpawnPlacements
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent
import sokeriaaa.candyworld.client.model.ModModelLayers
import sokeriaaa.candyworld.platform.Platform
import sokeriaaa.candyworld.platform.neoforge.impl.CandyWorldFluidImpl
import sokeriaaa.candyworld.registry.ModBlocks
import sokeriaaa.candyworld.registry.ModEntities
import sokeriaaa.candyworld.registry.ModItems
import sokeriaaa.candyworld.registry.client.ModEntityRenderers


object CandyWorldEventBus {

    @SubscribeEvent
    @JvmStatic
    fun onRegisterColorHandlersBlockEvent(event: RegisterColorHandlersEvent.Block) {
        ModBlocks.registerBlockColors { color, blocks ->
            event.register(color, *blocks)
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun onRegisterColorHandlersItemEvent(event: RegisterColorHandlersEvent.Item) {
        ModItems.registerItemColors { color, items ->
            event.register(color, *items)
        }
    }

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

    @SubscribeEvent
    @JvmStatic
    fun onRegisterClientExtensionsEvent(event: RegisterClientExtensionsEvent) {
        (Platform.fluid as CandyWorldFluidImpl).registerFluidExtOn(event)
    }

    @SubscribeEvent
    @JvmStatic
    fun onRegisterLayerDefinitionsEvent(
        event: EntityRenderersEvent.RegisterLayerDefinitions
    ) {
        ModModelLayers.register { location, definition ->
            event.registerLayerDefinition(location, definition)
        }
    }

    @SubscribeEvent
    @JvmStatic
    fun onRegisterRenderersEvent(event: EntityRenderersEvent.RegisterRenderers) {
        ModEntityRenderers.registerClient { entityType, renderer ->
            event.registerEntityRenderer(entityType) { context ->
                renderer(context) as EntityRenderer<Entity>
            }
        }
    }
}