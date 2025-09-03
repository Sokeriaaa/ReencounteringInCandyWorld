package sokeriaaa.candyworld.client.renderer

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.resources.ResourceLocation
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.client.model.GummyBearModel
import sokeriaaa.candyworld.client.model.ModModelLayers
import sokeriaaa.candyworld.entity.GummyBear
import sokeriaaa.candyworld.enums.EnumGummy

open class GummyBearRenderer(
    context: EntityRendererProvider.Context,
) : MobRenderer<GummyBear, GummyBearModel<GummyBear>>(
    context, GummyBearModel(context.bakeLayer(ModModelLayers.GUMMY_BEAR)), 0.7f,
) {

    override fun getTextureLocation(entity: GummyBear): ResourceLocation {
        return GUMMY_BEAR_TEXTURES.getOrDefault(
            entity.color,
            GUMMY_BEAR_TEXTURES[EnumGummy.RED]!!,
        )
    }

    override fun scale(
        livingEntity: GummyBear,
        poseStack: PoseStack,
        partialTickTime: Float,
    ) {
        poseStack.scale(1.2f, 1.2f, 1.2f)
    }

    companion object {
        private val GUMMY_BEAR_TEXTURES: Map<EnumGummy, ResourceLocation> = mapOf(
            EnumGummy.RED to CandyWorld.id("textures/entity/gummy_bear/red_gummy_bear.png"),
            EnumGummy.ORANGE to CandyWorld.id("textures/entity/gummy_bear/orange_gummy_bear.png"),
            EnumGummy.YELLOW to CandyWorld.id("textures/entity/gummy_bear/yellow_gummy_bear.png"),
            EnumGummy.WHITE to CandyWorld.id("textures/entity/gummy_bear/white_gummy_bear.png"),
            EnumGummy.GREEN to CandyWorld.id("textures/entity/gummy_bear/green_gummy_bear.png"),
        )
    }
}