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
            driver.findElement(By.xpath("//ul[@class='ll-nav clearfix']/li[2]")).click();//切换到来信选登tab
        } else driver.findElement(By.xpath("//ul[@class='ll-nav clearfix']/li[1]")).click();//切换到常见问题tab
        Thread.sleep(500);
        driver.findElement(By.cssSelector("button.ll-btn.btn-add")).click();//点击新建
        Thread.sleep(1000);
        CommonMethod.swichWindow(driver);//跳转到新标签页
        Thread.sleep(200);
        driver.findElement(By.id("title")).sendKeys("autoTest-" + strType + "-" + System.currentTimeMillis());//录入名称
        driver.switchTo().frame("ueditor_0");//切换到编辑框iframe
        Thread.sleep(200);
        driver.findElement(By.xpath("//body[@class='view']/p")).click();//激活编辑器
        driver.findElement(By.xpath("//body[@class='view']/p")).sendKeys("autoTest" + strType + "的内容" + System.currentTimeMillis());//编辑器中录入内容
        driver.switchTo().defaultContent();
        Thread.sleep(200);
        driver.findElement(By.xpath("//div[@class='btn']/span")).click();//点击保存
        Thread.sleep(1000);
        CommonMethod.closeWindow(driver);//关闭当前标签页
        System.out.println("~~~ addQuertion()，新建" + strType + "，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //编辑常见问题、来信
    public static void editQuestion(int type) throws InterruptedException {
        String strType = getAutotest(type);//切换到相应tab页并搜索auto测试数据，无数据则先增加
        List<WebElement> trs = driver.findElements(By.xpath("//ul[@class='my-table']/li[@class='curr']/div/div/div[2]/table/tbody/tr"));//获取列表数据
        trs.get(0).findElement(By.xpath("td[1]/div/div/i")).click();//取第一条测试数据
        driver.findElement(By.cssSelector("button.ll-btn.btn-edit")).click();//点击编辑
        CommonMethod.swichWindow(driver);//跳转到新标签页
        Thread.sleep(1000);
        driver.findElement(By.id("title")).clear();//清空标题
        driver.findElement(By.id("title")).sendKeys("autoTest-" + strType + "编辑-" + System.currentTimeMillis());//录入编辑后的标题
        driver.switchTo().defaultContent();
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@class='btn']/span")).click();//点击保存
        Thread.sleep(1000);
        CommonMethod.closeWindow(driver);//关闭当前窗口
        System.out.println("~~~ editQuestion()，编辑" + strType + "，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //公开常见问题、来信
    public static void openQuestion(int type) throws InterruptedException {
        String strType = getAutotest(type);//切换到相应tab页并搜索auto测试数据，无数据则先增加
        Boolean isOpened = false;
        List<WebElement> trs = driver.findElements(By.xpath("//ul[@class='my-table']/li[@class='curr']/div/div/div[2]/table/tbody/tr"));//获取数据列表
        for (int i = 0; i < trs.size(); i++) {
            if (trs.get(i).findElement(By.xpath("td[last()]/div")).getText().equals("未公开")) {//校验是否是未公开数据
                trs.get(i).findElement(By.xpath("td[1]/div/div/i")).click();//选中第一条数据
                driver.findElement(By.cssSelector("button.ll-btn.btn-open")).click();//点击公开
                Thread.sleep(200);
                driver.findElement(By.className("layui-layer-btn0")).click();//确定公开
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
        String strType = search(type);//切换到相应tab页并搜索auto数据
        Boolean isClosed = false;
        if (CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='my-table']/li[@class='curr']/div/div/div[2]/table/tbody/tr"))) {//校验是否有数据
            List<WebElement> trs = driver.findElements(By.xpath("//ul[@class='my-table']/li[@class='curr']/div/div/div[2]/table/tbody/tr"));//获取数据列表
            for (int i = 0; i < trs.size(); i++) {
                if (trs.get(i).findElement(By.xpath("td[last()]/div")).getText().equals("已公开")) {//校验是否是已公开数据
                    trs.get(i).findElement(By.xpath("td[1]/div/div/i")).click();//选中数据
                    driver.findElement(By.cssSelector("button.ll-btn.btn-openCancle")).click();//点击取消公开
                    Thread.sleep(200);
                    driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
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
        String strType = getAutotest(type);//切换到相应tab页并搜索auto测试数据，无数据则先增加
        driver.findElement(By.xpath("//ul[@class='my-table']/li[@class='curr']/div/div/div/table/thead/tr/th/div/div/i")).click();//选中当页所有数据
        Thread.sleep(500);
        driver.findElement(By.cssSelector("button.ll-btn.btn-del")).click();//点击删除
        Thread.sleep(200);
        driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
        System.out.println("~~~ deleteQuestion()，删除" + strType + "，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //搜索无数据则新增
    private static String getAutotest(int type) throws InterruptedException {

        String strType = search(type);//类型
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@class='my-table']/li[@class='curr']/div/div/div[2]/table/tbody/tr"))) {//校验是否有数据
            addQuertion(type);//增加新测试数据
            search(type);//切换到该类型并搜索测试数据
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
        driver.findElement(By.xpath("//ul[@class='ll-nav clearfix']/li[" + tab + "]")).click();//切换到相应tab
        Thread.sleep(500);
        driver.findElement(By.name("keyword")).clear();//清空搜索关键词编辑框
        driver.findElement(By.name("keyword")).sendKeys("autoTest");//录入搜索关键词
        driver.findElement(By.id("searchBtn")).click();//点击搜索
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
