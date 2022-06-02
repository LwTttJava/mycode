package stream;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author luotao
 * @date 2022-3-25  14:09
 */
public class ColectorsTest {
    public static void main(String[] args) {
        //Collectors.groupingBy(People::getTeacherName)  Map<String, List<People>>

        // 按照字段字段进行分组
        Map<String, List<People>> collect = getPeopleList().stream().collect(Collectors.groupingBy(People::getTeacherName));
        collect.entrySet().stream().forEach(a-> System.out.println(a));

        // summingInt 求和
        Integer collect1 = getPeopleList().stream().collect(Collectors.summingInt(People::getAger));
        System.out.println(collect1);

        // averagingInt 求平均数
        Double collect4 = getPeopleList().stream().collect(Collectors.averagingInt(People::getAger));
        System.out.println("collect4="+collect4);

        // toConcurrentMap
        ConcurrentMap<String, String> collect2 = getPeopleList().stream().collect(Collectors.toConcurrentMap(People::getTeacherName, People::getName));
        System.out.println("collect2:"+collect2);

        //统计摘要
        IntSummaryStatistics collect3 = getPeopleList().stream().collect(Collectors.summarizingInt(People::getAger));
        long sum = collect3.getSum();
        int min = collect3.getMin();
        int max = collect3.getMax();
        double average = collect3.getAverage();
        System.out.println("sum="+sum+" max="+max+" min="+min+" average="+average);

        // 通过 Predicate 进行分组
        Map<Boolean, List<People>> collect5 = getPeopleList().stream().collect(Collectors.partitioningBy(a -> a.getAger() >= 20, Collectors.toList()));
        collect5.entrySet().stream().forEach(a-> System.out.println(a));
    }

    public static List<People> getPeopleList(){
        List<People> peopleList = new ArrayList<>();
        peopleList.add(new People("张一",18,"王老师"));
        peopleList.add(new People("张二",19,"王老师"));
        peopleList.add(new People("张三",20,"李老师"));
        peopleList.add(new People("张四",20,"李老师"));
        peopleList.add(new People("张五",18,"刘老师"));
        peopleList.add(new People("张刘",19,"刘老师"));
        return peopleList;
    }
}

