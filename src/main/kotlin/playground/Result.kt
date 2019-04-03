@file:Suppress("unused")

package playground

import playground.Result.*

@Suppress("FunctionName")
inline fun <T> Try(operation: () -> T): Result<T, Throwable> {
    return try {
        Ok(operation())
    } catch (e: Throwable) {
        Err(e)
    }
}

@Suppress("EqualsOrHashCode")
sealed class Result<out T, out E> {
    class Ok<out T>(val value: T) : Result<T, Nothing>()
    class Err<out E>(val error: E) : Result<Nothing, E>()

    override fun equals(other: Any?): Boolean {
        return when (this) {
            is Result.Ok -> other is Result.Ok<*> && this.value == other.value
            is Result.Err -> other is Result.Err<*> && this.error == other.error
        }
    }
}

// NOTES:
// Use `when` when you are only doing side-effects with no further chaining
// Use `map/mapErr` when you are transforming the Ok and Err values into different Ok and Err values and intend to further chain/process those different values
// Use `onOk/onErr` when you are side-effecting and doing further chaining

inline fun <T, U, E> Result<T, E>.map(transform: (T) -> U): Result<U, E> {
    return when (this) {
        is Ok -> Ok(transform(value))
        is Err -> this
    }
}

inline fun <T, E, F> Result<T, E>.mapErr(transform: (E) -> F): Result<T, F> {
    return when (this) {
        is Ok -> this
        is Err -> Err(transform(error))
    }
}

inline fun <T, U, E> Result<T, E>.flatMap(transform: (T) -> Result<U, E>): Result<U, E> {
    return when (this) {
        is Ok -> transform(value)
        is Err -> this
    }
}

inline fun <T, E, F> Result<T, E>.flatMapErr(transform: (E) -> Result<T, F>): Result<T, F> {
    return when (this) {
        is Ok -> this
        is Err -> transform(error)
    }
}

inline fun <T, E> Result<T, E>.getOrElse(default: (E) -> T): T {
    return when (this) {
        is Ok -> value
        is Err -> default(error)
    }
}

inline fun <T, E> Result<T, E>.onOk(operation: (T) -> Unit): Result<T, E> {
    if (this is Ok) {
        operation(value)
    }

    return this
}

inline fun <T, E> Result<T, E>.onErr(operation: (E) -> Unit): Result<T, E> {
    if (this is Err) {
        operation(error)
    }

    return this
}

fun <T, E> Result<T, E>.or(other: Result<T, E>): Result<T, E> {
    return when (this) {
        is Ok -> this
        is Err -> other
    }
}

inline fun <T, U, E> U.fold(operand: Result<T, E>, op: (U, T) -> U): U {
    return when (operand) {
        is Ok -> op(this, operand.value)
        is Err -> this
    }
}

inline fun <T, E> Result<T, E>.otherwise(then: (err: Err<E>) -> T): T {
    return when (this) {
        is Ok -> value
        is Err -> then(this)
    }
}

fun <T> T.toResult() = this.toOk()
fun <T> T.toOk() = Ok(this)
fun <E> E.toErr() = Err(this)
