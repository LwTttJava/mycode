package test.lrudemo;


import java.util.Objects;

/**
 * @author luotao
 * @date 2022-6-8  20:33
 * 数组 + 链表方式
 * 实现了get(),put(),扩容方法
 */
public class MyHashMap<K,V> {

    public volatile int capacity;

    public volatile int size;

    final float loadFactor;

    int threshold;

    public MyHashMap.Node<K,V>[] table;

    public MyHashMap(int capacity){
        this(capacity,0.75f);
    }

    public MyHashMap(int capacity,float loadFactor){
        table = new MyHashMap.Node[capacity];
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        this.threshold = (int)(capacity*loadFactor);
    }

    public V get(K key){
        int hash = hash(key);
        int index = hash % table.length - 1;
        Node<K,V> cur = table[index];
        while(cur!=null){
            if (cur.key.equals(key)) {
                return cur.value;
            }else{
                cur = cur.next;
            }
        }
        return null;
    }

    public void put(K key,V value){
        Objects.requireNonNull(key,"key不能为null");
        final int hash = hash(key);
        int index = hash % (table.length - 1);
        Node cur = table[index];
        boolean isAdd = true;
        // 数组对应位置没有值，直接插入
        if (cur == null) {
            table[index] = new Node<>(hash,key,value,null);
        }else{
            //有值，进行循环遍历，判断key是否相同，相同更改值，否则插入
            while (cur!=null){
                if (cur.key.equals(key)) {
                    cur.value = value;
                    // 说明此值是更改，元素个数不会增加
                    isAdd=false;
                    break;
                }else{
                    cur = cur.next;
                }
            }
            if(cur==null){
                cur.next = new Node<>(hash,key,value,null);
            }
        }
        if(isAdd){
            size++;
        }
        // 判断是否需要进行扩容
        if(size>threshold){
            resize();
        }
    }

    private void resize(){
        MyHashMap.Node<K,V>[] oldTab = table;

        if(capacity>=Integer.MAX_VALUE){
            threshold = Integer.MAX_VALUE;
            return;
        }
        int newCap = capacity<<1 >= Integer.MAX_VALUE ? Integer.MAX_VALUE :capacity<<1;

        MyHashMap.Node<K,V>[] newTab = new MyHashMap.Node[newCap];

        //遍历旧的数组
        for(int i = 0;i<oldTab.length;i++){
            Node<K,V> cur = oldTab[i];
                //数组中节点不止一个值，进行重新计算在新数组中的位置
                while(cur!=null){
                    int index = cur.hash % (newCap - 1);
                    if (newTab[index] == null) {
                        newTab[index] = cur;
                    }else{
                        Node<K, V> curNew = newTab[index];
                        while(curNew.next!=null){
                            curNew = curNew.next;
                        }
                        curNew.next = cur;
                    }
                    cur = cur.next;
                }
        }

        capacity = newCap;
        // 计算下次扩容值
        threshold = (int)(capacity * loadFactor);
    }

    private int hash(K key) {
        return key.hashCode();
    }

    static class Node<K,V>{
        final int hash;
        final K key;
        V value;
        MyHashMap.Node<K,V> next;

        Node(int hash, K key, V value, MyHashMap.Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
