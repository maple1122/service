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
        String strType = switchTab(type);//切换到该类型tab

        selectByStatus(driver, 1);//筛选未处理的数据
        if (CommonMethod.isJudgingElement(driver, By.xpath(trURL))) {//校验是否有数据
            List<WebElement> trs = driver.findElements(By.xpath(trURL));//数据list
            trs.get(trs.size() - 1).findElement(By.xpath("td[3]/div/a")).click();//点击当页最后一条的编辑
            Thread.sleep(500);
            CommonMethod.swichWindow(driver);//切换到新标签页
            driver.findElement(By.id("textarea")).sendKeys("autoTest自动回复测试" + System.currentTimeMillis());//录入回复内容
            Thread.sleep(200);
            driver.findElement(By.cssSelector("p.fr.yesbtn")).click();//点击保存
            Thread.sleep(2000);
            CommonMethod.closeWindow(driver);//关闭当前标签页
            System.out.println("~~~ reply()，" + strType + "回复，执行成功 ~~~");
        } else System.out.println(strType + "没有可回复的数据");
        Thread.sleep(3000);
    }

    //流转
    public static void pass(int type) throws InterruptedException {
        String strType = switchTab(type);//切换到该类型tab

        driver.findElement(By.cssSelector("button.ll-btn.ll-reset")).click();//点击重置
        Thread.sleep(500);
        driver.findElement(By.name("unitName")).click();//点击选择处理单位筛选框
        Thread.sleep(1000);
        driver.findElement(By.xpath("//ul[@id='su-data']/li[2]/i")).click();//点击待流转
        driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
        Thread.sleep(200);
        driver.findElement(By.id("searchBtn")).click();//点击搜索
        Thread.sleep(1000);
        if (CommonMethod.isJudgingElement(driver, By.xpath(trURL))) {//校验是否有数据
            List<WebElement> trs = driver.findElements(By.xpath(trURL));//数据list
            trs.get(trs.size() - 1).findElement(By.xpath("td[2]/div/div/i")).click();//选中当前页面最后一条数据
            driver.findElement(By.cssSelector("button.ll-btn.btn-lz")).click();//点击流转
            Thread.sleep(500);
            driver.findElement(By.xpath("//div[@class='lz-cont hide layui-layer-wrap']/ul/li[last()]/i")).click();//点击流转单位图层中最后一个单位
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
            System.out.println("~~~ pass()，" + strType + "流转，执行成功 ~~~");
        } else System.out.println(strType +"没有待流转的数据");
        Thread.sleep(3000);
    }

    //公开
    public static void open(int type) throws InterruptedException {
        String strType = switchTab(type);//切换到该类型tab

        selectByStatus(driver, 2);//筛选已回复的数据
        if (CommonMethod.isJudgingElement(driver, By.xpath(trURL))) {//校验是否有数据
            List<WebElement> trs = driver.findElements(By.xpath(trURL));//数据list
            trs.get(trs.size() - 1).findElement(By.xpath("td[2]/div/div/i")).click();//选中当页最后一条数据
            driver.findElement(By.cssSelector("button.ll-btn.btn-open")).click();//点击公开
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
            System.out.println("~~~ open()，" + strType + "公开，执行成功 ~~~");
        } else System.out.println(strType +"没有待公开的数据");
        Thread.sleep(3000);
    }

    //取消公开
    public static void close(int type) throws InterruptedException {
        String strType = switchTab(type);//切换到该类型tab
        selectByStatus(driver, 3);//筛选已回复的数据
        if (CommonMethod.isJudgingElement(driver, By.xpath(trURL))) {//校验是否有数据
            List<WebElement> trs = driver.findElements(By.xpath(trURL));//数据list
            trs.get(trs.size() - 1).findElement(By.xpath("td[2]/div/div/i")).click();//选中当页最后一条数据
            driver.findElement(By.cssSelector("button.ll-btn.btn-cancle")).click();//点击取消公开
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
            System.out.println("~~~ close()，" + strType + "取消公开，执行成功 ~~~");
        } else System.out.println(strType +"没有已公开的数据");
        Thread.sleep(3000);
    }

    //删除
    public static void delete(int type) throws InterruptedException {
        String strType = switchTab(type);//切换到该类型tab
        if (CommonMethod.isJudgingElement(driver, By.xpath(trURL))) {//校验是否有数据
            List<WebElement> trs = driver.findElements(By.xpath(trURL));//数据list
            trs.get(trs.size() - 1).findElement(By.xpath("td[2]/div/div/i")).click();//选中当页最后一条数据
            driver.findElement(By.cssSelector("button.ll-btn.btn-del")).click();//点击删除
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
            System.out.println("~~~ delete()，" + strType + "删除，执行成功 ~~~");
        } else System.out.println(strType +"没有数据");
        Thread.sleep(3000);
    }

    //按照状态筛选
    private static void selectByStatus(WebDriver driver, int status) throws InterruptedException {
        driver.findElement(By.cssSelector("button.ll-btn.ll-reset")).click();//点击重置
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@class='layui-form ll-search clearfix']/div[4]/div/div/div")).click();//点击状态筛选框
        List<WebElement> liStatus = driver.findElements(By.xpath("//form[@class='layui-form ll-search clearfix']/div[4]/div/div/div/dl/dd"));//状态列表
        if (status < 1 || status > 3) status = 1;//1，待回复；2，已回复；3，已公开
        liStatus.get(status + 1).click();//点击对应状态
        Thread.sleep(200);
        driver.findElement(By.id("searchBtn")).click();//点击搜索
        Thread.sleep(1000);
    }

    //tab页切换
    private static String switchTab(int type) throws InterruptedException {
        String strTab = "咨询";
        switch (type) {
            case 1://1，咨询，切换到咨询
                driver.findElement(By.xpath("//ul[@class='ll-nav clearfix']/li[1]")).click();
                break;
            case 2://2，搜索，切换到搜索
                strTab = "投诉";
                driver.findElement(By.xpath("//ul[@class='ll-nav clearfix']/li[2]")).click();
                break;
            case 3://3，领导信息，切换到领导信箱
                strTab = "领导信箱";
                driver.findElement(By.xpath("//ul[@class='ll-nav clearfix']/li[3]")).click();
                break;
            default://默认咨询
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
            if (!driver.findElement(By.xpath("//div[@class='nav-right fr']/ul/li/a")).getText().contains(siteName)) {
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.className("nav-right fr"))).perform();
                Thread.sleep(500);
                driver.findElement(By.linkText(siteName)).click();
                Thread.sleep(3000);
            }
            driver.findElement(By.xpath("//ul[@class='nav-list']/li[1]/a")).click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
