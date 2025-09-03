package sokeriaaa.candyworld.client.renderer

import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.client.model.CandySheepModel
import sokeriaaa.candyworld.client.model.ModModelLayers
import sokeriaaa.candyworld.client.renderer.layers.LayerCandySheepWool
import sokeriaaa.candyworld.entity.CandySheep

open class CandySheepRenderer(
    context: EntityRendererProvider.Context
) : MobRenderer<CandySheep, CandySheepModel<CandySheep>>(
    context,
    CandySheepModel(context.bakeLayer(ModModelLayers.CANDY_SHEEP)),
    0.7f,
) {
    init {
        addLayer(LayerCandySheepWool(context, this))
    }

    override fun getTextureLocation(entity: CandySheep): ResourceLocation {
        return SHEARED_SHEEP_TEXTURES
    }

    companion object {
        private val SHEARED_SHEEP_TEXTURES = CandyWorld.id("textures/entity/candy_sheep/candy_sheep.png")
    }
}