package test;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

/**
 * @author luotao
 * @date 2022-3-23  15:29
 */
public class ListConcurrentAddAndRemoveTest {

    public static void main(String[] args) {

        List<String> list = getList();
        for (int i = 1; i <= list.size(); i++) {
            if(i%5==0){
                list.remove(i-1);
            }
            if(i%2==0){
                list.add(UUID.randomUUID().toString());
            }
        }

        /**
         * Iterator 与  ListIterator
         * Iterator 不能添加 ListIterator 可以添加
         * ListIterator 可以定位上一个元素,可以修改当前元素
         */
        Iterator<String> iterator = list.iterator();
        ListIterator<String> slt = list.listIterator();
        while(slt.hasNext()) {
            if (slt.next().contains("f")) {
                slt.remove();
            }
            int ran = new Random().nextInt(10) + 1;
            if(ran>=8){
                String s = UUID.randomUUID().toString();
                slt.add(s);
                System.out.println(ran+"添加成功"+"  "+s);
            }
        }

        list.removeIf(str ->str.contains("e5"));
        System.out.println("                       ");
        list.forEach(str-> System.out.println(str));
        System.out.println(list.size());

        // 下面循环会报错 java.util.ConcurrentModificationException
        for (String s : list) {
            list.remove(s);
        }
    }

    public static List<String> getList(){
        List<String> list =new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(UUID.randomUUID().toString());
        }
        return list;
    }

    /**
     * 测试 try with resource 写法
     * try 中的资源 需要 implements Closeable 接口
     * @throws IOException
     */
    public void tryWithResourceTest() throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("E:\\people.txt"))) {
            Object o = objectInputStream.readObject();
            System.out.println(o);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
