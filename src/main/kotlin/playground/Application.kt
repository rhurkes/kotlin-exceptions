@file:Suppress("RedundantLambdaArrow", "UNUSED_VARIABLE")

package playground

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.Jetty
import org.http4k.server.asServer

data class Event(
    val time1: String,
    val time2: String,
    val time3: String,
    val time4: String,
    val time5: String,
    val details: String
)

fun main() {
    val mapper = ObjectMapper().registerModule(KotlinModule())
    val ts = "2011-12-03T10:15:30Z"
//    val ts = "2011-12-03T10:15:30"
//    val ts = "1553537337000
//    val ts = "badvalue"

    val app = { _: Request ->
        val events = emptyList<Event>().toMutableList()

        for (i in 1..20) {
//            val event = Event(
//                parseDatetime(ts),
//                parseDatetime(ts),
//                parseDatetime(ts),
//                parseDatetime(ts),
//                parseDatetime(ts),
//                "This is a big chunk of text for the details - and the contents don't really matter"
//            )
//            events.add(event)
        }

        val serializedEvent = mapper.writeValueAsString(events)

        Response(OK).body(serializedEvent)
    }

    app.asServer(Jetty(9000)).start()
}
