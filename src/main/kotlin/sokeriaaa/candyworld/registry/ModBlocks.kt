package sokeriaaa.candyworld.registry

import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SlimeBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType
import net.minecraft.world.level.material.MapColor
import sokeriaaa.candyworld.blocks.ModSoundTypes
import sokeriaaa.candyworld.blocks.StackableBlock
import sokeriaaa.candyworld.blocks.SugarSandBlock
import sokeriaaa.candyworld.blocks.WaferStickBlock
import sokeriaaa.candyworld.blocks.candysoil.CandyGrassBlock
import sokeriaaa.candyworld.blocks.chocolate.ChocolateBarBlock
import sokeriaaa.candyworld.blocks.chocolate.ChocolateLeavesBlock
import sokeriaaa.candyworld.blocks.chocolate.ChocolateMushroomBlock
import sokeriaaa.candyworld.blocks.chocolate.ChocolateSaplingBlock
import sokeriaaa.candyworld.blocks.cottoncandy.CottonCandyBushBlock
import sokeriaaa.candyworld.blocks.cottoncandy.CottonCandyLeavesBlock
import sokeriaaa.candyworld.blocks.cottoncandy.CottonCandyPlantBlock
import sokeriaaa.candyworld.blocks.cottoncandy.CottonCandySaplingBlock
import sokeriaaa.candyworld.blocks.fluid.LiquidCandyBlock
import sokeriaaa.candyworld.blocks.fluid.LiquidChocolateBlock
import sokeriaaa.candyworld.blocks.fluid.ModFluids
import sokeriaaa.candyworld.blocks.gummy.GummyBlock
import sokeriaaa.candyworld.blocks.gummy.GummyWormBlock
import sokeriaaa.candyworld.blocks.ore.CookieOreBlock
import sokeriaaa.candyworld.blocks.ore.TeleporterOreBlock
import sokeriaaa.candyworld.blocks.workbench.ModCraftingTableBlock
import sokeriaaa.candyworld.platform.registry.CandyWorldRegistry
import sokeriaaa.candyworld.world.tree.ModTreeGrowers

object ModBlocks {

    val CHOCOLATE_SAPLING = block("chocolate_sapling") {
        ChocolateSaplingBlock(
            ModTreeGrowers.CHOCOLATE,
            BlockBehaviour.Properties.of()
                .noCollission()
                .randomTicks()
                .instabreak()
                .offsetType(OffsetType.XZ)
                .sound(SoundType.GRASS),
        )
    }

