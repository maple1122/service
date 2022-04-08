package shoot;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * @date 2022/4/2 11:29
 */
public class Comment extends LoginPortal {

    static WebDriver driver;

    //审核
    public static void check() throws InterruptedException {
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='el-checkbox-group']/div"))) {//校验是否有数据
            List<WebElement> list = driver.findElements(By.xpath("//div[@class='el-checkbox-group']/div"));//数据列表
            Actions action = new Actions(driver);
            Thread.sleep(200);
            action.moveToElement(list.get(list.size() - 2)).perform();//光标悬浮当页最后一条数据
            list.get(list.size() - 2).findElement(By.xpath("div[2]/div/p[2]/span[1]")).click();//点击审核
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='el-message-box__btns']/button[1]")).click();//点击第一个按钮（通过或不通过)
            System.out.println("~~~ check()，评论审核，执行成功 ~~~");
        } else System.out.println("没有可审核的数据");
        Thread.sleep(3000);
    }

    //删除
    public static void delete() throws InterruptedException {
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='el-checkbox-group']/div"))) {//校验是否有数据
            List<WebElement> list = driver.findElements(By.xpath("//div[@class='el-checkbox-group']/div"));//数据列表
            Actions action = new Actions(driver);
            action.moveToElement(list.get(list.size() - 2)).perform();//光标悬浮当页最后一条数据
            list.get(list.size() - 2).findElement(By.xpath("div[2]/div/p[2]/span[2]")).click();//点击审核
            Thread.sleep(500);
            driver.findElement(By.cssSelector("button.el-button.el-button--default.el-button--small.el-button--primary")).click();//点击确定
            System.out.println("~~~ delete()，评论删除，执行成功 ~~~");
        } else System.out.println("没有可删除的数据");
        Thread.sleep(3000);
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.className("header-user-pack"))) {
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();
                    driver.get(domain + "/shoot/static/index.html#/comment");
                    Thread.sleep(2000);
                } else break;
            }
            driver.findElement(By.className("communit-toggle")).click();
            Thread.sleep(200);
            List<WebElement> li = driver.findElements(By.xpath("//ul[@class='listParent']/li"));
            for (int i = 0; i < li.size(); i++) {
                if (li.get(i).getText().contains("爱富县")) {
                    li.get(i).click();
                    break;
                }
            }
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
