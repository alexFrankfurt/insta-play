package models.slick;

import play.data.validation.Constraints.*;

public class User  {
    @Required
    public String name;

    @Required
    @Email
    public String email;

    @Required
    public String message;

}
