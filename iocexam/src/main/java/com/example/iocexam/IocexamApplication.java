package com.example.iocexam;

import com.example.iocexam.config.UserConfig;
import com.example.iocexam.controller.UserController;
import com.example.iocexam.domain.User;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootApplication
public class IocexamApplication {

  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);

    UserController controller = context.getBean(UserController.class);

    //이렇게 실행하면~~~    user+"의 정보가 잘 저장되었습니다."  이렇게 출력될 수 있도록!!!   Bean을 등록해 보세요.

    System.out.println("============= joinUser =============");
    controller.joinUser("kang", "carami@gmail.com", "1111");
    controller.joinUser("eun2ce", "joeun2ce@gmail.com", "changeme");
    controller.joinUser("tiger", "tiger@naver.com", "roar");

    System.out.println("============= getUsers =============");
    List<User> users = controller.getUsers();
    users.forEach(System.out::println);

    System.out.println("============= getUser =============");
    User tiger = controller.getUserByEmail("tiger@naver.com");
    System.out.println(tiger.toString());
  }

}