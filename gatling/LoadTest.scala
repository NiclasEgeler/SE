package minesweeper

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class PersitenceSimulation extends Simulation {

    private val httpProtocol = http
        .baseUrl("http://0.0.0.0:8080")
        .inferHtmlResources()
        .acceptHeader(
          "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8"
        )
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .upgradeInsecureRequestsHeader("1")
        .userAgentHeader("Mozilla/5.0 (X11; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/112.0")

    private val uri1       = "0.0.0.0"
    private val userAmount = 500
    private val scn = scenario("Load Scenario")
        .exec(
          http("initial_generate")
              .get("http://" + uri1 + ":8080/load")
        )
    private val scn2 = scenario("Save Scenario")
        .exec(
          http("initial_generate")
              .get("http://" + uri1 + ":8080/save")
        )

    setUp(
        scn.inject(rampUsers(userAmount).during(20.seconds)),
        scn2.inject(rampUsers(userAmount).during(20.seconds))
        ).protocols(httpProtocol)
}
