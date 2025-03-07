package com.escass.shop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);

//        System.out.println(new Friend("park", 20).name);
//        System.out.println(new Friend().name);

//        var object = new HomeWork();
//        object.plusAge();
//        System.out.println(object.getAge());
//        object.setAge(-10);
//        System.out.println(object.getAge());
    }

}

//@Getter
//@Setter
//class HomeWork {
//    private String name;
//    private int age;
//    public void plusAge() {
//        this.age += 1;
//    }
//    public void setAge(int age) {
//        if (age < 0 || age >= 100) {
//            return;
//        }
//        this.age = age;
//    }
//}

//class Friend {
//    String name = "kim";
//    int age = 20;
//
//    Friend() {
//
//    }
//
//    Friend(String name, int age) { // constructor 문법
//        this.name = name;
//        this.age = age;
//    }
//}
