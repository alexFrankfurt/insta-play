package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import constants.AppConstants;

import javax.inject.Inject;

public class Application extends Controller {

    @Inject
    AppConstants ca;

    public Result index() {
        return ok(views.html.index.render(ca.AuthorizationUrl()));
    }

}
