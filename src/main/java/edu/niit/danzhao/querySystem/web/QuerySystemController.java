package edu.niit.danzhao.querySystem.web;
/**
 * Created by home
 * on 2018/4/3.
 */

import com.alibaba.fastjson.JSONArray;
import edu.niit.danzhao.querySystem.dao.FunctionQuHaoDAO;
import edu.niit.danzhao.querySystem.dao.KsInformationDTO;
import edu.niit.danzhao.querySystem.model.FunctionQuHao;
import edu.niit.danzhao.querySystem.model.VO.KsInformationVO;
import edu.niit.danzhao.querySystem.service.AdminService;
import edu.niit.danzhao.querySystem.util.BaseExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 取号系统web控制
 * @author zhou
 **/
@RestController
public class QuerySystemController {

    @Autowired
    private KsInformationDTO ksInformationDTO;
    @Autowired
    private FunctionQuHaoDAO functionQuHaoDAO;

    @Autowired
    private AdminService adminService;

    /**
     * 派号
     * 请求地址：IP地址/quhao/sfzh  如：http://localhost:8080/quhao/3402031666666
     *
     * @param sfzh 考生身份证号
     * @return map  状态信息、打印考生号码数据
     */
    @RequestMapping(value = "quhao/{sfzh}", method = RequestMethod.POST) //sfzh为提交的考生身份证号，参数必须存在，
    public
    @ResponseBody
    Map<String, Object> quhaoCtrl(@PathVariable String sfzh) throws SQLException {
        Map<String, Object> map = new HashMap();
        //查找有无此考生
        KsInformationVO record = ksInformationDTO.selectBySfzh(sfzh);
        //没有考试时间的考生（未取得考试资格）或者非考生过滤
        if (record == null) {
            map.put("MSG", "你非我校考生！");
        } else {
            if (record.getKssj().equals("")) {
                map.put("MSG", "你未取得考试资格！");
            } else {
                map = adminService.quhao(record);
            }
        }
        return map;
    }

    /**
     * 入场
     * 请求地址 IP地址/enter/zkzh
     * @param zkzh 准考证号
     * @return map 错误信息，正确信息（已更新的取号考生信息）
     */
    @RequestMapping(value = "enter/{zkzh}", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> zkzhCtrl(@PathVariable String zkzh) throws SQLException {
        return adminService.enter(zkzh);
    }
    /**
     * 导出excel
     * /excel
     * */
    @RequestMapping(value = "excel")
    public
    @ResponseBody
     void exportCtrl(HttpServletResponse response) {
        List<FunctionQuHao> functionQuHaos =  functionQuHaoDAO.selectAll();
        try {
            //第二种方法 生成文件并可以下载
            JSONArray ja = new JSONArray();             //获取业务数据集
            for(int i =0;i<functionQuHaos.size();i++){
                ja.add(functionQuHaos.get(i));
            }
            Map<String,String> headMap = new LinkedHashMap<String,String>();    // 获得属性列头
            headMap.put("ksName","考生姓名");
            headMap.put("ksSfzh","身份证号");
            headMap.put("ksKjh","考籍号");
            headMap.put("ksZkzh","准考证号");
            headMap.put("number","排队号码");
            headMap.put("numberType","既定考试时间类型");
            headMap.put("qhTime","取号时间");
            headMap.put("enterTime","入场时间");
            headMap.put("qhTimeFlag","实际取号时间");
            String title = "考生人数表";
            BaseExcelUtil.downloadExcelFile(title,headMap,ja,response);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            //第一种方法 只在本地硬盘生成文件
            adminService.export(functionQuHaos);

        }



    }
}
