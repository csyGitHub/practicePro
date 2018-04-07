package com.csy.javassist;

import javassist.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * ????????springbasic
 * ???????
 * ????????
 * ???????2016??02??26?? ????21:13
 *
 * @author csypc
 * @version 1.0
 */
public class Demo2 {
    //??????е???
    public static void test1() throws Exception{
        //????????1??,?????ι???????,???????SystemPath
       /* ClassPool classPool = new ClassPool();
        classPool.appendSystemPath();*/

        //??2??????ClassPool.getDefault()????????
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.csy.javassist.User");

        byte []bytes = ctClass.toBytecode();
        System.out.println(Arrays.toString(bytes));
    }

    //??????????????
    public static void test2() throws Exception{
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.csy.javassist.User");

        //??????????·?????1??,???make()????
      /*  CtMethod ctMethod = CtMethod.make("public int add(int a,int b){return a+b;};",ctClass);
        //??????????????
        ctClass.addMethod(ctMethod);
        System.out.println(ctMethod);*/

        //??????????·?????2??,??ù?????
        //???÷????????????????
        CtMethod ctMethod = new CtMethod(CtClass.intType,"add",
                new CtClass[]{CtClass.intType,CtClass.intType},ctClass);
        //???÷??????
        ctMethod.setModifiers(Modifier.PUBLIC);
        //???÷????壨????λ????????????????????????????$0???this??$1,$2???α?????????????????
        ctMethod.setBody("return $1+$2;");

        ctClass.addMethod(ctMethod);

        //??÷??????????????????
        Class clazz = ctClass.toClass();
        User user = (User)clazz.newInstance();
        Method method = clazz.getMethod("add",int.class,int.class);
        Object object = method.invoke(user, 100, 200);
        System.out.println(object);

    }

    //??????е????,?θ??????????
    public static void test3() throws Exception{
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.csy.javassist.User");

        CtMethod ctMethod = ctClass.getDeclaredMethod("sayHello", new CtClass[]{});
        //????????????
        ctMethod.insertBefore("System.out.println(\"aaa\");");
        //?????????????
        ctMethod.insertAfter("System.out.println(\"bbb\");");

        //??÷??????????????????
        Class clazz = ctClass.toClass();
        User user = (User)clazz.newInstance();
        Method method = clazz.getMethod("sayHello",null);
        method.invoke(user, null);

    }

    //????????
    public static void test4() throws Exception{
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.csy.javassist.User");

        CtField ctField = new CtField(CtClass.intType,"sex",ctClass);
        ctField.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ctField);

        //??÷??????????????????
        Class clazz = ctClass.toClass();
        Field field = clazz.getDeclaredField("sex");
        System.out.println(field.getModifiers()+":"+field.getName());

    }

    //??????????
    public static void test5() throws Exception{
        ClassPool classPool = ClassPool.getDefault();
        CtClass ctClass = classPool.get("com.csy.javassist.User");

        CtConstructor []ctConstructors = ctClass.getConstructors();
        for(CtConstructor c : ctConstructors){
            System.out.println(c);
        }
    }


    public static void main(String[] args) throws Exception{
        //test1();
        //test2();
        //test3();
        //test4();
        test5();
    }
}
