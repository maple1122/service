package politic;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/4/1 16:59
 */
public class QaPageTest {

    @Test(priority = 1)//回复
    public void testReply() throws InterruptedException {
        QaPage.reply(1);
        QaPage.reply(2);
        QaPage.reply(3);
    }

    @Test(priority = 2)//流转
    public void testPass() {
    }

    @Test
    public void testOpen() {
    }

    @Test
    public void testClose() {
    }

    @Test
    public void testDelete() {
    }
}