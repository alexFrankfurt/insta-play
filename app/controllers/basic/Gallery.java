package controllers.basic;

import constants.AppConstants;
import controllers.OAuther;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

import static play.mvc.Results.ok;
import static scala.collection.JavaConversions.asScalaBuffer;

public class Gallery {
    @Inject
    OAuther gallery;

    @Inject
    AppConstants ac;

    public Result gallery(){
        try{

            MediaFeed mediaFeed = gallery.instagram().getUserFeeds(null, null, 33);
            List<MediaFeedData> mediaFeedList = mediaFeed.getData();
            System.out.println(mediaFeedList.size() + " = size");
            return ok(ac.GalleryPage().render("Gallery", asScalaBuffer(mediaFeedList)));

        }catch (InstagramException c){
            System.out.println("gallery " + c);
        }
        return ok(views.html.basic.error.apply());

    }
}
