package sokeriaaa.candyworld.registry

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.material.Fluid
import sokeriaaa.candyworld.CandyWorld

object ModTags {
    val CHOCOLATE_BARS = itemTag(":chocolate_bars")
    val CHOCOLATE_BARS_DARK = itemTag(":chocolate_bars/dark")
    val CHOCOLATE_BARS_MILK = itemTag(":chocolate_bars/milk")
    val CHOCOLATE_BARS_WHITE = itemTag(":chocolate_bars/white")

    val CHOCOLATE_EGGS = itemTag(":chocolate_eggs")
    val CHOCOLATE_EGGS_DARK = itemTag(":chocolate_eggs/dark")
    val CHOCOLATE_EGGS_MILK = itemTag(":chocolate_eggs/milk")
    val CHOCOLATE_EGGS_WHITE = itemTag(":chocolate_eggs/white")

    val GUMMYS = itemTag(":gummys")
    val GUMMYS_GREEN = itemTag(":gummys/green")
    val GUMMYS_ORANGE = itemTag(":gummys/orange")
    val GUMMYS_RED = itemTag(":gummys/red")
    val GUMMYS_WHITE = itemTag(":gummys/white")
    val GUMMYS_YELLOW = itemTag(":gummys/yellow")

    val GUMMY_WORMS = itemTag(":gummy_worms")
    val GUMMY_WORMS_GREEN = itemTag(":gummy_worms/green")
    val GUMMY_WORMS_ORANGE = itemTag(":gummy_worms/orange")
    val GUMMY_WORMS_RED = itemTag(":gummy_worms/red")
    val GUMMY_WORMS_WHITE = itemTag(":gummy_worms/white")
    val GUMMY_WORMS_YELLOW = itemTag(":gummy_worms/yellow")

    val SUGAR = blockTag(":sugar")
    val GUMMY = blockTag(":gummy")
    val BROWNIE = blockTag(":brownie")
    val COVERED_BROWNIE = blockTag(":covered_brownie")
    val CANDY_SOIL = blockTag(":candy_soil")

    val CHOCOLATE_LEAVES = blockTag(":chocolate_leaves")
    val COTTON_CANDY_LEAVES = blockTag(":cotton_candy_leaves")

    val CANDY = fluidTag(":candy")

    val C_BLOCK_ORES = cBlockTag(":ores")
    val C_BLOCK_ORES_COOKIE = cBlockTag(":ores/cookie")
    val C_BLOCK_ORES_TELEPORTER = cBlockTag(":ores/teleporter")
    val C_BLOCK_CRAFT_TABLE = cBlockTag(":player_workstations/crafting_tables")
    val C_BLOCK_ORES_IN_GROUND_STONE = cBlockTag(":ores_in_ground/stone")
    val C_BLOCK_ORES_IN_GROUND_CRYSTALLIZED_SUGAR = cBlockTag(":ores_in_ground/crystallized_sugar")
    val C_BLOCK_ORE_RATE_SPARSE = cBlockTag(":ore_rates/sparse")
    val C_BLOCK_ORE_RATE_SINGULAR = cBlockTag(":ore_rates/singular")

    val C_ITEM_ORES = cItemTag(":ores")
    val C_ITEM_ORES_COOKIE = cItemTag(":ores/cookie")
    val C_ITEM_ORES_TELEPORTER = cItemTag(":ores/teleporter")
    val C_ITEM_ORES_IN_GROUND_STONE = cItemTag(":ores_in_ground/stone")
    val C_ITEM_ORES_IN_GROUND_CRYSTALLIZED_SUGAR = cItemTag(":ores_in_ground/crystallized_sugar")
    val C_ITEM_ORE_RATE_SPARSE = cItemTag(":ore_rates/sparse")
    val C_ITEM_ORE_RATE_SINGULAR = cItemTag(":ore_rates/singular")

    val C_FLUID_CHOCOLATE = cFluidTag(":chocolates")

    private fun itemTag(path: String): TagKey<Item> {
        return TagKey.create(
            Registries.ITEM,
            ResourceLocation.parse(CandyWorld.MOD_ID + path),
        )
    }

    private fun blockTag(path: String): TagKey<Block> {
        return TagKey.create(
            Registries.BLOCK,
            ResourceLocation.parse(CandyWorld.MOD_ID + path),
        )
    }

    private fun fluidTag(path: String): TagKey<Fluid> {
        return TagKey.create(
            Registries.FLUID,
            ResourceLocation.parse(CandyWorld.MOD_ID + path),
        )
    }

    private fun cItemTag(path: String): TagKey<Item> {
        return TagKey.create(
            Registries.ITEM,
            ResourceLocation.parse("c$path"),
        )
    }

    private fun cBlockTag(path: String): TagKey<Block> {
        return TagKey.create(
            Registries.BLOCK,
            ResourceLocation.parse("c$path"),
        )
    }

    private fun cFluidTag(path: String): TagKey<Fluid> {
        return TagKey.create(
            Registries.FLUID,
            ResourceLocation.parse("c$path"),
        )
    }
}