package controllers;

import com.google.inject.Inject;
import play.mvc.Controller;
import play.mvc.Result;
import constants.App;

public class Application extends Controller {

    @Inject App ca;

    public Result index() {
        return ok(views.html.index.render(ca.AuthorizationUrl()));
    }

}