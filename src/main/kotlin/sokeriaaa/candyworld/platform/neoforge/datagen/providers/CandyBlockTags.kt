package sokeriaaa.candyworld.platform.neoforge.datagen.providers

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.tags.BlockTags
import net.neoforged.neoforge.common.data.BlockTagsProvider
import net.neoforged.neoforge.common.data.ExistingFileHelper
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.registry.ModBlocks
import sokeriaaa.candyworld.registry.ModTags
import java.util.concurrent.CompletableFuture

class CandyBlockTags(
    output: PackOutput,
    lookupProvider: CompletableFuture<HolderLookup.Provider>,
    existingFileHelper: ExistingFileHelper?
) : BlockTagsProvider(
    output,
    lookupProvider,
    CandyWorld.MOD_ID,
    existingFileHelper,
) {

    override fun addTags(provider: HolderLookup.Provider) {
        this.tag(ModTags.BROWNIE)
            .add(
                ModBlocks.MILK_BROWNIE_BLOCK.value,
                ModBlocks.WHITE_BROWNIE_BLOCK.value,
                ModBlocks.DARK_BROWNIE_BLOCK.value,
            )
        this.tag(ModTags.COVERED_BROWNIE)
            .add(
                ModBlocks.CANDY_GRASS_BLOCK.value,
                ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.value,
                ModBlocks.DARK_CANDY_GRASS_BLOCK.value,
            )
        this.tag(ModTags.CANDY_SOIL).addTags(ModTags.BROWNIE, ModTags.COVERED_BROWNIE)

        this.tag(ModTags.GUMMY).add(
            ModBlocks.RED_GUMMY_BLOCK.value,
            ModBlocks.ORANGE_GUMMY_BLOCK.value,
            ModBlocks.YELLOW_GUMMY_BLOCK.value,
            ModBlocks.WHITE_GUMMY_BLOCK.value,
            ModBlocks.GREEN_GUMMY_BLOCK.value,
        )
        this.tag(ModTags.SUGAR).add(
            ModBlocks.CRYSTALLIZED_SUGAR.value,
        )
        this.tag(ModTags.COTTON_CANDY_LEAVES).add(
            ModBlocks.COTTON_CANDY_LEAVES.value
        )
        this.tag(ModTags.CHOCOLATE_LEAVES).add(
            ModBlocks.MILK_CHOCOLATE_LEAVES.value,
            ModBlocks.WHITE_CHOCOLATE_LEAVES.value,
            ModBlocks.DARK_CHOCOLATE_LEAVES.value,
        )

        // Minecraft tags
        this.tag(BlockTags.SAPLINGS).add(
            ModBlocks.CHOCOLATE_SAPLING.value,
            ModBlocks.COTTON_CANDY_SAPLING.value,
        )
        this.tag(BlockTags.LEAVES).add(
            ModBlocks.COTTON_CANDY_LEAVES.value,
            ModBlocks.MILK_CHOCOLATE_LEAVES.value,
            ModBlocks.DARK_CHOCOLATE_LEAVES.value,
            ModBlocks.WHITE_CHOCOLATE_LEAVES.value
        )
        this.tag(BlockTags.LOGS).add(
            ModBlocks.WHITE_CANDY_CANE_BLOCK.value,
            ModBlocks.WAFER_STICK_BLOCK.value,
        )
        // Harvest
        this.tag(BlockTags.MINEABLE_WITH_AXE)
            .add(
                ModBlocks.WAFER_STICK_BLOCK.value,
                ModBlocks.MILK_CHOCOLATE_WORKBENCH.value,
                ModBlocks.WHITE_CHOCOLATE_WORKBENCH.value,
                ModBlocks.DARK_CHOCOLATE_WORKBENCH.value,
            )
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(
                ModBlocks.MILK_CHOCOLATE_BAR_BLOCK.value,
                ModBlocks.WHITE_CHOCOLATE_BAR_BLOCK.value,
                ModBlocks.DARK_CHOCOLATE_BAR_BLOCK.value,
                ModBlocks.MILK_CHOCOLATE_BLOCK.value,
                ModBlocks.WHITE_CHOCOLATE_BLOCK.value,
                ModBlocks.DARK_CHOCOLATE_BLOCK.value,
                ModBlocks.MILK_CHOCOLATE_BRICK.value,
                ModBlocks.WHITE_CHOCOLATE_BRICK.value,
                ModBlocks.DARK_CHOCOLATE_BRICK.value,
                ModBlocks.WHITE_CANDY_CANE_BLOCK.value,
                ModBlocks.RED_CANDY_CANE_BLOCK.value,
                ModBlocks.GREEN_CANDY_CANE_BLOCK.value,
                ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.value,
                ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.value,
                ModBlocks.RED_GREEN_CANDY_CANE_BLOCK.value,
                ModBlocks.WHITE_CANDY_CANE_WORKBENCH.value,
                ModBlocks.RED_CANDY_CANE_WORKBENCH.value,
                ModBlocks.GREEN_CANDY_CANE_WORKBENCH.value,
                ModBlocks.WHITE_RED_CANDY_CANE_WORKBENCH.value,
                ModBlocks.WHITE_GREEN_CANDY_CANE_WORKBENCH.value,
                ModBlocks.RED_GREEN_CANDY_CANE_WORKBENCH.value,
                ModBlocks.CRYSTALLIZED_SUGAR.value,
                ModBlocks.CRYSTALLIZED_SUGAR_COOKIE_ORE.value,
                ModBlocks.COOKIE_ORE.value,
                ModBlocks.TELEPORTER_ORE.value,
            )
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
            .add(
                ModBlocks.SUGAR_SAND.value,
                ModBlocks.CANDY_GRASS_BLOCK.value,
                ModBlocks.MILK_BROWNIE_BLOCK.value,
                ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.value,
                ModBlocks.WHITE_BROWNIE_BLOCK.value,
                ModBlocks.DARK_CANDY_GRASS_BLOCK.value,
                ModBlocks.DARK_BROWNIE_BLOCK.value,
                ModBlocks.RED_GUMMY_BLOCK.value,
                ModBlocks.ORANGE_GUMMY_BLOCK.value,
                ModBlocks.YELLOW_GUMMY_BLOCK.value,
                ModBlocks.WHITE_GUMMY_BLOCK.value,
                ModBlocks.GREEN_GUMMY_BLOCK.value,
                ModBlocks.RED_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.ORANGE_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.YELLOW_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.WHITE_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.GREEN_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.RED_GUMMY_WORM_BLOCK.value,
                ModBlocks.ORANGE_GUMMY_WORM_BLOCK.value,
                ModBlocks.YELLOW_GUMMY_WORM_BLOCK.value,
                ModBlocks.WHITE_GUMMY_WORM_BLOCK.value,
                ModBlocks.GREEN_GUMMY_WORM_BLOCK.value,
                ModBlocks.RED_GUMMY_WORKBENCH.value,
                ModBlocks.ORANGE_GUMMY_WORKBENCH.value,
                ModBlocks.YELLOW_GUMMY_WORKBENCH.value,
                ModBlocks.WHITE_GUMMY_WORKBENCH.value,
                ModBlocks.GREEN_GUMMY_WORKBENCH.value,
            )
        this.tag(BlockTags.MINEABLE_WITH_HOE)
        // Others
        this.tag(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .add(
                ModBlocks.CRYSTALLIZED_SUGAR.value,
                ModBlocks.SUGAR_SAND.value,
                ModBlocks.RED_GUMMY_BLOCK.value,
                ModBlocks.ORANGE_GUMMY_BLOCK.value,
                ModBlocks.YELLOW_GUMMY_BLOCK.value,
                ModBlocks.WHITE_GUMMY_BLOCK.value,
                ModBlocks.GREEN_GUMMY_BLOCK.value,
                ModBlocks.RED_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.ORANGE_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.YELLOW_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.WHITE_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.GREEN_HARDENED_GUMMY_BLOCK.value,
                ModBlocks.CANDY_GRASS_BLOCK.value,
                ModBlocks.WHITE_BROWNIE_BLOCK.value,
                ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.value,
                ModBlocks.MILK_BROWNIE_BLOCK.value,
                ModBlocks.DARK_CANDY_GRASS_BLOCK.value,
                ModBlocks.DARK_BROWNIE_BLOCK.value
            )
        this.tag(BlockTags.REPLACEABLE)
            .add(ModBlocks.MILK_CHOCOLATE_MUSHROOM.value)
            .add(ModBlocks.WHITE_CHOCOLATE_MUSHROOM.value)
            .add(ModBlocks.DARK_CHOCOLATE_MUSHROOM.value)
            .add(ModBlocks.COTTON_CANDY_BUSH.value)
            .add(ModBlocks.LIQUID_CHOCOLATE_BLOCK.value)
            .add(ModBlocks.LIQUID_CANDY_BLOCK.value)
        this.tag(BlockTags.REPLACEABLE_BY_TREES)
            .add(ModBlocks.MILK_CHOCOLATE_MUSHROOM.value)
            .add(ModBlocks.WHITE_CHOCOLATE_MUSHROOM.value)
            .add(ModBlocks.DARK_CHOCOLATE_MUSHROOM.value)
            .add(ModBlocks.COTTON_CANDY_BUSH.value)
        this.tag(BlockTags.SWORD_EFFICIENT)
            .add(ModBlocks.MILK_CHOCOLATE_MUSHROOM.value)
            .add(ModBlocks.WHITE_CHOCOLATE_MUSHROOM.value)
            .add(ModBlocks.DARK_CHOCOLATE_MUSHROOM.value)
            .add(ModBlocks.COTTON_CANDY_BUSH.value)
    }
}