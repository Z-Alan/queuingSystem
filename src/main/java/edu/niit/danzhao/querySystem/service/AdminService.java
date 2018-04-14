package edu.niit.danzhao.querySystem.service;

import edu.niit.danzhao.querySystem.model.FunctionQuHao;
import edu.niit.danzhao.querySystem.model.VO.KsInformationVO;
import java.util.*;

/**
 * Created by home
 * on 2018/4/3.
 */

public interface AdminService {
    /**
     * 取新号
     * @param  record1  考生基本信息记录 为数据库中ks_information_vw中的数据
     * @return map 包括成功的状态，考生的考生号、姓名、考籍号、排队号码、取号时间等信息*/
    Map<String,Object> quhao(KsInformationVO record1);
    /**
     * 入场
     * @param ksZkzh 已取号考生
     * @return map 包括错误信息、正确信息（已更新的取号考生对象）
     * */
    Map<String,Object> enter(String ksZkzh);

    /**
     * 一个简单的excel导出实例
     * 在本地硬盘指定路径生成一个excel文件
     * @param functionQuHaos 需要导出的数据表数据对象集合
     * */
    void export(List<FunctionQuHao> functionQuHaos);
}
