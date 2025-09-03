package sokeriaaa.candyworld.client.renderer

import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.client.model.GummyMouseModel
import sokeriaaa.candyworld.client.model.ModModelLayers
import sokeriaaa.candyworld.client.renderer.layers.LayerMouseGummy
import sokeriaaa.candyworld.entity.GummyMouse
import sokeriaaa.candyworld.enums.EnumGummy

open class GummyMouseRenderer(
    context: EntityRendererProvider.Context,
) : MobRenderer<GummyMouse, GummyMouseModel<GummyMouse>>(
    context, GummyMouseModel(context.bakeLayer(ModModelLayers.GUMMY_MOUSE)), 0.25f,
) {
    init {
        addLayer(LayerMouseGummy(context, this))
    }

    override fun getTextureLocation(entity: GummyMouse): ResourceLocation {
        return GUMMY_MOUSE_TEXTURES.getOrDefault(
            entity.color,
            GUMMY_MOUSE_TEXTURES[EnumGummy.RED]!!,
        )
    }

    companion object {
        private val GUMMY_MOUSE_TEXTURES: Map<EnumGummy, ResourceLocation> = mapOf(
            EnumGummy.RED to CandyWorld.id("textures/entity/gummy_mouse/red_gummy_mouse.png"),
            EnumGummy.ORANGE to CandyWorld.id("textures/entity/gummy_mouse/orange_gummy_mouse.png"),
            EnumGummy.YELLOW to CandyWorld.id("textures/entity/gummy_mouse/yellow_gummy_mouse.png"),
            EnumGummy.WHITE to CandyWorld.id("textures/entity/gummy_mouse/white_gummy_mouse.png"),
            EnumGummy.GREEN to CandyWorld.id("textures/entity/gummy_mouse/green_gummy_mouse.png"),
        )
    }
}