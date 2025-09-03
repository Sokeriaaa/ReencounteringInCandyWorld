package sokeriaaa.candyworld.client.model

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.util.Mth
import sokeriaaa.candyworld.client.CustomRenderType
import sokeriaaa.candyworld.entity.GummyBear

open class GummyBearModel<T : GummyBear>(
    root: ModelPart,
) : EntityModel<T>(CustomRenderType::getEntityTranslucentZOffset) {
    protected val scaleHead = true
    protected val babyYHeadOffset = 16.0f
    protected val babyZHeadOffset = 4.0f
    protected val babyHeadScale = 2.25f
    protected val babyBodyScale = 2.0f
    protected val bodyYOffset = 24f

    protected val head: ModelPart by lazy { root.getChild("head") }
    protected val body: ModelPart by lazy { root.getChild("body") }
    protected val rightHindLeg: ModelPart by lazy { root.getChild("right_hind_leg") }
    protected val leftHindLeg: ModelPart by lazy { root.getChild("left_hind_leg") }
    protected val rightFrontLeg: ModelPart by lazy { root.getChild("right_front_leg") }
    protected val leftFrontLeg: ModelPart by lazy { root.getChild("left_front_leg") }
    protected val headOuter: ModelPart by lazy { root.getChild("head_outer") }
    protected val bodyOuter: ModelPart by lazy { root.getChild("body_outer") }
    protected val rightHindLegOuter: ModelPart by lazy { root.getChild("right_hind_leg_outer") }
    protected val leftHindLegOuter: ModelPart by lazy { root.getChild("left_hind_leg_outer") }
    protected val rightFrontLegOuter: ModelPart by lazy { root.getChild("right_front_leg_outer") }
    protected val leftFrontLegOuter: ModelPart by lazy { root.getChild("left_front_leg_outer") }

    protected fun headParts(): Iterable<ModelPart> {
        return listOf(this.head)
    }

    protected fun bodyParts(): Iterable<ModelPart> {
        return listOf(
            this.body,
            this.rightHindLeg,
            this.leftHindLeg,
            this.rightFrontLeg,
            this.leftFrontLeg
        )
    }

    protected fun getOuterParts(): Iterable<ModelPart> {
        return listOf(
            this.bodyOuter,
            this.rightHindLegOuter,
            this.leftHindLegOuter,
            this.rightFrontLegOuter,
            this.leftFrontLegOuter,
            this.headOuter
        )
    }

    override fun renderToBuffer(
        poseStack: PoseStack,
        buffer: VertexConsumer,
        packedLight: Int,
        packedOverlay: Int,
        color: Int
    ) {
        if (this.young) {
            poseStack.pushPose()
            if (scaleHead) {
                val f = 1.5f / babyHeadScale
                poseStack.scale(f, f, f)
            }

            poseStack.translate(0.0f, babyYHeadOffset / 16.0f, babyZHeadOffset / 16.0f)
            this.headParts().forEach {
                it.render(
                    poseStack,
                    buffer,
                    packedLight,
                    packedOverlay,
                    color
                )
            }
            poseStack.popPose()
            poseStack.pushPose()
            val f1 = 1.0f / babyBodyScale
            poseStack.scale(f1, f1, f1)
            poseStack.translate(0.0f, bodyYOffset / 16.0f, 0.0f)
            this.bodyParts().forEach {
                it.render(
                    poseStack,
                    buffer,
                    packedLight,
                    packedOverlay,
                    color
                )
            }
            this.getOuterParts().forEach {
                it.render(
                    poseStack,
                    buffer,
                    packedLight,
                    packedOverlay,
                    (color and 0xCCFFFFFF.toInt()),
                )
            }
            poseStack.popPose()
        } else {
            this.headParts().forEach {
                it.render(
                    poseStack,
                    buffer,
                    packedLight,
                    packedOverlay,
                    color
                )
            }
            this.bodyParts().forEach {
                it.render(
                    poseStack,
                    buffer,
                    packedLight,
                    packedOverlay,
                    color
                )
            }
            this.getOuterParts().forEach {
                it.render(
                    poseStack,
                    buffer,
                    packedLight,
                    packedOverlay,
                    (color and 0xCCFFFFFF.toInt()),
                )
            }
        }
    }


    /**
     * Sets this entity's model rotation angles
     */
    override fun setupAnim(
        entity: T,
        limbSwing: Float,
        limbSwingAmount: Float,
        ageInTicks: Float,
        netHeadYaw: Float,
        headPitch: Float
    ) {
        this.head.xRot = headPitch * 0.017453292f
        this.head.yRot = netHeadYaw * 0.017453292f
        this.body.xRot = (Math.PI.toFloat() / 2f)
        this.leftHindLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount
        this.rightHindLeg.xRot = Mth.cos(limbSwing * 0.6662f + Math.PI.toFloat()) * 1.4f * limbSwingAmount
        this.leftFrontLeg.xRot = Mth.cos(limbSwing * 0.6662f + Math.PI.toFloat()) * 1.4f * limbSwingAmount
        this.rightFrontLeg.xRot = Mth.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount

        this.headOuter.xRot = this.head.xRot
        this.headOuter.yRot = this.head.yRot
        this.bodyOuter.xRot = this.body.xRot
        this.rightHindLegOuter.xRot = this.rightHindLeg.xRot
        this.leftHindLegOuter.xRot = this.leftHindLeg.xRot
        this.rightFrontLegOuter.xRot = this.rightFrontLeg.xRot
        this.leftFrontLegOuter.xRot = this.leftFrontLeg.xRot

        val f = ageInTicks - entity.tickCount.toFloat()
        var f1 = entity.getStandingAnimationScale(f)
        f1 *= f1
        val f2 = 1.0f - f1

        this.body.xRot = (Math.PI / 2).toFloat() - f1 * Math.PI.toFloat() * 0.35f
        this.bodyOuter.xRot = this.body.xRot
        this.body.y = 9.0f * f2 + 11.0f * f1
        this.bodyOuter.y = this.body.y

        this.rightFrontLeg.y = 14.0f * f2 - 6.0f * f1
        this.rightFrontLeg.z = -8.0f * f2 - 4.0f * f1
        this.rightFrontLeg.xRot -= f1 * Math.PI.toFloat() * 0.45f
        this.leftFrontLeg.y = this.rightFrontLeg.y
        this.leftFrontLeg.z = this.rightFrontLeg.z
        this.leftFrontLeg.xRot -= f1 * Math.PI.toFloat() * 0.45f

        this.rightFrontLegOuter.y = this.rightFrontLeg.y
        this.rightFrontLegOuter.z = this.rightFrontLeg.z
        this.rightFrontLegOuter.xRot = this.rightFrontLeg.xRot
        this.leftFrontLegOuter.y = this.leftFrontLeg.y
        this.leftFrontLegOuter.z = this.leftFrontLeg.z
        this.leftFrontLegOuter.xRot = this.leftFrontLeg.xRot

        if (this.young) {
            this.head.y = 10.0f * f2 - 9.0f * f1
            this.head.z = -16.0f * f2 - 7.0f * f1
        } else {
            this.head.y = 10.0f * f2 - 14.0f * f1
            this.head.z = -16.0f * f2 - 3.0f * f1
        }
        this.headOuter.y = this.head.y
        this.headOuter.z = this.head.z

        this.head.xRot += f1 * Math.PI.toFloat() * 0.15f
        this.headOuter.xRot = this.head.xRot

        this.head.y += Mth.sin(ageInTicks * 0.04f) * 0.3f
        this.body.y += Mth.sin(ageInTicks * 0.04f + Math.PI.toFloat() / 2) * 0.3f
    }

    companion object {
        fun createBodyLayer(): LayerDefinition {
            return LayerDefinition.create(createBodyMesh(), 128, 96)
        }

        fun createBodyMesh(): MeshDefinition {
            val meshDefinition = MeshDefinition()
            val partDefinition = meshDefinition.root
            partDefinition.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-3.5f, -3.0f, -3.0f, 7.0f, 7.0f, 7.0f)
                    .texOffs(0, 44)
                    .addBox("mouth", -2.5f, 1.0f, -6.0f, 5.0f, 3.0f, 3.0f)
                    .texOffs(26, 0)
                    .addBox("right_ear", -4.5f, -4.0f, -1.0f, 2.0f, 2.0f, 1.0f)
                    .texOffs(26, 0)
                    .mirror()
                    .addBox("left_ear", 2.5f, -4.0f, -1.0f, 2.0f, 2.0f, 1.0f),
                PartPose.offset(0.0f, 10.0f, -16.0f)
            )
            partDefinition.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                    .texOffs(0, 19)
                    .addBox(-9.0f, -13.0f, -7.0f, 14.0f, 14.0f, 11.0f)
                    .texOffs(39, 0)
                    .addBox(-8.0f, -25.0f, -7.0f, 12.0f, 12.0f, 10.0f),
                PartPose.offsetAndRotation(2.0f, 9.0f, 12.0f, (Math.PI / 2).toFloat(), 0.0f, 0.0f)
            )
            partDefinition.addOrReplaceChild(
                "head_outer",
                CubeListBuilder.create()
                    .texOffs(13, 49)
                    .addBox(-4.5f, -4.0f, -4.0f, 9.0f, 9.0f, 9.0f)
                    .texOffs(0, 44)
                    .addBox("mouth_outer", -2.5f, 1.0f, -6.0f, 5.0f, 3.0f, 3.0f)
                    .texOffs(26, 0)
                    .addBox("right_ear_outer", 3.0f, -4.5f, -1.0f, 2.0f, 2.0f, 1.0f)
                    .texOffs(26, 0)
                    .mirror()
                    // "left_ear_outer"
                    .addBox(-5.0f, -4.5f, -1.0f, 2.0f, 2.0f, 1.0f, true),
                PartPose.offset(0.0f, 10.0f, -16.0f)
            )
            partDefinition.addOrReplaceChild(
                "body_outer",
                CubeListBuilder.create()
                    .texOffs(58, 70)
                    .addBox(-9.0f, -26.0f, -8.0f, 14.0f, 14.0f, 12.0f)
                    .texOffs(0, 67)
                    .addBox(-10.0f, -14.0f, -8.0f, 16.0f, 16.0f, 13.0f),
                PartPose.offsetAndRotation(2.0f, 9.0f, 12.0f, (Math.PI / 2).toFloat(), 0.0f, 0.0f)
            )
            val cubeListBuilder = CubeListBuilder.create()
                .texOffs(50, 22)
                .addBox(-2.0f, 0.0f, -2.0f, 4.0f, 10.0f, 8.0f)
            partDefinition.addOrReplaceChild(
                "right_hind_leg",
                cubeListBuilder,
                PartPose.offset(-3.5f, 14.0f, 6.0f),
            )
            partDefinition.addOrReplaceChild(
                "left_hind_leg",
                cubeListBuilder,
                PartPose.offset(3.5f, 14.0f, 6.0f),
            )
            val cubeListBuilder1 =
                CubeListBuilder.create()
                    .texOffs(50, 40)
                    .addBox(-2.0f, 0.0f, -2.0f, 4.0f, 10.0f, 6.0f)
            partDefinition.addOrReplaceChild(
                "right_front_leg",
                cubeListBuilder1,
                PartPose.offset(-2.5f, 14.0f, -8.0f),
            )
            partDefinition.addOrReplaceChild(
                "left_front_leg",
                cubeListBuilder1,
                PartPose.offset(2.5f, 14.0f, -8.0f),
            )
            val cubeListBuilder2 = CubeListBuilder.create()
                .texOffs(100, 32)
                .addBox(-2.5f, -1.0f, -2.5f, 5.0f, 11.0f, 9.0f)
            partDefinition.addOrReplaceChild(
                "right_hind_leg_outer",
                cubeListBuilder2,
                PartPose.offset(-3.5f, 14.0f, 6.0f)
            )
            partDefinition.addOrReplaceChild(
                "left_hind_leg_outer",
                cubeListBuilder2,
                PartPose.offset(3.5f, 14.0f, 6.0f)
            )
            val cubeListBuilder3 =
                CubeListBuilder.create()
                    .texOffs(104, 52)
                    .addBox(-2.5f, -1.0f, -2.5f, 5.0f, 11.0f, 7.0f)
            partDefinition.addOrReplaceChild(
                "right_front_leg_outer",
                cubeListBuilder3,
                PartPose.offset(-2.5f, 14.0f, -8.0f)
            )
            partDefinition.addOrReplaceChild(
                "left_front_leg_outer",
                cubeListBuilder3,
                PartPose.offset(2.5f, 14.0f, -8.0f)
            )
            return meshDefinition
        }
    }
}