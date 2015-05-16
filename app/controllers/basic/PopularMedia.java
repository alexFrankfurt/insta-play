package controllers.basic;


import controllers.OAuther;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

import static play.mvc.Results.ok;
import static scala.collection.JavaConversions.asScalaBuffer;

public class PopularMedia {

    @Inject
    OAuther popular;

    public Result getPopular(){
        try {
            List<MediaFeedData> mediaFeeds = popular.instagram().getPopularMedia().getData();
            return ok(views.html.basic.gallery.render("Popular Media",asScalaBuffer(mediaFeeds)));
        }catch (InstagramException c){
            System.out.println("popular " + c);
        }
        return ok(views.html.basic.error.apply());
    }

}
