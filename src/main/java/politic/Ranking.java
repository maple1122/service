package politic;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * @date 2022/4/1 11:47
 */
public class Ranking extends LoginPortal {
    static WebDriver driver;

    //编辑政务指数
    public static void edit() throws InterruptedException {
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr"))) {//校验是否有数据
            List<WebElement> trs = driver.findElements(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr"));//数据list
            String original = trs.get(trs.size() - 1).findElement(By.xpath("td[last()]/div")).getText();//当页最后一条数据
            String edit = String.valueOf(Integer.valueOf(original) + 1);//编辑后的指数=原指数+1
            trs.get(trs.size() - 1).findElement(By.xpath("td[last()]/div")).click();//点击激活指数编辑框
            trs.get(trs.size() - 1).findElement(By.xpath("td[last()]/input")).sendKeys(Keys.BACK_SPACE + edit);//录入新指数值
            Thread.sleep(200);
            driver.findElement(By.id("searchBtn")).click();//点击搜索使指数编辑框失去焦点进行保存
        } else System.out.println("无单位数据");
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
