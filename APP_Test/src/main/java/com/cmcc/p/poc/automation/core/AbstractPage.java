package com.cmcc.p.poc.automation.core;

import static com.cmcc.p.poc.automation.core.AbstractTest.driver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/**
 * 基类页面
 * 
 *
 */
public class AbstractPage {
	static int width = driver.manage().window().getSize().width;
	static int height = driver.manage().window().getSize().height;
    static Duration duration=Duration.ofMillis(300);
	
    @SuppressWarnings("unchecked")
    // 通过文本查找元素，返回元素列表
    public static List<MobileElement> findElementsByName(String text) {
        List<MobileElement> els1 = null;
        String queryText = "new UiSelector().text(\"" + text + "\")";
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                els1 = driver.findElementsByAndroidUIAutomator(queryText);
                Utils.print("findElement " + text + " success");
                return els1;
            } catch (NoSuchElementException e) {
                if (attempt == 2) {
                    Utils.print("findElement " + text + " fail");
                }
            }
        }
        return els1;
    }
   
    // 通过文本查找元素，返回单个元素
    public static MobileElement findElementByName(String text) {
        MobileElement els = null;
        String queryText = "new UiSelector().text(\"" + text + "\")";
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                els = (MobileElement) driver.findElementByAndroidUIAutomator(queryText);
                Utils.print("findElement " + text + " success");
                return els;
            } catch (NoSuchElementException e) {
                try {
                    els = (MobileElement) driver.findElementByAccessibilityId(text);
                    Utils.print("findElement " + text + " success");
                    return els;  

                } catch (NoSuchElementException d) {
                    if (attempt == 2) {
                        Utils.print("findElement " + text + " fail");
                    }
                }
            }
        }
        return els;
    }

    // 查找某个元素
    public static void untilElementVisable(final By by, int waitTimes) {
        for (int attempt = 0; attempt < waitTimes; attempt++) {
            System.out.println(Utils.formatTime() + "find element:" + by);
            try {
                AbstractTest.driver.findElement(by);
                System.out.println(Utils.formatTime() + "find element success");
                break;
            } catch (NoSuchElementException e) {
                System.out.println(Utils.formatTime() + "attempt " + (attempt + 1) + " time fail");
                if (attempt == waitTimes - 1) {
                    Assert.fail(Utils.formatTime() + "find element fail:" + by);
                }
            }
        }
    }

    // 返回元素
    public static WebElement waitforElementVisable(final By by, int waitTimes) {
        WebElement element = null;
        for (int attempt = 0; attempt < waitTimes; attempt++) {
            Utils.print("find element:" + by);
            try {
                element = AbstractTest.driver.findElement(by);
                Utils.print("find element success");
                return element;
            } catch (NoSuchElementException e) {
                Utils.print("attempt " + (attempt + 1) + " time fail");
                if (attempt == waitTimes - 1) {
                    Utils.print("find element fail:" + by);
                }
            }
        }
        return element;
    }



    // 是否能找到某个元素
    // 加assert.fail
    public static void untilElementDisplayed(final By by, int waitTimes) {
        for (int attempt = 0; attempt < waitTimes; attempt++) {
            Utils.print("find element: " + by + " 第" + (attempt + 1) + "次");
            try {
                driver.findElement(by).isDisplayed();
                Utils.print("find element: " + by + " success");
                break;
            } catch (NoSuchElementException e) {

                if (attempt == waitTimes - 1) {
                    Utils.print("find element fail: " + by);
                    Assert.fail(Utils.formatTime() + "find element fail:" + by);
                }
            }
        }
    }

    // android是否能通过文本text找到某个元素
    public static void untilElementDisplayed(String text , int waitTimes) {

        for (int attempt = 0; attempt < waitTimes; attempt++) {
            Utils.print("find element: " + text + " 第" + (attempt + 1) + "次");
            String queryText = "new UiSelector().text(\"" + text + "\")";
            try {
                driver.findElementByAndroidUIAutomator(queryText);
                Utils.print("find element: " + text + " success");
                break;
            } catch (NoSuchElementException e) {
                try {
                    driver.findElementByAccessibilityId(text);
                    Utils.print("findElement " + text + " success");
                    break;  

                } catch (NoSuchElementException d) {
                    if (attempt == 2) {
                        Utils.print("findElement " + text + " fail");
                    }

                }
            }
        }
    }

    // 重复（在用），返回布尔值
    public static boolean whetherElementVisable(final By by, int waitTimes) {
        for (int attempt = 0; attempt < waitTimes; attempt++) {
            Utils.print("find element:" + by);
            try {
                AbstractTest.driver.findElement(by);
                Utils.print("find element success");
                return true;
            } catch (NoSuchElementException e) {
                if (attempt == waitTimes - 1) {
                    Utils.print("find element fail:" + by);
                }
            }
        }
        return false;
    }


    // 是否能通过文本text找到某个元素，返回值为boolean
    public static boolean whetherElementVisable(String text, int waitTimes) {
        for (int attempt = 0; attempt < waitTimes; attempt++) {
            Utils.print("find element: " + text + " 第" + (attempt + 1) + "次");
            String queryText = "new UiSelector().text(\"" + text + "\")";
            try {
                driver.findElementByAndroidUIAutomator(queryText);
                Utils.print("find element: " + text + " success");
                return true;
            } catch (NoSuchElementException e) {
                try {
                    driver.findElementByAccessibilityId(text);
                    return true;
                } catch (NoSuchElementException d) {
                    if (attempt == waitTimes - 1) {
                        Utils.print("find element fail: " + text);
                        //  Assert.fail(Utils.formatTime() + "find element fail:" + text);
                    }
                }
            }
        }
        return false;
    }


   
    public static void untilElementVisableByName(String name, int waitTimes) {
        for (int attempt = 0; attempt < waitTimes; attempt++) {
            Utils.print("try to find element: " + name);
            try {
                AbstractTest.driver.findElementByAccessibilityId(name);
                Utils.print("find element " + name + " success");
                break;
            } catch (NoSuchElementException e) {
                Utils.print("attempt " + (attempt + 1) + " time fail");
                if (attempt == waitTimes - 1) {
                    Assert.fail(Utils.formatTime() + "find element fail: " + name);
                }
            }
        }
    }

    // 与untilElementVisable作用相同，但有返回值
    public static boolean elementVisable(final By by, int waitTimes) {
        boolean vis = false;
        for (int attempt = 0; attempt < waitTimes; attempt++) {
            System.out.println(Utils.formatTime() + "find element:" + by);

            try {
                driver.findElement(by);
                vis = true;
                System.out.println(Utils.formatTime() + "find element success");
                break;
            } catch (NoSuchElementException e) {
                System.out.println(Utils.formatTime() + "attempt " + (attempt + 1) + " time fail");
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            }
        }
        return vis;
    }

    /**
     * 
     * @param 是否能通过name找到某元素
     * @param waitTime
     * @return
     */
    public static boolean whetherElementDisplayedByName(String name, int waitTimes) {
        for (int attempt = 0; attempt < waitTimes; attempt++) {
            Utils.print("try to find element: " + name);
            try {
                driver.findElementByAccessibilityId(name).isDisplayed();
                Utils.print("find element: " + name + " success");
                return true;
            } catch (NoSuchElementException e) {
                if (attempt == waitTimes - 1) {
                    Utils.print("find element fail: " + name);
                }
            }
        }
        return false;
    }

    // 通过文本查找元素并点击
    public static void clickElementByName(String text) {
        findElementByName(text).click();
    }

    public static void clickElementByName(String text, int index) {
        List<MobileElement> els = findElementsByName(text);
        els.get(index).click();
    }
    
    // 通过ID查找元素，返回元素
    public static MobileElement findElementById(String text) {
        MobileElement els = null;
        for (int attempt = 0; attempt < 3; attempt++) {
            try {

                els = (MobileElement) driver.findElementById(text);
                Utils.print("findElement " + text + " success");
                return els;
            } catch (NoSuchElementException e) {
                if (attempt == 2) {
                    Utils.print("findElement " + text + " fail");
                }
            }
        }
        return els;
    }
    
    @SuppressWarnings("unchecked")
	public static List<MobileElement> findElementsById(String text) {
        List<MobileElement> els1 = null;
        @SuppressWarnings("unused")
		String queryText = "new UiSelector().text(\"" + text + "\")";
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                els1 = driver.findElementsById(text);
                Utils.print("findElement " + text + " success");
                return els1;
            } catch (NoSuchElementException e) {
                if (attempt == 2) {
                    Utils.print("findElement " + text + " fail");
                }
            }
        }
        return els1;
    }

    public static void clickElementById(String text) {
        findElementById(text).click();
    }

    public static void acceptAlert() {
        String id = "com.android.packageinstaller:id/permission_allow_button";
        Utils.print("检查有没有权限弹窗 start");
        try {
            driver.findElementById(id).click();
            Utils.print("自动接受权限弹窗");
        } catch (NoSuchElementException e) {
            Utils.print("没有弹窗权限弹窗");
        }
    }
    // 页面中是否含有某元素
    public static boolean findElementByPageSource(String element) {
        String pageSource = driver.getPageSource();
        String quot = "&quot;";
        String pageSourceResult = pageSource.replaceAll(quot, "\"");
        // Utils.print(pageSourceResult);
        if (pageSourceResult.indexOf(element) != -1) {
            Utils.print("find element " + element + " success");
            return true;
        } else {
            Utils.print("find element " + element + " fail");
            return false;
        }
    }
    public static boolean findElementByPageSource(String element ,String replace) {
        String pageSource = driver.getPageSource();
        String quot = "&quot;";
        String pageSourceResult = pageSource.replaceAll(quot, "\"");
        String result = pageSourceResult.replace(replace , "");
        // Utils.print(result);
        if (result.indexOf(element) != -1) {
            Utils.print("find element " + element + " success");
            return true;
        } else {
            Utils.print("find element " + element + " fail");
            return false;
        }
    }

    @SuppressWarnings("rawtypes")
	public static void  swipeToUp() {
    	try {
    	new TouchAction(driver).press(PointOption.point(width/4 , height*3/4)).
    	waitAction(WaitOptions.waitOptions(duration)).
    	moveTo(PointOption.point(width/4, height/4)).release().perform();
    	Thread.sleep(1000);
    	} catch (Exception e) {
    	e.printStackTrace();
    	}

    	}

	 //对讲页面向上滑动
	 @SuppressWarnings("rawtypes")
	public static void swipeup() {
	  // 上滑
	  int width1 = driver.manage().window().getSize().width;// 获取屏幕的宽
	  System.out.println("宽:" + width1);
	  int height1 = driver.manage().window().getSize().height; // 获取屏幕的长
	  System.out.println("高:" + height1);
	  // ·release 相当于放开 ·perform 相当于发送执行
	  Utils.waitDefaultTime();
	  new TouchAction(driver).press(PointOption.point(width1 / 2, height1 *5/6))
	    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(10000)))
	    .moveTo(PointOption.point(width1 / 2, height1 *1/6)).release().perform();
	 }

    
	public static void login(String account ,String pwd) {
		
		
		// 判断是否有升级弹窗，如果有就点击取消
	//if (AbstractPage.whetherElementVisable(By.id("com.cmcc.p.poc:id/tv_info"), 1)) {
	//		AbstractPage.findElementById("com.cmcc.p.poc:id/btn_cancel").click();
		//}
//
    	 Utils.waitDefaultTime();
	        // 判断是否进入了登陆页
	       // AbstractPage.untilElementDisplayed(By.id("com.cmcc.p.poc:id/tv_account_tip"), 1);
	        // 清空账号输入框
	        AbstractPage.findElementById("com.cmcc.p.poc:id/et_phone_num").clear();
	        // 输入用户名
	        AbstractPage.findElementById("com.cmcc.p.poc:id/et_phone_num").setValue(account);
	        // 输入密码
	        AbstractPage.findElementById("com.cmcc.p.poc:id/et_pwd").setValue(pwd);
	        
	       // 勾选记住密码
	        //AbstractPage.findElementById("com.cmcc.p.poc:id/iv_remember").click();
	        // 点击登陆
	        AbstractPage.findElementById("com.cmcc.p.poc:id/tv_login").click();
	        
	        Utils.waitLongTime();
	        
	     // 判断首页是否有公告，如果有就点击同意
			if (AbstractPage.whetherElementVisable(By.id("com.cmcc.p.poc:id/viewPager"), 1)) {
				AbstractPage.findElementById("com.cmcc.p.poc:id/iv_close").click();
		}
			// 判断首页是否有优惠券提示，如果有就点击同意
			if (AbstractPage.whetherElementVisable(By.id("com.cmcc.p.poc:id/tv_msg"), 1)) {
				AbstractPage.findElementById("com.cmcc.p.poc:id/iv_close").click();
		}		
			// 判断首页是否有密码提示，如果有就点击同意
			if (AbstractPage.whetherElementVisable(By.id("com.cmcc.p.poc:id/tv_msg"), 1)) {
				AbstractPage.findElementById("com.cmcc.p.poc:id/iv_close").click();
		}	
			// 判断首页是否有温馨提示，如果有就点击同意
			if (AbstractPage.whetherElementVisable(By.id("com.cmcc.p.poc:id/tv_title"), 1)) {
				AbstractPage.findElementById("com.cmcc.p.poc:id/iv_close").click();
		}			
			
		}
   

	public static void logout() {
		 Utils.waitDefaultTime();

		 	//点击个人中心
	       AbstractPage.findElementById("com.cmcc.p.poc:id/tab_me").click();
	       //等待1s
	       Utils.waitShortTime();
	       //上滑找到元素 兼容手机
	       AbstractPage.swipeup();
	       //点击设置
	       AbstractPage.findElementById("com.cmcc.p.poc:id/rl_me_setting").click();
	       //点击退出登录
	       AbstractPage.findElementById("com.cmcc.p.poc:id/tv_sign_out").click();
	       //点击确定
	       AbstractPage.findElementById("com.cmcc.p.poc:id/tv_confirm").click();
	       Utils.waitLongTime();
	    }

//长按好友
public static void pressClick(String element){
     //  把元素id赋值给longclick
 	 //WebElement longClick = driver.findElement(By.id("com.cmcc.p.poc:id/delete_bg"));
	WebElement longClick = driver.findElementByName("element");
 	 int x =longClick.getLocation().getX();
 	 int y =longClick.getLocation().getY();
 	 Duration duration =Duration.ofMillis(3000);//毫秒级
 	 @SuppressWarnings("rawtypes")
	TouchAction action = new TouchAction(driver);
 	 action.longPress(PointOption.point(x, y)).waitAction(WaitOptions.waitOptions(duration)).release().perform();
}

//长按好友
public static void pressClick1(String element){
   //  把元素id赋值给longclick
	WebElement longClick = driver.findElement(By.id("com.cmcc.p.poc:id/delete_bg"));
	
	 int x =longClick.getLocation().getX();
	 int y =longClick.getLocation().getY();
	 Duration duration =Duration.ofMillis(3000);//毫秒级
	 @SuppressWarnings("rawtypes")
	TouchAction action = new TouchAction(driver);
	 action.longPress(PointOption.point(x, y)).waitAction(WaitOptions.waitOptions(duration)).release().perform();
}




}
