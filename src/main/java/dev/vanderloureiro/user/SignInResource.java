package dev.vanderloureiro.user;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("signin")
public class SignInResource {

    @Inject
    private Template signin;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index() {
        return signin.instance();
    }
}
