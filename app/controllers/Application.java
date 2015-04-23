package controllers;

import play.*;
import play.mvc.*;
import static controllers.OAuther.authorizationUrl;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render(authorizationUrl()));
    }

}
