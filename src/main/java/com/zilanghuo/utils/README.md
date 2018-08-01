##### LRUCache 实现办法
1. 创建一个链表对象
>
    
    class Entry<K,V>{
        public Entry<K,V> pre;
        public Entry<K,V> next;
        public K key;
        public V value;
    }
>
2. 创建一个LRU对象，拥有以下属性

>

    private Integer maxSize; //个数
    private Entry<K,V> first; // 第一个元素
    private Entry<K,V> last; // 最后一个原色
    private Map<K,Entry<K,V>> cacheMap; // 数据集
    
3. put（）方法逻辑
- 1. 查询cacheMap 是否包含该元素
- 2. 若不包含该元素，这个元素为第一次新加
    1. 创建对象：entry
    2. 若first 和 last 对象为空，那么entry为第一个元素，设置为first和last，完成。
    3. 假使 entry为第三个元素（第一个：E1,第二个E2）,那么，E1为last，E2为first。此时的目标为：将E3设置为first，First往前移
>   
    if (null != entry.pre){
        entry.pre.next = entry.next;
    }
    if (null != entry.next){
        entry.next.pre = entry.pre;
    }
    if (entry == last) {
        last = last.pre;
    }
    E3.next= first;
    first.pre = E3;
    fisrt = E3;
    fisrt.pre = null;

4. 但put时，cacheMap的大小已经达到maxSize。此时，需要删除掉last元素，将last.pre设置为last
