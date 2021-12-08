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

@Epic("群组对讲页面-按麦讲话")
@Feature("开始创造测试数据")
public class LoginAndSpeak extends AbstractTest {
	 private String casename = "按麦讲话";
	 static String user1;
	 static String pwd1;
	 @Override
		@BeforeTest
		public void setUp(){
			Utils.print("casename: " + casename);
		}
	 @Test(dataProvider = "loginSuccess",alwaysRun = true, dataProviderClass = DataPro.class)
	 public static void getFirst(String account,String password) {			
		 user1 = account;	
		 pwd1=password;		
		}
	 @Story("开始执行测试用例")
		@Test
		public void test(){
			
			AbstractPage.login(user1,pwd1);
			// 新建群组
			AbstractGroupFunction.newgroup();
			Utils.waitDefaultTime();
            // 在群组对讲页面长按ptt键讲话	      
	        for (int i=1;i<3;i++){
	        	System.out.println("第" + i + "次讲话");
	        	AbstractGroupFunction.ptt(10000);	       
	           //i=i+1;
	           //Utils.print("casename" + i + "次");
	        	//Utils.print(casename + "---->PASS");
	        	}
	        Utils.print(casename + "---->PASS");
	        // 解散群组
			AbstractGroupFunction.dismissgroup1();
	        }
		
	
}
