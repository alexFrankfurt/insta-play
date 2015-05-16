package controllers;

import play.mvc.Result;
import play.twirl.api.Html;
import scala.Some;

import java.util.ArrayList;
import java.util.List;

import static play.mvc.Results.ok;
import static scala.collection.JavaConversions.asScalaBuffer;

public class TestJ {
    public Result testF() {
        List<Html> listStyles = new ArrayList<>();
        List<Html> listContents = new ArrayList<>();
        views.html.test.render("Hello!",
                Some.apply(asScalaBuffer(listStyles)),
                Some.apply(asScalaBuffer(listContents)));
        return ok();
    }
}
