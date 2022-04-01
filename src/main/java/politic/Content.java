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
 * @date 2022/4/1 13:44
 */
public class Content extends LoginPortal {
    static WebDriver driver;

    //新建常见问题、来信
    public static void addQuertion(int type) throws InterruptedException {

        String strType = "常见问题";
        if (type == 2) {
            strType = "来信选登";
            driver.findElement(By.xpath("//ul[@class='ll-nav clearfix']/li[2]")).click();
        } else driver.findElement(By.xpath("//ul[@class='ll-nav clearfix']/li[1]")).click();
        driver.findElement(By.cssSelector("button.ll-btn.btn-add")).click();
        Thread.sleep(1000);
        CommonMethod.swichWindow(driver);
        Thread.sleep(200);
        driver.findElement(By.id("title")).sendKeys("autoTest-" + strType + "-" + System.currentTimeMillis());
        driver.switchTo().frame("ueditor_0");
        Thread.sleep(200);
        driver.findElement(By.xpath("//body[@class='view']/p")).click();
        driver.findElement(By.xpath("//body[@class='view']/p")).sendKeys("autoTest" + strType + "的内容" + System.currentTimeMillis());
        driver.switchTo().defaultContent();
        Thread.sleep(200);
        driver.findElement(By.xpath("//div[@class='btn']/span")).click();
        Thread.sleep(1000);
        CommonMethod.closeWindow(driver);
        System.out.println("~~~ addQuertion()，新建" + strType + "，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //编辑常见问题、来信
    public static void editQuestion(int type) throws InterruptedException {
        String strType = getAutotest(type);
        List<WebElement> trs = driver.findElements(By.xpath("//ul[@class='my-table']/li[@class='curr']/div/div/div[2]/table/tbody/tr"));
        trs.get(0).findElement(By.xpath("td[1]/div/div/i")).click();
        driver.findElement(By.cssSelector("button.ll-btn.btn-edit")).click();
        CommonMethod.swichWindow(driver);
        Thread.sleep(1000);
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys("autoTest-" + strType + "编辑-" + System.currentTimeMillis());
        driver.switchTo().defaultContent();
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@class='btn']/span")).click();
        Thread.sleep(1000);
        CommonMethod.closeWindow(driver);
        System.out.println("~~~ editQuestion()，编辑" + strType + "，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //公开常见问题、来信
    public static void openQuestion(int type) throws InterruptedException {
        String strType = getAutotest(type);
        Boolean isOpened = false;
        List<WebElement> trs = driver.findElements(By.xpath("//ul[@class='my-table']/li[@class='curr']/div/div/div[2]/table/tbody/tr"));
        for (int i = 0; i < trs.size(); i++) {
            if (trs.get(i).findElement(By.xpath("td[last()]/div")).getText().equals("未公开")) {
                trs.get(i).findElement(By.xpath("td[1]/div/div/i")).click();
                driver.findElement(By.cssSelector("button.ll-btn.btn-open")).click();
                Thread.sleep(200);
                driver.findElement(By.className("layui-layer-btn0")).click();
                isOpened = true;
                break;
            }
        }
        if (isOpened) System.out.println("~~~ openQuestion()，公开" + strType + "，执行成功 ~~~");
        else System.out.println("没有待公开的数据");
        Thread.sleep(3000);
    }

    //取消公开常见问题、来信
    public static void closeQuestion(int type) throws InterruptedException {
        String strType = search(type);
        Boolean isClosed = false;
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='my-table']/li[@class='curr']/div/div/div[2]/table/tbody/tr"))) {
            List<WebElement> trs = driver.findElements(By.xpath("//ul[@class='my-table']/li[@class='curr']/div/div/div[2]/table/tbody/tr"));
            for (int i = 0; i < trs.size(); i++) {
                if (trs.get(i).findElement(By.xpath("td[last()]/div")).getText().equals("已公开")) {
                    trs.get(i).findElement(By.xpath("td[1]/div/div/i")).click();
                    driver.findElement(By.cssSelector("button.ll-btn.btn-openCancle")).click();
                    Thread.sleep(200);
                    driver.findElement(By.className("layui-layer-btn0")).click();
                    isClosed = true;
                    break;
                }
            }
            if (isClosed) System.out.println("~~~ closeQuestion()，取消公开" + strType + "，执行成功 ~~~");
            else System.out.println("没有已公开的数据");
        } else System.out.println("没有" + strType + "自动化测试数据");
        Thread.sleep(3000);
    }

    //删除常见问题、来信选登
    public static void deleteQuestion(int type) throws InterruptedException {
        String strType = getAutotest(type);
        driver.findElement(By.xpath("//ul[@class='my-table']/li[@class='curr']/div/div/div/table/thead/tr/th/div/div/i")).click();
        Thread.sleep(500);
        driver.findElement(By.cssSelector("button.ll-btn.btn-del")).click();
        Thread.sleep(200);
        driver.findElement(By.className("layui-layer-btn0")).click();
        System.out.println("~~~ deleteQuestion()，删除" + strType + "，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //搜索无数据则新增
    private static String getAutotest(int type) throws InterruptedException {

        String strType = search(type);
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='my-table']/li[@class='curr']/div/div/div[2]/table/tbody/tr"))) {
            addQuertion(type);
            search(type);
        }
        Thread.sleep(500);
        return strType;
    }

    //搜索auto测试数据、来信
    private static String search(int type) throws InterruptedException {
        int tab = 1;
        String strType = "常见问题";
        if (type == 2) {
            tab = 2;
            strType = "来信选登";
        }
        driver.findElement(By.xpath("//ul[@class='ll-nav clearfix']/li[" + tab + "]")).click();
        Thread.sleep(500);
        driver.findElement(By.name("keyword")).clear();
        driver.findElement(By.name("keyword")).sendKeys("autoTest");
        driver.findElement(By.id("searchBtn")).click();
        Thread.sleep(500);
        return strType;
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
            driver.findElement(By.xpath("//ul[@class='nav-list']/li[2]")).click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
