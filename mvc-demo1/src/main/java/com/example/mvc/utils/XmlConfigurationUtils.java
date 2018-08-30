package com.example.mvc.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

public class XmlConfigurationUtils {

    public static String readXmlBasePackage(String configPath) throws Exception{
        SAXReader reader = new SAXReader();
        InputStream inputStream = XmlConfigurationUtils.class.getClassLoader().getResourceAsStream(configPath);

        //获取XML文档对象
        Document document = reader.read(inputStream);
        inputStream.close();

        //获取配置文件的根元素
        Element rootElement = document.getRootElement();

        //获取到component-scan元素
        Element scanElement = rootElement.element("component-scan");
        //获取base-package配置的属性值
        String basePackageString = scanElement.attributeValue("base-package");

        return basePackageString;
    }

    public static void main(String[] args) throws Exception {
        String s = readXmlBasePackage("applicationContext.xml");
        System.out.println(s);
    }

}
