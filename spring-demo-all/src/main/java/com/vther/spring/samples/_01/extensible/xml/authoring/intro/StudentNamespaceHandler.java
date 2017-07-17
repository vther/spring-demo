package com.vther.spring.samples._01.extensible.xml.authoring.intro;



import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class StudentNamespaceHandler extends NamespaceHandlerSupport {

    public void init() {
        registerBeanDefinitionParser("student", new StudentBeanDefinitionParser());
    }

}