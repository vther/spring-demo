package com.vther.spring.samples._01.extensible.xml.authoring.intro;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 需要注意 放在 src/main/java处不成功的原因是因为xsd没编译进去，放在src/main/resources下才可以
 */
public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("_01/extensible/xml/authoring/intro/extensible-xml-authoring.xml");
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student);
    }
}
