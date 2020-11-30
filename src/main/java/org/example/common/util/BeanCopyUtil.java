package org.example.common.util;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author huapeng.huang
 * @version V1.0
 * @since 2020-11-23 21:32
 */
public class BeanCopyUtil {

    private static MapperFacade MAPPER = new DefaultMapperFactory.Builder().build().getMapperFacade();
    private static Map<String, MapperFacade> CACHE_MAPPER_MAP = new ConcurrentHashMap<>();

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
     * @param source           源对象
     * @param destinationClass 目标类
     * @param fieldMap         字段映射
     * @return 目标对象
     */
    public static <S, D> D map(S source, Class<D> destinationClass, Map<String, String> fieldMap) {
        MapperFacade mapper = getMapper(source.getClass(), destinationClass, fieldMap);
        return mapper.map(source, destinationClass);
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
        return MAPPER.mapAsList(sourceList, TypeFactory.valueOf(sourceClass), TypeFactory.valueOf(destinationClass));
    }

    /**
     * 映射列表
     *
     * @param sourceList       源对象列表
     * @param sourceClass      源对象类
     * @param destinationClass 目标类
     * @param fieldMap         字段映射
     * @return 目标对象列表
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<S> sourceClass, Class<D> destinationClass, Map<String, String> fieldMap) {
        MapperFacade mapper = getMapper(sourceClass, destinationClass, fieldMap);
        return mapper.mapAsList(sourceList, TypeFactory.valueOf(sourceClass), TypeFactory.valueOf(destinationClass));
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

    /**
     * 获取自定义映射
     *
     * @param sourceClass      源类
     * @param destinationClass 目标类
     * @param fieldMap         字段映射
     * @return 映射类对象
     */
    private static <S, D> MapperFacade getMapper(Class<S> sourceClass, Class<D> destinationClass, Map<String, String> fieldMap) {
        String mapperKey = String.join(StringPool.DASH,
                sourceClass.getName(), destinationClass.getName(), String.valueOf(fieldMap.hashCode()));
        MapperFacade mapper = CACHE_MAPPER_MAP.get(mapperKey);
        if (Objects.isNull(mapper)) {
            MapperFactory factory = new DefaultMapperFactory.Builder().build();
            ClassMapBuilder<S, D> classMapBuilder = factory.classMap(sourceClass, destinationClass);
            fieldMap.forEach(classMapBuilder::field);
            classMapBuilder.byDefault().register();
            mapper = factory.getMapperFacade();
            CACHE_MAPPER_MAP.put(mapperKey, mapper);
        }
        return mapper;
    }

}
