@file:Suppress("unused", "CanSealedSubClassBeObject", "UNUSED_PARAMETER", "UNUSED_VARIABLE")

package playground

enum class Color(string: String) {
    Red("burgundy"),
    Blue("light"),
    Green("pale")
}

sealed class Colors {
    data class Red(val hue: String) : Colors()
    object Green : Colors()
    class Blue {
        class LightBlue: Colors()
    }
}

fun makeColors() {
    val red = Colors.Red("burgundy")
    val green = Colors.Green
    val blue = Colors.Blue.LightBlue()
}
