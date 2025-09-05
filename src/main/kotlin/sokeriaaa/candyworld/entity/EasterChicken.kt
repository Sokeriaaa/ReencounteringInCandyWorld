package sokeriaaa.candyworld.entity

import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.pathfinder.PathType
import net.minecraft.world.phys.Vec3
import sokeriaaa.candyworld.entity.ai.EasterChickenPanicGoal
import sokeriaaa.candyworld.enums.EnumChocolate
import sokeriaaa.candyworld.registry.ModBlocks
import sokeriaaa.candyworld.registry.ModEntities
import sokeriaaa.candyworld.registry.ModItems

open class EasterChicken(
    entityType: EntityType<out EasterChicken> = ModEntities.EASTER_CHICKEN.value,
    level: Level,
) : Animal(entityType, level) {

    var wingRotation: Float = 0f
    var destPos: Float = 0f
    var oFlapSpeed: Float = 0f
    var oFlap: Float = 0f

    /**
     * This chicken will explode when eggComboAmount reaches 0
     */
    var explodeWhenDone: Boolean = false
    private var wingRotDelta = 1.0f

    /**
     * The time until the next egg is spawned
     */
    var timeUntilNextEgg: Int = this.random.nextInt(6000) + 6000

    /**
     * Type for the next egg
     */
    private var nextEggType = -1

    /**
     * Amount of eggs still to be laid in quick succession
     */
    private var eggComboAmount = 0

    init {
        this.setPathfindingMalus(PathType.WATER, 0.0f)
    }

    protected override fun spawnSprintParticle() {
        this.level()
            .addParticle(ParticleTypes.SMOKE, this.x, this.y + 0.5, this.z, 0.0, 0.0, 0.0)
    }

    protected override fun registerGoals() {
        this.goalSelector.addGoal(0, FloatGoal(this))
        this.goalSelector.addGoal(1, EasterChickenPanicGoal(this, 1.3))
        this.goalSelector.addGoal(2, BreedGoal(this, 1.0))
        this.goalSelector.addGoal(
            3, TemptGoal(
                this, 1.0, Ingredient.of(
                    ModItems.WAFER_STICK.value,
                    ModItems.MILK_CHOCOLATE_BAR.value,
                    ModItems.WHITE_CHOCOLATE_BAR.value,
                    ModItems.DARK_CHOCOLATE_BAR.value
                ), false
            )
        )
        this.goalSelector.addGoal(4, FollowParentGoal(this, 1.1))
        this.goalSelector.addGoal(5, WaterAvoidingRandomStrollGoal(this, 1.0))
        this.goalSelector.addGoal(6, LookAtPlayerGoal(this, Player::class.java, 6.0f))
        this.goalSelector.addGoal(7, RandomLookAroundGoal(this))
    }

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        val itemStack: ItemStack = player.getItemInHand(hand)

        if (!this.isBaby && this.nextEggType == -1 && !this.explodeWhenDone) {
            if (itemStack.item === Items.FIRE_CHARGE) {
                firePanic()
                player.swing(hand)
                itemStack.shrink(1)
                return InteractionResult.SUCCESS
            }
            if (itemStack.item === ModItems.MILK_CHOCOLATE_BAR.value) {
                if (!this.level().isClientSide) {
                    this.timeUntilNextEgg = 30 + this.random.nextInt(30)
                    itemStack.shrink(1)
                    this.nextEggType = 0
                }
                this.level().addParticle(
                    ParticleTypes.HAPPY_VILLAGER,
                    this.x + (this.random.nextFloat() * this.bbWidth * 2.0f).toDouble() - this.bbWidth.toDouble(),
                    this.y + 0.5 + (this.random.nextFloat() * this.bbHeight).toDouble(),
                    this.z + (this.random.nextFloat() * this.bbWidth * 2.0f).toDouble() - this.bbWidth.toDouble(),
                    0.0,
                    0.0,
                    0.0
                )
                return InteractionResult.SUCCESS
            } else if (itemStack.item === ModItems.WHITE_CHOCOLATE_BAR.value) {
                if (!this.level().isClientSide) {
                    this.timeUntilNextEgg = 30 + this.random.nextInt(30)
                    itemStack.shrink(1)
                    this.nextEggType = 1
                }
                this.level().addParticle(
                    ParticleTypes.HAPPY_VILLAGER,
                    this.x + (this.random.nextFloat() * this.bbWidth * 2.0f).toDouble() - this.bbWidth.toDouble(),
                    this.y + 0.5 + (this.random.nextFloat() * this.bbHeight).toDouble(),
                    this.z + (this.random.nextFloat() * this.bbWidth * 2.0f).toDouble() - this.bbWidth.toDouble(),
                    0.0,
                    0.0,
                    0.0
                )
                return InteractionResult.SUCCESS
            } else if (itemStack.item === ModItems.DARK_CHOCOLATE_BAR.value) {
                if (!this.level().isClientSide) {
                    this.timeUntilNextEgg = 30 + this.random.nextInt(30)
                    itemStack.shrink(1)
                    this.nextEggType = 2
                }
                this.level().addParticle(
                    ParticleTypes.HAPPY_VILLAGER,
                    this.x + (this.random.nextFloat() * this.bbWidth * 2.0f).toDouble() - this.bbWidth.toDouble(),
                    this.y + 0.5 + (this.random.nextFloat() * this.bbHeight).toDouble(),
                    this.z + (this.random.nextFloat() * this.bbWidth * 2.0f).toDouble() - this.bbWidth.toDouble(),
                    0.0,
                    0.0,
                    0.0
                )
                return InteractionResult.SUCCESS
            }
        }

        return super.mobInteract(player, hand)
    }

    override fun spawnAnim() {
        super.spawnAnim()
        if (this.level().isClientSide) {
            this.level().addParticle(ParticleTypes.EXPLOSION, this.x, this.y + 0.5, this.z, 0.0, 0.0, 0.0)
        } else {
            this.level().broadcastEntityEvent(this, 20.toByte())
        }
    }

    private fun firePanic() {
        if (!this.level().isClientSide) {
            this.timeUntilNextEgg = 20 + this.random.nextInt(50)
            this.hurt(this.damageSources().generic(), 0.0f)
            this.explodeWhenDone = true
            this.setSprinting(true)
            this.eggComboAmount = 25 + this.random.nextInt(20)
            this.playSound(SoundEvents.TNT_PRIMED, 1.0f, 1.0f)
        }
    }

    private fun explode() {
        this.spawnAnim()
        this.playSound(SoundEvents.GENERIC_EXPLODE.value(), 1.0f, 1.0f)
        for (i in 0..this.random.nextInt(5) + 3) {
            val ent: ItemEntity? = this.spawnAtLocation(ItemStack(Items.FEATHER, 1), 0f)
            if (ent != null) {
                val motion: Vec3 = deltaMovement
                setDeltaMovement(
                    motion.x + this.random.nextFloat() * 0.4f,
                    motion.y + (this.random.nextFloat() - this.random.nextFloat()) * 0.3f,
                    motion.z + (this.random.nextFloat() - this.random.nextFloat()) * 0.3f
                )
            }
        }
        for (i in 0..this.random.nextInt(3) + 3) {
            val list = listOf(
                ModItems.DARK_CHOCOLATE_EGG.value,
                ModItems.MILK_CHOCOLATE_EGG.value,
                ModItems.WHITE_CHOCOLATE_EGG.value,
            )
            val item = list[random.nextInt(list.size)]
            val ent: ItemEntity? = this.spawnAtLocation(ItemStack(item, 1))
            if (ent != null) {
                val motion: Vec3 = deltaMovement
                setDeltaMovement(
                    motion.x + this.random.nextFloat() * 0.4f,
                    motion.y + (this.random.nextFloat() - this.random.nextFloat()) * 0.3f,
                    motion.z + (this.random.nextFloat() - this.random.nextFloat()) * 0.3f
                )
            }
        }
        this.removeAfterChangingDimensions()
    }

    private fun dropEgg(meta: Int) {
        val eggItem: Item?
        when (meta) {
            1 -> eggItem = ModItems.WHITE_CHOCOLATE_EGG.value
            2 -> eggItem = ModItems.DARK_CHOCOLATE_EGG.value
            else -> eggItem = ModItems.MILK_CHOCOLATE_EGG.value
        }
        val stack = ItemStack(eggItem)
        val motion: Vec3 = deltaMovement
        val entityitem =
            ItemEntity(this.level(), this.x - motion.x * 5, this.y, this.z - motion.z * 5, stack)
        entityitem.setDefaultPickUpDelay()

        this.level().addFreshEntity(entityitem)
    }

    override fun aiStep() {
        super.aiStep()
        this.oFlap = this.wingRotation
        this.oFlapSpeed = this.destPos
        this.destPos = (this.destPos.toDouble() + (if (this.onGround()) -1 else 4).toDouble() * 0.3).toFloat()
        this.destPos = Mth.clamp(this.destPos, 0.0f, 1.0f)

        if (!this.onGround() && this.wingRotDelta < 1.0f) {
            this.wingRotDelta = 1.0f
        }

        this.wingRotDelta = (this.wingRotDelta.toDouble() * 0.9).toFloat()

        val motion: Vec3 = this.deltaMovement
        if (!this.onGround() && motion.y < 0.0) {
            this.deltaMovement = motion.multiply(1.0, 0.6, 1.0)
        }

        this.wingRotation += this.wingRotDelta * 2.0f

        // drop an egg
        if (!this.level().isClientSide && !this.isBaby && --this.timeUntilNextEgg <= 0) {
            // whether the chicken has been fed chocolate, combo's should not happen

            var flag = false

            this.playSound(
                SoundEvents.CHICKEN_EGG,
                1.0f,
                (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f
            )

            // choose egg type
            var meta = this.random.nextInt(EnumChocolate.entries.size)
            if (this.nextEggType != -1) {
                meta = this.nextEggType
                flag = true
                this.nextEggType = -1
            }
            this.dropEgg(meta)

            // chance for combo, can only happen when currently not in combo and last egg was not chocolate induced
            if (this.eggComboAmount <= 0 && this.random.nextInt(100) == 0 && !flag) {
                this.eggComboAmount = this.random.nextInt(30) + 30
                this.hurt(this.damageSources().generic(), 0.0f)
            }

            // set time until next egg
            if (this.eggComboAmount-- > 0) {
                this.timeUntilNextEgg = 1
            } else {
                this.timeUntilNextEgg = this.random.nextInt(6000) + 10000
            }

            // check whether the chicken should explode
            if (this.eggComboAmount == 0 && this.explodeWhenDone) {
                this.explode()
            }
        }
    }

    override fun causeFallDamage(damageMultiplier: Float, multiplier: Float, source: DamageSource): Boolean {
        return false
    }

    protected override fun getAmbientSound(): SoundEvent {
        return SoundEvents.CHICKEN_AMBIENT
    }

    protected override fun getHurtSound(damageSource: DamageSource): SoundEvent {
        return SoundEvents.CHICKEN_HURT
    }

    protected override fun getDeathSound(): SoundEvent {
        return SoundEvents.CHICKEN_DEATH
    }

    protected override fun playStepSound(pos: BlockPos, state: BlockState) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15f, 1.0f)
    }

    override fun getBreedOffspring(level: ServerLevel, otherParent: AgeableMob): AgeableMob? {
        return EasterChicken(level = this.level())
    }

    /**
     * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
     * the animal type)
     */
    override fun isFood(stack: ItemStack): Boolean {
        return stack.item === ModItems.WAFER_STICK.value
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)

        if (compound.contains("EggLayTime")) {
            this.timeUntilNextEgg = compound.getInt("EggLayTime")
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putInt("EggLayTime", this.timeUntilNextEgg)
    }

    companion object {

        fun canChickenSpawn(
            entityType: EntityType<*>,
            serverLevel: ServerLevelAccessor,
            spawnType: MobSpawnType,
            pos: BlockPos,
            random: RandomSource
        ): Boolean {
            return serverLevel.getBlockState(pos.below()).`is`(ModBlocks.CHOCOLATE_COVERED_WHITE_BROWNIE.value)
                    && serverLevel.getRawBrightness(pos, 0) > 8
        }

        fun registerAttributes(): AttributeSupplier.Builder {
            return createMobAttributes().add(Attributes.MAX_HEALTH, 4.0).add(Attributes.MOVEMENT_SPEED, 0.25)
        }
    }

}