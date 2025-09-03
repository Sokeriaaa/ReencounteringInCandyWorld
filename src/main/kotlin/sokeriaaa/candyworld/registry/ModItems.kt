package sokeriaaa.candyworld.registry

import net.minecraft.client.color.item.ItemColor
import net.minecraft.world.item.*
import net.minecraft.world.level.block.Block
import sokeriaaa.candyworld.blocks.fluid.ModFluids
import sokeriaaa.candyworld.items.teleporter.TeleporterItem
import sokeriaaa.candyworld.items.tools.EdibleAxeItem
import sokeriaaa.candyworld.items.tools.EdiblePickaxeItem
import sokeriaaa.candyworld.items.tools.EdibleShovelItem
import sokeriaaa.candyworld.items.tools.EdibleSwordItem
import sokeriaaa.candyworld.platform.registry.CandyWorldRegistry
import sokeriaaa.candyworld.registry.ModBlocks.GREEN_GUMMY_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.GREEN_GUMMY_WORKBENCH
import sokeriaaa.candyworld.registry.ModBlocks.GREEN_GUMMY_WORM_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.GREEN_HARDENED_GUMMY_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.ORANGE_GUMMY_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.ORANGE_GUMMY_WORKBENCH
import sokeriaaa.candyworld.registry.ModBlocks.ORANGE_GUMMY_WORM_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.ORANGE_HARDENED_GUMMY_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.RED_GUMMY_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.RED_GUMMY_WORKBENCH
import sokeriaaa.candyworld.registry.ModBlocks.RED_GUMMY_WORM_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.RED_HARDENED_GUMMY_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.WHITE_GUMMY_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.WHITE_GUMMY_WORKBENCH
import sokeriaaa.candyworld.registry.ModBlocks.WHITE_GUMMY_WORM_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.WHITE_HARDENED_GUMMY_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.YELLOW_GUMMY_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.YELLOW_GUMMY_WORKBENCH
import sokeriaaa.candyworld.registry.ModBlocks.YELLOW_GUMMY_WORM_BLOCK
import sokeriaaa.candyworld.registry.ModBlocks.YELLOW_HARDENED_GUMMY_BLOCK

object ModItems {

    // simple food items
    val BUTTER = item("butter") {
        Item(Item.Properties().food(ModFoods.BUTTER))
    }

    val COTTON_CANDY = item("cotton_candy") {
        Item(Item.Properties().food(ModFoods.COTTON_CANDY))
    }

    val WAFER_STICK = item("wafer_stick") {
        Item(Item.Properties().food(ModFoods.WAFER_STICK))
    }

    val SUGAR_CRYSTAL = item("sugar_crystal") {
        Item(Item.Properties().food(ModFoods.SUGAR_ROCK))
    }

    // food items
    val WHITE_CANDY_CANE = item("white_candy_cane") {
        Item(Item.Properties().food(ModFoods.CANDY_CANE))
    }

    val RED_CANDY_CANE = item("red_candy_cane") {
        Item(Item.Properties().food(ModFoods.CANDY_CANE))
    }

    val GREEN_CANDY_CANE = item("green_candy_cane") {
        Item(Item.Properties().food(ModFoods.CANDY_CANE))
    }

    val WHITE_RED_CANDY_CANE = item("white_red_candy_cane") {
        Item(Item.Properties().food(ModFoods.CANDY_CANE))
    }

    val WHITE_GREEN_CANDY_CANE = item("white_green_candy_cane") {
        Item(Item.Properties().food(ModFoods.CANDY_CANE))
    }

    val RED_GREEN_CANDY_CANE = item("red_green_candy_cane") {
        Item(Item.Properties().food(ModFoods.CANDY_CANE))
    }

    val MILK_BROWNIE = item("milk_brownie") {
        Item(Item.Properties().food(ModFoods.BROWNIE))
    }

    val WHITE_BROWNIE = item("white_brownie") {
        Item(Item.Properties().food(ModFoods.BROWNIE))
    }

    val DARK_BROWNIE = item("dark_brownie") {
        Item(Item.Properties().food(ModFoods.BROWNIE))
    }

    val MILK_CHOCOLATE_BAR = item("milk_chocolate_bar") {
        Item(Item.Properties().food(ModFoods.CHOCOLATE_BAR))
    }

    val WHITE_CHOCOLATE_BAR = item("white_chocolate_bar") {
        Item(Item.Properties().food(ModFoods.CHOCOLATE_BAR))
    }

    val DARK_CHOCOLATE_BAR = item("dark_chocolate_bar") {
        Item(Item.Properties().food(ModFoods.CHOCOLATE_BAR))
    }

