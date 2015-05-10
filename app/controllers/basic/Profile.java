package controllers.basic;


import controllers.OAuther;
import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.entity.users.basicinfo.UserInfoData;
import org.jinstagram.exceptions.InstagramException;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Results.ok;

public class Profile {
    @Inject
    OAuther profile;

    public Result profile(){
        try{
            UserInfo userInfo = profile.instagram().getCurrentUserInfo();
            UserInfoData userInfoData = userInfo.getData();

            return ok(views.html.basic.profile.render(userInfoData));
        } catch (InstagramException c){
            System.out.println("userInfo" + c);
        }
        return ok(views.html.basic.error.render());
    }
}
