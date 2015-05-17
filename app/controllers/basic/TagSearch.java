package controllers.basic;

import constants.AppConstants;
import controllers.OAuther;
import models.Tag;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;
import play.libs.Scala;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Html;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static scala.collection.JavaConversions.asScalaBuffer;


public class TagSearch extends Controller {

    public Result inputTag(){
        List<Html> list = new ArrayList<>();
        list.add(ac.Navigation().render());
        list.add(views.html.tagForm.render(Scala.Option((Tag) null)));
        return ok(ac.HomePage().apply(asScalaBuffer(list)));
    }

    @Inject
    OAuther search;

    @Inject
    AppConstants ac;

    public  Result searchPhotoByTag(String tagName) {
        List<Html> list = new ArrayList<>();
        list.add(ac.Navigation().render());
        try {
            TagMediaFeed tagMediaFeed = search.instagram().getRecentMediaTags(tagName);

            List<MediaFeedData> mediaList = tagMediaFeed.getData();
            MediaFeed recentMediaNextPage = search.instagram().getRecentMediaNextPage(tagMediaFeed.getPagination());
            int counter = 0;
            while (recentMediaNextPage.getPagination() != null && counter < 1) {
                mediaList.addAll(recentMediaNextPage.getData());
                recentMediaNextPage = search.instagram().getRecentMediaNextPage(recentMediaNextPage.getPagination());
                counter++;
            }
            list.add(views.html.basic.gallery.render("Search", asScalaBuffer(mediaList)));
            return ok(ac.HomePage().apply(asScalaBuffer(list)));
        } catch (InstagramException c) {
            System.out.println("find " + c.getMessage());
        }
        return ok(views.html.basic.error.render());
    }

}
