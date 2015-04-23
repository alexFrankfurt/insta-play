package controllers;

import play.mvc.Controller;
import play.mvc.Result;

import static constants.Java.AuthorizationUrl;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render(AuthorizationUrl()));
    }

}
