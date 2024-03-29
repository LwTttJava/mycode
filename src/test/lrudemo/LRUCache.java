package test.lrudemo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 利用 LinkedHashMap 的 removeEldestEntry方法实现 LRU算法
 * @param <K>
 * @param <V>
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V>
{
    private int cacheSize;

    public LRUCache(int cacheSize){
        super(16,0.75f,true);
        this.cacheSize = cacheSize;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size()>cacheSize;
    }

}