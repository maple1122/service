package politic;

import base.LoginPortal;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/4/1 14:09
 */
public class ContentTest {

    /**
     * @description type=1,常见问题；2，来信选登
     */
    @Test(priority = 1)//新建常见问题、来信选登
    public void testAddQuertion() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            Content.addQuertion(1);
            Content.addQuertion(2);
        }
    }

    @Test(priority = 2)//编辑常见问题、来信选登
    public void testEditQuestion() throws InterruptedException {
        Content.editQuestion(1);
        Content.editQuestion(2);
    }

    @Test(priority = 3)//公开常见问题、来信选登
    public void testOpenQuestion() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            Content.openQuestion(1);
            Content.openQuestion(2);
        }
    }

    @Test(priority = 4)//取消公开常见问题、来信选登
    public void testCloseQuestion() throws InterruptedException {
        Content.closeQuestion(1);
        Content.closeQuestion(2);
    }

//    @Test(priority = 5)//删除常见问题、来信选登
//    public void testDeleteQuestion() throws InterruptedException {
//        Content.deleteQuestion(1);
//        Content.deleteQuestion(2);
//    }

    @BeforeMethod
    public void testStart(Method method) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + method.getName());
    }

    @AfterMethod
    public void testEnd(Method method) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<< Test End!\n");
    }

    @Test
    public void test() {
        LoginPortal.env();
    }
}