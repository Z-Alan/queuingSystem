package edu.niit.danzhao.querySystem.service.impl;

import com.sun.tools.javac.resources.javac;
import edu.niit.danzhao.querySystem.dao.FunctionQuHaoDAO;
import edu.niit.danzhao.querySystem.model.FunctionQuHao;
import edu.niit.danzhao.querySystem.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by home
 * on 2018/4/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration//如果不加的话，是没法调用WEB的一些特性的。经常会被遗忘掉。。。
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AdminServiceImplTest {

    @Autowired
    private FunctionQuHaoDAO functionQuHaoDAO;
    @Autowired
    private AdminService adminService;
    @Test
    public void exportCheck() throws Exception {
      java.util.List<FunctionQuHao> functionQuHaos =  functionQuHaoDAO.selectAll();
        adminService.export(functionQuHaos);

    }


}