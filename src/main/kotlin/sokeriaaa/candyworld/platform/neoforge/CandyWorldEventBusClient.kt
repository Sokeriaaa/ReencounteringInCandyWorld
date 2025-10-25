package sokeriaaa.candyworld.platform.neoforge

import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.world.entity.Entity
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.client.model.ModModelLayers
import sokeriaaa.candyworld.platform.Platform
import sokeriaaa.candyworld.platform.neoforge.impl.CandyWorldFluidImpl
import sokeriaaa.candyworld.registry.client.ModBlockColors
import sokeriaaa.candyworld.registry.client.ModEntityRenderers
import sokeriaaa.candyworld.registry.client.ModItemColors

@EventBusSubscriber(modid = CandyWorld.MOD_ID, value = [Dist.CLIENT])
object CandyWorldEventBusClient {

    @SubscribeEvent
    fun onRegisterColorHandlersBlockEvent(event: RegisterColorHandlersEvent.Block) {
        ModBlockColors.registerBlockColors { color, blocks ->
            event.register(color, *blocks)
        }
    }

    @SubscribeEvent
    fun onRegisterColorHandlersItemEvent(event: RegisterColorHandlersEvent.Item) {
        ModItemColors.registerItemColors { color, items ->
            event.register(color, *items)
        }
    }

    @SubscribeEvent
    fun onRegisterClientExtensionsEvent(event: RegisterClientExtensionsEvent) {
        (Platform.fluid as CandyWorldFluidImpl).registerFluidExtOn(event)
    }

    @SubscribeEvent
    fun onRegisterLayerDefinitionsEvent(
        event: EntityRenderersEvent.RegisterLayerDefinitions
    ) {
        ModModelLayers.register { location, definition ->
            event.registerLayerDefinition(location, definition)
        }
    }

    @SubscribeEvent
    fun onRegisterRenderersEvent(event: EntityRenderersEvent.RegisterRenderers) {
        ModEntityRenderers.registerClient { entityType, renderer ->
            event.registerEntityRenderer(entityType) { context ->
                renderer(context) as EntityRenderer<Entity>
            }
        }
    }
}