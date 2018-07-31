package com.zilanghuo.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author lwf
 * @date 2018/7/31
 * use：去掉最少使用的策略
 */
@Data
public class LRUCache<K,V> {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put("1","1");
        lruCache.put("2","2");
        lruCache.put("3","3");
        System.out.println(lruCache.toString());
    }

    private Integer maxSize;

    private Entry<K,V> first;

    private Entry<K,V> last;

    private Map<K,Entry<K,V>> cacheMap;

    public LRUCache(int size){
        this.maxSize = size;
        cacheMap = new HashMap(size);
    }

    public void put(K key,V value){
        Entry<K, V> entry = cacheMap.get(key);
        if (null == entry){
            entry = new Entry(key,value);
            entry.key = key;
        }
        entry.value = value;
        //超过大小
        if (cacheMap.size() >= maxSize){
            //删除一个数据
            removeLast();
        }
        moveFirst(entry);
        cacheMap.put(key,entry);
    }

    private Entry<K, V> getEntry(K key) {
        return cacheMap.get(key);
    }

    private void removeLast(){
        cacheMap.remove(last.key);
        if (null == last){
            return;
        }
        last = last.pre;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
    }

    private void moveFirst(Entry<K,V> entry){

        if (entry == first){
            return;
        }

        if (null != entry.pre){
            entry.pre.next = entry.next;
        }

        if (null != entry.next){
            entry.next.pre = entry.pre;
        }

        if (entry == last) {
            last = last.pre;
        }

        // 第一个元素
        if (null == first || null == last){
            first = last = entry;
            return;
        }

        // 设置first
        entry.next = first;
        first.pre = entry;
        first = entry;
        entry.pre = null;
        System.out.println("last:"+last.key);
    }

    class Entry<K,V>{

        public Entry<K,V> pre;

        public Entry<K,V> next;

        public K key;

        public V value;

        public Entry(K key,V value){
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public String toString(){
        Iterator<Map.Entry<K, Entry<K, V>>> iterator = cacheMap.entrySet().iterator();
        StringBuffer buffer = new StringBuffer("");
        while (iterator.hasNext()){
            Map.Entry<K, Entry<K, V>> next = iterator.next();
            buffer.append("key:"+next.getKey()+",value:"+next.getValue().value).append("|");
        }
        return buffer.toString();
    }
}
