package sokeriaaa.candyworld.client.renderer.layers

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.RenderLayerParent
import net.minecraft.client.renderer.entity.layers.RenderLayer
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.client.model.CandySheepFlossModel
import sokeriaaa.candyworld.client.model.CandySheepModel
import sokeriaaa.candyworld.client.model.ModModelLayers
import sokeriaaa.candyworld.entity.CandySheep

open class LayerCandySheepWool(
    context: EntityRendererProvider.Context,
    renderer: RenderLayerParent<CandySheep, CandySheepModel<CandySheep>>,
) : RenderLayer<CandySheep, CandySheepModel<CandySheep>>(renderer) {
    private val sheepModel: CandySheepFlossModel<CandySheep> =
        CandySheepFlossModel(context.bakeLayer(ModModelLayers.CANDY_SHEEP_FLOSS))

    override fun render(
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        livingEntity: CandySheep,
        limbSwing: Float,
        limbSwingAmount: Float,
        partialTick: Float,
        ageInTicks: Float,
        netHeadYaw: Float,
        headPitch: Float
    ) {
        if (!livingEntity.sheared && !livingEntity.isInvisible) {
            coloredCutoutModelCopyLayerRender(
                this.parentModel,
                this.sheepModel,
                TEXTURE,
                poseStack,
                bufferSource,
                packedLight,
                livingEntity,
                limbSwing,
                limbSwingAmount,
                ageInTicks,
                netHeadYaw,
                headPitch,
                partialTick,
                -1,
            )
        }
    }

    companion object {
        private val TEXTURE = CandyWorld.id("textures/entity/candy_sheep/candy_sheep_fur.png")

    }
}