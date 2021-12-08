package com.cmcc.p.poc.automation.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cmcc.p.poc.automation.core.AbstractPage;
import com.cmcc.p.poc.automation.core.AbstractTest;
import com.cmcc.p.poc.automation.core.Utils;
import com.cmcc.p.poc.automation.pagefunction.PersonalCenterPage;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("个人中心-设置功能页面测试")
@Feature("开始创造测试数据")
public class SetUp extends AbstractTest {
	private String casename = "检查设置功能";

	static String user1;
	static String pwd1;

	@Override
	@BeforeTest
	public void setUp() {
		Utils.print("casename: " + casename);
	}

	@Test(dataProvider = "loginSuccess", alwaysRun = true, dataProviderClass = DataPro.class)
	public static void getFirst(String account, String password) {
		user1 = account;
		pwd1 = password;
	}

	@Story("开始执行测试用例")
	@Test
	public void test() {
		
		AbstractPage.login(user1,pwd1);
		Utils.waitDefaultTime();
		// 点击个人中心
		PersonalCenterPage.clickPersionalcenter();
		//PersonalCenterPage.clickPersionalcenter();		
		//上滑
		AbstractPage.swipeup();
		// 点击设置
		PersonalCenterPage.clickSetup();
		Utils.waitShortTime();
		// 点击修改密码
		PersonalCenterPage.clickModifypwd();
		Utils.waitShortTime();
		// 点击常见问题
		PersonalCenterPage.clickProblem();	
		Utils.waitShortTime();
		// 点击关于和对讲
		PersonalCenterPage.clickAbout();
		Utils.waitShortTime();
		// 点击分享APP
		PersonalCenterPage.clickShareApp();
		PersonalCenterPage.clickCancel();
		Utils.waitShortTime();
		// 点击返回
		AbstractPage.findElementByName("返回").click();
		Utils.waitDefaultTime();
		Utils.waitDefaultTime();		
		//上滑
		AbstractPage.swipeup();
		// 输入意见反馈信息
		AbstractPage.findElementById("com.cmcc.p.poc:id/et_feed_back").setValue("feedback test");
		Utils.waitDefaultTime();
		// 点击提交
		AbstractPage.findElementById("com.cmcc.p.poc:id/tv_submit").click();
		Utils.print(casename + "---->PASS");
		
	}

//	@AfterTest
	//public void tearDown() {
	//	Utils.snapshot(driver, this.getClass().getSimpleName());
	//}
	
	
}
