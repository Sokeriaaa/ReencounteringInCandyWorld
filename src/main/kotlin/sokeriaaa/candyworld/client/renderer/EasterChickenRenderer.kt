package sokeriaaa.candyworld.client.renderer

import net.minecraft.client.model.ChickenModel
import net.minecraft.client.model.geom.ModelLayers
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.entity.EasterChicken

open class EasterChickenRenderer(
    context: EntityRendererProvider.Context
) : MobRenderer<EasterChicken, ChickenModel<EasterChicken>>(
    context, ChickenModel(context.bakeLayer(ModelLayers.CHICKEN)), 0.3f,
) {

    override fun getTextureLocation(entity: EasterChicken): ResourceLocation {
        return CHICKEN_TEXTURES
    }

    override fun getBob(livingBase: EasterChicken, partialTicks: Float): Float {
        val f: Float = Mth.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation)
        val f1: Float = Mth.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos)
        return (Mth.sin(f) + 1.0f) * f1
    }

    companion object {
        private val CHICKEN_TEXTURES = CandyWorld.id("textures/entity/easter_chicken/easter_chicken.png")
    }
}