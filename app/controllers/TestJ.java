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
        listStyles.add(views.html.drytest.style1.render());
        listStyles.add(views.html.drytest.style2.render());
        List<Html> listContents = new ArrayList<>();
//        listContents.add(views.html.drytest.content1.render());
//        listContents.add(views.html.drytest.content2.render());
        return ok(
            views.html.common.render("Hello!",
                    Some.apply(asScalaBuffer(listStyles)),
                    Some.apply(asScalaBuffer(new ArrayList<>())),
                    Some.apply(asScalaBuffer(new ArrayList<>()))));
    }
}
