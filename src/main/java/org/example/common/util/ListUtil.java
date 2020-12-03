package org.example.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @version V1.0
 * @since 2020-11-21 16:52
 */
public class ListUtil {

    /**
     * 数组转换list
     *
     * @param arr 数组
     * @param <T> 数组类型
     * @return List
     */
    public static <T> List<T> arr2List(T[] arr) {
        if (Objects.isNull(arr) || arr.length == 0) {
            return Collections.emptyList();
        }
        return new ArrayList<>(Arrays.asList(arr));
    }

    /**
     * 获取首位元素,如果list为null,返回null
     *
     * @param list list
     * @param <T>  列表类型
     * @return T
     */
    public static <T> T getFirstElement(List<T> list) {
        return isEmpty(list) ? null : list.get(0);
    }

    /**
     * 判断列表是否不为空,不为空返回true
     *
     * @param list list
     * @return boolean
     */
    public static boolean isNotEmpty(List<?> list) {
        return Objects.nonNull(list) && !list.isEmpty();
    }

    /**
     * 判断列表是否为空,为空返回true
     *
     * @param list list
     * @return boolean
     */
    public static boolean isEmpty(List<?> list) {
        return Objects.isNull(list) || list.isEmpty();
    }

    /**
     * 去除列表元素,符合过滤条件的会被移除
     *
     * @param list   list
     * @param filter 过滤条件
     * @param <E>    类型
     */
    public static <E> void removeIf(List<E> list, Predicate<? super E> filter) {
        if (isEmpty(list) || Objects.isNull(filter)) {
            return;
        }
        list.removeIf(filter);
    }

    /**
     * 转换list
     *
     * @param originList 原列表
     * @param fun        转换function
     * @param <E>        原列表类型
     * @param <V>        转换后类型
     * @return List
     */
    public static <E, V> List<V> parseList(List<E> originList, Function<E, V> fun) {
        if (isEmpty(originList)) {
            return Collections.emptyList();
        }
        return originList.stream().map(fun).collect(Collectors.toList());
    }

    /**
     * 获取size
     *
     * @param list list
     * @return int
     */
    public static int size(List<?> list) {
        return isEmpty(list) ? 0 : list.size();
    }

}
