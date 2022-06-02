package stream;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author luotao
 * @date 2022-3-24  23:03
 */
public class StreamTest {

    /**
     *  链式执行函数
     * @param processors
     * @return
     */
    static String chain(String input, Function<String, String>... processors) {
        Function<String, String> reduce = Stream.of(processors).map(p -> (Function<String, String>) p).reduce(Function.identity(), Function::andThen);
        //input->reduce.apply(input);
        return  Stream.of(processors)
                //.map(p -> (Function<String, String>) p)
                //.reduce(Function.identity(), Function::andThen)
                /**
                 * T reduce(T identity, BinaryOperator<T> accumulator)
                 * identity 两个作用，一个设置初始值，二是指定类型
                 * Function.identity() 指定类型     hello  ->s1 ->s2 ->s3 ->s4
                 * (s)->s+"->init "，自己指定最开始执行的函数    hello ->init ->s1 ->s2 ->s3 ->s4
                 *  Optional<T> reduce(BinaryOperator<T> accumulator)
                 *  需要后面Optional<T>.get() 获取value
                 */
                //.reduce(Function::andThen)
                .reduce((s)->s+"->init ",Function::andThen)
                .apply(input);

    }

    /**
     *
     * @param mapStream
     * @param merger 合并时重复key处理方式
     * @return
     */
    public static Map<String,String> mergeMaps(Stream<Map<String,String>> mapStream, BinaryOperator<String> merger)
    {
        BinaryOperator<String> mergerMethod = Optional.ofNullable(merger).orElse((u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        });
        //(u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); }
        return mapStream // 每个元素是Map
                .map(Map::entrySet)   //每个元素变为entry     // 元素集合
                .flatMap(Collection::stream)    // 将每个元素集合 合成一个流
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, mergerMethod));
    }

    public static Map<String,List<String>> mergeMaps02mergeMap(Stream<Map<String,List<String>>> mapStream, BinaryOperator<List<String>> merger){
        BinaryOperator<List<String>> mergerMethod = Optional.ofNullable(merger).orElse((u, v) -> {
            u.addAll(v);
            return u;
        });
        Map<String, List<String>> collect = mapStream
                .map(Map::entrySet)
                .flatMap(Collection::stream) //
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, mergerMethod));
        return collect;
    }

    public static void main(String[] args) {
        List<Integer> list = getList();

        //testSortedLimitSkip(list);

        //testFilter(list);

        //testReduceConcatOf(list);
        Map<String,String> map1 = new HashMap<>();
        map1.put("key1","value1");
        map1.put("key2","value2");
        map1.put("key3","value3");
        map1.put("key4","value4");
        Map<String,String> map2 = new HashMap<>();
        map2.put("key1","value1");
        map2.put("key6","value2");
        map2.put("key7","value3");
        map2.put("key8","value4");
        BinaryOperator<String> merger = (s, s2) ->{
            if (s==s2){
                return s2;
            }
            throw new IllegalStateException(String.format("Duplicate key %s", s));
        };
        Map<String, String> params = mergeMaps(Stream.of(map1, map2), merger);
        //params.forEach((key,value)-> System.out.println("key："+key+" value:"+value));

        Map<String,List<String>> mapList1 = new HashMap<>();
        Map<String,List<String>> mapList2 = new HashMap<>();
        mapList1.put("key1",new ArrayList(Arrays.asList("1", "2", "3")));
        mapList1.put("key2",new ArrayList(Arrays.asList("4", "5", "6")));
        mapList2.put("key1",new ArrayList(Arrays.asList("3", "2", "1")));
        mapList2.put("key2",new ArrayList(Arrays.asList("6", "5", "4")));


        Map<String, List<String>> stringListMap = mergeMaps02mergeMap(Stream.of(mapList1, mapList2), null);
        //stringListMap.forEach((key,value)-> System.out.println("key："+key+" value:"+value));
        List<String> collect = stringListMap.entrySet()
                .stream()
                .map(e -> e.getValue())
                .flatMap(List::stream)
                .collect(Collectors.toList());
        collect.forEach(a-> System.out.println(a));
    }

    static void testReduceConcatOf(List<Integer> list) {
        Optional<Integer> reduce = list.stream().reduce((t1, t2) -> t1 + t2);
        list.stream().reduce(Integer::sum);
        System.out.println("reduce"+reduce.get());

        Function<String, String> f1 = (s1)-> s1+"->s1 ";
        Function<String, String> f2 = (s1)-> s1+"->s2 ";
        Function<String, String> f3 = (s1)-> s1+"->s3 ";
        Function<String, String> f4 = (s1)-> s1+"->s4 ";

        System.out.println(chain("hello ", f1, f2, f3, f4));

        Stream<Function<String, String>> f11 = Stream.of(f1, f2);
        Stream<Function<String, String>> f31 = Stream.of(f3, f4);
        System.out.println(Stream.concat(f11, f31).skip(1).reduce(Function::andThen).get().apply(" hello haha  "));
    }

    static void testFilter(List<Integer> list) {
        Predicate<Integer> o = t1 -> t1 % 2 == 0;
        Predicate<Integer> b = t1 -> t1 > 20;
        // 判断集合是否存在偶数
        /**
         * anyMatch 集合有一个满足就可以
         * anyMatch 集合所有元素都要满足
         */
        System.out.println(list.stream().sorted((t1,t2)->t2-t1).peek((a)-> System.out.print(a+" ")).collect(Collectors.toList()));
        List<Integer> collect1 = list.stream()
                .filter(b)
                .collect(Collectors.toList());
        System.out.println("filter 后："+collect1);
    }

    /**
     * 所有数据排序后  70 39 20 10 9 8 6 5 4 3 2 1
     * sorted之后     70 39 20 10 9 8 6 5 4
     * skip之后       9 8 6 5 4
     * limit之后      9 8 6 5 4
     * 由此可见，如果有skip和limit 会先计算个数，sorted（4+5) 取前9个
     *
     */
    private static void testSortedLimitSkip(List<Integer> list) {
        /**
         *  list.stream()等价于
         * StreamSupport.stream(Spliterators.spliterator(list, 0),false);
         */
        List<Integer> collect = StreamSupport.stream(Spliterators.spliterator(list, 0), false)
                //.limit(5)
                .sorted((t1,t2)->t2-t1)
                .peek((a)-> System.out.print("s"+a+" "))
                .skip(4)
                .peek((a)-> System.out.print("k"+a+" "))
                .limit(5)
                .peek((a)-> System.out.print("l"+a+" "))
                .collect(Collectors.toList());
        System.out.println();
    }


    public static List<Integer> getList(){
        Integer[] result = {6,9,2,8,10,4,39,20,5,70,1,3};
        return Arrays.asList(result);
    }
}
