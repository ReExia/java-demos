package com.example.mvc.utils;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassesUtils {

    private ClassesUtils(){
    }

    /**
     * 获取方法参数名
     * @param cm
     * @return
     */
    protected static String[] getMethodParamNames(CtMethod cm){
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attribute = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);

        String[] paramNames = null;

        try {
            paramNames = new String[cm.getParameterTypes().length];
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0 ; i < paramNames.length ; i++){
            paramNames[i] = attribute.variableName(i + pos);
        }
        return paramNames;
    }

    /**
     * 获取方法参数名,按给定的参数类型匹配方法
     * @param clazz
     * @param method
     * @param paramTypes
     * @return
     */
    public static String[] getMethodParamNames(Class<?> clazz, String method,
                                               Class<?>... paramTypes){
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = null;
        CtMethod cm = null;

        try {
            cc = pool.get(clazz.getName());
            String[] paramTypeNames = new String[paramTypes.length];
            for (int i = 0 ; i < paramTypes.length ; i++){
                paramTypeNames[i] = paramTypes[i].getName();
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return getMethodParamNames(cm);
    }

    public static String[] getMethodParamNames(Method method){
        ClassPool pool = ClassPool.getDefault();
        CtClass cc;
        CtMethod cm = null;
        try {
            cc = pool.get(method.getDeclaringClass().getName());
            cm = cc.getDeclaredMethod(method.getName());
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return getMethodParamNames(cm);
    }

    public void test(String addd, int bww, String aaa123) {
    }

    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println(Arrays.toString(getMethodParamNames(ClassesUtils.class.getDeclaredMethod("test", String.class, int.class, String.class))));
    }

}
