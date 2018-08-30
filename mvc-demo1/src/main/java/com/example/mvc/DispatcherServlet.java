package com.example.mvc;

import com.example.mvc.annotation.*;
import com.example.mvc.utils.ArrayUtils;
import com.example.mvc.utils.ReflectUtil;
import com.example.mvc.utils.XmlConfigurationUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class DispatcherServlet extends HttpServlet {

    //定义存储MySpringMVC配置文件
    private String contextConfigLocation;
    //定义一个线程安全的集合,需要扫描管理类
    private List<Class<?>> classesList = Collections.synchronizedList(new ArrayList<Class<?>>());
    //定义村塾实例与别名对应实例关系的集合对象
    private Map<String, Object> contextContainer = Collections.synchronizedMap(new HashMap<String,Object>());
    //定义存储访问路径与方法的映射关系的集合
    private Map<String, Object> urlMappingContext = Collections.synchronizedMap(new HashMap<String, Object>());

    public DispatcherServlet(){
        System.out.println("servlet实例化");
    }

    /**
     * 设置配置文件路径
     * @param contextConfigLocation
     */
    public void setContextConfigLocation(String contextConfigLocation){
        this.contextConfigLocation = contextConfigLocation.replace("classpath:", "");
    }

    public void init() throws ServletException{
        setContextConfigLocation(getInitParameter("contextConfigLocation"));

        try {
            //扫描配置文件指定的根包路径下面所有需要交给MySpring框架管理类
            String basePackage = XmlConfigurationUtils.readXmlBasePackage(contextConfigLocation);
            scanBasePackageAnnotationClass(basePackage);

            //把所有扫描到的类(用于完成实例类别名映射关系)
            doIoc();
            //注入
            doDI();
            //完成访问路径与对应方法映射匹配
            urlMappingToMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 完成访问路径与对应方法的映射匹配
     */
    private void urlMappingToMethod() {
        if (classesList.size() == 0){
            return;
        }

        for (int i = 0; i < classesList.size() ; i ++){
            Class<?> c1 = classesList.get(i);
            //判断是否有MyController注解
            if (c1.isAnnotationPresent(MyController.class)){
                //判断是否存在MyRequestMaping注解
                if (c1.isAnnotationPresent(MyRequestMapping.class)){
                    //获取注解配置的value值
                    MyRequestMapping requestMapping = c1.getAnnotation(MyRequestMapping.class);
                    //定义存储根路径的句柄
                    String baseUrlMapping = null;
                    if (!"".equals(requestMapping.value())){
                        baseUrlMapping = ReflectUtil.handleUrl(requestMapping.value());
                    }

                    //要求获取方法必须是public修饰的方法,并且是本类中定义的方法
                    Method[] methods = c1.getDeclaredMethods();
                    if (ArrayUtils.isNotEmpty(methods)){
                        for (Method method : methods){
                            if (method.getModifiers() == Modifier.PUBLIC && method.isAnnotationPresent(MyRequestMapping.class)){
                                MyRequestMapping myRequestMapping = method.getAnnotation(MyRequestMapping.class);
                                String methodUrlMapping = baseUrlMapping;

                                if (!"".equals(myRequestMapping.value())){
                                    String url = ReflectUtil.handleUrl(myRequestMapping.value());
                                    methodUrlMapping = baseUrlMapping;
                                }
                                urlMappingContext.put(methodUrlMapping, method);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 扫描当前根包下面需要交给容器管理类
     * @param basePackage
     */
    private void scanBasePackageAnnotationClass(String basePackage) throws URISyntaxException {
        // 获取到本磁盘上面根包路径
        URL url = this.getClass().getClassLoader().getResource(basePackage.replace(".","/"));
        // 创建当前这个路径对应文件对象
        File file = new File(url.toURI());
        // 获取当前路径下面所有的子文件
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File childFile) {
                if (childFile.isDirectory()) {
                    try {
                        scanBasePackageAnnotationClass(basePackage+"."+childFile.getName());
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (childFile.getName().endsWith(".class")) {
                        String className = childFile.getName().replace(".class","");
                        try {
                            Class<?> aClass = this.getClass().getClassLoader().loadClass((basePackage + "." + className));
                            // 只需要使用@MyRepository,@MyService, @MyController这些类才交给容器管理
                            if (aClass.isAnnotationPresent(MyRepository.class)
                                    || aClass.isAnnotationPresent(MyService.class)
                                    || aClass.isAnnotationPresent(MyController.class)) {
                                // 把需要管理的类的Class对象全部存储起来
                                classesList.add(aClass);
                            }
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return false;
            }
        });
    }


    /**
     * 执行IOC操作
     */
    public void doIoc() throws IllegalAccessException, InstantiationException {
        if (classesList.size() == 0){
            return;
        }
        for (int i = 0 ; i < classesList.size() ; i++){
            Class<?> c1 = classesList.get(i);
            //获取到别名
            String anntationAlias = ReflectUtil.getAnnotationAlias(c1);
            //把别名与实例映射起来
            contextContainer.put(anntationAlias, c1.newInstance());
        }
    }


    /**
     * 执行DI操作
     */
    public void doDI() throws IllegalAccessException {
        if (classesList.size() == 0){
            return;
        }

        //遍历每个类
        for (int i = 0; i < classesList.size() ; i++){
            Class<?> c1 = classesList.get(i);
            //获取别名
            String annotationAlias = ReflectUtil.getAnnotationAlias(c1);
            //获取需要注入依赖对象实例
            Object annotationInstance = contextContainer.get(annotationAlias);
            //获取当前类下面所有定义属性
            Field[] fields = c1.getDeclaredFields();

            if (ArrayUtils.isNotEmpty(fields)){
                for (Field field : fields) {
                    if (field.isAnnotationPresent(MyAutowired.class)){
                        MyAutowired myAutowired = field.getAnnotation(MyAutowired.class);
                        //定义变量获取需要装配的依赖对象
                        Object injectionObj = null;

                        if (!"".equals(myAutowired.value())){
                            String instanceName = myAutowired.value();
                            //按照名称注入依赖对象
                            injectionObj = contextContainer.get(instanceName);
                        }else {
                            //默认按照类型注入对应的依赖对象
                            //1.获取字段的类型
                            Class<?> fieldType = field.getType();
                            //2.获取到容器中所有装配实例的集合
                            Collection values = contextContainer.values();
                            Iterator iterator = values.iterator();
                            //3.遍历找到与当前字段类型匹配的对象
                            while (iterator.hasNext()){
                                //获取ioc容器中对应实例
                                Object obj = iterator.next();
                                if (fieldType.isAssignableFrom(obj.getClass())){
                                    //确定对应类型的注入对象
                                    injectionObj = obj;
                                    break;
                                }
                            }
                        }
                        field.setAccessible(true);
                        field.set(annotationInstance, injectionObj);
                    }
                }
            }
        }
    }

    /**
     * 定义根据容器key获取对应实例
     * @param beanName
     * @return
     */
    public Object getContextBean(String beanName){
        return contextContainer.get(beanName);
    }





}
