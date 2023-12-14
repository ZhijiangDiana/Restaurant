package com.restaurant.restaurant.utils;

import java.io.Serializable;
import java.util.Objects;

/**
 * 算法竞赛最喜欢使用の数据结构之一
 * 可以用于存储两种不同类型的有对应关系的数据
 * @param <K>
 * @param <V>
 */
public class Pair<K, V> implements Serializable {
    public K first;
    public V second;
    public Pair(K first, V second){
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    public Pair(){}

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
