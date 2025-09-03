package sokeriaaa.candyworld.registry

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.storage.loot.LootTable
import sokeriaaa.candyworld.CandyWorld

object ModLootTables {
    val ENTITY_BEAR_RED: ResourceKey<LootTable> by lazy {
        create("entities/gummy_bear/bear_red")
    }
    val ENTITY_BEAR_ORANGE: ResourceKey<LootTable> by lazy {
        create("entities/gummy_bear/bear_orange")
    }
    val ENTITY_BEAR_YELLOW: ResourceKey<LootTable> by lazy {
        create("entities/gummy_bear/bear_yellow")
    }
    val ENTITY_BEAR_WHITE: ResourceKey<LootTable> by lazy {
        create("entities/gummy_bear/bear_white")
    }
    val ENTITY_BEAR_GREEN: ResourceKey<LootTable> by lazy {
        create("entities/gummy_bear/bear_green")
    }

    val ENTITY_MOUSE_RED: ResourceKey<LootTable> by lazy {
        create("entities/gummy_mouse/mouse_red")
    }
    val ENTITY_MOUSE_ORANGE: ResourceKey<LootTable> by lazy {
        create("entities/gummy_mouse/mouse_orange")
    }
    val ENTITY_MOUSE_YELLOW: ResourceKey<LootTable> by lazy {
        create("entities/gummy_mouse/mouse_yellow")
    }
    val ENTITY_MOUSE_WHITE: ResourceKey<LootTable> by lazy {
        create("entities/gummy_mouse/mouse_white")
    }
    val ENTITY_MOUSE_GREEN: ResourceKey<LootTable> by lazy {
        create("entities/gummy_mouse/mouse_green")
    }

    private fun create(name: String): ResourceKey<LootTable> {
        return ResourceKey.create(Registries.LOOT_TABLE, CandyWorld.id(name))
    }
}