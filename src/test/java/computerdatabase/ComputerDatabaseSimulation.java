package computerdatabase;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.util.concurrent.ThreadLocalRandom;

public class ComputerDatabaseSimulation extends Simulation {


    ChainBuilder search = exec(
        http("myTest")
                .get("/test")
                .check(status().is(200))
    );


    HttpProtocolBuilder httpProtocol =
        http.baseUrl("http://localhost:8080");

    ScenarioBuilder myTestScenario = scenario("myTestScenario").exec(search);

    {
        setUp(
                myTestScenario.injectOpen(rampUsers(10).during(10))
        ).protocols(httpProtocol);
    }
}
