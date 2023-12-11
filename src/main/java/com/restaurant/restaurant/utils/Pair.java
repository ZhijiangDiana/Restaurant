package com.restaurant.restaurant.utils;

/**
 * 算法竞赛最喜欢使用の数据结构之一
 * 可以用于存储两种不同类型的有对应关系的数据
 * @param <K>
 * @param <V>
 */
public class Pair<K, V>{
    public K first;
    public V second;
    public Pair(K first, V second){
        this.first = first;
        this.second = second;
    }

    public Pair(){}
}
