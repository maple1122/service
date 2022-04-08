package service;

import base.CommonMethod;
import base.LoginPortal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * 服务
 *
 * @author wufeng
 * @date 2022/3/31 13:59
 */
public class Service extends LoginPortal {

    static WebDriver driver;

    //新建服务
    public static void addService() throws InterruptedException {
        getAutoCategory();
        int count = (int) (1 + Math.random() * 99);
        driver.findElement(By.id("addService")).click();
        Thread.sleep(500);
        driver.findElement(By.name("title")).sendKeys("autotest" + count);
        driver.findElement(By.id("addIconBtn1")).sendKeys("D:/autotest/resources/imgTest.jpg");
        Thread.sleep(500);
        driver.findElement(By.xpath("//form[@id='addServiceForm']/div[3]/div/div[2]/i")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath("//form[@id='addServiceForm']/div[@class='layui-form-item link-url']/div/input")).sendKeys("http://www.baidu.com");
        Thread.sleep(500);
        driver.findElement(By.className("layui-layer-btn0")).click();
        System.out.println("~~~ addService()，新建服务，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //编辑服务
    public static void edit() throws InterruptedException {
        getAutoCategory();
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//div[@id='normalList']/a")))
            addService();
        int count = (int) (1 + Math.random() * 99);
        List<WebElement> services = driver.findElements(By.xpath("//div[@id='normalList']/a"));
        if (!CommonMethod.isJudgingElement(services.get(0), By.xpath("img[2]"))) onOrOff();
        driver.findElement(By.xpath("//div[@id='normalList']/a[1]")).click();
        Thread.sleep(200);
        driver.findElement(By.id("editService")).click();
        Thread.sleep(500);
        driver.findElement(By.name("title")).clear();
        driver.findElement(By.name("title")).sendKeys("autoEdit" + count);
        Thread.sleep(200);
        driver.findElement(By.className("layui-layer-btn0")).click();
        System.out.println("~~~ edit()，编辑服务，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //签发服务
    public static void publish() throws InterruptedException {
        getAutoCategory();
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//div[@id='normalList']/a")))
            addService();
        List<WebElement> services = driver.findElements(By.xpath("//div[@id='normalList']/a"));
        if (CommonMethod.isJudgingElement(services.get(0), By.xpath("img[2]"))) onOrOff();
        driver.findElement(By.xpath("//div[@id='normalList']/a[1]")).click();
        driver.findElement(By.id("publishService")).click();
        Thread.sleep(500);
        Boolean selected = CommonMethod.getPublishChannel(driver, "测试test");
        Thread.sleep(200);
        if (selected) {
            driver.findElement(By.cssSelector("button.ll-btn.btn-yes.fl")).click();//已选择频道，点击确定签发
            System.out.println("~~~ publish()，签发服务，执行成功 ~~~");
        } else {
            driver.findElement(By.cssSelector("button.ll-btn.btn-no.fl")).click();//未选择频道，点击取消签发
            System.out.println("没找到签发频道");
        }
        Thread.sleep(5000);
    }

    //签发服务
    public static void onOrOff() throws InterruptedException {
        getAutoCategory();
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//div[@id='normalList']/a")))
            addService();
        List<WebElement> services = driver.findElements(By.xpath("//div[@id='normalList']/a"));
        services.get(0).click();
        Thread.sleep(200);
        driver.findElement(By.id("onOrOffService")).click();
        Thread.sleep(500);
        if (driver.findElement(By.id("onOrOffService")).getText().equals("下线"))
            System.out.println("~~~ onOrOff()，服务上线，执行成功 ~~~");
        else System.out.println("~~~ onOrOff()，服务下线，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //删除服务
    public static void delete() throws InterruptedException {
        getAutoCategory();
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//div[@id='normalList']/a")))
            addService();
        List<WebElement> services = driver.findElements(By.xpath("//div[@id='normalList']/a"));
        for (int i = services.size(); i > 0; i--) {
            if (!CommonMethod.isJudgingElement(driver, By.xpath("//div[@id='normalList']/a[" + i + "]/img[2]"))) {
                driver.findElement(By.xpath("//div[@id='normalList']/a[" + i + "]")).click();
                Thread.sleep(200);
                driver.findElement(By.id("onOrOffService")).click();
                Thread.sleep(1500);
            }
            driver.findElement(By.xpath("//div[@id='normalList']/a[" + i + "]")).click();
            Thread.sleep(200);
            driver.findElement(By.id("delService")).click();
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();
            Thread.sleep(2000);
        }
        System.out.println("~~~ delete()，删除服务，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //打开自动化测试分组
    private static void getAutoCategory() throws InterruptedException {
        WebElement serviceCategory = searchAuto();
        if (serviceCategory == null) {
            ServiceCategory.addCategory(false);
            serviceCategory = searchAuto();
        }
        serviceCategory.click();
        Thread.sleep(2000);
    }

    //搜索自动化测试分组
    private static WebElement searchAuto() {
        WebElement autoTest = null;
        List<WebElement> category = driver.findElements(By.xpath("//div[@id='categoryList']/div"));
        for (int i = 0; i < category.size(); i++) {
            if (category.get(i).findElement(By.xpath("a/span")).getText().equals("autoTest")) {
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
