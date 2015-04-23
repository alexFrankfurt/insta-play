package controllers;

import play.*;
import play.mvc.*;
import static constants.Java.AutorithationUrl;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render(AutorithationUrl()));
    }

}
