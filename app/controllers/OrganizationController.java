package controllers;

import models.Organization;
import models.OrganizationRepository;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

/**
 * Created by Erlan Ibraev on 26.08.2018.
 */
public class OrganizationController extends Controller {

    private final FormFactory formFactory;
    private final HttpExecutionContext ec;
    private final OrganizationRepository organizationRepository;

    @Inject
    public OrganizationController(FormFactory formFactory, HttpExecutionContext ec, OrganizationRepository organizationRepository) {
        this.formFactory = formFactory;
        this.ec = ec;
        this.organizationRepository = organizationRepository;
    }

    public Result index() {
        return ok(views.html.organization.render());
    }

    public CompletionStage<Result> addOrganization() {
        Organization organization = formFactory.form(Organization.class).bindFromRequest().get();
        return organizationRepository.add(organization).thenApplyAsync( o -> {
            return redirect(routes.OrganizationController.index());
        }, ec.current());
    }

    public CompletionStage<Result> getOrganizations() {
        return organizationRepository.list().thenApplyAsync(organizationStream -> {
            return ok(toJson(organizationStream.collect(Collectors.toList())));
        }, ec.current());
    }

}
