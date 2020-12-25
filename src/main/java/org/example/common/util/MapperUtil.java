package org.example.common.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;

import java.util.List;

/**
 * 映射工具类
 */
public class MapperUtil {

    private static MapperFacade MAPPER = new DefaultMapperFactory.Builder().build().getMapperFacade();

    /**
     * 映射实体
     *
     * @param source           源对象
     * @param destinationClass 目标类
     * @return 目标对象
     */
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return MAPPER.map(source, destinationClass);
    }

    /**
     * 映射实体
     *
     * @param source          源对象
     * @param sourceType      源类型
     * @param destinationType 目标类型
     * @return 目标对象
     */
    public static <S, D> D map(S source, Type<S> sourceType, Type<D> destinationType) {
        return MAPPER.map(source, sourceType, destinationType);
    }

    /**
     * 映射列表
     *
     * @param sourceList       源对象列表
     * @param sourceClass      源对象类
     * @param destinationClass 目标类
     * @return 目标对象列表
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<S> sourceClass, Class<D> destinationClass) {
        return MAPPER.mapAsList(sourceList, getType(sourceClass), getType(destinationClass));
    }


    /**
     * 映射列表
     *
     * @param sourceList      源对象列表
     * @param sourceType      源类型
     * @param destinationType 目标类型
     * @return 目标对象列表
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Type<S> sourceType, Type<D> destinationType) {
        return MAPPER.mapAsList(sourceList, sourceType, destinationType);
    }

    /**
     * 映射数组
     *
     * @param destination      目标对象数组
     * @param source           源对象数组
     * @param destinationClass 目标类
     * @return 目标对象数组
     */
    public static <S, D> D[] mapArray(final D[] destination, final S[] source, final Class<D> destinationClass) {
        return MAPPER.mapAsArray(destination, source, destinationClass);
    }

    /**
     * 映射数组
     *
     * @param destination      目标对象数组
     * @param source           源对象数组
     * @param sourceType       源类型
     * @param destinationType  目标类型
     * @return 目标对象数组
     */
    public static <S, D> D[] mapArray(D[] destination, S[] source, Type<S> sourceType, Type<D> destinationType) {
        return MAPPER.mapAsArray(destination, source, sourceType, destinationType);
    }

    /**
     * 获取类类型
     *
     * @param rawType 类
     * @return 类型
     */
    public static <E> Type<E> getType(final Class<E> rawType) {
        return TypeFactory.valueOf(rawType);
    }

}
