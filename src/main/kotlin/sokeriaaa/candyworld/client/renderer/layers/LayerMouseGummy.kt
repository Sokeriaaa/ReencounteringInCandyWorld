package sokeriaaa.candyworld.client.renderer.layers

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.LivingEntityRenderer
import net.minecraft.client.renderer.entity.RenderLayerParent
import net.minecraft.client.renderer.entity.layers.RenderLayer
import sokeriaaa.candyworld.client.CustomRenderType
import sokeriaaa.candyworld.client.model.GummyMouseModel
import sokeriaaa.candyworld.client.model.GummyMouseOuterModel
import sokeriaaa.candyworld.client.model.ModModelLayers
import sokeriaaa.candyworld.entity.GummyMouse

open class LayerMouseGummy(
    context: EntityRendererProvider.Context,
    renderer: RenderLayerParent<GummyMouse, GummyMouseModel<GummyMouse>>,
) : RenderLayer<GummyMouse, GummyMouseModel<GummyMouse>>(renderer) {
    private val gummyMouseOuterModel: GummyMouseOuterModel<GummyMouse> =
        GummyMouseOuterModel(context.bakeLayer(ModModelLayers.GUMMY_MOUSE_OUTER))

    override fun render(
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        livingEntity: GummyMouse,
        limbSwing: Float,
        limbSwingAmount: Float,
        partialTick: Float,
        ageInTicks: Float,
        netHeadYaw: Float,
        headPitch: Float
    ) {
        if (!livingEntity.isInvisible) {
            this.parentModel.copyPropertiesTo(this.gummyMouseOuterModel)
            this.gummyMouseOuterModel.prepareMobModel(
                livingEntity,
                limbSwing,
                limbSwingAmount,
                partialTick,
            )
            this.gummyMouseOuterModel.setupAnim(
                livingEntity,
                limbSwing,
                limbSwingAmount,
                ageInTicks,
                netHeadYaw,
                headPitch
            )
            val vertexConsumer: VertexConsumer =
                bufferSource.getBuffer(CustomRenderType.getEntityTranslucentZOffset(this.getTextureLocation(livingEntity)))
            this.gummyMouseOuterModel.renderToBuffer(
                poseStack,
                vertexConsumer,
                packedLight,
                LivingEntityRenderer.getOverlayCoords(livingEntity, 0.0f),
                0x99FFFFFF.toInt(),
            )
        }
    }

}