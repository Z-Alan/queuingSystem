package edu.niit.danzhao.querySystem.model;
/**
 * Created by home
 * on 2018/4/3.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 排队取号实体类
 * @author zhou
 **/

@Entity                                                 //实体表注解
@Table(name="function_quhao")                           //数据库表映射
public class FunctionQuHao {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer quhaoId;
    private String ksName;                              //考生姓名
    private String ksSfzh;                              //身份证号
    private String ksKjh;                               //考籍号
    private String ksZkzh;                              //准考证号
    private Integer number;                             //排队号码
    private String numberType;                          //考试时间类型 14a 14p 上午a 下午p
    private Timestamp qhTime;                           //取号时间
    private Timestamp enterTime;                        //入场时间
    private String qhTimeFlag;                          //取号标志 14a 14p 15a 15p

    public Integer getQuhaoId() {
        return quhaoId;
    }

    public void setQuhaoId(Integer quhaoId) {
        this.quhaoId = quhaoId;
    }

    public String getKsName() {
        return ksName;
    }

    public void setKsName(String ksName) {
        this.ksName = ksName;
    }

    public String getKsSfzh() {
        return ksSfzh;
    }

    public void setKsSfzh(String ksSfzh) {
        this.ksSfzh = ksSfzh;
    }

    public String getKsKjh() {
        return ksKjh;
    }

    public void setKsKjh(String ksKjh) {
        this.ksKjh = ksKjh;
    }

    public Timestamp getQhTime() {
        return qhTime;
    }

    public void setQhTime(Timestamp qhTime) {
        this.qhTime = qhTime;
    }

    public Timestamp getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Timestamp enterTime) {
        this.enterTime = enterTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getNumberType() {
        return numberType;
    }

    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }

    public String getKsZkzh() {
        return ksZkzh;
    }

    public void setKsZkzh(String ksZkzh) {
        this.ksZkzh = ksZkzh;
    }

    public String getQhTimeFlag() {
        return qhTimeFlag;
    }

    public void setQhTimeFlag(String qhTimeFlag) {
        this.qhTimeFlag = qhTimeFlag;
    }

    @Override
    public String toString() {
        return "FunctionQuHao{" +
                "quhaoId=" + quhaoId +
                ", ksName='" + ksName + '\'' +
                ", ksSfzh='" + ksSfzh + '\'' +
                ", ksKjh='" + ksKjh + '\'' +
                ", ksZkzh='" + ksZkzh + '\'' +
                ", number=" + number +
                ", numberType='" + numberType + '\'' +
                ", qhTime=" + qhTime +
                ", enterTime=" + enterTime +
                ", qhTimeFlag='" + qhTimeFlag + '\'' +
                '}';
    }
}