    val MILK_CHOCOLATE_EGG = item("milk_chocolate_egg") {
        Item(Item.Properties().food(ModFoods.CHOCOLATE_EGG))
    }

    val WHITE_CHOCOLATE_EGG = item("white_chocolate_egg") {
        Item(Item.Properties().food(ModFoods.CHOCOLATE_EGG))
    }

    val DARK_CHOCOLATE_EGG = item("dark_chocolate_egg") {
        Item(Item.Properties().food(ModFoods.CHOCOLATE_EGG))
    }

    val RED_GUMMY = item("red_gummy") {
        Item(Item.Properties().food(ModFoods.GUMMY))
    }

    val ORANGE_GUMMY = item("orange_gummy") {
        Item(Item.Properties().food(ModFoods.GUMMY))
    }

    val YELLOW_GUMMY = item("yellow_gummy") {
        Item(Item.Properties().food(ModFoods.GUMMY))
    }

    val WHITE_GUMMY = item("white_gummy") {
        Item(Item.Properties().food(ModFoods.GUMMY))
    }

    val GREEN_GUMMY = item("green_gummy") {
        Item(Item.Properties().food(ModFoods.GUMMY))
    }

    val RED_GUMMY_WORM = item("red_gummy_worm") {
        Item(Item.Properties().food(ModFoods.GUMMY_WORM))
    }

    val ORANGE_GUMMY_WORM = item("orange_gummy_worm") {
        Item(Item.Properties().food(ModFoods.GUMMY_WORM))
    }

    val YELLOW_GUMMY_WORM = item("yellow_gummy_worm") {
        Item(Item.Properties().food(ModFoods.GUMMY_WORM))
    }

    val WHITE_GUMMY_WORM = item("white_gummy_worm") {
        Item(Item.Properties().food(ModFoods.GUMMY_WORM))
    }

    val GREEN_GUMMY_WORM = item("green_gummy_worm") {
        Item(Item.Properties().food(ModFoods.GUMMY_WORM))
    }

    val TELEPORTER = item("teleporter") {
        TeleporterItem(Item.Properties().food(ModFoods.TELEPORTER))
    }

    val MILK_CHOCOLATE_AXE = item("milk_chocolate_axe") {
        EdibleAxeItem(
            ModItemTiers.CHOCOLATE,
            Item.Properties()
                .attributes(
                    AxeItem.createAttributes(
                        ModItemTiers.CHOCOLATE,
                        5.5F,
                        -3F,
                    ),
                )
                .food(ModFoods.CHOCOLATE_TOOL)
        )
    }

    val MILK_CHOCOLATE_PICKAXE = item("milk_chocolate_pickaxe") {
        EdiblePickaxeItem(
            ModItemTiers.CHOCOLATE,
            Item.Properties()
                .attributes(
                    PickaxeItem.createAttributes(
                        ModItemTiers.CHOCOLATE,
                        1F,
                        -2.8F,
                    ),
                )
                .food(ModFoods.CHOCOLATE_TOOL)
        )
    }

    val MILK_CHOCOLATE_SHOVEL = item("milk_chocolate_shovel") {
        EdibleShovelItem(
            ModItemTiers.CHOCOLATE,
            Item.Properties()
                .attributes(
                    ShovelItem.createAttributes(
                        ModItemTiers.CHOCOLATE,
                        1.5F,
                        -3.0F,
                    ),
                )
                .food(ModFoods.CHOCOLATE_TOOL)
        )
    }

    val MILK_CHOCOLATE_SWORD = item("milk_chocolate_sword") {
        EdibleSwordItem(
            ModItemTiers.CHOCOLATE,
            Item.Properties()
                .attributes(
                    SwordItem.createAttributes(
                        ModItemTiers.CHOCOLATE,
                        3,
                        -2.4F,
                    ),
                )
                .food(ModFoods.CHOCOLATE_TOOL)
        )
    }

    val WHITE_CHOCOLATE_AXE = item("white_chocolate_axe") {
        EdibleAxeItem(
            ModItemTiers.CHOCOLATE,
            Item.Properties()
                .attributes(
                    AxeItem.createAttributes(
                        ModItemTiers.CHOCOLATE,
                        5.5F,
                        -3F,
                    ),
                )
                .food(ModFoods.CHOCOLATE_TOOL)
        )
    }

    val WHITE_CHOCOLATE_PICKAXE = item("white_chocolate_pickaxe") {
        EdiblePickaxeItem(
            ModItemTiers.CHOCOLATE,
            Item.Properties()
                .attributes(
                    PickaxeItem.createAttributes(
                        ModItemTiers.CHOCOLATE,
                        1F,
                        -2.8F,
                    ),
                )
                .food(ModFoods.CHOCOLATE_TOOL)
        )
    }

