package sokeriaaa.candyworld.platform.neoforge.datagen.providers

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.world.level.block.Block
import net.neoforged.neoforge.common.data.ExistingFileHelper
import sokeriaaa.candyworld.CandyWorld
import sokeriaaa.candyworld.registry.ModItems
import sokeriaaa.candyworld.registry.ModTags
import java.util.concurrent.CompletableFuture

class CandyItemTags(
    output: PackOutput,
    provider: CompletableFuture<HolderLookup.Provider>,
    blockTags: CompletableFuture<TagLookup<Block>>,
    existingFileHelper: ExistingFileHelper?
) : ItemTagsProvider(
    output,
    provider,
    blockTags,
    CandyWorld.MOD_ID,
    existingFileHelper
) {
    override fun addTags(provider: HolderLookup.Provider) {
        this.tag(ModTags.CHOCOLATE_BARS_DARK).add(ModItems.DARK_CHOCOLATE_BAR.value)
        this.tag(ModTags.CHOCOLATE_BARS_MILK).add(ModItems.MILK_CHOCOLATE_BAR.value)
        this.tag(ModTags.CHOCOLATE_BARS_WHITE).add(ModItems.WHITE_CHOCOLATE_BAR.value)
        this.tag(ModTags.CHOCOLATE_BARS)
            .addTags(ModTags.CHOCOLATE_BARS_DARK, ModTags.CHOCOLATE_BARS_MILK, ModTags.CHOCOLATE_BARS_WHITE)

        this.tag(ModTags.CHOCOLATE_EGGS_DARK).add(ModItems.DARK_CHOCOLATE_EGG.value)
        this.tag(ModTags.CHOCOLATE_EGGS_MILK).add(ModItems.MILK_CHOCOLATE_EGG.value)
        this.tag(ModTags.CHOCOLATE_EGGS_WHITE).add(ModItems.WHITE_CHOCOLATE_EGG.value)
        this.tag(ModTags.CHOCOLATE_EGGS).addTags(
            ModTags.CHOCOLATE_EGGS_DARK,
            ModTags.CHOCOLATE_EGGS_MILK,
            ModTags.CHOCOLATE_EGGS_WHITE,
        )

        this.tag(ModTags.GUMMYS_GREEN).add(ModItems.GREEN_GUMMY.value)
        this.tag(ModTags.GUMMYS_ORANGE).add(ModItems.ORANGE_GUMMY.value)
        this.tag(ModTags.GUMMYS_RED).add(ModItems.RED_GUMMY.value)
        this.tag(ModTags.GUMMYS_WHITE).add(ModItems.WHITE_GUMMY.value)
        this.tag(ModTags.GUMMYS_YELLOW).add(ModItems.YELLOW_GUMMY.value)
        this.tag(ModTags.GUMMY_WORMS_GREEN).add(ModItems.GREEN_GUMMY_WORM.value)
        this.tag(ModTags.GUMMY_WORMS_ORANGE).add(ModItems.ORANGE_GUMMY_WORM.value)
        this.tag(ModTags.GUMMY_WORMS_RED).add(ModItems.RED_GUMMY_WORM.value)
        this.tag(ModTags.GUMMY_WORMS_WHITE).add(ModItems.WHITE_GUMMY_WORM.value)
        this.tag(ModTags.GUMMY_WORMS_YELLOW).add(ModItems.YELLOW_GUMMY_WORM.value)
        this.tag(ModTags.GUMMY_WORMS).addTags(
            ModTags.GUMMY_WORMS_GREEN,
            ModTags.GUMMY_WORMS_ORANGE,
            ModTags.GUMMY_WORMS_RED,
            ModTags.GUMMY_WORMS_WHITE,
            ModTags.GUMMY_WORMS_YELLOW,
        )
        this.tag(ModTags.GUMMYS).addTags(
            ModTags.GUMMYS_GREEN,
            ModTags.GUMMYS_ORANGE,
            ModTags.GUMMYS_RED,
            ModTags.GUMMYS_WHITE,
            ModTags.GUMMYS_YELLOW,
            ModTags.GUMMY_WORMS,
        )
    }
}