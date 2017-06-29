package com.vther.spring.samples.xml;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 需要注意 放在 src/main/java处不成功的原因是因为xsd没编译进去，放在src/main/resources下才可以
 */
public class ExtensibleXmlAuthoringMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("extensible-xml-authoring.xml");
        SimpleDateFormat format = (SimpleDateFormat) applicationContext.getBean("defaultDateFormat");
        System.out.println(format.format(new Date()));
    }
}
