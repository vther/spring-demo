package com.vther.spring.samples._01.extensible.xml.authoring.intro;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

public class StudentBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class getBeanClass(Element element) {
        return Student.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        bean.addPropertyValue("boy", Boolean.valueOf(element.getAttribute("boy")));
        bean.addPropertyValue("name", element.getAttribute("name"));
    }

}