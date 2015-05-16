package controllers.map;


import constants.AppConstants;
import controllers.OAuther;
import org.jinstagram.entity.common.Location;
import org.jinstagram.entity.locations.LocationSearchFeed;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.exceptions.InstagramException;
import play.mvc.Result;
import play.twirl.api.Html;

import javax.inject.Inject;
import javax.swing.text.html.HTML;

import java.util.ArrayList;
import java.util.List;

import static play.mvc.Results.ok;
import static scala.collection.JavaConversions.asScalaBuffer;


public class Map {

    @Inject
    OAuther location;

    @Inject
    AppConstants ac;

    public Result getLocation(){
        List<Html> list = new ArrayList<>();
        list.add(ac.Navigation().render());
        list.add(views.html.map.location.render());
        return ok(ac.HomePage().apply(asScalaBuffer(list)));
    }

    public Result showPhoto(String lat,String lng){
        String latitude = lat;
        String longitude = lng;
        try {
            LocationSearchFeed searchFeed = location.instagram().searchLocation(
                    Double.parseDouble(latitude),
                    Double.parseDouble(longitude));

            List<String> locationIds = new ArrayList<String>();
            for (Location location : searchFeed.getLocationList()) {
//                System.out.println("id : " + location.getId());
                locationIds.add(location.getId());
            }
            List<MediaFeedData> mediaFeeds = new ArrayList<MediaFeedData>();
            for (String locationId : locationIds) {
                MediaFeed mediaFeed = location.instagram().getRecentMediaByLocation(locationId);
                mediaFeeds.addAll(mediaFeed.getData());
                if (mediaFeeds.size() > 20) break;
            }

//            System.out.println(mediaFeeds.size() + "sie");
            return ok(views.html.list.render(asScalaBuffer(mediaFeeds))
            );
        }catch (InstagramException e){
            System.out.println("location " + e);
        }
        return ok(views.html.basic.error.apply());
    }
}
