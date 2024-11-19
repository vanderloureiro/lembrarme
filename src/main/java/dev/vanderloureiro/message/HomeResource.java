package dev.vanderloureiro.message;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import static java.util.Objects.requireNonNull;

@Path("")
public class HomeResource {

    private final Template page;
    private final SaveMessageService saveMessageService;

    public HomeResource(Template page, SaveMessageService saveMessageService) {
        this.page = requireNonNull(page, "page is required");
        this.saveMessageService = saveMessageService;
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
    public TemplateInstance post(@BeanParam MessageForm form) {

        saveMessageService.execute(form);
        return page.instance();
    }

}
