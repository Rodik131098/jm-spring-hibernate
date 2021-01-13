package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user = new User("User1", "lastname1", "user1@mail.ru");
      User user2 = new User("User2", "lastname2", "user2@mail.ru");
      User user3 = new User("User3", "lastname3", "user3@mail.ru");

      user.setCar(new Car("model1", "series1"));
      user2.setCar(new Car("model2", "series2"));
      user3.setCar(new Car("model3", "series3"));

      userService.add(user);
      userService.add(user2);
      userService.add(user3);

      List<User> users = userService.listUsers();

      for (User usr : users) {
         System.out.println("Id = "+usr.getId());
         System.out.println("First Name = "+usr.getFirstName());
         System.out.println("Last Name = "+usr.getLastName());
         System.out.println("Email = "+usr.getEmail());
         System.out.println("Car = "+usr.getCar());
         System.out.println();
      }

      User userByCar = userService.getUserByCar(new Car("model1", "series1"));
      System.out.println(userByCar);

      context.close();
   }
}
