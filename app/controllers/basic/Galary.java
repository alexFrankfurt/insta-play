package controllers.basic;

import controllers.OAuther;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

import static play.mvc.Results.ok;
import static scala.collection.JavaConversions.asScalaBuffer;

public class Galary {

    @Inject
    OAuther galary;

    public Result galary(){
        try{

            MediaFeed mediaFeed = galary.instagram().getUserFeeds(null, null, 33);
            List<MediaFeedData> mediaFeedList = mediaFeed.getData();
            System.out.println(mediaFeedList.size() + " = size");
            return ok(views.html.basic.galary.render("Galary",asScalaBuffer(mediaFeedList)));

        }catch (InstagramException c){
            System.out.println("galary " + c);
        }
        return ok(views.html.basic.error.apply());

    }
}
