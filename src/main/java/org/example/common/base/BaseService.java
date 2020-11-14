package org.example.common.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author linzhaoming
 * @since 2020/11/14
 **/
public abstract class BaseService<M extends BaseMapper<E>, E> extends ServiceImpl<M, E> {

}
