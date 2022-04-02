package politic;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/4/1 16:59
 * @description 问政测试，type：1，咨询；2，投诉；3，领导信箱
 */
public class QaPageTest {

    @Test(priority = 1)//回复
    public void testReply() throws InterruptedException {
        QaPage.reply(1);
        QaPage.reply(2);
        QaPage.reply(3);
    }

    @Test(priority = 2)//流转
    public void testPass() throws InterruptedException {
        QaPage.pass(1);
        QaPage.pass(2);
        QaPage.pass(3);
    }

    @Test(priority = 3)//公开
    public void testOpen() throws InterruptedException {
        QaPage.open(1);
        QaPage.open(2);
        QaPage.open(3);
    }

    @Test(priority = 4)//取消公开
    public void testClose() throws InterruptedException {
        QaPage.close(1);
        QaPage.close(2);
        QaPage.close(3);
    }

    @Test(priority = 5)//删除
    public void testDelete() throws InterruptedException {
        QaPage.delete(1);
        QaPage.delete(2);
        QaPage.delete(3);
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