package dev.vanderloureiro.message;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestForm;

import java.util.Map;

import static java.util.Objects.requireNonNull;

@Path("/")
public class HomeResource {

    private final Template page;

    public HomeResource(Template page) {
        this.page = requireNonNull(page, "page is required");
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {

        return page.instance();
    }

    @POST
    @Path("/create")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public TemplateInstance post(
            @FormParam("body") String body,
            @FormParam("email") String email,
            @FormParam("date") String date,
            @FormParam("recurrence") String recurrence) {

        return page.instance();
    }

}