    val WHITE_CHOCOLATE_SHOVEL = item("white_chocolate_shovel") {
        EdibleShovelItem(
            ModItemTiers.CHOCOLATE,
            Item.Properties()
                .attributes(
                    ShovelItem.createAttributes(
                        ModItemTiers.CHOCOLATE,
                        1.5F,
                        -3.0F,
                    ),
                )
                .food(ModFoods.CHOCOLATE_TOOL)
        )
    }

    val WHITE_CHOCOLATE_SWORD = item("white_chocolate_sword") {
        EdibleSwordItem(
            ModItemTiers.CHOCOLATE,
            Item.Properties()
                .attributes(
                    SwordItem.createAttributes(
                        ModItemTiers.CHOCOLATE,
                        3,
                        -2.4F,
                    ),
                )
                .food(ModFoods.CHOCOLATE_TOOL)
        )
    }

    val DARK_CHOCOLATE_AXE = item("dark_chocolate_axe") {
        EdibleAxeItem(
            ModItemTiers.CHOCOLATE,
            Item.Properties()
                .attributes(
                    AxeItem.createAttributes(
                        ModItemTiers.CHOCOLATE,
                        5.5F,
                        -3F,
                    ),
                )
                .food(ModFoods.CHOCOLATE_TOOL)
        )
    }

    val DARK_CHOCOLATE_PICKAXE = item("dark_chocolate_pickaxe") {
        EdiblePickaxeItem(
            ModItemTiers.CHOCOLATE,
            Item.Properties()
                .attributes(
                    PickaxeItem.createAttributes(
                        ModItemTiers.CHOCOLATE,
                        1F,
                        -2.8F,
                    ),
                )
                .food(ModFoods.CHOCOLATE_TOOL)
        )
    }

    val DARK_CHOCOLATE_SHOVEL = item("dark_chocolate_shovel") {
        EdibleShovelItem(
            ModItemTiers.CHOCOLATE,
            Item.Properties()
                .attributes(
                    ShovelItem.createAttributes(
                        ModItemTiers.CHOCOLATE,
                        1.5F,
                        -3.0F,
                    ),
                )
                .food(ModFoods.CHOCOLATE_TOOL)
        )
    }

    val DARK_CHOCOLATE_SWORD = item("dark_chocolate_sword") {
        EdibleSwordItem(
            ModItemTiers.CHOCOLATE,
            Item.Properties()
                .attributes(
                    SwordItem.createAttributes(
                        ModItemTiers.CHOCOLATE,
                        3,
                        -2.4F,
                    ),
                )
                .food(ModFoods.CHOCOLATE_TOOL)
        )
    }

    val COTTON_CANDY_AXE = item("cotton_candy_axe") {
        EdibleAxeItem(
            ModItemTiers.COTTON_CANDY,
            Item.Properties()
                .attributes(
                    AxeItem.createAttributes(
                        ModItemTiers.COTTON_CANDY,
                        5.5F,
                        -3F,
                    ),
                )
                .food(ModFoods.COTTON_CANDY_TOOL)
        )
    }

    val COTTON_CANDY_PICKAXE = item("cotton_candy_pickaxe") {
        EdiblePickaxeItem(
            ModItemTiers.COTTON_CANDY,
            Item.Properties()
                .attributes(
                    PickaxeItem.createAttributes(
                        ModItemTiers.COTTON_CANDY,
                        1F,
                        -2.8F,
                    ),
                )
                .food(ModFoods.COTTON_CANDY_TOOL)
        )
    }

    val COTTON_CANDY_SHOVEL = item("cotton_candy_shovel") {
        EdibleShovelItem(
            ModItemTiers.COTTON_CANDY,
            Item.Properties()
                .attributes(
                    ShovelItem.createAttributes(
                        ModItemTiers.COTTON_CANDY,
                        1.5F,
                        -3.0F,
                    ),
                )
                .food(ModFoods.COTTON_CANDY_TOOL)
        )
    }

    val COTTON_CANDY_SWORD = item("cotton_candy_sword") {
        EdibleSwordItem(
            ModItemTiers.COTTON_CANDY,
            Item.Properties()
                .attributes(
                    SwordItem.createAttributes(
                        ModItemTiers.COTTON_CANDY,
                        3,
                        -2.4F,
                    ),
                )
                .food(ModFoods.COTTON_CANDY_TOOL)
        )
    }

