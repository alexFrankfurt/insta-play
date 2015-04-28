package controllers;

import org.jinstagram.entity.relationships.RelationshipFeed;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.model.Relationship;
import play.mvc.Result;

import static play.mvc.Results.ok;
import static controllers.OAuther.instagram;

public class Subscribe {

    public static Result subscribeByTag(String userId){
        try{
            System.out.println(userId);
            RelationshipFeed feed = instagram().setUserRelationship(userId, Relationship.FOLLOW);
            System.out.println("Meta Code : " + feed.getMeta().getCode());
            System.out.println("Incoming_Status : " + feed.getData().getIncomingStatus());
            return ok(views.html.ok.render());
        }catch (InstagramException c) {
            System.out.println("follow " + c.getMessage());
        }
        return ok(views.html.error.render());
    }

}
