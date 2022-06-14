package politic;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.*;

/**
 * @author wufeng
 * @date 2022/4/1 11:34
 */
public class DepartmentTest {

    @Test(priority = 1)//添加测试单位
    public void testAddDepartment() throws InterruptedException {
        Department.addDepartment();
    }

    @Test(priority = 2)//编辑测试单位
    public void testEditDepartment() throws InterruptedException {
        Department.editDepartment();
    }

//    @Test(priority = 6)//删除测试单位
//    public void testDeleteDepartment() throws InterruptedException {
//        Department.deleteDepartment();
//    }

    @Test(priority = 3)//添加用户
    public void testAddUser() throws InterruptedException {
        Department.addUser();
    }

    @Test(priority = 4)//编辑用户
    public void testEditUser() throws InterruptedException {
        Department.editUser();
    }

//    @Test(priority = 5)//删除用户
//    public void testDeleteUser() throws InterruptedException {
//        Department.deleteUser();
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