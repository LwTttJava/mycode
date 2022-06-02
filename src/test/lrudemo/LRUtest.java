package test.lrudemo;


/**
 * @author luotao
 * @date 2022-3-9  22:14
 */
public class LRUtest {
    public static void main(String[] args) {
        LRUCache<Integer,Integer> cacheSize = new LRUCache<>(8);
        for (int i=1;i<=20;i++){
            cacheSize.put(i,i);
            cacheSize.forEach((k,v)->{
                System.out.print("k:"+k+" ");
            });
            System.out.println();
        }
    }
}

