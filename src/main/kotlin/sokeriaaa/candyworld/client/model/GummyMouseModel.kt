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
import net.minecraft.util.Mth
import sokeriaaa.candyworld.entity.GummyMouse

open class GummyMouseModel<T : GummyMouse>(
    root: ModelPart,
) : EntityModel<T>() {

    val body1 by lazy { root.getChild("body_1") }
    val tail1 by lazy { body1.getChild("tail_1") }
    val head1 by lazy { body1.getChild("head_1") }
    override fun renderToBuffer(
        poseStack: PoseStack,
        buffer: VertexConsumer,
        packedLight: Int,
        packedOverlay: Int,
        color: Int
    ) {
        body1.render(poseStack, buffer, packedLight, packedOverlay)
    }

    override fun setupAnim(
        entityIn: T,
        limbSwing: Float,
        limbSwingAmount: Float,
        ageInTicks: Float,
        netHeadYaw: Float,
        headPitch: Float
    ) {
        this.body1.setPos(0f, 21.5f, -2.5f)

        this.tail1.yRot = Mth.sin(ageInTicks * 0.6f + 0.3f) * Math.PI.toFloat() * 0.03f
        this.body1.y += Mth.sin(ageInTicks * 0.05f) * 0.04f
        this.body1.x += Mth.sin(ageInTicks * 0.6f + 0.3f) * 0.004f
    }

    companion object {
        fun createBodyLayer(): LayerDefinition {
            return LayerDefinition.create(createBodyMesh(), 32, 32)
        }

        fun createBodyMesh(): MeshDefinition {
            val meshDefinition = QuadrupedModel.createBodyMesh(12, CubeDeformation.NONE)
            val partDefinition = meshDefinition.root

            partDefinition.addOrReplaceChild(
                "body_1",
                CubeListBuilder.create()
                    .texOffs(16, 15)
                    .addBox(-1.5f, 0.0f, 0.0f, 3.0f, 2.0f, 5.0f),
                PartPose.offset(0f, 21.5f, -2.5f)
            ).also {
                it.addOrReplaceChild(
                    "tail_1",
                    CubeListBuilder.create()
                        .texOffs(18, 8)
                        .addBox(-0.5f, 0.0f, -1.5f, 1.0f, 1.0f, 6.0f),
                    PartPose.offset(0.0f, 1.0f, 6.0f)
                )
                it.addOrReplaceChild(
                    "head_1",
                    CubeListBuilder.create()
                        .texOffs(22, 4)
                        .addBox(-1.0f, 0.0f, -3.0f, 2.0f, 1.0f, 3.0f),
                    PartPose.offset(0.0f, 1.0f, 0.0f)
                )
            }
            return meshDefinition
        }
    }


}