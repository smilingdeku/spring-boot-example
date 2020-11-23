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
 * @author huapeng.huang
 * @version V1.0
 * @since 2020-11-21 16:52
 */
public class ListUtil {

    public static <T> List<T> arr2List(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    public static <T> T getFirstElement(List<T> list) {
        return isEmpty(list) ? null : list.get(0);
    }

    public static boolean isNotEmpty(List<?> list) {
        return Objects.nonNull(list) && !list.isEmpty();
    }

    public static boolean isEmpty(List<?> list) {
        return Objects.isNull(list) || list.isEmpty();
    }

    public static <E> void removeIf(List<E> list, Predicate<? super E> filter) {
        if (isEmpty(list) || Objects.isNull(filter)) {
            return;
        }
        list.removeIf(filter);
    }

    public static <E, V> List<V> parseList(List<E> originList, Function<E, V> fun) {
        if (isEmpty(originList)) {
            return Collections.emptyList();
        }
        return originList.stream().map(fun).collect(Collectors.toList());
    }

}
