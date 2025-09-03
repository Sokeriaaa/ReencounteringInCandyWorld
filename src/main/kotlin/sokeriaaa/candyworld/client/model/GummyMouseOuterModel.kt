package sokeriaaa.candyworld.client.model

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.QuadrupedModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.util.Mth
import sokeriaaa.candyworld.client.CustomRenderType
import sokeriaaa.candyworld.entity.GummyMouse

open class GummyMouseOuterModel<T : GummyMouse>(
    root: ModelPart,
) : EntityModel<T>(CustomRenderType::getEntityTranslucentZOffset) {

    val body by lazy { root.getChild("body") }
    val tail by lazy { body.getChild("tail") }
    val head by lazy { body.getChild("head") }
    val earLeft by lazy { head.getChild("ear_left") }
    val earRight by lazy { head.getChild("ear_right") }

    override fun renderToBuffer(
        poseStack: PoseStack,
        buffer: VertexConsumer,
        packedLight: Int,
        packedOverlay: Int,
        color: Int
    ) {
        this.body.render(poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, color)
    }

    override fun setupAnim(
        entityIn: T,
        limbSwing: Float,
        limbSwingAmount: Float,
        ageInTicks: Float,
        netHeadYaw: Float,
        headPitch: Float
    ) {
        this.tail.yRot = Mth.sin(ageInTicks * 0.6f) * Math.PI.toFloat() * 0.04f
    }

    companion object {
        fun createBodyLayer(): LayerDefinition {
            return LayerDefinition.create(createBodyMesh(), 32, 32)
        }

        fun createBodyMesh(): MeshDefinition {
            val meshDefinition = QuadrupedModel.createBodyMesh(12, CubeDeformation.NONE)
            val partDefinition = meshDefinition.root

            partDefinition.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-2.0f, 0.0f, 0.0f, 4.0f, 3.0f, 6.0f),
                PartPose.offset(0.0f, 21.0f, -3.0f)
            ).also { b ->
                b.addOrReplaceChild(
                    "tail",
                    CubeListBuilder.create()
                        .texOffs(0, 14)
                        .addBox(-1.0f, 0.0f, 0.0f, 2.0f, 2.0f, 6.0f),
                    PartPose.offset(0.0f, 1.0f, 6.0f)
                )
                b.addOrReplaceChild(
                    "head",
                    CubeListBuilder.create()
                        .texOffs(0, 9)
                        .addBox(-1.5f, 0.0f, -3.0f, 3.0f, 2.0f, 3.0f),
                    PartPose.offset(0.0f, 1.0f, 0.0f)
                ).also { h ->
                    h.addOrReplaceChild(
                        "ear_left",
                        CubeListBuilder.create()
                            .texOffs(0, 14)
                            .addBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 2.0f),
                        PartPose.offset(0.3f, -0.6f, -2.5f)
                    )
                    h.addOrReplaceChild(
                        "ear_right",
                        CubeListBuilder.create()
                            .texOffs(0, 17)
                            .addBox(-1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 2.0f),
                        PartPose.offset(-0.3f, -0.6f, -2.5f)
                    )
                }
            }
            return meshDefinition
        }
    }

}