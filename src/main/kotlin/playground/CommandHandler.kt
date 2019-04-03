@file:Suppress("unused", "UNUSED_PARAMETER")

package playground

import playground.Command.*

class CommandHandler {
    /* In the real world, we'd:
        1. Read messages off a Kafka topic into a message handler
        2. Deserialize the message into a Command
        3. Then feed into the processCommand function
    */

    fun processCommand(command: Command) =// NOTE: when is only exhaustive as an expression!
        when (command) {
            is CreateCommand -> createItem(command)
            is RemoveCommand -> removeItem(command)
            is UpdateCommand -> updateItem(command)
            is DeleteCommand -> deleteItem(command)
        }.let {}

    private fun createItem(command: CreateCommand) {
        // do stuff
    }

    private fun removeItem(command: RemoveCommand) {
        // do stuff
    }

    private fun updateItem(command: UpdateCommand) {
        // do stuff
    }

    private fun deleteItem(command: DeleteCommand) {
        // do stuff
    }
}
