package service;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/4/1 9:08
 */
public class ServiceTest {

    @Test(priority = 1)//服务新增
    public void testAddService() throws InterruptedException {
        Service.addService();
    }

    @Test(priority = 4)//服务编辑
    public void testEdit() throws InterruptedException {
        Service.edit();
    }

    @Test(priority = 2)//服务签发
    public void testPublish() throws InterruptedException {
        Service.publish();
    }

    @Test(priority = 5)//服务删除
    public void testDelete() throws InterruptedException {
        Service.delete();
    }

    @Test(priority = 3)//服务上下线
    public void testOnOrOff() throws InterruptedException {
        Service.onOrOff();
    }

    @BeforeMethod
    public void testStart(Method method) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + method.getName());
    }

    @AfterMethod
    public void testEnd(Method method) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<< Test End!\n");
    }
}