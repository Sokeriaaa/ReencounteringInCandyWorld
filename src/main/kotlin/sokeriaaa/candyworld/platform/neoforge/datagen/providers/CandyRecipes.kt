package sokeriaaa.candyworld.platform.neoforge.datagen.providers

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.recipes.*
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.neoforged.neoforge.common.Tags
import sokeriaaa.candyworld.registry.ModBlocks
import sokeriaaa.candyworld.registry.ModItems
import sokeriaaa.candyworld.registry.ModTags
import java.util.concurrent.CompletableFuture

class CandyRecipes(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>,
) : RecipeProvider(output, registries) {
    override fun buildRecipes(recipeOutput: RecipeOutput) {
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(Items.SUGAR),
            RecipeCategory.FOOD,
            ModItems.COTTON_CANDY.value, 0.35f, 200
        ).unlockedBy("has_sugar", has(Items.SUGAR))
            .save(recipeOutput)

        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.MILK_CHOCOLATE_EGG.value),
            RecipeCategory.FOOD,
            ModItems.MILK_CHOCOLATE_BAR.value, 0.4f, 200
        ).unlockedBy("has_milk_chocolate_egg", has(ModItems.MILK_CHOCOLATE_EGG.value))
            .save(recipeOutput, "candyworld:milk_chocolate_bar_from_smelting")
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.WHITE_CHOCOLATE_EGG.value),
            RecipeCategory.FOOD,
            ModItems.WHITE_CHOCOLATE_BAR.value, 0.4f, 200
        ).unlockedBy("has_white_chocolate_egg", has(ModItems.WHITE_CHOCOLATE_EGG.value))
            .save(recipeOutput, "candyworld:white_chocolate_bar_from_smelting")
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModItems.DARK_CHOCOLATE_EGG.value),
            RecipeCategory.FOOD,
            ModItems.DARK_CHOCOLATE_BAR.value, 0.4f, 200
        ).unlockedBy("has_dark_chocolate_egg", has(ModItems.DARK_CHOCOLATE_EGG.value))
            .save(recipeOutput, "candyworld:dark_chocolate_bar_from_smelting")

        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModBlocks.MILK_CHOCOLATE_BRICK.value),
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.MILK_CHOCOLATE_BLOCK.value, 0.4f, 200
        ).unlockedBy("has_milk_chocolate_brick", has(ModBlocks.MILK_CHOCOLATE_BRICK.value))
            .save(recipeOutput, "candyworld:milk_chocolate_block_from_smelting")
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModBlocks.WHITE_CHOCOLATE_BRICK.value),
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.WHITE_CHOCOLATE_BLOCK.value, 0.4f, 200
        ).unlockedBy("has_white_chocolate_brick", has(ModBlocks.WHITE_CHOCOLATE_BRICK.value))
            .save(recipeOutput, "candyworld:white_chocolate_block_from_smelting")
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(ModBlocks.DARK_CHOCOLATE_BRICK.value),
            RecipeCategory.BUILDING_BLOCKS,
            ModBlocks.DARK_CHOCOLATE_BLOCK.value, 0.4f, 200
        ).unlockedBy("has_dark_chocolate_brick", has(ModBlocks.DARK_CHOCOLATE_BRICK.value))
            .save(recipeOutput, "candyworld:dark_chocolate_block_from_smelting")

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.BUTTER.value)
            .define('S', Tags.Items.RODS_WOODEN)
            .define('M', Items.MILK_BUCKET)
            .pattern("S")
            .pattern("M")
            .unlockedBy("has_milk_bucket", has(Items.MILK_BUCKET))
            .save(recipeOutput)


        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.WAFER_STICK.value, 3)
            .define('W', Tags.Items.CROPS_WHEAT)
            .define('C', ModTags.CHOCOLATE_BARS)
            .pattern("WW")
            .pattern("WC")
            .unlockedBy("has_wheat", has(Items.WHEAT)).save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WAFER_STICK.value, 4)
            .requires(ModBlocks.WAFER_STICK_BLOCK.value)
            .unlockedBy("has_wafer_stick_block", has(ModBlocks.WAFER_STICK_BLOCK.value))
            .save(recipeOutput, "candyworld:wafer_stick_from_block")

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WAFER_STICK_BLOCK.value)
            .define('#', ModItems.WAFER_STICK.value)
            .pattern("##")
            .pattern("##")
            .unlockedBy("has_wafer_stick", has(ModItems.WAFER_STICK.value))
            .save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SUGAR_CRYSTAL.value, 4)
            .requires(ModBlocks.CRYSTALLIZED_SUGAR.value)
            .unlockedBy("has_crystallized_sugar", has(ModBlocks.CRYSTALLIZED_SUGAR.value))
            .save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRYSTALLIZED_SUGAR.value)
            .define('#', ModItems.SUGAR_CRYSTAL.value)
            .pattern("##")
            .pattern("##")
            .unlockedBy("has_sugar_crystal", has(ModItems.SUGAR_CRYSTAL.value)).save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_CANDY_CANE.value, 4)
            .requires(ModBlocks.WHITE_CANDY_CANE_BLOCK.value)
            .unlockedBy("has_white_candy_cane_block", has(ModBlocks.WHITE_CANDY_CANE_BLOCK.value))
            .save(recipeOutput, "candyworld:white_candy_cane_from_block")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_CANDY_CANE.value, 4)
            .requires(Items.SUGAR)
            .requires(Items.SUGAR)
            .requires(Items.WATER_BUCKET)
            .unlockedBy("has_sugar", has(Items.SUGAR))
            .save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RED_CANDY_CANE.value, 4)
            .requires(ModBlocks.RED_CANDY_CANE_BLOCK.value)
            .unlockedBy("has_red_candy_cane_block", has(ModBlocks.RED_CANDY_CANE_BLOCK.value))
            .save(recipeOutput, "candyworld:red_candy_cane_from_block")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RED_CANDY_CANE.value, 4)
            .requires(Items.SUGAR)
            .requires(Items.SUGAR)
            .requires(Items.WATER_BUCKET)
            .requires(Tags.Items.DYES_RED)
            .unlockedBy("has_sugar", has(Items.SUGAR))
            .save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GREEN_CANDY_CANE.value, 4)
            .requires(ModBlocks.GREEN_CANDY_CANE_BLOCK.value)
            .unlockedBy("has_green_candy_cane_block", has(ModBlocks.GREEN_CANDY_CANE_BLOCK.value))
            .save(recipeOutput, "candyworld:green_candy_cane_from_block")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GREEN_CANDY_CANE.value, 4)
            .requires(Items.SUGAR)
            .requires(Items.SUGAR)
            .requires(Items.WATER_BUCKET).requires(Tags.Items.DYES_GREEN)
            .unlockedBy("has_sugar", has(Items.SUGAR)).save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_RED_CANDY_CANE.value, 4)
            .requires(ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.value)
            .unlockedBy("has_white_red_candy_cane_block", has(ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.value))
            .save(recipeOutput, "candyworld:white_red_candy_cane_from_block")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_RED_CANDY_CANE.value, 4)
            .requires(Items.SUGAR)
            .requires(Items.SUGAR)
            .requires(Items.WATER_BUCKET)
            .requires(Tags.Items.DYES_RED)
            .requires(Tags.Items.DYES_WHITE)
            .unlockedBy("has_sugar", has(Items.SUGAR))
            .save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_GREEN_CANDY_CANE.value, 4)
            .requires(ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.value)
            .unlockedBy("has_white_green_candy_cane_block", has(ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.value))
            .save(recipeOutput, "candyworld:white_green_candy_cane_from_block")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_GREEN_CANDY_CANE.value, 4)
            .requires(Items.SUGAR)
            .requires(Items.SUGAR)
            .requires(Items.WATER_BUCKET)
            .requires(Tags.Items.DYES_GREEN)
            .requires(Tags.Items.DYES_WHITE)
            .unlockedBy("has_sugar", has(Items.SUGAR)).save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RED_GREEN_CANDY_CANE.value, 4)
            .requires(ModBlocks.RED_GREEN_CANDY_CANE_BLOCK.value)
            .unlockedBy("red_green_candy_cane_block", has(ModBlocks.RED_GREEN_CANDY_CANE_BLOCK.value))
            .save(recipeOutput, "candyworld:red_green_candy_cane_from_block")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RED_GREEN_CANDY_CANE.value, 4)
            .requires(Items.SUGAR)
            .requires(Items.SUGAR)
            .requires(Items.WATER_BUCKET)
            .requires(Tags.Items.DYES_GREEN)
            .requires(Tags.Items.DYES_RED)
            .unlockedBy("has_sugar", has(Items.SUGAR)).save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_BROWNIE.value, 6)
            .requires(Items.SUGAR).requires(ModItems.MILK_CHOCOLATE_EGG.value).requires(Tags.Items.CROPS_WHEAT)
            .requires(ModItems.BUTTER.value)
            .unlockedBy("has_butter", has(ModItems.BUTTER.value)).save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_BROWNIE.value, 6)
            .requires(Items.SUGAR)
            .requires(Tags.Items.EGGS)
            .requires(ModItems.MILK_CHOCOLATE_BAR.value)
            .requires(Tags.Items.CROPS_WHEAT)
            .requires(ModItems.BUTTER.value)
            .unlockedBy("has_butter", has(ModItems.BUTTER.value))
            .save(recipeOutput, "candyworld:milk_brownie_from_bar")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_BROWNIE.value, 4)
            .requires(ModBlocks.MILK_BROWNIE_BLOCK.value)
            .unlockedBy("has_milk_brownie_block", has(ModBlocks.MILK_BROWNIE_BLOCK.value))
            .save(recipeOutput, "candyworld:milk_brownie_from_block")

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_BROWNIE.value, 6)
            .requires(Items.SUGAR).requires(ModItems.WHITE_CHOCOLATE_EGG.value).requires(Tags.Items.CROPS_WHEAT)
            .requires(ModItems.BUTTER.value)
            .unlockedBy("has_butter", has(ModItems.BUTTER.value)).save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_BROWNIE.value, 6)
            .requires(Items.SUGAR)
            .requires(Tags.Items.EGGS)
            .requires(ModItems.WHITE_CHOCOLATE_BAR.value)
            .requires(Tags.Items.CROPS_WHEAT)
            .requires(ModItems.BUTTER.value)
            .unlockedBy("has_butter", has(ModItems.BUTTER.value))
            .save(recipeOutput, "candyworld:white_brownie_from_bar")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_BROWNIE.value, 4)
            .requires(ModBlocks.WHITE_BROWNIE_BLOCK.value)
            .unlockedBy("has_white_brownie_block", has(ModBlocks.WHITE_BROWNIE_BLOCK.value))
            .save(recipeOutput, "candyworld:white_brownie_from_block")

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.DARK_BROWNIE.value, 6)
            .requires(Items.SUGAR)
            .requires(ModItems.DARK_CHOCOLATE_EGG.value)
            .requires(Tags.Items.CROPS_WHEAT)
            .requires(ModItems.BUTTER.value)
            .unlockedBy("has_butter", has(ModItems.BUTTER.value))
            .save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.DARK_BROWNIE.value, 6)
            .requires(Items.SUGAR)
            .requires(Tags.Items.EGGS)
            .requires(ModItems.DARK_CHOCOLATE_BAR.value)
            .requires(Tags.Items.CROPS_WHEAT)
            .requires(ModItems.BUTTER.value)
            .unlockedBy("has_butter", has(ModItems.BUTTER.value))
            .save(recipeOutput, "candyworld:dark_brownie_from_bar")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.DARK_BROWNIE.value, 4)
            .requires(ModBlocks.DARK_BROWNIE_BLOCK.value)
            .unlockedBy("has_dark_brownie_block", has(ModBlocks.DARK_BROWNIE_BLOCK.value))
            .save(recipeOutput, "candyworld:dark_brownie_from_block")

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_CHOCOLATE_BAR.value, 4)
            .requires(ModBlocks.MILK_CHOCOLATE_BLOCK.value)
            .unlockedBy("has_milk_chocolate_block", has(ModBlocks.MILK_CHOCOLATE_BLOCK.value))
            .save(recipeOutput, "candyworld:milk_chocolate_bar_from_block")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_CHOCOLATE_BAR.value, 4)
            .requires(ModBlocks.MILK_CHOCOLATE_BRICK.value)
            .unlockedBy("has_milk_chocolate_block", has(ModBlocks.MILK_CHOCOLATE_BRICK.value))
            .save(recipeOutput, "candyworld:milk_chocolate_bar_from_brick")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.MILK_CHOCOLATE_BAR.value, 3)
            .requires(Items.COCOA_BEANS).requires(Items.SUGAR).requires(Items.MILK_BUCKET)
            .unlockedBy("has_cocoa_beans", has(Items.COCOA_BEANS))
            .save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_CHOCOLATE_BAR.value, 4)
            .requires(ModBlocks.WHITE_CHOCOLATE_BLOCK.value)
            .unlockedBy("has_white_chocolate_block", has(ModBlocks.WHITE_CHOCOLATE_BLOCK.value))
            .save(recipeOutput, "candyworld:white_chocolate_bar_from_block")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_CHOCOLATE_BAR.value, 4)
            .requires(ModBlocks.WHITE_CHOCOLATE_BRICK.value)
            .unlockedBy("has_white_chocolate_block", has(ModBlocks.WHITE_CHOCOLATE_BRICK.value))
            .save(recipeOutput, "candyworld:white_chocolate_bar_from_brick")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_CHOCOLATE_BAR.value, 3)
            .requires(Items.COCOA_BEANS).requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.MILK_BUCKET)
            .requires(Items.MILK_BUCKET)
            .unlockedBy("has_cocoa_beans", has(Items.COCOA_BEANS))
            .save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.DARK_CHOCOLATE_BAR.value, 4)
            .requires(ModBlocks.DARK_CHOCOLATE_BLOCK.value)
            .unlockedBy("has_dark_chocolate_block", has(ModBlocks.DARK_CHOCOLATE_BLOCK.value))
            .save(recipeOutput, "candyworld:dark_chocolate_bar_from_block")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.DARK_CHOCOLATE_BAR.value, 4)
            .requires(ModBlocks.DARK_CHOCOLATE_BRICK.value)
            .unlockedBy("has_dark_chocolate_block", has(ModBlocks.DARK_CHOCOLATE_BRICK.value))
            .save(recipeOutput, "candyworld:dark_chocolate_bar_from_brick")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.DARK_CHOCOLATE_BAR.value, 3)
            .requires(Items.COCOA_BEANS).requires(Items.COCOA_BEANS).requires(Items.SUGAR).requires(Items.MILK_BUCKET)
            .unlockedBy("has_cocoa_beans", has(Items.COCOA_BEANS))
            .save(recipeOutput)

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RED_GUMMY.value, 4)
            .requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET)
            .requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_RED)
            .unlockedBy("has_red_dye", has(Tags.Items.DYES_RED))
            .save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RED_GUMMY.value, 3)
            .requires(ModItems.RED_GUMMY_WORM.value)
            .unlockedBy("has_red_gummy_worm", has(ModItems.RED_GUMMY_WORM.value))
            .save(recipeOutput, "candyworld:red_gummy_from_worm")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RED_GUMMY.value, 4)
            .requires(ModBlocks.RED_GUMMY_BLOCK.value)
            .unlockedBy("has_red_gummy_block", has(ModBlocks.RED_GUMMY_BLOCK.value))
            .save(recipeOutput, "candyworld:red_gummy_from_block")

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.ORANGE_GUMMY.value, 4)
            .requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET)
            .requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_ORANGE)
            .unlockedBy("has_orange_dye", has(Tags.Items.DYES_ORANGE))
            .save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.ORANGE_GUMMY.value, 3)
            .requires(ModItems.ORANGE_GUMMY_WORM.value)
            .unlockedBy("has_orange_gummy_worm", has(ModItems.ORANGE_GUMMY_WORM.value))
            .save(recipeOutput, "candyworld:orange_gummy_from_worm")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.ORANGE_GUMMY.value, 4)
            .requires(ModBlocks.ORANGE_GUMMY_BLOCK.value)
            .unlockedBy("has_orange_gummy_block", has(ModBlocks.ORANGE_GUMMY_BLOCK.value))
            .save(recipeOutput, "candyworld:orange_gummy_from_block")

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.YELLOW_GUMMY.value, 4)
            .requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET)
            .requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_YELLOW)
            .unlockedBy("has_yellow_dye", has(Tags.Items.DYES_YELLOW))
            .save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.YELLOW_GUMMY.value, 3)
            .requires(ModItems.YELLOW_GUMMY_WORM.value)
            .unlockedBy("has_yellow_gummy_worm", has(ModItems.YELLOW_GUMMY_WORM.value))
            .save(recipeOutput, "candyworld:yellow_gummy_from_worm")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.YELLOW_GUMMY.value, 4)
            .requires(ModBlocks.YELLOW_GUMMY_BLOCK.value)
            .unlockedBy("has_yellow_gummy_block", has(ModBlocks.YELLOW_GUMMY_BLOCK.value))
            .save(recipeOutput, "candyworld:yellow_gummy_from_block")

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_GUMMY.value, 4)
            .requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET)
            .requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_WHITE)
            .unlockedBy("has_white_dye", has(Tags.Items.DYES_WHITE))
            .save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_GUMMY.value, 3)
            .requires(ModItems.WHITE_GUMMY_WORM.value)
            .unlockedBy("has_white_gummy_worm", has(ModItems.WHITE_GUMMY_WORM.value))
            .save(recipeOutput, "candyworld:white_gummy_from_worm")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_GUMMY.value, 4)
            .requires(ModBlocks.WHITE_GUMMY_BLOCK.value)
            .unlockedBy("has_white_gummy_block", has(ModBlocks.WHITE_GUMMY_BLOCK.value))
            .save(recipeOutput, "candyworld:white_gummy_from_block")

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GREEN_GUMMY.value, 4)
            .requires(Items.SUGAR).requires(Items.SUGAR).requires(Items.WATER_BUCKET)
            .requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_WHITE).requires(Tags.Items.DYES_GREEN)
            .unlockedBy("has_green_dye", has(Tags.Items.DYES_GREEN))
            .save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GREEN_GUMMY.value, 3)
            .requires(ModItems.GREEN_GUMMY_WORM.value)
            .unlockedBy("has_green_gummy_worm", has(ModItems.GREEN_GUMMY_WORM.value))
            .save(recipeOutput, "candyworld:green_gummy_from_worm")
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GREEN_GUMMY.value, 4)
            .requires(ModBlocks.GREEN_GUMMY_BLOCK.value)
            .unlockedBy("has_green_gummy_block", has(ModBlocks.GREEN_GUMMY_BLOCK.value))
            .save(recipeOutput, "candyworld:green_gummy_from_block")

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.RED_GUMMY_WORM.value)
            .define('#', ModItems.RED_GUMMY.value).pattern("  #").pattern(" # ").pattern("#  ")
            .unlockedBy("has_red_gummy", has(ModItems.RED_GUMMY.value)).save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.RED_GUMMY_WORM.value, 4)
            .requires(ModBlocks.RED_GUMMY_WORM_BLOCK.value)
            .unlockedBy("has_red_gummy_worm_block", has(ModBlocks.RED_GUMMY_WORM_BLOCK.value))
            .save(recipeOutput, "candyworld:red_gummy_worm_from_block")

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.ORANGE_GUMMY_WORM.value)
            .define('#', ModItems.ORANGE_GUMMY.value).pattern("  #").pattern(" # ").pattern("#  ")
            .unlockedBy("has_orange_gummy", has(ModItems.ORANGE_GUMMY.value)).save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.ORANGE_GUMMY_WORM.value, 4)
            .requires(ModBlocks.ORANGE_GUMMY_WORM_BLOCK.value)
            .unlockedBy("has_orange_gummy_worm_block", has(ModBlocks.ORANGE_GUMMY_WORM_BLOCK.value))
            .save(recipeOutput, "candyworld:orange_gummy_worm_from_block")

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.YELLOW_GUMMY_WORM.value)
            .define('#', ModItems.YELLOW_GUMMY.value).pattern("  #").pattern(" # ").pattern("#  ")
            .unlockedBy("has_yellow_gummy", has(ModItems.YELLOW_GUMMY.value)).save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.YELLOW_GUMMY_WORM.value, 4)
            .requires(ModBlocks.YELLOW_GUMMY_WORM_BLOCK.value)
            .unlockedBy("has_yellow_gummy_worm_block", has(ModBlocks.YELLOW_GUMMY_WORM_BLOCK.value))
            .save(recipeOutput, "candyworld:yellow_gummy_worm_from_block")

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.WHITE_GUMMY_WORM.value)
            .define('#', ModItems.WHITE_GUMMY.value).pattern("  #").pattern(" # ").pattern("#  ")
            .unlockedBy("has_white_gummy", has(ModItems.WHITE_GUMMY.value)).save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.WHITE_GUMMY_WORM.value, 4)
            .requires(ModBlocks.WHITE_GUMMY_WORM_BLOCK.value)
            .unlockedBy("has_white_gummy_worm_block", has(ModBlocks.WHITE_GUMMY_WORM_BLOCK.value))
            .save(recipeOutput, "candyworld:white_gummy_worm_from_block")

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.GREEN_GUMMY_WORM.value)
            .define('#', ModItems.GREEN_GUMMY.value).pattern("  #").pattern(" # ").pattern("#  ")
            .unlockedBy("has_green_gummy", has(ModItems.GREEN_GUMMY.value)).save(recipeOutput)
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.GREEN_GUMMY_WORM.value, 4)
            .requires(ModBlocks.GREEN_GUMMY_WORM_BLOCK.value)
            .unlockedBy("has_green_gummy_worm_block", has(ModBlocks.GREEN_GUMMY_WORM_BLOCK.value))
            .save(recipeOutput, "candyworld:green_gummy_worm_from_block")

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.TELEPORTER.value)
            .define('P', Items.BLAZE_POWDER)
            .define('B', ModTags.CHOCOLATE_BARS)
            .define('C', Items.CHORUS_FRUIT)
            .pattern("PBP").pattern("BCB").pattern("PBP")
            .unlockedBy("has_chorus_fruit", has(Items.CHORUS_FRUIT)).save(recipeOutput)


        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.MILK_CHOCOLATE_PICKAXE.value)
            .define('B', ModItems.MILK_CHOCOLATE_BAR.value).define('W', ModItems.WAFER_STICK.value)
            .pattern("BBB").pattern(" W ").pattern(" W ")
            .unlockedBy("has_milk_chocolate_bar", has(ModItems.MILK_CHOCOLATE_BAR.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.MILK_CHOCOLATE_AXE.value)
            .define('B', ModItems.MILK_CHOCOLATE_BAR.value).define('W', ModItems.WAFER_STICK.value)
            .pattern("BB ").pattern("BW ").pattern(" W ")
            .unlockedBy("has_milk_chocolate_bar", has(ModItems.MILK_CHOCOLATE_BAR.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.MILK_CHOCOLATE_SHOVEL.value)
            .define('B', ModItems.MILK_CHOCOLATE_BAR.value).define('W', ModItems.WAFER_STICK.value)
            .pattern(" B ").pattern(" W ").pattern(" W ")
            .unlockedBy("has_milk_chocolate_bar", has(ModItems.MILK_CHOCOLATE_BAR.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.MILK_CHOCOLATE_SWORD.value)
            .define('B', ModItems.MILK_CHOCOLATE_BAR.value).define('W', ModItems.WAFER_STICK.value)
            .pattern(" B ").pattern(" B ").pattern(" W ")
            .unlockedBy("has_milk_chocolate_bar", has(ModItems.MILK_CHOCOLATE_BAR.value)).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.WHITE_CHOCOLATE_PICKAXE.value)
            .define('B', ModItems.WHITE_CHOCOLATE_BAR.value).define('W', ModItems.WAFER_STICK.value)
            .pattern("BBB").pattern(" W ").pattern(" W ")
            .unlockedBy("has_white_chocolate_bar", has(ModItems.WHITE_CHOCOLATE_BAR.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.WHITE_CHOCOLATE_AXE.value)
            .define('B', ModItems.WHITE_CHOCOLATE_BAR.value).define('W', ModItems.WAFER_STICK.value)
            .pattern("BB ").pattern("BW ").pattern(" W ")
            .unlockedBy("has_white_chocolate_bar", has(ModItems.WHITE_CHOCOLATE_BAR.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.WHITE_CHOCOLATE_SHOVEL.value)
            .define('B', ModItems.WHITE_CHOCOLATE_BAR.value).define('W', ModItems.WAFER_STICK.value)
            .pattern(" B ").pattern(" W ").pattern(" W ")
            .unlockedBy("has_white_chocolate_bar", has(ModItems.WHITE_CHOCOLATE_BAR.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.WHITE_CHOCOLATE_SWORD.value)
            .define('B', ModItems.WHITE_CHOCOLATE_BAR.value).define('W', ModItems.WAFER_STICK.value)
            .pattern(" B ").pattern(" B ").pattern(" W ")
            .unlockedBy("has_white_chocolate_bar", has(ModItems.WHITE_CHOCOLATE_BAR.value)).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DARK_CHOCOLATE_PICKAXE.value)
            .define('B', ModItems.DARK_CHOCOLATE_BAR.value).define('W', ModItems.WAFER_STICK.value)
            .pattern("BBB").pattern(" W ").pattern(" W ")
            .unlockedBy("has_dark_chocolate_bar", has(ModItems.DARK_CHOCOLATE_BAR.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DARK_CHOCOLATE_AXE.value)
            .define('B', ModItems.DARK_CHOCOLATE_BAR.value).define('W', ModItems.WAFER_STICK.value)
            .pattern("BB ").pattern("BW ").pattern(" W ")
            .unlockedBy("has_dark_chocolate_bar", has(ModItems.DARK_CHOCOLATE_BAR.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DARK_CHOCOLATE_SHOVEL.value)
            .define('B', ModItems.DARK_CHOCOLATE_BAR.value).define('W', ModItems.WAFER_STICK.value)
            .pattern(" B ").pattern(" W ").pattern(" W ")
            .unlockedBy("has_dark_chocolate_bar", has(ModItems.DARK_CHOCOLATE_BAR.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.DARK_CHOCOLATE_SWORD.value)
            .define('B', ModItems.DARK_CHOCOLATE_BAR.value).define('W', ModItems.WAFER_STICK.value)
            .pattern(" B ").pattern(" B ").pattern(" W ")
            .unlockedBy("has_dark_chocolate_bar", has(ModItems.DARK_CHOCOLATE_BAR.value)).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.COTTON_CANDY_PICKAXE.value)
            .define('B', ModItems.COTTON_CANDY.value).define('W', ModItems.WAFER_STICK.value)
            .pattern("BBB").pattern(" W ").pattern(" W ")
            .unlockedBy("has_cotton_candy", has(ModItems.COTTON_CANDY.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.COTTON_CANDY_AXE.value)
            .define('B', ModItems.COTTON_CANDY.value).define('W', ModItems.WAFER_STICK.value)
            .pattern("BB ").pattern("BW ").pattern(" W ")
            .unlockedBy("has_cotton_candy", has(ModItems.COTTON_CANDY.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.COTTON_CANDY_SHOVEL.value)
            .define('B', ModItems.COTTON_CANDY.value).define('W', ModItems.WAFER_STICK.value)
            .pattern(" B ").pattern(" W ").pattern(" W ")
            .unlockedBy("has_cotton_candy", has(ModItems.COTTON_CANDY.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.COTTON_CANDY_SWORD.value)
            .define('B', ModItems.COTTON_CANDY.value).define('W', ModItems.WAFER_STICK.value)
            .pattern(" B ").pattern(" B ").pattern(" W ")
            .unlockedBy("has_cotton_candy", has(ModItems.COTTON_CANDY.value)).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SUGAR_SAND.value)
            .define('#', Items.SUGAR).pattern("##").pattern("##")
            .unlockedBy("has_sugar", has(Items.SUGAR)).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_CANDY_CANE_BLOCK.value)
            .define('#', ModItems.WHITE_CANDY_CANE.value).pattern("##").pattern("##")
            .unlockedBy("has_white_candy_cane", has(ModItems.WHITE_CANDY_CANE.value)).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_CANDY_CANE_BLOCK.value)
            .define('#', ModItems.RED_CANDY_CANE.value).pattern("##").pattern("##")
            .unlockedBy("has_red_candy_cane", has(ModItems.RED_CANDY_CANE.value)).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GREEN_CANDY_CANE_BLOCK.value)
            .define('#', ModItems.GREEN_CANDY_CANE.value).pattern("##").pattern("##")
            .unlockedBy("has_green_candy_cane", has(ModItems.GREEN_CANDY_CANE.value)).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.value)
            .define('#', ModItems.WHITE_RED_CANDY_CANE.value).pattern("##").pattern("##")
            .unlockedBy("has_white_red_candy_cane", has(ModItems.WHITE_RED_CANDY_CANE.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.value)
            .define('#', ModItems.WHITE_CANDY_CANE.value)
            .define('@', ModItems.RED_CANDY_CANE.value)
            .pattern("#@").pattern("@#").unlockedBy("has_red_candy_cane", has(ModItems.RED_CANDY_CANE.value))
            .save(recipeOutput, "candyworld:white_red_candy_cane_block_from_colored_cane")

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.value)
            .define('#', ModItems.WHITE_GREEN_CANDY_CANE.value).pattern("##").pattern("##")
            .unlockedBy("has_white_green_candy_cane", has(ModItems.WHITE_GREEN_CANDY_CANE.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.value)
            .define('#', ModItems.WHITE_CANDY_CANE.value)
            .define('@', ModItems.GREEN_CANDY_CANE.value)
            .pattern("#@").pattern("@#").unlockedBy("has_green_candy_cane", has(ModItems.GREEN_CANDY_CANE.value))
            .save(recipeOutput, "candyworld:white_green_candy_cane_block_from_colored_cane")

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_GREEN_CANDY_CANE_BLOCK.value)
            .define('#', ModItems.RED_GREEN_CANDY_CANE.value).pattern("##").pattern("##")
            .unlockedBy("has_red_green_candy_cane", has(ModItems.RED_GREEN_CANDY_CANE.value)).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_GREEN_CANDY_CANE_BLOCK.value)
            .define('#', ModItems.RED_CANDY_CANE.value)
            .define('@', ModItems.GREEN_CANDY_CANE.value)
            .pattern("#@").pattern("@#").unlockedBy("has_green_candy_cane", has(ModItems.GREEN_CANDY_CANE.value))
            .save(recipeOutput, "candyworld:red_green_candy_cane_block_from_colored_canes")

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MILK_BROWNIE_BLOCK.value)
            .define('#', ModItems.MILK_BROWNIE.value).pattern("##").pattern("##").unlockedBy(
                "has_milk_brownie",
                has(ModItems.MILK_BROWNIE.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_BROWNIE_BLOCK.value)
            .define('#', ModItems.WHITE_BROWNIE.value).pattern("##").pattern("##").unlockedBy(
                "has_white_brownie",
                has(ModItems.WHITE_BROWNIE.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_BROWNIE_BLOCK.value)
            .define('#', ModItems.DARK_BROWNIE.value).pattern("##").pattern("##").unlockedBy(
                "has_dark_brownie",
                has(ModItems.DARK_BROWNIE.value)
            ).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MILK_CHOCOLATE_BRICK.value)
            .define('#', ModItems.MILK_CHOCOLATE_BAR.value).pattern("##").pattern("##").unlockedBy(
                "has_milk_chocolate_bar",
                has(ModItems.MILK_CHOCOLATE_BAR.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_CHOCOLATE_BRICK.value)
            .define('#', ModItems.WHITE_CHOCOLATE_BAR.value).pattern("##").pattern("##").unlockedBy(
                "has_white_chocolate_bar",
                has(ModItems.WHITE_CHOCOLATE_BAR.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_CHOCOLATE_BRICK.value)
            .define('#', ModItems.DARK_CHOCOLATE_BAR.value).pattern("##").pattern("##").unlockedBy(
                "has_dark_chocolate_bar",
                has(ModItems.DARK_CHOCOLATE_BAR.value)
            ).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MILK_CHOCOLATE_BRICK.value, 4)
            .define('#', ModBlocks.MILK_CHOCOLATE_BLOCK.value).pattern("##").pattern("##").unlockedBy(
                "has_milk_chocolate_block",
                has(ModBlocks.MILK_CHOCOLATE_BLOCK.value)
            ).save(recipeOutput, "candyworld:milk_chocolate_brick_from_block")
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_CHOCOLATE_BRICK.value, 4)
            .define('#', ModBlocks.WHITE_CHOCOLATE_BLOCK.value).pattern("##").pattern("##").unlockedBy(
                "has_white_chocolate_block",
                has(ModBlocks.WHITE_CHOCOLATE_BLOCK.value)
            ).save(recipeOutput, "candyworld:white_chocolate_brick_from_block")
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_CHOCOLATE_BRICK.value, 4)
            .define('#', ModBlocks.DARK_CHOCOLATE_BLOCK.value).pattern("##").pattern("##").unlockedBy(
                "has_dark_chocolate_block",
                has(ModBlocks.DARK_CHOCOLATE_BLOCK.value)
            ).save(recipeOutput, "candyworld:dark_chocolate_brick_from_block")

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_GUMMY_BLOCK.value)
            .define('#', ModItems.RED_GUMMY.value).pattern("##").pattern("##").unlockedBy(
                "has_red_gummy",
                has(ModItems.RED_GUMMY.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_GUMMY_BLOCK.value)
            .define('#', ModItems.ORANGE_GUMMY.value).pattern("##").pattern("##").unlockedBy(
                "has_orange_gummy",
                has(ModItems.ORANGE_GUMMY.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.YELLOW_GUMMY_BLOCK.value)
            .define('#', ModItems.YELLOW_GUMMY.value).pattern("##").pattern("##").unlockedBy(
                "has_yellow_gummy",
                has(ModItems.YELLOW_GUMMY.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_GUMMY_BLOCK.value)
            .define('#', ModItems.WHITE_GUMMY.value).pattern("##").pattern("##").unlockedBy(
                "has_white_gummy",
                has(ModItems.WHITE_GUMMY.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GREEN_GUMMY_BLOCK.value)
            .define('#', ModItems.GREEN_GUMMY.value).pattern("##").pattern("##").unlockedBy(
                "has_green_gummy",
                has(ModItems.GREEN_GUMMY.value)
            ).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_GUMMY_WORM_BLOCK.value)
            .define('#', ModItems.RED_GUMMY_WORM.value).pattern("##").pattern("##").unlockedBy(
                "has_red_gummy_worm",
                has(ModItems.RED_GUMMY_WORM.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_GUMMY_WORM_BLOCK.value)
            .define('#', ModItems.ORANGE_GUMMY_WORM.value).pattern("##").pattern("##").unlockedBy(
                "has_orange_gummy_worm",
                has(ModItems.ORANGE_GUMMY_WORM.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.YELLOW_GUMMY_WORM_BLOCK.value)
            .define('#', ModItems.YELLOW_GUMMY_WORM.value).pattern("##").pattern("##").unlockedBy(
                "has_yellow_gummy_worm",
                has(ModItems.YELLOW_GUMMY_WORM.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_GUMMY_WORM_BLOCK.value)
            .define('#', ModItems.WHITE_GUMMY_WORM.value).pattern("##").pattern("##").unlockedBy(
                "has_white_gummy_worm",
                has(ModItems.WHITE_GUMMY_WORM.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GREEN_GUMMY_WORM_BLOCK.value)
            .define('#', ModItems.GREEN_GUMMY_WORM.value).pattern("##").pattern("##").unlockedBy(
                "has_green_gummy_worm",
                has(ModItems.GREEN_GUMMY_WORM.value)
            ).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MILK_CHOCOLATE_WORKBENCH.value)
            .define('@', ModItems.MILK_CHOCOLATE_BAR.value).define('#', ModBlocks.WAFER_STICK_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_wafer_stick_block",
                has(ModBlocks.WAFER_STICK_BLOCK.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_CHOCOLATE_WORKBENCH.value)
            .define('@', ModItems.WHITE_CHOCOLATE_BAR.value).define('#', ModBlocks.WAFER_STICK_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_wafer_stick_block",
                has(ModBlocks.WAFER_STICK_BLOCK.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_CHOCOLATE_WORKBENCH.value)
            .define('@', ModItems.DARK_CHOCOLATE_BAR.value).define('#', ModBlocks.WAFER_STICK_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_wafer_stick_block",
                has(ModBlocks.WAFER_STICK_BLOCK.value)
            ).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_CANDY_CANE_WORKBENCH.value)
            .define('@', ModItems.COTTON_CANDY.value).define('#', ModBlocks.WHITE_CANDY_CANE_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_white_candy_cane_block",
                has(ModBlocks.WHITE_CANDY_CANE_BLOCK.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_CANDY_CANE_WORKBENCH.value)
            .define('@', ModItems.COTTON_CANDY.value).define('#', ModBlocks.RED_CANDY_CANE_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_red_candy_cane_block",
                has(ModBlocks.RED_CANDY_CANE_BLOCK.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GREEN_CANDY_CANE_WORKBENCH.value)
            .define('@', ModItems.COTTON_CANDY.value).define('#', ModBlocks.GREEN_CANDY_CANE_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_green_candy_cane_block",
                has(ModBlocks.GREEN_CANDY_CANE_BLOCK.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_RED_CANDY_CANE_WORKBENCH.value)
            .define('@', ModItems.COTTON_CANDY.value).define('#', ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_white_red_candy_cane_block",
                has(ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_GREEN_CANDY_CANE_WORKBENCH.value)
            .define('@', ModItems.COTTON_CANDY.value).define('#', ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_white_green_candy_cane_block",
                has(ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_GREEN_CANDY_CANE_WORKBENCH.value)
            .define('@', ModItems.COTTON_CANDY.value).define('#', ModBlocks.RED_GREEN_CANDY_CANE_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_red_green_candy_cane_block",
                has(ModBlocks.RED_GREEN_CANDY_CANE_BLOCK.value)
            ).save(recipeOutput)

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_GUMMY_WORKBENCH.value)
            .define('@', ModItems.RED_GUMMY.value).define('#', ModBlocks.RED_GUMMY_WORM_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_red_gummy_worm_blockk",
                has(ModBlocks.RED_GUMMY_WORM_BLOCK.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_GUMMY_WORKBENCH.value)
            .define('@', ModItems.ORANGE_GUMMY.value).define('#', ModBlocks.ORANGE_GUMMY_WORM_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_orange_gummy_worm_blockk",
                has(ModBlocks.ORANGE_GUMMY_WORM_BLOCK.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.YELLOW_GUMMY_WORKBENCH.value)
            .define('@', ModItems.YELLOW_GUMMY.value).define('#', ModBlocks.YELLOW_GUMMY_WORM_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_yellow_gummy_worm_blockk",
                has(ModBlocks.YELLOW_GUMMY_WORM_BLOCK.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_GUMMY_WORKBENCH.value)
            .define('@', ModItems.WHITE_GUMMY.value).define('#', ModBlocks.WHITE_GUMMY_WORM_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_white_gummy_worm_blockk",
                has(ModBlocks.WHITE_GUMMY_WORM_BLOCK.value)
            ).save(recipeOutput)
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GREEN_GUMMY_WORKBENCH.value)
            .define('@', ModItems.GREEN_GUMMY.value).define('#', ModBlocks.GREEN_GUMMY_WORM_BLOCK.value)
            .pattern("@@").pattern("##").unlockedBy(
                "has_green_gummy_worm_blockk",
                has(ModBlocks.GREEN_GUMMY_WORM_BLOCK.value)
            ).save(recipeOutput)
    }
}