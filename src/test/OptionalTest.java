package test;

import java.util.*;

/**
 * @author luotao
 * @date 2022-3-24  14:54
 */
public class OptionalTest {

    /**
     * 得到一个Optional 可以有两种方式
     * 1、Optional.of 如果传入值为null，会报NPE
     * 2、Optional.ofNullable 可以接受null值
     */
    public static void testInitOptionalValue(){
        List<String> list = null;
        Optional.of(list);
        Optional.ofNullable(list);
    }

    /**
     * 测试 跟获取值相关的方法
     */
    public static void testOptionalGetValueMethod(){
        List<String> list = null;
        Optional<List<String>> optionalList = Optional.ofNullable(list);
        // 此时直接调用get()方法 或抛出 java.util.NoSuchElementException: No value present 异常
        optionalList.get();
        // 可以调用 isPresent() 判断是否存在值
        if(optionalList.isPresent()){
            optionalList.get();
        }
        //  Opthinal 存在元素 会执行 ifPresent 括号里面的代码
        optionalList.ifPresent((temp)-> System.out.println(temp));

        // 当Optional中value == null时，可以使用 orElse 复制
        // orElse 不支持 lambda  ; orElseGet 是传 lambda表达式
        optionalList.orElse(Arrays.asList("1,2,3,4,5".split(",")));
        optionalList.orElseGet(()->Arrays.asList("1,2,3,4,5".split(",")));

        // 不存在值 抛异常
        optionalList.orElseThrow(ArithmeticException::new);
        optionalList.orElseThrow(()-> new ArithmeticException());
    }


    public static void testOptionalFilter(){
        // 初始化一个list
        List<String> strList =new ArrayList<>();
        for (int i=0;i<10;i++){
            strList.add(UUID.randomUUID().toString());
        }
        // 使用filter方法
        Optional<List<String>> filterResultOptional = Optional.ofNullable(strList).filter(temp -> {
            System.out.println(temp);
            return temp.size() == 8;
        });
    }


    public static void main(String[] args) {
        testInitOptionalValue();
        testOptionalGetValueMethod();
        testOptionalFilter();
    }
}
