package com.cmcc.p.poc.automation.test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cmcc.p.poc.automation.core.AbstractPage;
import com.cmcc.p.poc.automation.core.AbstractTest;
import com.cmcc.p.poc.automation.core.Utils;
import com.cmcc.p.poc.automation.pagefunction.FriendPageAction;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
@Epic("好友页面-添加和删除成员")
@Feature("꪿Ê创造测试数据")
public class Addfriend extends AbstractTest {
	private String casename = "添加/删除好友";
	static String user1;
	static String pwd1;
	static String user2;
	static String pwd2;
	@Override
	@BeforeTest
	public void setUp() {
		Utils.print("casename: " + casename);
	}

	@Test(dataProvider = "loginSuccess", alwaysRun = true, dataProviderClass = DataPro.class)
	// 账号密码登录
	public static void getsecond(String account, String password) {
		user2 = account;
		pwd2 = password;
	}

	@Test(dataProvider = "loginSuccess", alwaysRun = true, dataProviderClass = DataPro.class)
	public static void getFirst(String account, String password) {
		user1 = account;
		pwd1 = password;
	}
	@Story("开始执行测试用例")
	@Test
	public void test() {
		// 登录
		AbstractPage.login(user1, pwd1);
		// 添加好友
		FriendPageAction.AddFriend1(user2);
		// 退出登录
		AbstractPage.logout();
		// 好友账号登录
		AbstractPage.login(user2, pwd2);
		// 同意好友申请
		FriendPageAction.acceptFriend1();
		// 退出登录
		AbstractPage.logout();
		// 账号登录
		AbstractPage.login(user1, pwd1);
		// 搜索存在的好友
		FriendPageAction.searchFriend1(user2);
		// 断言
	    Object text = AbstractPage.findElementById("com.cmcc.p.poc:id/tv_tip").getText();
	    System.out.print(text);
	    Assert.assertEquals(text,"搜索结果(1)");
	    Utils.print("添加好友--->PASS");
	    //点击返回
	    AbstractPage.findElementById("com.cmcc.p.poc:id/title_left_text").click();
		//删除好友
		Utils.waitDefaultTime();
		FriendPageAction.deleteFirstFriend2();
	    
	}

}