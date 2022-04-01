package service;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * 服务分组
 *
 * @author wufeng
 * @date 2022/3/31 14:04
 */
public class ServiceCategory extends LoginPortal {

    static WebDriver driver;

    //新建分组
    public static void addCategory(Boolean isHaveChild) throws InterruptedException {
        String categoryName = "autoTest";
        if (isHaveChild) categoryName = "autoTestHC";
        if (searchAuto(categoryName) == null) {
            driver.findElement(By.id("addCategory")).click();
            Thread.sleep(200);
            if (isHaveChild)
                driver.findElement(By.xpath("//div[@class='layui-form-item control-item']/div/div[1]/i")).click();
            driver.findElement(By.name("groupname")).sendKeys(categoryName);
            driver.findElement(By.name("groupcode")).sendKeys(categoryName);
            driver.findElement(By.className("layui-layer-btn0")).click();
            System.out.println("~~~ addCategory()，新建自动化分组，执行成功 ~~~");
        } else System.out.println("已经存在autoTest分类");
        Thread.sleep(3000);
    }

    //新建子分组
    public static void addChild() throws InterruptedException {
        if (searchAuto("autoTestHC") == null) addCategory(true);
        int count = (int) (1 + Math.random() * 100000);
        WebElement category = searchAuto("autoTestHC");
        category.findElement(By.xpath("a/span")).click();
        Thread.sleep(500);
        category.findElement(By.xpath("a/div/span[1]")).click();
        Thread.sleep(200);
        driver.findElement(By.name("groupname")).sendKeys("auto" + count);
        driver.findElement(By.name("groupcode")).sendKeys("auto" + count);
        Thread.sleep(500);
        driver.findElement(By.className("layui-layer-btn0")).click();
        System.out.println("~~~ addChild()，创建子分类，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //编辑分组
    public static void edit() throws InterruptedException {
        if (searchAuto() != null) {
            WebElement category = searchAuto();
            category.click();
            category.findElement(By.xpath("a/div/span[1]")).click();
            Thread.sleep(500);
            driver.findElement(By.name("groupname")).clear();
            driver.findElement(By.name("groupname")).sendKeys("autoTest");
            driver.findElement(By.className("layui-layer-btn0")).click();
            System.out.println("~~~ edit()，编辑分类，执行成功 ~~~");
        } else System.out.println("没有可编辑的自动化分类");
        Thread.sleep(3000);
    }

    //删除分组
    public static void delete() throws InterruptedException {
        Boolean delete = false;
        List<WebElement> categories = driver.findElements(By.xpath("//div[@id='categoryList']/div"));
        for (int i = categories.size(); i>0; i--) {
            if (driver.findElement(By.xpath("//div[@id='categoryList']/div[" + i + "]/a/span")).getText().contains("autoTest")) {
                if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@id='categoryList']/div[" + i + "]/div"))) {
                    List<WebElement>children= driver.findElements(By.xpath("//div[@id='categoryList']/div[" + i + "]/div/div"));
                    for (int c=children.size();c>0;c--){
                        driver.findElement(By.xpath("//div[@id='categoryList']/div[" + i + "]/div/div["+c+"]")).click();
                        Thread.sleep(200);
                        driver.findElement(By.xpath("//div[@id='categoryList']/div[" + i + "]/div/div["+c+"]/a/div/span[@class='del-btn']")).click();
                        Thread.sleep(200);
                        driver.findElement(By.className("layui-layer-btn0")).click();
                        Thread.sleep(2000);
                    }
                }
                driver.findElement(By.xpath("//div[@id='categoryList']/div[" + i + "]")).click();
                Thread.sleep(200);
                driver.findElement(By.xpath("//div[@id='categoryList']/div[" + i + "]/a/div/span[@class='del-btn']")).click();
                Thread.sleep(200);
                driver.findElement(By.className("layui-layer-btn0")).click();
                delete=true;
                Thread.sleep(3000);
            }
        }
        if (delete) System.out.println("~~~ delete()，删除auto分组，执行成功 ~~~");
        else System.out.println("没有可删除的自动化分组");
        Thread.sleep(3000);
    }

    //搜索自动化分组
    public static WebElement searchAuto() {
        return searchAuto("autoTest");
    }

    //搜索自动化测试分组
    public static WebElement searchAuto(String autokeyword) {
        WebElement autoTest = null;
        List<WebElement> category = driver.findElements(By.xpath("//div[@id='categoryList']/div"));
        for (int i = 0; i < category.size(); i++) {
            if (category.get(i).findElement(By.xpath("a/span")).getText().equals(autokeyword)) {
                autoTest = category.get(i);
                break;
            }
        }
        return autoTest;
    }

    //初始化登录
    static {
        try {
            driver = login();
            for (int i = 0; i < 3; i++) {
                if (!CommonMethod.isJudgingElement(driver, By.tagName("header"))) {
                    if (CommonMethod.isJudgingElement(driver, By.className("loginBtn"))) driver = login();
                    driver.get(domain + "/service/service/serviceList");
                    Thread.sleep(2000);
                } else break;
            }

            if (!driver.findElement(By.xpath("//div[@class='nav-right']/ul/li/a")).getText().contains("爱富县")) {
                Actions action = new Actions(driver);
                action.moveToElement(driver.findElement(By.className("nav-right"))).perform();
                Thread.sleep(500);
                driver.findElement(By.linkText("爱富县")).click();
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