    val COTTON_CANDY_SHEEP_SPAWN_EGG = item("cotton_candy_sheep_spawn_egg") {
        SpawnEggItem(
            ModEntities.COTTON_CANDY_SHEEP.value,
            0xff33ff,
            0xffccff,
            Item.Properties(),
        )
    }

    val EASTER_CHICKEN_SPAWN_EGG = item("easter_chicken_spawn_egg") {
        SpawnEggItem(
            ModEntities.EASTER_CHICKEN.value,
            0x996611,
            0x774411,
            Item.Properties(),
        )
    }

    val GUMMY_MOUSE_SPAWN_EGG = item("gummy_mouse_spawn_egg") {
        SpawnEggItem(
            ModEntities.GUMMY_MOUSE.value,
            0x00ff00,
            0x33bb33,
            Item.Properties(),
        )
    }

    val GUMMY_BEAR_SPAWN_EGG = item("gummy_bear_spawn_egg") {
        SpawnEggItem(
            ModEntities.GUMMY_BEAR.value,
            0x00ff00,
            0x33bb33,
            Item.Properties(),
        )
    }

    val LIQUID_CHOCOLATE_BUCKET = item("liquid_chocolate_bucket") {
        BucketItem(
            ModFluids.LIQUID_CHOCOLATE_SOURCE.value,
            Item.Properties()
                .craftRemainder(Items.BUCKET)
                .stacksTo(1),
        )
    }

    val LIQUID_CANDY_BUCKET = item("liquid_candy_bucket") {
        BucketItem(
            ModFluids.LIQUID_CANDY_SOURCE.value,
            Item.Properties()
                .craftRemainder(Items.BUCKET)
                .stacksTo(1),
        )
    }

    fun registerItemColors(
        registerItemColor: (ItemColor, Array<Block>) -> Unit,
    ) {
        registerItemColor(
            simpleItemColor(0xff4530),
            arrayOf(
                RED_GUMMY_BLOCK.value,
                RED_HARDENED_GUMMY_BLOCK.value,
                RED_GUMMY_WORKBENCH.value,
                RED_GUMMY_WORM_BLOCK.value,
            ),
        )
        registerItemColor(
            simpleItemColor(0xff9b4f),
            arrayOf(
                ORANGE_GUMMY_BLOCK.value,
                ORANGE_HARDENED_GUMMY_BLOCK.value,
                ORANGE_GUMMY_WORKBENCH.value,
                ORANGE_GUMMY_WORM_BLOCK.value,
            ),
        )
        registerItemColor(
            simpleItemColor(0xffe563),
            arrayOf(
                YELLOW_GUMMY_BLOCK.value,
                YELLOW_HARDENED_GUMMY_BLOCK.value,
                YELLOW_GUMMY_WORKBENCH.value,
                YELLOW_GUMMY_WORM_BLOCK.value,
            ),
        )
        registerItemColor(
            simpleItemColor(0xfffeb0),
            arrayOf(
                WHITE_GUMMY_BLOCK.value,
                WHITE_HARDENED_GUMMY_BLOCK.value,
                WHITE_GUMMY_WORKBENCH.value,
                WHITE_GUMMY_WORM_BLOCK.value,
            ),
        )
        registerItemColor(
            simpleItemColor(0x80e22b),
            arrayOf(
                GREEN_GUMMY_BLOCK.value,
                GREEN_HARDENED_GUMMY_BLOCK.value,
                GREEN_GUMMY_WORKBENCH.value,
                GREEN_GUMMY_WORM_BLOCK.value,
            ),
        )
    }

