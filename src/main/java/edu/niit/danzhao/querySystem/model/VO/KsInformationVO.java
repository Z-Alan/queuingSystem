package edu.niit.danzhao.querySystem.model.VO;
/**
 * Created by home
 * on 2018/4/3.
 */

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 考生信息DTO 存储考生信息的一些字段 (可以看作VO)
 * @author zhou
 **/
@Entity
@Table(name="ks_information_vw")
public class KsInformationVO {

    private  String Sfzh;         //视图中的考生身份证号
    private String Kjh;           //考生考籍号
    private String Xm;            //考生姓名
    private String kssj;          //考生考试时间 数据类似： 4月14日上午8：30 4月15日上午8：30
    private String zkzh;          // 考生准考证号

    public String getSfzh() {
        return Sfzh;
    }

    public void setSfzh(String sfzh) {
        Sfzh = sfzh;
    }

    public String getKjh() {
        return Kjh;
    }

    public void setKjh(String kjh) {
        Kjh = kjh;
    }

    public String getXm() {
        return Xm;
    }

    public void setXm(String xm) {
        Xm = xm;
    }

    public String getKssj() {
        return kssj;
    }

    public void setKssj(String kssj) {
        this.kssj = kssj;
    }

    public String getZkzh() {
        return zkzh;
    }

    public void setZkzh(String zkzh) {
        this.zkzh = zkzh;
    }

    @Override
    public String toString() {
        return "KsInformationVO{" +
                "Sfzh='" + Sfzh + '\'' +
                ", Kjh='" + Kjh + '\'' +
                ", Xm='" + Xm + '\'' +
                ", kssj='" + kssj + '\'' +
                ", zkzh='" + zkzh + '\'' +
                '}';
    }
}
