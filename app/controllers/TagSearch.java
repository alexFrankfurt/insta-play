package controllers;

import models.Tag;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;
import play.libs.Scala;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static scala.collection.JavaConversions.asScalaBuffer;


public class TagSearch extends Controller {

    public static Result inputTag(){
        return ok(views.html.tagForm.render(Scala.Option((Tag) null)));
    }

    @Inject
    OAuther oa;

    public  Result findPhotoByTag(String tagName) {
        try {
            TagMediaFeed mediaFeed = oa.instagram().getRecentMediaTags(tagName);

            List<MediaFeedData> mediaFeeds = mediaFeed.getData();
            List<String> links = new ArrayList<String>();
            for (MediaFeedData link : mediaFeeds){
                links.add(link.getImages().getLowResolution().getImageUrl());
            }

            return ok(views.html.list.render(asScalaBuffer(mediaFeeds)));
        } catch (InstagramException c) {
            System.out.println("find " + c.getMessage());
        }
        return ok(views.html.error.render());
    }

}
