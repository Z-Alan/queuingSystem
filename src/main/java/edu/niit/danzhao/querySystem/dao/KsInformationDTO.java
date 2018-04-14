package edu.niit.danzhao.querySystem.dao;

import edu.niit.danzhao.querySystem.model.VO.KsInformationVO;

/**
 * Created by home
 * on 2018/4/3.
 */
public interface KsInformationDTO  extends BaseDAO<KsInformationVO>{

    /**
     * 根据考生身份证号查询一条记录
     * @param ksSfzh 考生的身份证号
     * @return record 考生的相关记录 数据类型 KsInformationVO*/
    KsInformationVO selectBySfzh(String  ksSfzh);
}
