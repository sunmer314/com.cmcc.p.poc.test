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
@Epic("好友页面-群修改好友备注-修改成功")
@Feature("开始创造测试数据")
public class Updatefriendname extends AbstractTest {
	private String casename = "修改好友备注-修改成功";
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
		// 搜索存在的好友
		FriendPageAction.searchFriend1(user1);
	    // 获取好友现在的备注名
	 	String name = AbstractPage.findElementById("com.cmcc.p.poc:id/tv_name").getText();
	 	System.out.print(name);
	 	//点击该好友，进入其详情
	    AbstractPage.findElementById("com.cmcc.p.poc:id/rl").click();
	    //修改备注，使备注名和用户账号一致
		FriendPageAction.updateFriendname(user1);
		// 断言
	    Object text = AbstractPage.findElementById("com.cmcc.p.poc:id/tv_remark").getText();
	    // System.out.print(text);
	    Assert.assertEquals(text,user1);
	    Utils.print("修改昵称成功--->PASS");
	    // 再次修改备注
	    FriendPageAction.updateFriendname(name);
	    //等待1s
	  	Utils.waitShortTime();
	    //点击返回
	    AbstractPage.findElementById("com.cmcc.p.poc:id/title_left_text").click();
	    //等待1s
	  	Utils.waitShortTime();
	    AbstractPage.findElementById("com.cmcc.p.poc:id/title_left_text").click();
	    //等待1s
	  	Utils.waitShortTime();
	    //删除好友
	  	FriendPageAction.deleteFirstFriend2();
		// 退出登录
	 	AbstractPage.logout(); 	
	}
}
