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
 * @date 2022/4/1 10:50
 */
public class Department extends LoginPortal {
    static WebDriver driver;

    //新建单位
    public static void addDepartment() throws InterruptedException {
        search();//搜索测试单位数据
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='unitList']/li/i"))) {//校验是否有数据
            driver.findElement(By.xpath("//p[@class='add-depart']/i")).click();//点击添加单位
            Thread.sleep(500);
            driver.findElement(By.name("unitName")).sendKeys("测试单位autotest");//录入名称
            driver.findElement(By.name("code")).sendKeys("autotest");//录入编码
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
            System.out.println("~~~ addDepartment()，新建单位，执行成功 ~~~");
        } else System.out.println("测试单位已存在");
        Thread.sleep(3000);
    }

    //编辑测试单位
    public static void editDepartment() throws InterruptedException {
        search();//搜索测试单位
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='unitList']/li/i"))) {//校验是否没有数据
            addDepartment();//添加部门
            search();//搜索测试单位
        }
        driver.findElement(By.xpath("//ul[@id='unitList']/li[1]/i[@class='edit-icon']")).click();//点击第一条数据的编辑
        Thread.sleep(500);
        driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
        System.out.println("~~~ editDepartment()，编辑测试单位，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //删除测试单位
    public static void deleteDepartment() throws InterruptedException {
        search();//搜索测试单位
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='unitList']/li/i"))) {//校验是否没有数据
            addDepartment();//添加部门
            search();//搜索测试单位
        }
        driver.findElement(By.xpath("//ul[@id='unitList']/li[1]/i[2]")).click();//点击第一条数据的删除
        Thread.sleep(200);
        driver.findElement(By.className("layui-layer-btn0")).click();//点击确定
        System.out.println("~~~ deleteDepartment()，删除测试单位，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //添加用户
    public static void addUser() throws InterruptedException {
        int num = 1;
        search();//搜索测试单位
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='unitList']/li/i"))) {//校验是否没有数据
            addDepartment();//添加部门
            search();//搜索测试单位
        }
        Thread.sleep(1000);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr"))) {//校验是否有数据
            List<WebElement> trs = driver.findElements(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr"));//数据列表
            num = trs.size() + 1;//已有数量
        }
        driver.findElement(By.cssSelector("button.ll-btn.btn-add")).click();//点击添加
        Thread.sleep(500);
        driver.findElement(By.name("username")).sendKeys("usertest" + num);//录入用户账号
        driver.findElement(By.name("name")).sendKeys("用户" + num);//录入用户名称
        driver.findElement(By.name("mail")).sendKeys("usertest" + num + "@sina.com");//录入用户邮箱
        Thread.sleep(200);
        driver.findElement(By.className("layui-layer-btn0")).click();//点击保存
        System.out.println("~~~ addUser()，添加用户，执行成功 ~~~");
        Thread.sleep(3000);
    }

    //编辑用户
    public static void editUser() throws InterruptedException {
        search();//搜索测试单位
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='unitList']/li/i"))) {//校验是否没有数据
            addDepartment();//添加部门
            search();//搜索测试单位
            addUser();//添加用户
        }
        Thread.sleep(1000);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr"))) {//校验是否有数据
            List<WebElement> trs = driver.findElements(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr"));//数据列表
            trs.get(0).findElement(By.xpath("td[@class='layui-table-col-special']/div/i[1]")).click();//点击第一条的编辑
            Thread.sleep(200);
            driver.findElement(By.name("name")).clear();//清空用户名称编辑框
            driver.findElement(By.name("name")).sendKeys("用户编辑1");//录入编辑后的用户名称
            Thread.sleep(200);
            driver.findElement(By.className("layui-layer-btn0")).click();//点击保存
            System.out.println("~~~ editUser()，编辑用户，执行成功 ~~~");
        } else System.out.println("没有用户数据可编辑");
        Thread.sleep(3000);
    }

    //删除用户
    public static void deleteUser() throws InterruptedException {
        search();//搜索测试单位
        if (!CommonMethod.isJudgingElement(driver, By.xpath("//ul[@id='unitList']/li/i"))) {//校验是否没有数据
            addDepartment();//添加部门
            search();//搜索测试单位
            addUser();//添加用户
        }
        Thread.sleep(1000);
        if (CommonMethod.isJudgingElement(driver, By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr"))) {//校验是否有数据
            List<WebElement> trs = driver.findElements(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr"));//数据list
            for (int i = trs.size(); i > 0; i--) {
                driver.findElement(By.xpath("//div[@class='layui-table-body layui-table-main']/table/tbody/tr[" + i + "]/td[@class='layui-table-col-special']/div/i[2]")).click();//点击该数据的删除
                Thread.sleep(200);
                driver.findElement(By.className("layui-layer-btn0")).click();//确定删除
                Thread.sleep(1000);
            }
            System.out.println("~~~ editUser()，编辑用户，执行成功 ~~~");
        } else System.out.println("没有用户数据可删除");
        Thread.sleep(3000);
    }

    //搜索测试单位
    private static void search() throws InterruptedException {
        driver.findElement(By.id("keywordMedia")).clear();//清空搜索关键词
        driver.findElement(By.id("keywordMedia")).sendKeys("autotest");//录入搜索关键词
        driver.findElement(By.cssSelector("button.layui-btn.fl.layui-btn-primary.reset-media.search-btn")).click();//点击搜索
        Thread.sleep(500);
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
            driver.findElement(By.xpath("//ul[@class='nav-list']/li[4]")).click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
