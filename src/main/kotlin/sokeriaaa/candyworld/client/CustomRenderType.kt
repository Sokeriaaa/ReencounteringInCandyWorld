package sokeriaaa.candyworld.client

import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.client.renderer.RenderType
import net.minecraft.resources.ResourceLocation
import sokeriaaa.candyworld.CandyWorld

open class CustomRenderType(
    name: String,
    format: VertexFormat,
    mode: VertexFormat.Mode,
    bufferSize: Int,
    affectsCrumbling: Boolean,
    sortOnUpload: Boolean,
    setupState: Runnable, clearState: Runnable,
) : RenderType(name, format, mode, bufferSize, affectsCrumbling, sortOnUpload, setupState, clearState) {

    companion object {

        fun getEntityTranslucentZOffset(
            resourceLocation: ResourceLocation,
            outlineIn: Boolean = true,
        ): RenderType {
            val compositeState = CompositeState.builder()
                .setShaderState(ShaderStateShard { GameRenderer.getRendertypeEntityTranslucentShader() })
                .setTextureState(TextureStateShard(resourceLocation, false, false))
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
//                    .setDiffuseLightingState(DIFFUSE_LIGHTING)
//                    .setAlphaState(DEFAULT_ALPHA)
                .setCullState(NO_CULL)
                .setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY)
                .setLayeringState(VIEW_OFFSET_Z_LAYERING)
                .createCompositeState(outlineIn)
            // FIXME: Inferred return type 'RenderType.CompositeRenderType!' for 'create' is not visible in this scope.
            //  This will become an error in language version 2.3. See https://youtrack.jetbrains.com/issue/KT-25513.
            return create(
                CandyWorld.MOD_ID + ":entity_translucent_z_offset",
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                256,
                true,
                true,
                compositeState
            )
        }
    }

}