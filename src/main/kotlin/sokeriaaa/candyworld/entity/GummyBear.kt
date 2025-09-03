package sokeriaaa.candyworld.entity

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.animal.PolarBear
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.storage.loot.LootTable
import sokeriaaa.candyworld.enums.EnumGummy
import sokeriaaa.candyworld.registry.ModEntities
import sokeriaaa.candyworld.registry.ModLootTables
import sokeriaaa.candyworld.registry.ModTags

open class GummyBear(
    entityType: EntityType<out GummyBear> = ModEntities.GUMMY_BEAR.value,
    level: Level,
    color: EnumGummy? = null,
) : PolarBear(entityType, level) {

    var color: EnumGummy
        get() = EnumGummy.entries[entityData[COLOR].toInt()]
        set(value) {
            entityData[COLOR] = value.meta.toByte()
        }

    override fun getDefaultLootTable(): ResourceKey<LootTable> {
        return when (color) {
            EnumGummy.RED -> ModLootTables.ENTITY_BEAR_RED
            EnumGummy.ORANGE -> ModLootTables.ENTITY_BEAR_ORANGE
            EnumGummy.YELLOW -> ModLootTables.ENTITY_BEAR_YELLOW
            EnumGummy.WHITE -> ModLootTables.ENTITY_BEAR_WHITE
            EnumGummy.GREEN -> ModLootTables.ENTITY_BEAR_GREEN
        }
    }

    protected override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(COLOR, 0.toByte())
    }

    override fun finalizeSpawn(
        level: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnType: MobSpawnType,
        spawnGroupData: SpawnGroupData?
    ): SpawnGroupData {
        var spawnGroupDataResult: SpawnGroupData? = spawnGroupData
        if (spawnGroupDataResult is GroupData) {
            if (spawnGroupDataResult.madeParent) {
                if (!spawnGroupDataResult.madeSecondParent && random.nextInt(3) == 0) {
                    spawnGroupDataResult.madeSecondParent = true
                } else {
                    this.setAge(-24000)
                }
            }
            this.color = spawnGroupDataResult.color
        } else {
            val groupData = GroupData(
                madeParent = true,
                color = EnumGummy.random(this.random),
            )
            groupData.madeParent = true
            groupData.color = EnumGummy.random(this.random)
            this.color = groupData.color
            spawnGroupDataResult = groupData
        }
        return spawnGroupDataResult
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putByte("Color", this.color.meta.toByte())
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        this.color = EnumGummy.byMetadata(compound.getByte("Color").toInt())
    }

    override fun getBreedOffspring(level: ServerLevel, otherParent: AgeableMob): AgeableMob {
        if (otherParent is GummyBear) {
            return GummyBear(level = this.level(), color = otherParent.color)
        }
        return GummyBear(level = this.level())
    }

    private data class GroupData(
        var madeParent: Boolean = false,
        var madeSecondParent: Boolean = false,
        var color: EnumGummy,
    ) : SpawnGroupData

    companion object {
        @JvmStatic
        private val COLOR: EntityDataAccessor<Byte> =
            SynchedEntityData.defineId(GummyBear::class.java, EntityDataSerializers.BYTE)

        fun canGummySpawn(
            entityType: EntityType<*>,
            serverLevel: ServerLevelAccessor,
            spawnType: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            return serverLevel.getBlockState(pos.below()).`is`(ModTags.GUMMY)
                    && serverLevel.getRawBrightness(pos, 0) > 8
        }

        fun registerAttributes(): AttributeSupplier.Builder {
            return createAttributes()
        }

    }

}