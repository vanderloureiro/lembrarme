package dev.vanderloureiro.health;

import io.quarkus.logging.Log;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/wake-up")
public class WakeUpResource {

    @GET
    public void execute() {
        Log.info("Application is up");
    }
}
