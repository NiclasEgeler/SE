package minesweeper

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class BasicSimulation extends Simulation {

  private val httpProtocol = http
    .baseUrl("http://0.0.0.0:8080")
    .inferHtmlResources()
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (X11; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/112.0")
  
  
  private val uri1 = "0.0.0.0"
  private val userAmount = 500
  private val scn = scenario("BasicSimulation")
    .exec(
      http("initial_generate")
        .get("http://" + uri1 + ":8081/generate")
    )
    .pause(1)
    .exec(
      http("open_1_cell")
        .get("/open/1/1")
    )
    .pause(1)
    .exec(
      http("flag_1_cell")
        .get("/flag/2/2")
    )
    .pause(1)
    .exec(
      http("undo")
        .get("/undo")
    )
    .pause(1)
    .exec(
      http("undo")
        .get("/undo")
    )
    .pause(1)
    .exec(
      http("solve_grid")
        .get("/openGrid")
    )
    .pause(1)
    .exec(
      http("undo")
        .get("/undo")
    )
    setUp(scn.inject(stressPeakUsers(userAmount).during(20.seconds))
  ).protocols(httpProtocol)
}

