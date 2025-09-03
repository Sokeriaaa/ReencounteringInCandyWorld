package sokeriaaa.candyworld.registry

import net.minecraft.tags.BlockTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Tier
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Block

object ModItemTiers {

    val CHOCOLATE = object : Tier {
        override fun getIncorrectBlocksForDrops(): TagKey<Block> = BlockTags.INCORRECT_FOR_IRON_TOOL
        override fun getUses() = 750
        override fun getSpeed() = 7.0F
        override fun getAttackDamageBonus() = 2.5F
        override fun getEnchantmentValue() = 25
        override fun getRepairIngredient() = Ingredient.of(ModTags.CHOCOLATE_BARS)
    }

    val COTTON_CANDY = object : Tier {
        override fun getIncorrectBlocksForDrops(): TagKey<Block> = BlockTags.INCORRECT_FOR_STONE_TOOL
        override fun getUses() = 5
        override fun getSpeed() = 15.0F
        override fun getAttackDamageBonus() = 5F
        override fun getEnchantmentValue() = 65
        override fun getRepairIngredient() = Ingredient.of(ModTags.CHOCOLATE_BARS)
    }
}