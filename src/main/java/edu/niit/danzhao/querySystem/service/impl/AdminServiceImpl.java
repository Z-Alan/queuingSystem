package edu.niit.danzhao.querySystem.service.impl;
/**
 * Created by home
 * on 2018/4/3.
 */

import edu.niit.danzhao.querySystem.dao.FunctionQuHaoDAO;
import edu.niit.danzhao.querySystem.model.FunctionQuHao;
import edu.niit.danzhao.querySystem.model.VO.KsInformationVO;
import edu.niit.danzhao.querySystem.service.AdminService;
import edu.niit.danzhao.querySystem.util.MyExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员服务
 *
 * @author zhou
 **/
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private FunctionQuHaoDAO functionQuHaoDAO;

    public Map<String, Object> quhao(KsInformationVO ksInformationVO) {
        Map<String, Object> map = new HashMap(); // 返回前端的map 反馈信息
        int number;                             // 系统派发新的号码
        String numbeType;                       // 记录考生的考试时间类型 上午还是下午 哪天？
        FunctionQuHao MaxNumberFq;              // 记录数据库中最大号码的
        FunctionQuHao functionQuHao;            // functionQuHao 表明 （function_quhao表）中有无数据，决定派新号，还是取旧号
        FunctionQuHao functionQuHao1;           // 新号对象
        String qhTimeFlag;                      // 取号时间类型
        synchronized (this) {
            functionQuHao = functionQuHaoDAO.hasRecordBySfzh(ksInformationVO.getSfzh());
            numbeType = regexNumberType(ksInformationVO.getKssj());
            qhTimeFlag = nowTimeFlag();
            MaxNumberFq = functionQuHaoDAO.maxNumberByQhTimeFlag(qhTimeFlag);
            if (MaxNumberFq == null) {
                number = 1;
            } else {
                number = MaxNumberFq.getNumber() + 1;
            }
            if (functionQuHao == null) {                                                            // 派新号
                functionQuHao1 = getKsInformation(ksInformationVO, numbeType, number, qhTimeFlag);     // number为系统派发的排队号码
                int successFlag = functionQuHaoDAO.insertReturnAutoPrimaryKey(functionQuHao1);      // 插入成功标志 持久化数据
                map.put("RECORD", functionQuHao1);                                                   // 返回取号信息 新号 未取过号的记录
                map.put("SUCCESS", successFlag);
            } else {                                                                                  // 取旧号
                map.put("RECORD", functionQuHao);
            }
        }

        return map;
    }

    public Map<String, Object> enter(String ksZkzh) {

        FunctionQuHao enterQuHao;                   // 根据准考证号取得的取号考生对象
        int successFlag;                            // 更新成功标志
        Map<String, Object> map = new HashMap();     // 返回map

        enterQuHao = functionQuHaoDAO.selectByZkzh(ksZkzh);
        if (enterQuHao != null) {
            enterQuHao.setEnterTime(new Timestamp(System.currentTimeMillis()));
            successFlag = functionQuHaoDAO.updateEnterTime(enterQuHao);
            map.put("RECORD", enterQuHao);
            map.put("SUCCESS", successFlag);
        } else {
            map.put("MSG", "您未取号，请先去取号！");
        }
        return map;
    }

    public void export(List<FunctionQuHao> functionQuHaos) {
        String[] headers = {"考生姓名", "身份证号", "考籍号", "准考证号",
                "排队号码", "既定考试时间类型", "取号时间", "入场时间", "实际取号时间"};
        String name = "取号考生人数";
        MyExcelUtil me = new MyExcelUtil();
        me.exportExcel(headers,name,functionQuHaos);
    }


    /**
     * 字符串截取考试时间 变为 标准格式
     *
     * @param kssj 考生的考试时间
     * @return "14a","14p","15a","15p"
     * @throws ArrayIndexOutOfBoundsException
     */
    private String regexNumberType(String kssj) {
        //4月15日下午1：00
        String day = kssj.substring(2, 4);
        String str = kssj.substring(5, 7);
        String isMorning;
        if (str.equals("上午")) {
            isMorning = "A";
        } else {
            isMorning = "P";

        }
        return day + isMorning;
    }

    /**
     * 标志现在时间是那个区段 上午下午 以便重新派号
     */
    private String nowTimeFlag() {
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        String qhTimeFlag;
        if (hour < 12) {
            qhTimeFlag = date + "A";
        } else {
            qhTimeFlag = date + "P";
        }
        return qhTimeFlag;
    }

    /**
     * 得到原数据表ks_information_vw 的记录对象和function_quhao的最大number封装在
     *
     * @param number     系统派发的队列号码
     * @param record1    考生的基本信息（身份证号、考籍号、姓名、考试时间）
     * @param numberType 考生的考试时间类型 标识他是14a 14p 15a 15p
     * @return functionQuHao 一个完整的对象 排队号码已经生成，取号时间已经生成
     */
    private FunctionQuHao getKsInformation(KsInformationVO record1, String numberType, int number, String qhTimeFlag) {
        //将考生的基本信息数据封装到functionQuHao的对象 并设置取号时间
        FunctionQuHao functionQuHao = new FunctionQuHao();
        functionQuHao.setKsName(record1.getXm());                           //考生姓名
        functionQuHao.setKsSfzh(record1.getSfzh());                         //考生身份证号
        functionQuHao.setKsKjh(record1.getKjh());                           //考籍号
        functionQuHao.setKsZkzh(record1.getZkzh());                         //考生准考证号
        functionQuHao.setNumberType(numberType);                            //考试时间类型
        functionQuHao.setNumber(number);                                    //排队号码
        functionQuHao.setQhTime(new Timestamp(System.currentTimeMillis())); //取号时间
        functionQuHao.setQhTimeFlag(qhTimeFlag);                            //取号时间标志

        return functionQuHao;
    }



}
