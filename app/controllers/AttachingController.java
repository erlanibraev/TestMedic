package controllers;

import models.AttachingRepository;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

/**
 * Created by Erlan Ibraev on 26.08.2018.
 */
public class AttachingController extends Controller {

    private final FormFactory formFactory;
    private final HttpExecutionContext ec;
    private final AttachingRepository attachingRepository;

    @Inject
    public AttachingController(FormFactory formFactory, HttpExecutionContext ec, AttachingRepository attachingRepository) {
        this.formFactory = formFactory;
        this.ec = ec;
        this.attachingRepository = attachingRepository;
    }

    public Result index() {
        return ok(views.html.attaching.render());
    }
}
