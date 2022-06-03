package test.reflectdemo;


import stream.People;

import java.lang.reflect.*;

/**
 * @author luotao
 * @date 2022-3-8  16:56
 */
public class ReflectTest<T> {

    public static void main(String[] args) throws Exception {
        testReflectClass();
    }

    public static void testReflectClass() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {

        Class<?> pClass = Class.forName("com.lt.test.clonedemo.People");
        Constructor<?> constructor = pClass.getConstructor(String.class, int.class);
        People zs = (People)constructor.newInstance("张三", 18);
        System.out.println("张三是："+pClass.isInstance(zs));

        /**
         * 使用反射读取使用属性;需要调用 setAccessible(true)
         */
        // 1、读取特定的字段
        Field name = pClass.getDeclaredField("name");
        name.setAccessible(true);
        name.get(zs);
        // 2、读取Class对象的所有字段
        for (Field field : pClass.getDeclaredFields()) {
            field.setAccessible(true);
            System.out.println(field.getName()+":"+field.get(zs));
        }

        Class<People> c = People.class;
        // 获取方法
        Method[] declaredMethods = c.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            declaredMethod.getModifiers();
            System.out.println(declaredMethod);
            System.out.println("方法名："+declaredMethod.getName());
            System.out.println("参数：");
            Parameter[] parameters = declaredMethod.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println( parameter.getName()+"类型："+ parameter.getType());
            }
            System.out.println("参数类型:");
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                System.out.println(parameterType.getName());
            }

        }
    }

    /**
     *  使用类对象，创建实例对象
     *  1、获取 Constructor
     *  2、调用 Constructor 的 newInstance()方法
     * @param clazz
     */
    public void useRelectClassNewInstanceTest(Class<T> clazz){
        try {
            Constructor<T> constructor = clazz.getConstructor(String.class, int.class);
            T person = constructor.newInstance("zs", 18);
            System.out.println(person);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}






