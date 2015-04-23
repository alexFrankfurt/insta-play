package controllers;

import models.Tag;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import play.data.Form;
import play.libs.Scala;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

import static controllers.OAuther.instagram;
import static play.data.Form.form;
import static scala.collection.JavaConversions.asScalaBuffer;

public class TagSearch extends Controller {
//
//    private static Form<Tag> tagForm = form(Tag.class);


    public static Result findPhotoByTag(String tagName) {
//        Tag tag = tagForm.bindFromRequest().get();
//        System.out.println(tag);
//        String tagName = tag.tagName;

        try {
//            String tagName = "polotsk";
            TagMediaFeed mediaFeed = instagram().getRecentMediaTags(tagName);

            List<MediaFeedData> mediaFeeds = mediaFeed.getData();
            List<String> links = new ArrayList<String>();
            for (MediaFeedData link : mediaFeeds){
                links.add(link.getImages().getLowResolution().getImageUrl());
            }
            return ok(views.html.list.render(asScalaBuffer(mediaFeeds)));
        } catch (Exception c) {
            c.getStackTrace();
        }
        return ok(views.html.error.render());
    }

    public static Result inputTag(){
        return ok(views.html.data.render(Scala.Option((Tag) null)));
    }


}
