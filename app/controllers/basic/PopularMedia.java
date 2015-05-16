package controllers.basic;


import constants.AppConstants;
import controllers.OAuther;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;
import play.mvc.Result;
import play.twirl.api.Html;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static play.mvc.Results.ok;
import static scala.collection.JavaConversions.asScalaBuffer;

public class PopularMedia {

    @Inject
    OAuther popular;

    @Inject
    AppConstants ac;

    public Result getPopular(){
        List<Html> list = new ArrayList<>();
        list.add(ac.Navigation().render());

        try {
            List<MediaFeedData> mediaFeeds = popular.instagram().getPopularMedia().getData();
            list.add(views.html.basic.gallery.render("Popular Media",asScalaBuffer(mediaFeeds)));
            return ok(ac.HomePage().apply(asScalaBuffer(list)));
        }catch (InstagramException c){
            System.out.println("popular " + c);
        }
        return ok(views.html.basic.error.apply());
    }

}
