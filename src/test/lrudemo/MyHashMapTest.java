package test.lrudemo;

/**
 * @author luotao
 * @date 2022-6-8  21:25
 */
public class MyHashMapTest {

    public static void main(String[] args) {
        MyHashMap<String,String> myHashMap = new MyHashMap(8);
        for (int i = 1; i <= 6; i++) {
            myHashMap.put("key"+i,"key"+i);
        }
        myHashMap.put("key7","key7");
        myHashMap.put("key7","key7");

    }
}
