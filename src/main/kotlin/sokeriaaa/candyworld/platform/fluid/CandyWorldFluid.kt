package sokeriaaa.candyworld.platform.fluid

import net.minecraft.Util
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.FlowingFluid
import net.minecraft.world.level.material.Fluid

interface CandyWorldFluid {
    fun createSourceFluid(attributes: Attributes): FlowingFluid
    fun createFlowingFluid(attributes: Attributes): FlowingFluid

    data class Attributes(
        val path: String,
        val sourceFluid: () -> Fluid,
        val flowingFluid: () -> Fluid,
        val canConvertToSource: Boolean = false,
        val slopeFindDistance: Int = 4,
        val levelDecreasePerBlock: Int = 1,
        val bucketItem: (() -> Item)? = null,
        val tickRate: Int = 5,
        val explosionResistance: Float = 100.0f,
        val block: (() -> Block)? = null,
        val stillTexture: ResourceLocation? = null,
        val flowingTexture: ResourceLocation? = null,
        val overlayTexture: ResourceLocation? = null,
        val tintColor: Int = 0xFFFFFF,
        val lightLevel: Int = 0,
        val density: Int = 1000,
        val temperature: Int = 300,
        val viscosity: Int = 1000,
        val lighterThanAir: Boolean = false,
        val rarity: Rarity = Rarity.COMMON,
        val fillSound: SoundEvent? = SoundEvents.BUCKET_FILL,
        val emptySound: SoundEvent? = SoundEvents.BUCKET_EMPTY,
        val defaultTranslationKey: () -> String = {
            Util.makeDescriptionId(
                "fluid",
                sourceFluid().builtInRegistryHolder()
                    .unwrapKey()
                    .map(ResourceKey<*>::location)
                    .orElse(null),
            )
        }
    )
}