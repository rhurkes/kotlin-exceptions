@file:Suppress("unused")

package playground

import java.time.ZonedDateTime

// NOTE: this is here for serialization
enum class CommandType {
    CREATE,
    REMOVE,
    UPDATE,
    DELETE
}

sealed class Command(
    open val commandType: CommandType,
    open val sourceSystem: String,
    open val sourceProductId: String,
    open val sourceTimestamp: ZonedDateTime
) {
    open fun toMap(): Map<String, Any> = mapOf(
        "commandType" to commandType,
        "sourceSystem" to sourceSystem,
        "sourceProductId" to sourceProductId
    )

    val key
        get() = "$sourceSystem-$sourceProductId"

    data class CreateCommand(
        override val sourceSystem: String,
        override val sourceProductId: String,
        val ingestItem: String,
        override val sourceTimestamp: ZonedDateTime
    ) : Command(CommandType.CREATE, sourceSystem, sourceProductId, sourceTimestamp)

    data class RemoveCommand(
        override val sourceSystem: String,
        override val sourceProductId: String,
        val itemVersion: Int,
        override val sourceTimestamp: ZonedDateTime
    ) :
        Command(CommandType.REMOVE, sourceSystem, sourceProductId, sourceTimestamp) {
        override fun toMap() = mapOf(
            "commandType" to CommandType.REMOVE,
            "itemVersion" to itemVersion,
            "sourceSystem" to sourceSystem,
            "sourceProductId" to sourceProductId
        )
    }

    data class UpdateCommand(
        override val sourceSystem: String,
        override val sourceProductId: String,
        val itemVersion: Int,
        override val sourceTimestamp: ZonedDateTime
    ) :
        Command(CommandType.UPDATE, sourceSystem, sourceProductId, sourceTimestamp) {
        override fun toMap() = mapOf(
            "commandType" to CommandType.UPDATE,
            "itemVersion" to itemVersion,
            "sourceSystem" to sourceSystem,
            "sourceProductId" to sourceProductId
        )
    }

    data class DeleteCommand(
        override val sourceSystem: String,
        override val sourceProductId: String,
        override val sourceTimestamp: ZonedDateTime,
        val really: Boolean
    ) :
        Command(CommandType.DELETE, sourceSystem, sourceProductId, sourceTimestamp)

        data class NewCommand(
            override val sourceSystem: String,
            override val sourceProductId: String,
            val ingestItem: String,
            override val sourceTimestamp: ZonedDateTime
        ) : Command(CommandType.REMOVE, sourceSystem, sourceProductId, sourceTimestamp)
}
