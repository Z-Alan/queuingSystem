package edu.niit.danzhao.querySystem.dao;

import edu.niit.danzhao.querySystem.model.FunctionQuHao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by home
 * on 2018/4/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration//如果不加的话，是没法调用WEB的一些特性的。经常会被遗忘掉。。。
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FunctionQuHaoDAOTest {

    @Autowired
    private FunctionQuHaoDAO functionQuHaoDAO;
    @Test
    public void hasRecordCheck() throws Exception {
        System.out.println(functionQuHaoDAO.maxNumberByNumberType("15P"));
        System.out.println(functionQuHaoDAO.hasRecordBySfzh("321322199701131072"));
    }


}