package edu.niit.danzhao.querySystem.dao;

import edu.niit.danzhao.querySystem.model.VO.KsInformationVO;
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
public class KsInformationDTOTest {
    @Autowired
    private KsInformationDTO ksInformationDTO;
    @Test
    public void selectBySfzhCheck() throws Exception {
        KsInformationVO ksInformationVO = ksInformationDTO.selectBySfzh("321322199701131072");
        System.out.println(ksInformationVO);

    }

}