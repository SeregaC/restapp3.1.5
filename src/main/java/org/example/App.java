package org.example;

import org.example.config.MyConfig;
import org.example.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App 
{
    public static void main( String[] args ) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

        List<User> allUser = communication.showAllUser();
        System.out.println(allUser);

        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte)37);
        communication.saveUser(user);

        user.setId(3L);
        user.setName("Thomas");
        user.setLastName("Shelby");
        communication.update(user);

        communication.delete(user);

    }
}
