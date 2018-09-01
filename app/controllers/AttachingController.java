package controllers;

import models.*;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.sql.Date;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import static play.libs.Json.toJson;

/**
 * Created by Erlan Ibraev on 26.08.2018.
 */
public class AttachingController extends Controller {

    private final FormFactory formFactory;
    private final HttpExecutionContext ec;
    private final AttachingRepository attachingRepository;
    private final PersonRepository personRepository;
    private final OrganizationRepository organizationRepository;

    @Inject
    public AttachingController(FormFactory formFactory,
                               HttpExecutionContext ec,
                               AttachingRepository attachingRepository,
                               PersonRepository personRepository,
                               OrganizationRepository organizationRepository
    ) {
        this.formFactory = formFactory;
        this.ec = ec;
        this.attachingRepository = attachingRepository;
        this.personRepository = personRepository;
        this.organizationRepository = organizationRepository;
    }

    public Result index() {
        return ok(views.html.attaching.render());
    }

    public CompletionStage<Result> setAttach(Long personId) {
        Person person = personRepository.findOne(personId);
        return organizationRepository.list().thenApplyAsync( organizationStream -> {
            return ok(views.html.attach.render(person, organizationStream.collect(Collectors.toList())));
        }, ec.current());
    }

    public CompletionStage<Result> attach() {
        Attaching attaching = formFactory.form(Attaching.class).bindFromRequest().get();
        attaching.status = "ATTACHED";
        return attachingRepository.update(attaching).thenApplyAsync( a -> {
            return redirect(routes.AttachingController.index());
        }, ec.current());
    }

    public CompletionStage<Result> addAttaching() {
        Attaching attaching = formFactory.form(Attaching.class).bindFromRequest().get();
        attaching.created = new Date(new java.util.Date().getTime());
        attaching.status="REQUEST";
        return attachingRepository.add(attaching).thenApplyAsync(a -> {
            return redirect(routes.AttachingController.index());
        }, ec.current());
    }

    public CompletionStage<Result> getAttachings() {
        return attachingRepository.list().thenApplyAsync( attachingStream -> {
            return ok(toJson(attachingStream.collect(Collectors.toList())));
        }, ec.current());
    }
}
