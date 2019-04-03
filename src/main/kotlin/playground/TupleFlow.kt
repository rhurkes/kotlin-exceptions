@file:Suppress("unused")

package playground

fun validateRepublishParams(parameters: Map<String, String>): Pair<RepublishParams?, String> {
    val tcin = parameters["tcin"]
    val version = parameters["version"]?.toIntOrNull()

    return when {
        tcin.isNullOrEmpty() -> Pair(null, "No Tcin provided")
        version == null -> Pair(null, "Invalid version '${parameters["version"]}'")
        else -> Pair(RepublishParams(tcin, version), "")
    }
}

data class RepublishParams(
    val tcin: String,
    val version: Int
)
