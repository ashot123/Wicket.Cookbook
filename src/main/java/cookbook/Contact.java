package cookbook;

import java.io.Serializable;

/**
 * Created by Ashot Karakhanyan on 05-01-2015
 */
public class Contact implements Serializable {

    public String name;
    public String email;
    public String phone;

    public Contact() {

    }

    public Contact(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


}
