package controllers;

import models.Tag;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import play.data.Form;
import play.libs.Scala;
import play.mvc.Result;
import java.util.ArrayList;
import java.util.List;

import static controllers.OAuther.instagram;
import static play.data.Form.form;
import static play.mvc.Results.ok;
import static scala.collection.JavaConversions.asScalaBuffer;

public class Test {

    private static Form<Tag> tagForm = form(Tag.class);

    public static Result get(){
        return ok(views.html.data.render(Scala.Option((Tag) null)));
    }

    public static Result post(){
        Tag tag = tagForm.bindFromRequest().get();
        String tagName = tag.tagName;
        System.out.println(tagName);
        try {
            TagMediaFeed mediaFeed = instagram().getRecentMediaTags(tagName);

            List<MediaFeedData> mediaFeeds = mediaFeed.getData();
            List<String> links = new ArrayList<String>();

            for (MediaFeedData link : mediaFeeds) {
                links.add(link.getImages().getLowResolution().getImageUrl());
            }

            return ok(views.html.list.render(asScalaBuffer(mediaFeeds)));
        }catch (Exception c){
            c.getMessage();
        }
        return ok(views.html.error.render());
    }
}
