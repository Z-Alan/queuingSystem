package edu.niit.danzhao.querySystem.service;

import edu.niit.danzhao.querySystem.dao.KsInformationDTO;
import edu.niit.danzhao.querySystem.model.VO.KsInformationVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by home
 * on 2018/4/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration//如果不加的话，是没法调用WEB的一些特性的。经常会被遗忘掉。。。
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;
    @Autowired
    private KsInformationDTO ksInformationDTO;


    @Test
    public void testVAdd(){

        //创建一个线程池
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1);//初始设置为1
        final CountDownLatch cdAnswer = new CountDownLatch(20);
        final List<KsInformationVO> ksInformationVOList= ksInformationDTO.selectAll();

        for(int i=0;i<20;i++){
            final int j = i;
            Runnable runnable = new Runnable(){
                public void run(){
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "正准备接受命令");
                        cdOrder.await();
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "已接受命令");

                        KsInformationVO record = ksInformationDTO.selectBySfzh(ksInformationVOList.get(j).getSfzh());
                        adminService.quhao(record);

                        System.out.println("线程" + Thread.currentThread().getName() +
                                "回应命令处理结果");


                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        //任务执行完毕，cdAnswer减1。
                        cdAnswer.countDown();
                    }
                }
            };
            //为线程池添加任务
            service.execute(runnable);
        }
        try {
            Thread.sleep((long)(Math.random()*10000));

            System.out.println("线程" + Thread.currentThread().getName() +
                    "即将发布命令");
            //发送命令，cdOrder减1
            cdOrder.countDown();
            System.out.println("线程" + Thread.currentThread().getName() +
                    "已发送命令，正在等待结果");
            cdAnswer.await();
            System.out.println("线程" + Thread.currentThread().getName() +
                    "已收到所有响应结果");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        service.shutdown();

    }




    @Test
    public void quhaoCheck() throws Exception {
        KsInformationVO ksInformation = ksInformationDTO.selectBySfzh("321322199701131072");
        if(ksInformation.getKssj().equals("")){
            System.out.println("您未取得考试资格！");
        }else{
            System.out.println(adminService.quhao(ksInformation));
        }

    }

    @Test
    public void enterCheck() throws Exception {
        adminService.enter("1702050001");
    }
}