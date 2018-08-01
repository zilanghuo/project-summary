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

    private Integer maxSize;

    private Entry<K,V> first;

    private Entry<K,V> last;

    private Map<K,Entry<K,V>> cacheMap;
