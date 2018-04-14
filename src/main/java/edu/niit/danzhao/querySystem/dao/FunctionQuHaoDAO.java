package edu.niit.danzhao.querySystem.dao;

import edu.niit.danzhao.querySystem.model.FunctionQuHao;

/**
 * Created by home
 * on 2018/4/3.
 */
public interface FunctionQuHaoDAO extends BaseDAO<FunctionQuHao> {

    /**
     * 查找数据表有无记录
     * @param  sfzh
     * @return 0 无记录 1 有记录
     * */
    FunctionQuHao hasRecordBySfzh(String sfzh);

    /**
     * 查找时间类型中的最大记录行
     * @param numberType 考试时间类型*/
    FunctionQuHao maxNumberByNumberType(String numberType);

    /**
     * 查找当前时间段的最大行记录
     * 分为两个时间段 0点-12点 12点-24点
     * @param qhTimeFlag  当前时间
     * */
    FunctionQuHao maxNumberByQhTimeFlag(String qhTimeFlag);

    /**
     * 将取过号的考生插入数据表（function_quhao）
     * @param t FunctionQuHao
     * @return 插入记录的数据库的自增主键*/
    int insertReturnAutoPrimaryKey(FunctionQuHao t);

    /**更新入场时间
     *@param t 取号考生对象
     *@return rowsAffects 数据表受影响的记录行数
     * */
    int updateEnterTime(FunctionQuHao t);

    /**
     * 查找取号考生
     * @param ksZkzh 考生准考证号
     * @return 取号对象
     * */
    FunctionQuHao selectByZkzh(String ksZkzh);


}
