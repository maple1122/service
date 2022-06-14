package service;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/3/31 15:54
 */
public class ServiceCategoryTest {

    @Test(priority = 1)//新建自动化分类
    public void testAddCategory() throws InterruptedException {
        ServiceCategory.addCategory(true);
        ServiceCategory.addCategory(false);
    }

    @Test(priority = 2)//创建子分类
    public void testAddChild() throws InterruptedException {
        ServiceCategory.addChild();
    }

    @Test(priority = 3)//编辑分类
    public void testEdit() throws InterruptedException {
        ServiceCategory.edit();
    }

//    @Test(priority = 4)//删除分类
//    public void testDelete() throws InterruptedException {
//        ServiceCategory.delete();
//    }

    @BeforeMethod
    public void testStart(Method method) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: "
                + method.getName());
    }

    @AfterMethod
    public void testEnd(Method method) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<< Test End!\n");
    }
}