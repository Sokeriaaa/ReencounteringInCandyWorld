package sokeriaaa.candyworld.platform.neoforge.datagen.providers

import net.minecraft.core.HolderLookup
import net.minecraft.data.PackOutput
import net.minecraft.data.loot.BlockLootSubProvider
import net.minecraft.data.loot.EntityLootSubProvider
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.world.entity.EntityType
import net.minecraft.world.flag.FeatureFlags
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.entries.NestedLootTable
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import sokeriaaa.candyworld.platform.Platform
import sokeriaaa.candyworld.platform.neoforge.impl.CandyWorldRegistryImpl
import sokeriaaa.candyworld.registry.ModBlocks
import sokeriaaa.candyworld.registry.ModEntities
import sokeriaaa.candyworld.registry.ModItems
import sokeriaaa.candyworld.registry.ModLootTables
import java.util.concurrent.CompletableFuture
import java.util.stream.Stream

class CandyLootTables(
    output: PackOutput,
    registries: CompletableFuture<HolderLookup.Provider>
) : LootTableProvider(
    output,
    emptySet(),
    listOf(
        SubProviderEntry({ CandyBlocks(it) }, LootContextParamSets.BLOCK),
        SubProviderEntry({ CandyEntities(it) }, LootContextParamSets.ENTITY),
    ),
    registries,
) {

    private class CandyBlocks(
        registries: HolderLookup.Provider,
    ) : BlockLootSubProvider(
        emptySet(),
        FeatureFlags.REGISTRY.allFlags(),
        registries,
    ) {
        private val hasShearsOrSilkTouch = HAS_SHEARS.or(hasSilkTouch())
        private val hasNoShearsOrSilkTouch = hasShearsOrSilkTouch.invert()

        override fun generate() {
            this.dropSelf(ModBlocks.CHOCOLATE_SAPLING.value)
            this.dropSelf(ModBlocks.WAFER_STICK_BLOCK.value)
            this.add(ModBlocks.MILK_CHOCOLATE_LEAVES.value) { block ->
                createChocolateLeavesDrops(
                    block,
                    ModBlocks.CHOCOLATE_SAPLING.value,
                    ModItems.MILK_CHOCOLATE_BAR.value,
                    *NORMAL_LEAVES_SAPLING_CHANCES
                )
            }
            this.add(ModBlocks.WHITE_CHOCOLATE_LEAVES.value) { block ->
                createChocolateLeavesDrops(
                    block,
                    ModBlocks.CHOCOLATE_SAPLING.value,
                    ModItems.WHITE_CHOCOLATE_BAR.value,
                    *NORMAL_LEAVES_SAPLING_CHANCES
                )
            }
            this.add(ModBlocks.DARK_CHOCOLATE_LEAVES.value) { block ->
                createChocolateLeavesDrops(
                    block,
                    ModBlocks.CHOCOLATE_SAPLING.value,
                    ModItems.DARK_CHOCOLATE_BAR.value,
                    *NORMAL_LEAVES_SAPLING_CHANCES
                )
            }
            this.dropOther(ModBlocks.MILK_CHOCOLATE_BAR_BLOCK.value, ModItems.MILK_CHOCOLATE_BAR.value)
            this.dropOther(ModBlocks.WHITE_CHOCOLATE_BAR_BLOCK.value, ModItems.WHITE_CHOCOLATE_BAR.value)
            this.dropOther(ModBlocks.DARK_CHOCOLATE_BAR_BLOCK.value, ModItems.DARK_CHOCOLATE_BAR.value)
            this.add(ModBlocks.MILK_CHOCOLATE_MUSHROOM.value) { block ->
                createSilkAndChanceDrop(block, ModItems.MILK_CHOCOLATE_BAR.value, 0f, 1f, 0.125f)
            }
            this.add(ModBlocks.WHITE_CHOCOLATE_MUSHROOM.value) { block ->
                createSilkAndChanceDrop(block, ModItems.WHITE_CHOCOLATE_BAR.value, 0f, 1f, 0.125f)
            }
            this.add(ModBlocks.DARK_CHOCOLATE_MUSHROOM.value) { block ->
                createSilkAndChanceDrop(block, ModItems.DARK_CHOCOLATE_BAR.value, 0f, 1f, 0.125f)
            }
            this.dropSelf(ModBlocks.MILK_CHOCOLATE_BLOCK.value)
            this.dropSelf(ModBlocks.WHITE_CHOCOLATE_BLOCK.value)
            this.dropSelf(ModBlocks.DARK_CHOCOLATE_BLOCK.value)
            this.dropSelf(ModBlocks.MILK_CHOCOLATE_BRICK.value)
            this.dropSelf(ModBlocks.WHITE_CHOCOLATE_BRICK.value)
            this.dropSelf(ModBlocks.DARK_CHOCOLATE_BRICK.value)
            this.dropSelf(ModBlocks.MILK_CHOCOLATE_WORKBENCH.value)
            this.dropSelf(ModBlocks.WHITE_CHOCOLATE_WORKBENCH.value)
            this.dropSelf(ModBlocks.DARK_CHOCOLATE_WORKBENCH.value)
            this.dropSelf(ModBlocks.COTTON_CANDY_SAPLING.value)
            this.add(ModBlocks.COTTON_CANDY_LEAVES.value) { block ->
                createChocolateLeavesDrops(
                    block,
                    ModBlocks.COTTON_CANDY_SAPLING.value,
                    ModItems.COTTON_CANDY.value,
                    *NORMAL_LEAVES_SAPLING_CHANCES
                )
            }
            this.add(ModBlocks.COTTON_CANDY_PLANT.value) { block ->
                createShearsDispatchTable(
                    block,
                    LootItem.lootTableItem(ModItems.COTTON_CANDY.value)
                        .apply(SetItemCountFunction.setCount(ConstantValue(1f)))
                )
            }
            this.add(ModBlocks.COTTON_CANDY_BUSH.value) { block ->
                createSilkAndChanceDrop(block, ModItems.COTTON_CANDY.value, 0f, 2f, 0.125f)
            }
            this.dropSelf(ModBlocks.WHITE_CANDY_CANE_BLOCK.value)
            this.dropSelf(ModBlocks.RED_CANDY_CANE_BLOCK.value)
            this.dropSelf(ModBlocks.GREEN_CANDY_CANE_BLOCK.value)
            this.dropSelf(ModBlocks.WHITE_RED_CANDY_CANE_BLOCK.value)
            this.dropSelf(ModBlocks.WHITE_GREEN_CANDY_CANE_BLOCK.value)
            this.dropSelf(ModBlocks.RED_GREEN_CANDY_CANE_BLOCK.value)

            this.dropSelf(ModBlocks.WHITE_CANDY_CANE_WORKBENCH.value)
            this.dropSelf(ModBlocks.RED_CANDY_CANE_WORKBENCH.value)
            this.dropSelf(ModBlocks.GREEN_CANDY_CANE_WORKBENCH.value)
            this.dropSelf(ModBlocks.WHITE_RED_CANDY_CANE_WORKBENCH.value)
            this.dropSelf(ModBlocks.WHITE_GREEN_CANDY_CANE_WORKBENCH.value)
            this.dropSelf(ModBlocks.RED_GREEN_CANDY_CANE_WORKBENCH.value)

            this.add(ModBlocks.CRYSTALLIZED_SUGAR.value) { block ->
                createSilkTouchDispatchTable(
                    block,
                    LootItem.lootTableItem(ModItems.SUGAR_CRYSTAL.value)
                        .apply(SetItemCountFunction.setCount(ConstantValue(4f)))
                )
            }
            this.add(ModBlocks.SUGAR_SAND.value) { block ->
                createSilkTouchDispatchTable(
                    block,
                    LootItem.lootTableItem(Items.SUGAR).apply(SetItemCountFunction.setCount(ConstantValue(4f)))
                )
            }
            this.add(ModBlocks.CANDY_GRASS_BLOCK.value) { block ->
                createSingleItemTableWithSilkTouch(block, ModBlocks.MILK_BROWNIE_BLOCK.value)
            }
            this.dropSelf(ModBlocks.MILK_BROWNIE_BLOCK.value)
            this.add(ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.value) { block ->
                createSingleItemTableWithSilkTouch(block, ModBlocks.WHITE_BROWNIE_BLOCK.value)
            }
            this.dropSelf(ModBlocks.WHITE_BROWNIE_BLOCK.value)
            this.add(ModBlocks.DARK_CANDY_GRASS_BLOCK.value) { block ->
                createSingleItemTableWithSilkTouch(block, ModBlocks.DARK_BROWNIE_BLOCK.value)
            }
            this.dropSelf(ModBlocks.DARK_BROWNIE_BLOCK.value)
            this.add(ModBlocks.CRYSTALLIZED_SUGAR_COOKIE_ORE.value) { block -> createOreDrop(block, Items.COOKIE) }
            this.add(ModBlocks.COOKIE_ORE.value) { block -> createOreDrop(block, Items.COOKIE) }
            this.add(ModBlocks.TELEPORTER_ORE.value) { block -> createOreDrop(block, ModItems.TELEPORTER.value) }

            this.dropSelf(ModBlocks.RED_GUMMY_BLOCK.value)
            this.dropSelf(ModBlocks.ORANGE_GUMMY_BLOCK.value)
            this.dropSelf(ModBlocks.YELLOW_GUMMY_BLOCK.value)
            this.dropSelf(ModBlocks.WHITE_GUMMY_BLOCK.value)
            this.dropSelf(ModBlocks.GREEN_GUMMY_BLOCK.value)

            this.dropSelf(ModBlocks.RED_HARDENED_GUMMY_BLOCK.value)
            this.dropSelf(ModBlocks.ORANGE_HARDENED_GUMMY_BLOCK.value)
            this.dropSelf(ModBlocks.YELLOW_HARDENED_GUMMY_BLOCK.value)
            this.dropSelf(ModBlocks.WHITE_HARDENED_GUMMY_BLOCK.value)
            this.dropSelf(ModBlocks.GREEN_HARDENED_GUMMY_BLOCK.value)

            this.dropSelf(ModBlocks.RED_GUMMY_WORM_BLOCK.value)
            this.dropSelf(ModBlocks.ORANGE_GUMMY_WORM_BLOCK.value)
            this.dropSelf(ModBlocks.YELLOW_GUMMY_WORM_BLOCK.value)
            this.dropSelf(ModBlocks.WHITE_GUMMY_WORM_BLOCK.value)
            this.dropSelf(ModBlocks.GREEN_GUMMY_WORM_BLOCK.value)

            this.dropSelf(ModBlocks.RED_GUMMY_WORKBENCH.value)
            this.dropSelf(ModBlocks.ORANGE_GUMMY_WORKBENCH.value)
            this.dropSelf(ModBlocks.YELLOW_GUMMY_WORKBENCH.value)
            this.dropSelf(ModBlocks.WHITE_GUMMY_WORKBENCH.value)
            this.dropSelf(ModBlocks.GREEN_GUMMY_WORKBENCH.value)
        }

        override fun getKnownBlocks(): Iterable<Block> {
            return (Platform.registry as CandyWorldRegistryImpl).BLOCKS
                .entries
                .map { it.get() }
        }


        private fun createChocolateLeavesDrops(
            block: Block,
            sapling: Block,
            chocolate: Item,
            vararg chances: Float
        ): LootTable.Builder {
            return createLeavesDrops(block, sapling, *chances).withPool(
                LootPool.lootPool().setRolls(ConstantValue(1f))
                    .`when`(hasNoShearsOrSilkTouch).add(
                        applyExplosionCondition(block, LootItem.lootTableItem(chocolate)).`when`(
                            BonusLevelTableCondition.bonusLevelFlatChance(
                                registries.holderOrThrow(Enchantments.FORTUNE),
                                0.005f,
                                0.0055555557f,
                                0.00625f,
                                0.008333334f,
                                0.025f,
                            )
                        )
                    )
            )
        }

        private fun createSilkAndChanceDrop(
            block: Block,
            drop: Item,
            min: Float,
            max: Float,
            chance: Float
        ): LootTable.Builder {
            return createShearsDispatchTable(
                block,
                applyExplosionDecay(
                    block,
                    LootItem.lootTableItem(drop)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)))
                        .`when`(LootItemRandomChanceCondition.randomChance(chance))
                )
            )
        }

        companion object {
            private val NORMAL_LEAVES_SAPLING_CHANCES: FloatArray = floatArrayOf(0.05f, 0.0625f, 0.083333336f, 0.1f)
        }
    }

    private class CandyEntities(
        registries: HolderLookup.Provider,
    ) : EntityLootSubProvider(
        FeatureFlags.REGISTRY.allFlags(),
        registries,
    ) {
        override fun generate() {


            //TODO: See if the animals should drop candy variants of the vanilla drops instead
            this.add(
                ModEntities.COTTON_CANDY_SHEEP.value,
                LootTable.lootTable().withPool(
                    LootPool.lootPool().setRolls(ConstantValue(1f))
                        .add(
                            LootItem.lootTableItem(Items.MUTTON)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)))
                                .apply(SmeltItemFunction.smelted().`when`(this.shouldSmeltLoot()))
                                .apply(
                                    EnchantedCountIncreaseFunction.lootingMultiplier(
                                        this.registries,
                                        UniformGenerator.between(0.0f, 1.0f)
                                    )
                                )
                        )
                )
            )

            this.add(
                ModEntities.EASTER_CHICKEN.value,
                LootTable.lootTable()
                    .withPool(
                        LootPool.lootPool().setRolls(ConstantValue(1f))
                            .add(
                                LootItem.lootTableItem(Items.FEATHER)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                                    .apply(
                                        EnchantedCountIncreaseFunction.lootingMultiplier(
                                            this.registries,
                                            UniformGenerator.between(0.0f, 1.0f)
                                        )
                                    )
                            )
                    )
                    .withPool(
                        LootPool.lootPool().setRolls(ConstantValue(1f))
                            .add(
                                LootItem.lootTableItem(Items.CHICKEN)
                                    .apply(SmeltItemFunction.smelted().`when`(this.shouldSmeltLoot()))
                                    .apply(
                                        EnchantedCountIncreaseFunction.lootingMultiplier(
                                            this.registries,
                                            UniformGenerator.between(0.0f, 1.0f)
                                        )
                                    )
                            )
                    )
            )

            this.add(ModEntities.GUMMY_BEAR.value, LootTable.lootTable())
            this.add(
                ModEntities.GUMMY_BEAR.value,
                ModLootTables.ENTITY_BEAR_GREEN,
                createGummyBearTable(ModItems.GREEN_GUMMY.value)
            )
            this.add(
                ModEntities.GUMMY_BEAR.value,
                ModLootTables.ENTITY_BEAR_ORANGE,
                createGummyBearTable(ModItems.ORANGE_GUMMY.value)
            )
            this.add(
                ModEntities.GUMMY_BEAR.value,
                ModLootTables.ENTITY_BEAR_RED,
                createGummyBearTable(ModItems.RED_GUMMY.value)
            )
            this.add(
                ModEntities.GUMMY_BEAR.value,
                ModLootTables.ENTITY_BEAR_WHITE,
                createGummyBearTable(ModItems.WHITE_GUMMY.value)
            )
            this.add(
                ModEntities.GUMMY_BEAR.value,
                ModLootTables.ENTITY_BEAR_YELLOW,
                createGummyBearTable(ModItems.YELLOW_GUMMY.value)
            )

            this.add(ModEntities.GUMMY_MOUSE.value, LootTable.lootTable())
            this.add(
                ModEntities.GUMMY_MOUSE.value,
                ModLootTables.ENTITY_MOUSE_GREEN,
                createGummyMouseTable(ModItems.GREEN_GUMMY.value)
            )
            this.add(
                ModEntities.GUMMY_MOUSE.value,
                ModLootTables.ENTITY_MOUSE_ORANGE,
                createGummyMouseTable(ModItems.ORANGE_GUMMY.value)
            )
            this.add(
                ModEntities.GUMMY_MOUSE.value,
                ModLootTables.ENTITY_MOUSE_RED,
                createGummyMouseTable(ModItems.RED_GUMMY.value)
            )
            this.add(
                ModEntities.GUMMY_MOUSE.value,
                ModLootTables.ENTITY_MOUSE_WHITE,
                createGummyMouseTable(ModItems.WHITE_GUMMY.value)
            )
            this.add(
                ModEntities.GUMMY_MOUSE.value,
                ModLootTables.ENTITY_MOUSE_YELLOW,
                createGummyMouseTable(ModItems.YELLOW_GUMMY.value)
            )
        }

        override fun getKnownEntityTypes(): Stream<EntityType<*>> {
            return (Platform.registry as CandyWorldRegistryImpl).ENTITY_TYPES
                .entries
                .map { it.get() }
                .stream()
        }


        private fun createGummyBearTable(itemProvider: Item): LootTable.Builder {
            return LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue(1f))
                        .add(
                            LootItem.lootTableItem(itemProvider)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 20.0f)))
                                .apply(
                                    EnchantedCountIncreaseFunction.lootingMultiplier(
                                        this.registries,
                                        UniformGenerator.between(0.0f, 1.0f)
                                    )
                                )
                        )
                )
                .withPool(
                    LootPool.lootPool().setRolls(ConstantValue(1f))
                        .add(NestedLootTable.lootTableReference(ModEntities.GUMMY_BEAR.value.getDefaultLootTable()))
                )
        }

        private fun createGummyMouseTable(itemProvider: Item): LootTable.Builder {
            return LootTable.lootTable()
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue(1f))
                        .add(
                            LootItem.lootTableItem(itemProvider)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
                                .apply(
                                    EnchantedCountIncreaseFunction.lootingMultiplier(
                                        this.registries,
                                        UniformGenerator.between(0.0f, 1.0f)
                                    )
                                )
                        )
                )
                .withPool(
                    LootPool.lootPool()
                        .setRolls(ConstantValue(1f))
                        .add(NestedLootTable.lootTableReference(ModEntities.GUMMY_MOUSE.value.getDefaultLootTable()))
                )
        }
    }

}