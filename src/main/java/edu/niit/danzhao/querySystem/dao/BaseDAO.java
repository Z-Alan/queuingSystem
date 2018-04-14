package edu.niit.danzhao.querySystem.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by home on 2017/3/26.
 * 通用DAO接口，继承了通用的Mapper
 * 可以实现单表操作、不能批量操作、不能多表操作
 * @param <T>
 *
 */

public interface BaseDAO<T> extends Mapper<T>,MySqlMapper<T> {
    
}