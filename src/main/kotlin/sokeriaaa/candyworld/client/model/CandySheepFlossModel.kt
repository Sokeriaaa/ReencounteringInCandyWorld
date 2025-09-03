package sokeriaaa.candyworld.client.model

import net.minecraft.client.model.QuadrupedModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import sokeriaaa.candyworld.entity.CandySheep

open class CandySheepFlossModel<T : CandySheep>(
    root: ModelPart,
) : QuadrupedModel<T>(root, false, 8.0f, 4.0f, 2.0f, 2.0f, 24) {
    private var headXRot = 0f

    override fun prepareMobModel(entity: T, limbSwing: Float, limbSwingAmount: Float, partialTick: Float) {
        super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick)
        this.head.y = 6.0f + entity.getHeadRotationPointY(partialTick) * 9.0f
        this.headXRot = entity.getHeadRotationAngleX(partialTick)
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
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch)
        this.head.xRot = this.headXRot
    }

    companion object {
        fun createBodyLayer(): LayerDefinition {
            return LayerDefinition.create(createBodyMesh(), 64, 32)
        }

        fun createBodyMesh(): MeshDefinition {
            val meshDefinition = createBodyMesh(12, CubeDeformation.NONE)
            val partDefinition = meshDefinition.root
            partDefinition.addOrReplaceChild(
                "head",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-3.0f, -4.0f, -3.85f, 6.0f, 6.0f, 6.0f, CubeDeformation(1.2f)),
                PartPose.offset(0.0f, 6.0f, -8.0f),
            )
            partDefinition.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                    .texOffs(28, 8)
                    .addBox(-4.0f, -9.0f, -9.0f, 8.0f, 16.0f, 6.0f, CubeDeformation(2.4f)),
                PartPose.offsetAndRotation(0.0f, 5.0f, 2.0f, (Math.PI / 2).toFloat(), 0.0f, 0.0f)
            )
            val cubeListBuilder = CubeListBuilder.create().texOffs(0, 16)
                .addBox(-2.0f, 2.0f, -2.0f, 4.0f, 6.0f, 4.0f, CubeDeformation(0.8f))
            partDefinition.addOrReplaceChild(
                "right_hind_leg",
                cubeListBuilder,
                PartPose.offset(-3.0f, 12.0f, 7.0f),
            )
            partDefinition.addOrReplaceChild(
                "left_hind_leg",
                cubeListBuilder,
                PartPose.offset(3.0f, 12.0f, 7.0f),
            )
            partDefinition.addOrReplaceChild(
                "right_front_leg",
                cubeListBuilder,
                PartPose.offset(-3.0f, 12.0f, -5.0f),
            )
            partDefinition.addOrReplaceChild(
                "left_front_leg",
                cubeListBuilder,
                PartPose.offset(3.0f, 12.0f, -5.0f),
            )
            return meshDefinition
        }
    }

}