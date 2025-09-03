package sokeriaaa.candyworld.registry.client

import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import sokeriaaa.candyworld.client.renderer.CandySheepRenderer
import sokeriaaa.candyworld.client.renderer.EasterChickenRenderer
import sokeriaaa.candyworld.client.renderer.GummyBearRenderer
import sokeriaaa.candyworld.client.renderer.GummyMouseRenderer
import sokeriaaa.candyworld.registry.ModEntities

object ModEntityRenderers {

    fun registerClient(
        registerEntityRenderer: (
            EntityType<out Entity>,
            (EntityRendererProvider.Context) -> EntityRenderer<out Entity>
        ) -> Unit
    ) {
        registerEntityRenderer(ModEntities.COTTON_CANDY_SHEEP.value) {
            CandySheepRenderer(it)
        }
        registerEntityRenderer(ModEntities.EASTER_CHICKEN.value) {
            EasterChickenRenderer(it)
        }
        registerEntityRenderer(ModEntities.GUMMY_MOUSE.value) {
            GummyMouseRenderer(it)
        }
        registerEntityRenderer(ModEntities.GUMMY_BEAR.value) {
            GummyBearRenderer(it)
        }
    }
}