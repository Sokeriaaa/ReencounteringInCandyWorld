package sokeriaaa.candyworld.platform.neoforge.impl

import net.minecraft.client.renderer.block.model.BlockElementFace
import net.minecraft.core.BlockPos
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.BlockAndTintGetter
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.material.FlowingFluid
import net.minecraft.world.level.material.FluidState
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent
import net.neoforged.neoforge.fluids.BaseFlowingFluid
import net.neoforged.neoforge.fluids.FluidStack
import net.neoforged.neoforge.fluids.FluidType
import sokeriaaa.candyworld.platform.Platform
import sokeriaaa.candyworld.platform.fluid.CandyWorldFluid

class CandyWorldFluidImpl : CandyWorldFluid {

    private val _fluidExtMap: MutableMap<FluidType, IClientFluidTypeExtensions> = HashMap()

    override fun createSourceFluid(attributes: CandyWorldFluid.Attributes): FlowingFluid {
        return BaseFlowingFluid.Source(attributes.toProperties())
    }

    override fun createFlowingFluid(attributes: CandyWorldFluid.Attributes): FlowingFluid {
        return BaseFlowingFluid.Flowing(attributes.toProperties())
    }

    fun registerFluidExtOn(event: RegisterClientExtensionsEvent) {
        _fluidExtMap.forEach { (fluidType, ext) ->
            event.registerFluidType(ext, fluidType)
        }
    }

    private fun CandyWorldFluid.Attributes.toProperties(): BaseFlowingFluid.Properties {
        return with(this) {
            val fluidType = (Platform.registry as CandyWorldRegistryImpl).fluidTypeMap[path]
                ?: error("FluidType \"$path\" not registered.")
            val ext = object : IClientFluidTypeExtensions {
                override fun getFlowingTexture(): ResourceLocation = this@with.flowingTexture ?: super.flowingTexture
                override fun getOverlayTexture(): ResourceLocation? = this@with.overlayTexture ?: super.overlayTexture
                override fun getStillTexture(): ResourceLocation = this@with.stillTexture ?: super.stillTexture
                override fun getTintColor(): Int = BlockElementFace.NO_TINT
                override fun getTintColor(stack: FluidStack): Int = BlockElementFace.NO_TINT
                override fun getTintColor(state: FluidState, getter: BlockAndTintGetter, pos: BlockPos): Int =
                    BlockElementFace.NO_TINT
            }
            _fluidExtMap[fluidType] = ext
            BaseFlowingFluid.Properties(
                { fluidType },
                sourceFluid,
                flowingFluid,
            ).bucket { bucketItem?.invoke() ?: Items.AIR }
                .block { (block?.invoke() ?: Blocks.WATER) as LiquidBlock }
                .slopeFindDistance(slopeFindDistance)
                .levelDecreasePerBlock(levelDecreasePerBlock)
                .explosionResistance(explosionResistance)
                .tickRate(tickRate)
        }
    }

    companion object {
        fun CandyWorldFluid.Attributes.toFluidType(): FluidType {
            return with(this) {
                object : FluidType(
                    Properties.create()
                        .canConvertToSource(canConvertToSource)
                        .lightLevel(lightLevel)
                        .density(density)
                        .temperature(temperature)
                        .viscosity(viscosity)
                        .rarity(rarity)
                ) {
                    override fun getBucket(stack: FluidStack): ItemStack {
                        return bucketItem?.invoke()?.let { ItemStack(it) } ?: super.getBucket(stack)
                    }

//                override fun initializeClient(consumer: Consumer<IClientFluidTypeExtensions?>) {
//                    consumer.accept(
//                        object : IClientFluidTypeExtensions {
//                            override fun getFlowingTexture(): ResourceLocation = this@with.flowingTexture ?: super.flowingTexture
//                            override fun getOverlayTexture(): ResourceLocation? = this@with.overlayTexture ?: super.overlayTexture
//                            override fun getStillTexture(): ResourceLocation = this@with.stillTexture ?: super.stillTexture
//                            override fun getTintColor(): Int = BlockElementFace.NO_TINT
//                        }
//                    )
//                }
                }
            }
        }
    }
}