package controllers;

import org.jinstagram.entity.relationships.RelationshipFeed;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.model.Relationship;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Results.ok;

public class Subscribe {

    @Inject
    OAuther sub;

    public Result followByTag(String userId){
        try{
            System.out.println(userId);
            RelationshipFeed feed = sub.instagram().setUserRelationship(userId, Relationship.FOLLOW);
            System.out.println("Meta Code : " + feed.getMeta().getCode());
            System.out.println("Incoming_Status : " + feed.getData().getIncomingStatus());
            return ok(views.html.basic.ok.render());
        }catch (InstagramException c) {
            System.out.println("follow " + c.getMessage());
        }
        return ok(views.html.basic.error.render());
    }

}
