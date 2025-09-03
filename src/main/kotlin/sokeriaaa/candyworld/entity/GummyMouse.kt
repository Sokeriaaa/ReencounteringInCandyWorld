package sokeriaaa.candyworld.entity

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.animal.Ocelot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.loot.LootTable
import sokeriaaa.candyworld.enums.EnumGummy
import sokeriaaa.candyworld.registry.ModBlocks
import sokeriaaa.candyworld.registry.ModEntities
import sokeriaaa.candyworld.registry.ModLootTables
import sokeriaaa.candyworld.registry.ModTags

open class GummyMouse(
    entityType: EntityType<out GummyMouse> = ModEntities.GUMMY_MOUSE.value,
    level: Level,
) : Animal(entityType, level) {

    var color: EnumGummy
        get() = EnumGummy.entries[entityData[COLOR].toInt()]
        set(value) {
            entityData[COLOR] = value.meta.toByte()
        }

    public override fun registerGoals() {
        this.goalSelector.addGoal(0, FloatGoal(this))
        this.goalSelector.addGoal(1, PanicGoal(this, 1.2))
        this.goalSelector.addGoal(2, AvoidEntityGoal(this, Ocelot::class.java, 8.0f, 0.85, 1.33))
        this.goalSelector.addGoal(3, AvoidEntityGoal(this, Player::class.java, 1.2f, 0.85, 1.33))
        this.goalSelector.addGoal(4, WaterAvoidingRandomStrollGoal(this, 1.0))
        this.goalSelector.addGoal(5, WaterAvoidingRandomStrollGoal(this, 0.6))
        this.goalSelector.addGoal(6, LookAtPlayerGoal(this, Player::class.java, 6.0f))
        this.goalSelector.addGoal(7, RandomLookAroundGoal(this))
    }

    protected override fun getDefaultLootTable(): ResourceKey<LootTable> {
        return when (color) {
            EnumGummy.RED -> ModLootTables.ENTITY_MOUSE_RED
            EnumGummy.ORANGE -> ModLootTables.ENTITY_MOUSE_ORANGE
            EnumGummy.YELLOW -> ModLootTables.ENTITY_MOUSE_YELLOW
            EnumGummy.WHITE -> ModLootTables.ENTITY_MOUSE_WHITE
            EnumGummy.GREEN -> ModLootTables.ENTITY_MOUSE_GREEN
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
    ): SpawnGroupData? {
        val state: BlockState = level.getBlockState(this.blockPosition().below())
        this.color = when {
            state.`is`(ModBlocks.RED_GUMMY_BLOCK.value) -> EnumGummy.RED
            state.`is`(ModBlocks.ORANGE_GUMMY_BLOCK.value) -> EnumGummy.ORANGE
            state.`is`(ModBlocks.YELLOW_GUMMY_BLOCK.value) -> EnumGummy.YELLOW
            state.`is`(ModBlocks.WHITE_GUMMY_BLOCK.value) -> EnumGummy.WHITE
            state.`is`(ModBlocks.GREEN_GUMMY_BLOCK.value) -> EnumGummy.GREEN
            else -> EnumGummy.random(this.random)
        }
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData)
    }

    override fun getBreedOffspring(level: ServerLevel, otherParent: AgeableMob): AgeableMob? {
        return null
    }

    override fun isFood(stack: ItemStack): Boolean {
        return false
    }


    public override fun getAmbientSound(): SoundEvent? {
        return null
    }

    public override fun getHurtSound(damageSource: DamageSource): SoundEvent? {
        return SoundEvents.RABBIT_HURT
    }

    public override fun getDeathSound(): SoundEvent {
        return SoundEvents.RABBIT_DEATH
    }

    protected fun isMovementNoisy(): Boolean {
        return false
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putByte("Color", this.color.meta.toByte())
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        this.color = EnumGummy.byMetadata(compound.getByte("Color").toInt())
    }

    companion object {
        @JvmStatic
        private val COLOR: EntityDataAccessor<Byte> =
            SynchedEntityData.defineId(GummyMouse::class.java, EntityDataSerializers.BYTE)

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
            return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
        }

    }

}