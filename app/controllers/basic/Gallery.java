package controllers.basic;

import constants.AppConstants;
import controllers.OAuther;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;
import play.mvc.Result;
import play.twirl.api.Html;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static play.mvc.Results.ok;
import static scala.collection.JavaConversions.asScalaBuffer;

public class Gallery {
    @Inject
    OAuther gallery;

    @Inject
    AppConstants ac;

    public Result gallery(){
        List<Html> contents = new ArrayList<>();
        contents.add(ac.Navigation().render());
        try{

            MediaFeed mediaFeed = gallery.instagram().getUserFeeds(null, null, 33);
            List<MediaFeedData> mediaFeedList = mediaFeed.getData();
            System.out.println(mediaFeedList.size() + " = size");
            contents.add(ac.GalleryPage().render("Gallery", asScalaBuffer(mediaFeedList)));
            return ok(ac.HomePage().apply(asScalaBuffer(contents)));
        }catch (InstagramException c){
            System.out.println("gallery " + c);
        }
        return ok(views.html.basic.error.apply());
    }


}
