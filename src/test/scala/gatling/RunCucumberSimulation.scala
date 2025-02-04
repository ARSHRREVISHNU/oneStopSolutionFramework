package gatling

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import java.util.concurrent.Executors
import io.cucumber.core.cli.Main

class RunCucumberSimulation extends Simulation {

  val concurrentUsers = 10 // Number of concurrent users
  val totalRequests = 100  // Total number of test executions

  val executor = Executors.newFixedThreadPool(concurrentUsers)

  // Define Gatling Scenario that calls Cucumber Tests
  val scn = scenario("Run Cucumber Tests in Parallel")
    .repeat(totalRequests / concurrentUsers) {
      exec { session =>
        executor.submit(new Runnable {
          override def run(): Unit = {
            Main.run(Array(
              "--threads", concurrentUsers.toString,
              "--glue", "stepdefinitions",
              "--tags", "@weatherAPIGET",
              "src/test/resources/features"
            ), Thread.currentThread.getContextClassLoader)
          }
        })
        session
      }
    }

  // Execute the test with multiple users
  setUp(
    scn.inject(atOnceUsers(concurrentUsers))
  )
}
