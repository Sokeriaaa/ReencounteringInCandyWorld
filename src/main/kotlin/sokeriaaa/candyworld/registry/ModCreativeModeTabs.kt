package sokeriaaa.candyworld.registry

import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.platform.registry.CandyWorldRegistry

object ModCreativeModeTabs {

    val BLOCKS by lazy {
        Wrapper(
            name = "blocks",
            itemSupplier = { ItemStack(ModBlocks.WAFER_STICK_BLOCK.value) }
        )
    }
    val ITEMS by lazy {
        Wrapper(
            name = "items",
            itemSupplier = { ItemStack(ModItems.WAFER_STICK.value) }
        )
    }
    val TOOLS by lazy {
        Wrapper(
            name = "tools",
            itemSupplier = { ItemStack(ModItems.MILK_CHOCOLATE_PICKAXE.value) }
        )
    }

    fun register() {
        CandyWorldRegistry {
            registerCreativeModeTab(BLOCKS)
            registerCreativeModeTab(ITEMS)
            registerCreativeModeTab(TOOLS)
        }
    }

    class Wrapper(
        val name: String,
        val itemSupplier: () -> ItemStack,
    ) {
        val resourceLocation: ResourceLocation = CandyWorld.id(name)

        private val _items: MutableList<Item> = ArrayList()
        val items: List<Item> get() = _items

        val value: CreativeModeTab by lazy {
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                .title(Component.translatable("itemGroup.${CandyWorld.MOD_ID}.$name"))
                .icon { itemSupplier() }
                .displayItems { _, output ->
                    items.forEach {
                        output.accept(it)
                    }
                }
                .build()
        }

        fun addItem(item: Item) {
            _items.add(item)
        }
    }
}