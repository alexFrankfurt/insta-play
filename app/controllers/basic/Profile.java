package controllers.basic;


import constants.AppConstants;
import controllers.Application;
import controllers.OAuther;
import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.entity.users.basicinfo.UserInfoData;
import org.jinstagram.exceptions.InstagramException;
import play.Routes;
import play.mvc.*;
import play.twirl.api.Html;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static play.mvc.Results.ok;
import static scala.collection.JavaConversions.asScalaBuffer;

public class Profile extends Controller {
    @Inject
    OAuther profile;

    @Inject
    AppConstants ac;

    public Result profile(){
        if (!profile.auther()){
            return ok(views.html.basic.error.render());
        }
        List<Html> list = new ArrayList<>();
        list.add(ac.Navigation().render());

//        try{
//            UserInfo userInfo = profile.instagram().getCurrentUserInfo();
//            UserInfoData userInfoData = userInfo.getData();
//            list.add(views.html.basic.profile.render(userInfoData));
//            return ok(ac.HomePage().apply(asScalaBuffer(list)));
//        } catch (InstagramException c){
//            System.out.println("userInfo" + c);
//        }
        return ok(views.html.basic.error.render());
    }


}
