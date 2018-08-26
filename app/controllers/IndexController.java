package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Erlan Ibraev on 26.08.2018.
 */
public class IndexController extends Controller {


    public Result index() {
        return ok(views.html.index.render());
    }

}