    val WAFER_STICK_BLOCK = block("wafer_stick_block") {
        WaferStickBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_ORANGE)
                .strength(0.9F)
                .sound(SoundType.CALCITE),
        )
    }

    val MILK_CHOCOLATE_LEAVES = block("milk_chocolate_leaves") {
        ChocolateLeavesBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(0.3F)
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS),
        )
    }

    val WHITE_CHOCOLATE_LEAVES = block("white_chocolate_leaves") {
        ChocolateLeavesBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .strength(0.3F)
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS),
        )
    }

    val DARK_CHOCOLATE_LEAVES = block("dark_chocolate_leaves") {
        ChocolateLeavesBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_BROWN)
                .strength(0.3F)
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS),
        )
    }

    val MILK_CHOCOLATE_BAR_BLOCK = block("milk_chocolate_bar_block") {
        ChocolateBarBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(0.7F)
                .sound(SoundType.CALCITE)
        )
    }

    val WHITE_CHOCOLATE_BAR_BLOCK = block("white_chocolate_bar_block") {
        ChocolateBarBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .strength(0.7F)
                .sound(SoundType.CALCITE)
        )
    }

    val DARK_CHOCOLATE_BAR_BLOCK = block("dark_chocolate_bar_block") {
        ChocolateBarBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_BROWN)
                .strength(0.7F)
                .sound(SoundType.CALCITE)
        )
    }

    val MILK_CHOCOLATE_MUSHROOM = block("milk_chocolate_mushroom") {
        ChocolateMushroomBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .noCollission()
                .instabreak()
                .replaceable()
                .offsetType(OffsetType.XYZ)
                .sound(SoundType.GRASS),
        )
    }

    val WHITE_CHOCOLATE_MUSHROOM = block("white_chocolate_mushroom") {
        ChocolateMushroomBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .noCollission()
                .instabreak()
                .replaceable()
                .offsetType(OffsetType.XYZ)
                .sound(SoundType.GRASS),
        )
    }

    val DARK_CHOCOLATE_MUSHROOM = block("dark_chocolate_mushroom") {
        ChocolateMushroomBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .noCollission()
                .instabreak()
                .replaceable()
                .offsetType(OffsetType.XYZ)
                .sound(SoundType.GRASS),
        )
    }

    val MILK_CHOCOLATE_BLOCK = block("milk_chocolate_block") {
        Block(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(0.7f)
                .sound(SoundType.CALCITE),
        )
    }

    val WHITE_CHOCOLATE_BLOCK = block("white_chocolate_block") {
        Block(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(0.7f)
                .sound(SoundType.CALCITE),
        )
    }

    val DARK_CHOCOLATE_BLOCK = block("dark_chocolate_block") {
        Block(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(0.7f)
                .sound(SoundType.CALCITE),
        )
    }

    val MILK_CHOCOLATE_BRICK = block("milk_chocolate_brick") {
        Block(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(0.7f)
                .sound(SoundType.CALCITE),
        )
    }

    val WHITE_CHOCOLATE_BRICK = block("white_chocolate_brick") {
        Block(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(0.7f)
                .sound(SoundType.CALCITE),
        )
    }

    val DARK_CHOCOLATE_BRICK = block("dark_chocolate_brick") {
        Block(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(0.7f)
                .sound(SoundType.CALCITE),
        )
    }

    val MILK_CHOCOLATE_WORKBENCH = block("milk_chocolate_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(0.9f)
                .sound(SoundType.CALCITE),
        )
    }

    val WHITE_CHOCOLATE_WORKBENCH = block("white_chocolate_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(0.9f)
                .sound(SoundType.CALCITE),
        )
    }

    val DARK_CHOCOLATE_WORKBENCH = block("dark_chocolate_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(0.9f)
                .sound(SoundType.CALCITE),
        )
    }

    val COTTON_CANDY_SAPLING = block("cotton_candy_sapling") {
        CottonCandySaplingBlock(
            ModTreeGrowers.COTTON_CANDY,
            BlockBehaviour.Properties.of()
                .noCollission()
                .randomTicks()
                .instabreak()
                .offsetType(OffsetType.XZ)
                .sound(SoundType.GRASS),
        )
    }

    val COTTON_CANDY_LEAVES = block("cotton_candy_leaves") {
        CottonCandyLeavesBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_PINK)
                .strength(0.2F)
                .randomTicks()
                .instabreak()
                .sound(ModSoundTypes.COTTON_CANDY),
        )
    }

    val COTTON_CANDY_PLANT = block("cotton_candy_plant") {
        CottonCandyPlantBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_PINK)
                .noCollission()
                .instabreak()
                .offsetType(OffsetType.XYZ)
                .sound(ModSoundTypes.COTTON_CANDY),
        )
    }

    val COTTON_CANDY_BUSH = block("cotton_candy_bush") {
        CottonCandyBushBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_PINK)
                .noCollission()
                .instabreak()
                .replaceable()
                .offsetType(OffsetType.XYZ)
                .sound(ModSoundTypes.COTTON_CANDY),
        )
    }

    val WHITE_CANDY_CANE_BLOCK = block("white_candy_cane_block") {
        StackableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.2F)
                .sound(SoundType.TUFF),
        )
    }

    val RED_CANDY_CANE_BLOCK = block("red_candy_cane_block") {
        StackableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.2F)
                .sound(SoundType.TUFF),
        )
    }

    val GREEN_CANDY_CANE_BLOCK = block("green_candy_cane_block") {
        StackableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.2F)
                .sound(SoundType.TUFF),
        )
    }

    val WHITE_RED_CANDY_CANE_BLOCK = block("white_red_candy_cane_block") {
        StackableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.2F)
                .sound(SoundType.TUFF),
        )
    }

    val WHITE_GREEN_CANDY_CANE_BLOCK = block("white_green_candy_cane_block") {
        StackableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.2F)
                .sound(SoundType.TUFF),
        )
    }

    val RED_GREEN_CANDY_CANE_BLOCK = block("red_green_candy_cane_block") {
        StackableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.2F)
                .sound(SoundType.TUFF),
        )
    }

    val WHITE_CANDY_CANE_WORKBENCH = block("white_candy_cane_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.2f)
                .sound(SoundType.TUFF),
        )
    }

    val RED_CANDY_CANE_WORKBENCH = block("red_candy_cane_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.2f)
                .sound(SoundType.TUFF),
        )
    }

    val GREEN_CANDY_CANE_WORKBENCH = block("green_candy_cane_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.2f)
                .sound(SoundType.TUFF),
        )
    }

    val WHITE_RED_CANDY_CANE_WORKBENCH = block("white_red_candy_cane_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.2f)
                .sound(SoundType.TUFF),
        )
    }

    val WHITE_GREEN_CANDY_CANE_WORKBENCH = block("white_green_candy_cane_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.2f)
                .sound(SoundType.TUFF),
        )
    }

    val RED_GREEN_CANDY_CANE_WORKBENCH = block("red_green_candy_cane_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.2f)
                .sound(SoundType.TUFF),
        )
    }

    val CRYSTALLIZED_SUGAR = block("crystallized_sugar") {
        Block(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.5F)
                .sound(SoundType.CALCITE),
        )
    }

    val SUGAR_SAND = block("sugar_sand") {
        SugarSandBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .strength(0.5F)
                .sound(SoundType.SAND),
        )
    }

    val CANDY_GRASS_BLOCK = block("candy_grass_block") {
        CandyGrassBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.DIRT)
                .randomTicks()
                .strength(0.6F)
                .sound(ModSoundTypes.CANDY_GRASS)
        )
    }

    val MILK_BROWNIE_BLOCK = block("milk_brownie_block") {
        Block(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.DIRT)
                .strength(0.6F)
                .sound(ModSoundTypes.CANDY_DIRT),
        )
    }

    val CHOCOLATE_COVERED_WHITE_BROWNIE = block("chocolate_covered_white_brownie") {
        CandyGrassBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .randomTicks()
                .strength(0.6F)
                .sound(ModSoundTypes.CANDY_DIRT)
        )
    }

    val WHITE_BROWNIE_BLOCK = block("white_brownie_block") {
        Block(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .strength(0.6F)
                .sound(ModSoundTypes.CANDY_DIRT),
        )
    }

    val DARK_CANDY_GRASS_BLOCK = block("dark_candy_grass_block") {
        CandyGrassBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.DIRT)
                .randomTicks()
                .strength(0.6F)
                .sound(ModSoundTypes.CANDY_GRASS)
        )
    }

    val DARK_BROWNIE_BLOCK = block("dark_brownie_block") {
        Block(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.DIRT)
                .strength(0.6F)
                .sound(ModSoundTypes.CANDY_DIRT),
        )
    }

    val CRYSTALLIZED_SUGAR_COOKIE_ORE = block("crystallized_sugar_cookie_ore") {
        CookieOreBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.5F)
                .sound(SoundType.CALCITE),
        )
    }

    val COOKIE_ORE = block("cookie_ore") {
        CookieOreBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .strength(1.5F)
                .sound(SoundType.STONE),
        )
    }

    val TELEPORTER_ORE = block("teleporter_ore") {
        TeleporterOreBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .strength(1.5F)
                .sound(SoundType.STONE),
        )
    }

    val RED_GUMMY_BLOCK = block("red_gummy_block") {
        GummyBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.FIRE)
                .strength(0.4F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY)
                .noOcclusion(),
        )
    }

    val ORANGE_GUMMY_BLOCK = block("orange_gummy_block") {
        GummyBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(0.4F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY)
                .noOcclusion(),
        )
    }

    val YELLOW_GUMMY_BLOCK = block("yellow_gummy_block") {
        GummyBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_YELLOW)
                .strength(0.4F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY)
                .noOcclusion(),
        )
    }

    val WHITE_GUMMY_BLOCK = block("white_gummy_block") {
        GummyBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .strength(0.4F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY)
                .noOcclusion(),
        )
    }

    val GREEN_GUMMY_BLOCK = block("green_gummy_block") {
        GummyBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .strength(0.4F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY)
                .noOcclusion(),
        )
    }

    val RED_HARDENED_GUMMY_BLOCK = block("red_hardened_gummy_block") {
        SlimeBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.FIRE)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val ORANGE_HARDENED_GUMMY_BLOCK = block("orange_hardened_gummy_block") {
        SlimeBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val YELLOW_HARDENED_GUMMY_BLOCK = block("yellow_hardened_gummy_block") {
        SlimeBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_YELLOW)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val WHITE_HARDENED_GUMMY_BLOCK = block("white_hardened_gummy_block") {
        SlimeBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val GREEN_HARDENED_GUMMY_BLOCK = block("green_hardened_gummy_block") {
        SlimeBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val RED_GUMMY_WORM_BLOCK = block("red_gummy_worm_block") {
        GummyWormBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.FIRE)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val ORANGE_GUMMY_WORM_BLOCK = block("orange_gummy_worm_block") {
        GummyWormBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val YELLOW_GUMMY_WORM_BLOCK = block("yellow_gummy_worm_block") {
        GummyWormBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_YELLOW)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val WHITE_GUMMY_WORM_BLOCK = block("white_gummy_worm_block") {
        GummyWormBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val GREEN_GUMMY_WORM_BLOCK = block("green_gummy_worm_block") {
        GummyWormBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val RED_GUMMY_WORKBENCH = block("red_gummy_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.FIRE)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val ORANGE_GUMMY_WORKBENCH = block("orange_gummy_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.TERRACOTTA_YELLOW)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val YELLOW_GUMMY_WORKBENCH = block("yellow_gummy_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_YELLOW)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val WHITE_GUMMY_WORKBENCH = block("white_gummy_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.SAND)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val GREEN_GUMMY_WORKBENCH = block("green_gummy_workbench") {
        ModCraftingTableBlock(
            BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_LIGHT_GREEN)
                .strength(0.5F)
                .friction(0.6F)
                .sound(ModSoundTypes.GUMMY),
        )
    }

    val LIQUID_CHOCOLATE_BLOCK = block("liquid_chocolate") {
        LiquidChocolateBlock(
            ModFluids.LIQUID_CHOCOLATE_SOURCE.value,
            BlockBehaviour.Properties.of()
                .noCollission()
                .strength(100.0F)
                .replaceable()
                .noLootTable(),
        )
    }

    val LIQUID_CANDY_BLOCK = block("liquid_candy") {
        LiquidCandyBlock(
            ModFluids.LIQUID_CANDY_SOURCE.value,
            BlockBehaviour.Properties.of()
                .noCollission()
                .strength(100.0F)
                .lightLevel { 12 }
                .replaceable()
                .noLootTable(),
        )
    }

    fun register() {
        CandyWorldRegistry {
            registerBlock(CHOCOLATE_SAPLING, ModCreativeModeTabs.BLOCKS)
            registerBlock(WAFER_STICK_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(MILK_CHOCOLATE_LEAVES, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_CHOCOLATE_LEAVES, ModCreativeModeTabs.BLOCKS)
            registerBlock(DARK_CHOCOLATE_LEAVES, ModCreativeModeTabs.BLOCKS)

            registerBlock(MILK_CHOCOLATE_BAR_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_CHOCOLATE_BAR_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(DARK_CHOCOLATE_BAR_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(MILK_CHOCOLATE_MUSHROOM, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_CHOCOLATE_MUSHROOM, ModCreativeModeTabs.BLOCKS)
            registerBlock(DARK_CHOCOLATE_MUSHROOM, ModCreativeModeTabs.BLOCKS)
            registerBlock(MILK_CHOCOLATE_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_CHOCOLATE_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(DARK_CHOCOLATE_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(MILK_CHOCOLATE_BRICK, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_CHOCOLATE_BRICK, ModCreativeModeTabs.BLOCKS)
            registerBlock(DARK_CHOCOLATE_BRICK, ModCreativeModeTabs.BLOCKS)
            registerBlock(MILK_CHOCOLATE_WORKBENCH, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_CHOCOLATE_WORKBENCH, ModCreativeModeTabs.BLOCKS)
            registerBlock(DARK_CHOCOLATE_WORKBENCH, ModCreativeModeTabs.BLOCKS)

            registerBlock(COTTON_CANDY_SAPLING, ModCreativeModeTabs.BLOCKS)
            registerBlock(COTTON_CANDY_LEAVES, ModCreativeModeTabs.BLOCKS)
            registerBlock(COTTON_CANDY_PLANT, ModCreativeModeTabs.BLOCKS)
            registerBlock(COTTON_CANDY_BUSH, ModCreativeModeTabs.BLOCKS)

            registerBlock(WHITE_CANDY_CANE_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(RED_CANDY_CANE_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(GREEN_CANDY_CANE_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_RED_CANDY_CANE_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_GREEN_CANDY_CANE_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(RED_GREEN_CANDY_CANE_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_CANDY_CANE_WORKBENCH, ModCreativeModeTabs.BLOCKS)
            registerBlock(RED_CANDY_CANE_WORKBENCH, ModCreativeModeTabs.BLOCKS)
            registerBlock(GREEN_CANDY_CANE_WORKBENCH, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_RED_CANDY_CANE_WORKBENCH, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_GREEN_CANDY_CANE_WORKBENCH, ModCreativeModeTabs.BLOCKS)
            registerBlock(RED_GREEN_CANDY_CANE_WORKBENCH, ModCreativeModeTabs.BLOCKS)

            registerBlock(CRYSTALLIZED_SUGAR, ModCreativeModeTabs.BLOCKS)
            registerBlock(SUGAR_SAND, ModCreativeModeTabs.BLOCKS)
            registerBlock(CANDY_GRASS_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(MILK_BROWNIE_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(CHOCOLATE_COVERED_WHITE_BROWNIE, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_BROWNIE_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(DARK_CANDY_GRASS_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(DARK_BROWNIE_BLOCK, ModCreativeModeTabs.BLOCKS)

            registerBlock(CRYSTALLIZED_SUGAR_COOKIE_ORE, ModCreativeModeTabs.BLOCKS)
            registerBlock(COOKIE_ORE, ModCreativeModeTabs.BLOCKS)
            registerBlock(TELEPORTER_ORE, ModCreativeModeTabs.BLOCKS)

            registerBlock(RED_GUMMY_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(ORANGE_GUMMY_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(YELLOW_GUMMY_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_GUMMY_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(GREEN_GUMMY_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(RED_HARDENED_GUMMY_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(ORANGE_HARDENED_GUMMY_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(YELLOW_HARDENED_GUMMY_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_HARDENED_GUMMY_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(GREEN_HARDENED_GUMMY_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(RED_GUMMY_WORM_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(ORANGE_GUMMY_WORM_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(YELLOW_GUMMY_WORM_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_GUMMY_WORM_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(GREEN_GUMMY_WORM_BLOCK, ModCreativeModeTabs.BLOCKS)
            registerBlock(RED_GUMMY_WORKBENCH, ModCreativeModeTabs.BLOCKS)
            registerBlock(ORANGE_GUMMY_WORKBENCH, ModCreativeModeTabs.BLOCKS)
            registerBlock(YELLOW_GUMMY_WORKBENCH, ModCreativeModeTabs.BLOCKS)
            registerBlock(WHITE_GUMMY_WORKBENCH, ModCreativeModeTabs.BLOCKS)
            registerBlock(GREEN_GUMMY_WORKBENCH, ModCreativeModeTabs.BLOCKS)

            registerBlock(LIQUID_CHOCOLATE_BLOCK)
            registerBlock(LIQUID_CANDY_BLOCK)
        }
    }

    private inline fun <reified T : Block> block(
        path: String,
        noinline supplier: () -> T,
    ) = RegistryObject(
        path = path,
        lazy = lazy(supplier),
    )
}