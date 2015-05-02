package controllers;

import org.jinstagram.entity.likes.LikesFeed;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static play.mvc.Results.ok;
import static scala.collection.JavaConversions.asScalaBuffer;

public class LikeMedia {
    @Inject
    OAuther like;

    public Result likeByTag(String tagName){
        try {
            TagMediaFeed mediaFeed = like.instagram().getRecentMediaTags(tagName);

            List<MediaFeedData> mediaFeeds = mediaFeed.getData();

            List<String> mediaIds = new ArrayList<>();
            List<String> links = new ArrayList<>();

            for (MediaFeedData mediaId : mediaFeeds){
                mediaIds.add(mediaId.getId());
                links.add(mediaId.getLink());
                LikesFeed feed = like.instagram().setUserLike(mediaIds.get(mediaIds.size() - 1));
            }

            return ok(views.html.listLikedMedia.render(asScalaBuffer(links)));
        } catch (InstagramException c) {
            System.out.println("find " + c.getMessage());
        }
        return ok(views.html.error.render());
    }
}
