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
        if (CommonMethod.isJudgingElement(driver,By.xpath("//div[@class='el-checkbox-group']/div"))){//校验是否有数据
            List<WebElement> list=driver.findElements(By.xpath("//div[@class='el-checkbox-group']/div"));//数据列表
            Actions action=new Actions(driver);
            action.moveToElement(list.get(list.size()-1)).perform();//光标悬浮当页最后一条数据
            list.get(list.size()-1).findElement(By.xpath("div[2]/div/p[2]/span[1]")).click();//点击审核
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='el-message-box__btns']/button[1]")).click();//点击第一个按钮（通过或不通过)
            System.out.println("~~~ check()，评论审核，执行成功 ~~~");
        }else System.out.println("没有可审核的数据");
        Thread.sleep(3000);
    }

    //删除
    public static void delete() throws InterruptedException {
        if (CommonMethod.isJudgingElement(driver,By.xpath("//div[@class='el-checkbox-group']/div"))){//校验是否有数据
            List<WebElement> list=driver.findElements(By.xpath("//div[@class='el-checkbox-group']/div"));//数据列表
            Actions action=new Actions(driver);
            action.moveToElement(list.get(list.size()-1)).perform();//光标悬浮当页最后一条数据
            list.get(list.size()-1).findElement(By.xpath("div[2]/div/p[2]/span[2]")).click();//点击审核
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='el-message-box__btns']/button[1]")).click();//点击第一个按钮（通过或不通过)
            System.out.println("~~~ check()，评论审核，执行成功 ~~~");
        }else System.out.println("没有可审核的数据");
        Thread.sleep(3000);
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.cssSelector("div.nav.fl"))) {
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();
                    driver.get(domain + "/politic/manage/qa/page");
                    Thread.sleep(2000);
                } else break;
            }
            if (!driver.findElement(By.xpath("//div[@class='nav-right fr']/ul/li/a")).getText().contains("爱富县")) {
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.className("nav-right fr"))).perform();
                Thread.sleep(500);
                driver.findElement(By.linkText("爱富县")).click();
                Thread.sleep(3000);
            }
            driver.findElement(By.xpath("//ul[@class='nav-list']/li[3]")).click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
