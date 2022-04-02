package shoot;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/4/2 15:24
 */
public class CommentTest {

    @Test(priority = 1)//审核评论
    public void testCheck() throws InterruptedException {
        Comment.check();
    }

    @Test(priority = 2)//删除评论
    public void testDelete() throws InterruptedException {
        Comment.delete();
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