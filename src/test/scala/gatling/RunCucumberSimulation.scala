package gatling

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import java.util.Properties

class RunCucumberSimulation extends Simulation {

  // Load properties
  val props = new Properties()
  props.load(getClass.getClassLoader.getResourceAsStream("config.properties"))
  val baseUrl = props.getProperty("base.url")
  val apiKey = props.getProperty("api.key")

  // Define HTTP Protocol
  val httpProtocol = http
    .baseUrl(baseUrl)
    .acceptHeader("application/json")

  // Define Scenario
  val scn = scenario("Load Test - Weather API")
    .repeat(100) { // Load testing with 100 requests
      exec(
        http("Get Weather Data")
          .get("")
          .queryParam("q", "Chennai")
          .queryParam("key", apiKey)
          .check(status.is(200))
      )
    }

  // Setup the load test
  setUp(
    scn.inject(atOnceUsers(10)) // 10 concurrent users
  ).protocols(httpProtocol)
}