    fun register() {
        CandyWorldRegistry {
            registerItem(BUTTER, ModCreativeModeTabs.ITEMS)
            registerItem(COTTON_CANDY, ModCreativeModeTabs.ITEMS)
            registerItem(WAFER_STICK, ModCreativeModeTabs.ITEMS)
            registerItem(SUGAR_CRYSTAL, ModCreativeModeTabs.ITEMS)

            registerItem(WHITE_CANDY_CANE, ModCreativeModeTabs.ITEMS)
            registerItem(RED_CANDY_CANE, ModCreativeModeTabs.ITEMS)
            registerItem(GREEN_CANDY_CANE, ModCreativeModeTabs.ITEMS)
            registerItem(WHITE_RED_CANDY_CANE, ModCreativeModeTabs.ITEMS)
            registerItem(WHITE_GREEN_CANDY_CANE, ModCreativeModeTabs.ITEMS)
            registerItem(RED_GREEN_CANDY_CANE, ModCreativeModeTabs.ITEMS)

            registerItem(MILK_BROWNIE, ModCreativeModeTabs.ITEMS)
            registerItem(WHITE_BROWNIE, ModCreativeModeTabs.ITEMS)
            registerItem(DARK_BROWNIE, ModCreativeModeTabs.ITEMS)
            registerItem(MILK_CHOCOLATE_BAR, ModCreativeModeTabs.ITEMS)
            registerItem(WHITE_CHOCOLATE_BAR, ModCreativeModeTabs.ITEMS)
            registerItem(DARK_CHOCOLATE_BAR, ModCreativeModeTabs.ITEMS)
            registerItem(MILK_CHOCOLATE_EGG, ModCreativeModeTabs.ITEMS)
            registerItem(WHITE_CHOCOLATE_EGG, ModCreativeModeTabs.ITEMS)
            registerItem(DARK_CHOCOLATE_EGG, ModCreativeModeTabs.ITEMS)

            registerItem(RED_GUMMY, ModCreativeModeTabs.ITEMS)
            registerItem(ORANGE_GUMMY, ModCreativeModeTabs.ITEMS)
            registerItem(YELLOW_GUMMY, ModCreativeModeTabs.ITEMS)
            registerItem(WHITE_GUMMY, ModCreativeModeTabs.ITEMS)
            registerItem(GREEN_GUMMY, ModCreativeModeTabs.ITEMS)
            registerItem(RED_GUMMY_WORM, ModCreativeModeTabs.ITEMS)
            registerItem(ORANGE_GUMMY_WORM, ModCreativeModeTabs.ITEMS)
            registerItem(YELLOW_GUMMY_WORM, ModCreativeModeTabs.ITEMS)
            registerItem(WHITE_GUMMY_WORM, ModCreativeModeTabs.ITEMS)
            registerItem(GREEN_GUMMY_WORM, ModCreativeModeTabs.ITEMS)

            registerItem(TELEPORTER, ModCreativeModeTabs.ITEMS)

            registerItem(MILK_CHOCOLATE_AXE, ModCreativeModeTabs.TOOLS)
            registerItem(MILK_CHOCOLATE_PICKAXE, ModCreativeModeTabs.TOOLS)
            registerItem(MILK_CHOCOLATE_SHOVEL, ModCreativeModeTabs.TOOLS)
            registerItem(MILK_CHOCOLATE_SWORD, ModCreativeModeTabs.TOOLS)
            registerItem(WHITE_CHOCOLATE_AXE, ModCreativeModeTabs.TOOLS)
            registerItem(WHITE_CHOCOLATE_PICKAXE, ModCreativeModeTabs.TOOLS)
            registerItem(WHITE_CHOCOLATE_SHOVEL, ModCreativeModeTabs.TOOLS)
            registerItem(WHITE_CHOCOLATE_SWORD, ModCreativeModeTabs.TOOLS)
            registerItem(DARK_CHOCOLATE_AXE, ModCreativeModeTabs.TOOLS)
            registerItem(DARK_CHOCOLATE_PICKAXE, ModCreativeModeTabs.TOOLS)
            registerItem(DARK_CHOCOLATE_SHOVEL, ModCreativeModeTabs.TOOLS)
            registerItem(DARK_CHOCOLATE_SWORD, ModCreativeModeTabs.TOOLS)
            registerItem(COTTON_CANDY_AXE, ModCreativeModeTabs.TOOLS)
            registerItem(COTTON_CANDY_PICKAXE, ModCreativeModeTabs.TOOLS)
            registerItem(COTTON_CANDY_SHOVEL, ModCreativeModeTabs.TOOLS)
            registerItem(COTTON_CANDY_SWORD, ModCreativeModeTabs.TOOLS)

            registerItem(COTTON_CANDY_SHEEP_SPAWN_EGG, ModCreativeModeTabs.ITEMS)
            registerItem(EASTER_CHICKEN_SPAWN_EGG, ModCreativeModeTabs.ITEMS)
            registerItem(GUMMY_MOUSE_SPAWN_EGG, ModCreativeModeTabs.ITEMS)
            registerItem(GUMMY_BEAR_SPAWN_EGG, ModCreativeModeTabs.ITEMS)

            registerItem(LIQUID_CHOCOLATE_BUCKET, ModCreativeModeTabs.ITEMS)
            registerItem(LIQUID_CANDY_BUCKET, ModCreativeModeTabs.ITEMS)
        }
    }

    private fun simpleItemColor(color: Int): ItemColor = ItemColor { _, _ -> color }

    private inline fun <reified T : Item> item(
        path: String,
        noinline supplier: () -> T,
    ) = RegistryObject(
        path = path,
        lazy = lazy(supplier),
    )
}