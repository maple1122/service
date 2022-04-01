package politic;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * @author wufeng
 * @date 2022/4/1 10:49
 */
public class QaPage extends LoginPortal {
    static WebDriver driver;
    static String trURL = "//ul[@class='my-table']/li[@class='curr']/div/div/div[2]/table/tbody/tr";

    //回复处理
    public static void reply(int type) throws InterruptedException {
        String strType = switchTab(type);

        selectByStatus(driver, 1);//筛选未处理的数据
        if (CommonMethod.isJudgingElement(driver, By.xpath(trURL))) {
            List<WebElement> trs = driver.findElements(By.xpath(trURL));
            trs.get(trs.size() - 1).findElement(By.xpath("td[3]/div/a")).click();
            Thread.sleep(500);
            CommonMethod.swichWindow(driver);
            driver.findElement(By.id("textarea")).sendKeys("autoTest自动回复测试" + System.currentTimeMillis());
            Thread.sleep(200);
            driver.findElement(By.cssSelector("p.fr.yesbtn")).click();
            Thread.sleep(2000);
            CommonMethod.closeWindow(driver);
            System.out.println("~~~ reply()，回复" + strType + "，执行成功 ~~~");
        } else System.out.println(strType + "没有可回复的数据");
        Thread.sleep(3000);
    }

    //流转
    public static void pass(int type) throws InterruptedException {
        String strType = switchTab(type);

    }

    //公开
    public static void open(int type) throws InterruptedException {
        String strType = switchTab(type);

    }

    //取消公开
    public static void close(int type) throws InterruptedException {
        String strType = switchTab(type);

    }

    //删除
    public static void delete(int type) throws InterruptedException {
        String strType = switchTab(type);

    }

    //按照状态筛选
    private static void selectByStatus(WebDriver driver, int status) throws InterruptedException {
        driver.findElement(By.xpath("//form[@class='layui-form ll-search clearfix']/div[4]/div/div/div")).click();
        List<WebElement> liStatus = driver.findElements(By.xpath("//form[@class='layui-form ll-search clearfix']/div[4]/div/div/div/dl/dd"));
        if (status < 1 || status > 3) status = 1;
        liStatus.get(status + 1).click();
        driver.findElement(By.id("searchBtn")).click();
        Thread.sleep(1000);
    }

    //tab页切换
    private static String switchTab(int type) throws InterruptedException {
        String strTab = "咨询";
        switch (type) {
            case 1:
                driver.findElement(By.xpath("//ul[@class='ll-nav clearfix']/li[1]")).click();
                break;
            case 2:
                strTab = "投诉";
                driver.findElement(By.xpath("//ul[@class='ll-nav clearfix']/li[2]")).click();
                break;
            case 3:
                strTab = "领导信箱";
                driver.findElement(By.xpath("//ul[@class='ll-nav clearfix']/li[3]")).click();
                break;
            default:
                driver.findElement(By.xpath("//ul[@class='ll-nav clearfix']/li[1]"));
        }
        Thread.sleep(1000);
        return strTab;
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
            driver.findElement(By.xpath("//ul[@class='nav-list']/li[1]")).click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
