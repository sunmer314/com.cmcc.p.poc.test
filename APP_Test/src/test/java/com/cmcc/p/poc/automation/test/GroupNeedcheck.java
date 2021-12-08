package com.cmcc.p.poc.automation.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cmcc.p.poc.automation.core.AbstractPage;
import com.cmcc.p.poc.automation.core.AbstractTest;
import com.cmcc.p.poc.automation.core.Utils;
import com.cmcc.p.poc.automation.pagefunction.AbstractGroupFunction;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
@Epic("群组对讲页面-群组内-群组信息-加群设置-需审核验证")
@Feature("开始创造测试数据")
public class GroupNeedcheck extends AbstractTest {

	private String casename = "进群验证功能";
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
	public static void getFirst(String account, String password) {
		user1 = account;
		pwd1 = password;
	}

	@Test(dataProvider = "loginSuccess", alwaysRun = true, dataProviderClass = DataPro.class)
	// 账号密码登录
	public static void getsecond(String account, String password) {
		user2 = account;
		pwd2 = password;
	}
	@Story("开始执行测试用例")
	@Test
	public void test() {
		// A登录
		AbstractPage.login(user1, pwd1);
		// 新建群组
		AbstractGroupFunction.newgroup();
		Utils.waitDefaultTime();
		// 获取群组名称
		String groupname = AbstractPage.findElementById("com.cmcc.p.poc:id/tv_group_name").getText();
		System.out.println("群组名称：" + groupname);
		// 开启进群验证
		AbstractGroupFunction.NeedCheck();
		// 分享群口令
		AbstractPage.findElementById("com.cmcc.p.poc:id/rl_invivate_command").click();
		String code = AbstractPage.findElementById("com.cmcc.p.poc:id/token").getText();
		System.out.println("群口令：" + code);
		Utils.waitDefaultTime();
		// 点击取消
		AbstractPage.findElementByName("取消").click();
		// 点击返回，返回到对讲页面
		AbstractPage.findElementById("com.cmcc.p.poc:id/title_left_text").click();
		// A退出登录
		AbstractPage.logout();
		// 账号B登录，并输入群口令，加入群组
		AbstractPage.login(user2, pwd2);
		// 加入群组
		AbstractGroupFunction.joingroup(code);
		if (AbstractPage.whetherElementVisable(groupname, 1)) {
			Utils.print("口令加入成功");
		} else {
			// 加入失败就返回到对讲页面
			AbstractPage.findElementById("com.cmcc.p.poc:id/title_left_text").click();
		}
		// B退出登录
		AbstractPage.logout();
		// 账号A登录，在群组通知查看进群申请
		AbstractPage.login(user1, pwd1);
		// 对讲页面，点击群组
		AbstractPage.findElementByName("群组").click();
		Utils.waitShortTime();
		// 点击群组通知铃铛
		AbstractPage.findElementById("com.cmcc.p.poc:id/title_right_edit").click();
		Utils.waitShortTime();
		// 目前只能点击第一个
		// 找到第一个，然后点击同意
		AbstractPage.findElementById("com.cmcc.p.poc:id/btn_agree").click();
		//等待1s
		Utils.waitShortTime();
		//点击确定
		AbstractPage.findElementById("com.cmcc.p.poc:id/tv_confirm").click();
		//等待1s
		Utils.waitShortTime();
		// 点击返回，进入群组列表页面
		AbstractPage.findElementById("com.cmcc.p.poc:id/title_left_text").click();
		//等待1s
		Utils.waitShortTime();
		//点击返回，进入群组对讲页面
		AbstractPage.findElementById("com.cmcc.p.poc:id/title_left_text").click();
		// 点击加群设置,取消进群验证
		AbstractGroupFunction.Unlimited();
		// A退出登录
		AbstractPage.logout();
		// B登录
		AbstractPage.login(user2, pwd2);
		// 对讲页面，点击群组
		AbstractPage.findElementByName("群组").click();
		Utils.waitShortTime();
		if (AbstractPage.whetherElementVisable(groupname, 2)) {
			Utils.waitDefaultTime();
			Utils.print(casename + "---->pass");
		// 点击返回，进入群组列表页面
		AbstractPage.findElementById("com.cmcc.p.poc:id/title_left_text").click();
		// B退出登录
		AbstractPage.logout();
		// A登录
		AbstractPage.login(user1, pwd1);
		// 解散群组
		AbstractGroupFunction.dismissgroup1();		
		}
	}
}
