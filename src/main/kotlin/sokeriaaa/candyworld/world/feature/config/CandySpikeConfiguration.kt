package sokeriaaa.candyworld.world.feature.config

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

// 糖晶尖刺特征配置
// Sugar crystal spike feature configuration
data class CandySpikeConfiguration(
    val stateProvider: BlockStateProvider,
    val whitelist: Set<BlockState>,
    val blacklist: Set<BlockState>,
    val chance: Int,
    val minLength: Int,
    val maxLength: Int,
    val canReplace: Boolean,
    val project: Boolean,
) : FeatureConfiguration {

    companion object {
        // CODEC 用于序列化和反序列化配置
        // CODEC for serializing and deserializing configuration
        val CODEC: Codec<CandySpikeConfiguration> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.CODEC
                    .fieldOf("state_provider").forGetter { it.stateProvider },
                Codec.list(BlockState.CODEC)
                    .fieldOf("whitelist")
                    .xmap({ it.toSet() }, { it.toList() })
                    .forGetter { it.whitelist },
                Codec.list(BlockState.CODEC)
                    .fieldOf("blacklist")
                    .xmap({ it.toSet() }, { it.toList() })
                    .forGetter { it.blacklist },
                Codec.INT.fieldOf("chance")
                    .orElse(8)
                    .forGetter { it.chance },
                Codec.INT.fieldOf("min_length")
                    .orElse(3)
                    .forGetter { it.minLength },
                Codec.INT.fieldOf("max_length")
                    .orElse(8)
                    .forGetter { it.maxLength },
                Codec.BOOL.fieldOf("can_replace")
                    .orElse(false)
                    .forGetter { it.canReplace },
                Codec.BOOL.fieldOf("project")
                    .orElse(true)
                    .forGetter { it.project },
            ).apply(instance, ::CandySpikeConfiguration)
        }
    }

    // 构建器类
    // Builder class
    class Builder(private val stateProvider: BlockStateProvider) {
        private var whitelist: Set<BlockState> = emptySet()
        private var blacklist: Set<BlockState> = emptySet()
        private var chance: Int = 8
        private var minLength: Int = 3
        private var maxLength: Int = 8
        private var canReplace: Boolean = false
        private var project: Boolean = true

        fun whitelist(whitelist: Set<BlockState>): Builder {
            this.whitelist = whitelist
            return this
        }

        fun blacklist(blacklist: Set<BlockState>): Builder {
            this.blacklist = blacklist
            return this
        }

        fun chance(chance: Int): Builder {
            this.chance = chance
            return this
        }

        fun minLength(minLength: Int): Builder {
            this.minLength = minLength
            return this
        }

        fun maxLength(maxLength: Int): Builder {
            this.maxLength = maxLength
            return this
        }

        fun canReplace(): Builder {
            this.canReplace = true
            return this
        }

        fun noProjection(): Builder {
            this.project = false
            return this
        }

        fun build(): CandySpikeConfiguration {
            return CandySpikeConfiguration(
                stateProvider,
                whitelist,
                blacklist,
                chance,
                minLength,
                maxLength,
                canReplace,
                project,
            )
        }
    }
}