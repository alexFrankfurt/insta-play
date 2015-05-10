package controllers.basic;

import controllers.OAuther;
import models.Tag;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.users.feed.MediaFeed;
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

    public Result inputTag(){
        return ok(views.html.tagForm.render(Scala.Option((Tag) null)));
    }

    @Inject
    OAuther search;

    public  Result searchPhotoByTag(String tagName) {
        try {
            TagMediaFeed tagMediaFeed = search.instagram().getRecentMediaTags(tagName);

            List<MediaFeedData> mediaList = tagMediaFeed.getData();
            MediaFeed recentMediaNextPage = search.instagram().getRecentMediaNextPage(tagMediaFeed.getPagination());
            int counter = 0;
            while (recentMediaNextPage.getPagination() != null && counter < 4) {
                mediaList.addAll(recentMediaNextPage.getData());
                recentMediaNextPage = search.instagram().getRecentMediaNextPage(recentMediaNextPage.getPagination());
                counter++;
            }
            return ok(views.html.basic.galary.render("Search",asScalaBuffer(mediaList)));
        } catch (InstagramException c) {
            System.out.println("find " + c.getMessage());
        }
        return ok(views.html.basic.error.render());
    }

}
