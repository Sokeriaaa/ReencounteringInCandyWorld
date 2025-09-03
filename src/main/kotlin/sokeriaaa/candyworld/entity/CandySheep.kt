package sokeriaaa.candyworld.entity

import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.ItemTags
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.block.state.BlockState
import net.neoforged.neoforge.common.Tags
import sokeriaaa.candyworld.entity.ai.EatCandyGrassGoal
import sokeriaaa.candyworld.registry.ModBlocks
import sokeriaaa.candyworld.registry.ModEntities
import sokeriaaa.candyworld.registry.ModItems
import kotlin.math.max

open class CandySheep(
    entityType: EntityType<out CandySheep> = ModEntities.COTTON_CANDY_SHEEP.value,
    level: Level,
) : Animal(entityType, level) {

    // registerGoals invokes at constructor of Mob so we have to use lateinit var,
    // or NullPointerException will throw.
    private lateinit var eatCandyGrassGoal: EatCandyGrassGoal
    private var sheepTimer = 0

    var sheared: Boolean
        get() = entityData[SHEARED]
        private set(value) {
            entityData[SHEARED] = value
        }

    public override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(SHEARED, false)
    }

    override fun registerGoals() {
        eatCandyGrassGoal = EatCandyGrassGoal(this)
        this.goalSelector.addGoal(0, FloatGoal(this))
        this.goalSelector.addGoal(1, PanicGoal(this, 1.25))
        this.goalSelector.addGoal(2, BreedGoal(this, 1.0))
        this.goalSelector.addGoal(
            3, TemptGoal(this, 1.1, { it.`is`(ItemTags.SHEEP_FOOD) }, false)
        )
        this.goalSelector.addGoal(4, FollowParentGoal(this, 1.1))
        this.goalSelector.addGoal(5, this.eatCandyGrassGoal)
        this.goalSelector.addGoal(6, WaterAvoidingRandomStrollGoal(this, 1.0))
        this.goalSelector.addGoal(7, LookAtPlayerGoal(this, Player::class.java, 6.0f))
        this.goalSelector.addGoal(8, RandomLookAroundGoal(this))
    }

    public override fun customServerAiStep() {
        this.sheepTimer = this.eatCandyGrassGoal.getEatingGrassTimer()
        super.customServerAiStep()
    }

    override fun aiStep() {
        if (this.level().isClientSide) {
            this.sheepTimer = max(0, this.sheepTimer - 1)
        }
        super.aiStep()
    }

    override fun handleEntityEvent(id: Byte) {
        if (id.toInt() == 10) {
            this.sheepTimer = 40
        } else {
            super.handleEntityEvent(id)
        }
    }

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        val itemStack: ItemStack = player.getItemInHand(hand)

        if (itemStack.`is`(Tags.Items.RODS_WOODEN) && !this.sheared && !this.isBaby) {
            this.sheared = true
            if (itemStack.count == 1) {
                // changes the held stick to cotton candy
                player.setItemInHand(hand, ItemStack(ModItems.COTTON_CANDY.value))
            } else {
                itemStack.shrink(1)
                if (!player.addItem(ItemStack(ModItems.COTTON_CANDY.value, 1))) {
                    // drop cotton candy
                    player.drop(ItemStack(ModItems.COTTON_CANDY.value), false)
                }
            }
            return InteractionResult.SUCCESS
        } else {
            return super.mobInteract(player, hand)
        }
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.item === Items.SUGAR
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 5
    }

    fun getHeadRotationPointY(partialTick: Float): Float {
        return if (this.sheepTimer <= 0) {
            0.0f
        } else if (this.sheepTimer in 4..36) {
            1.0f
        } else {
            if (this.sheepTimer < 4) {
                (this.sheepTimer.toFloat() - partialTick) / 4.0f
            } else {
                -((this.sheepTimer - 40).toFloat() - partialTick) / 4.0f
            }
        }
    }

    fun getHeadRotationAngleX(partialTick: Float): Float {
        return if (this.sheepTimer in 5..36) {
            val f = ((this.sheepTimer - 4).toFloat() - partialTick) / 32.0f
            (Math.PI.toFloat() / 5f) + 0.21991149f * Mth.sin(f * 28.7f)
        } else {
            if (this.sheepTimer > 0) {
                (Math.PI.toFloat() / 5f)
            } else {
                this.xRot * (Math.PI.toFloat() / 180f)
            }
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putBoolean("Sheared", this.sheared)
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        this.sheared = compound.getBoolean("Sheared")
    }

    protected override fun getAmbientSound(): SoundEvent {
        return SoundEvents.SHEEP_AMBIENT
    }

    protected override fun getHurtSound(damageSourceIn: DamageSource): SoundEvent {
        return SoundEvents.SHEEP_HURT
    }

    protected override fun getDeathSound(): SoundEvent {
        return SoundEvents.SHEEP_DEATH
    }

    protected override fun playStepSound(pos: BlockPos, state: BlockState) {
        this.playSound(SoundEvents.SHEEP_STEP, 0.15f, 1.0f)
    }

    override fun getBreedOffspring(level: ServerLevel, otherParent: AgeableMob): AgeableMob? {
        return CandySheep(level = this.level())
    }

    override fun ate() {
        this.sheared = false

        if (this.isBaby) {
            this.ageUp(60)
        }
    }

    override fun finalizeSpawn(
        level: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnType: MobSpawnType,
        spawnGroupData: SpawnGroupData?
    ): SpawnGroupData? {
        this.sheared = false
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData)
    }

    companion object {
        @JvmStatic
        private val SHEARED: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(CandySheep::class.java, EntityDataSerializers.BOOLEAN)

        fun canSheepSpawn(
            entityType: EntityType<*>,
            serverLevel: ServerLevelAccessor,
            spawnType: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            return serverLevel.getBlockState(pos.below()).`is`(ModBlocks.CANDY_GRASS_BLOCK.value)
                    && serverLevel.getRawBrightness(pos, 0) > 8
        }

        fun registerAttributes(): AttributeSupplier.Builder {
            return createMobAttributes().add(Attributes.MAX_HEALTH, 8.0).add(Attributes.MOVEMENT_SPEED, 0.23)
        }
    }
}