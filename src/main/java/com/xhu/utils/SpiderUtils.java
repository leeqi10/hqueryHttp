package com.xhu.utils;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.scheduling.annotation.Scheduled;
public class SpiderUtils {
    /** 爬取后的页面初始页面内容 */
    public static String pageCon;
    public static  String Url;
    @Scheduled(initialDelay = 100, fixedDelay =  20 * 1000)
    public static void spiderRun(String param) {
        /** 需要爬取页面：*** */
         Url = "https://36kr.com/search/articles/"+param;
        System.out.println(Url);
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setThrowExceptionOnScriptError(false);   // 当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false); // 当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false); // 不启用ActiveX
        webClient.getOptions().setCssEnabled(false);    // 是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true);  // 很重要，启用JS
        webClient.getOptions().setDownloadImages(false);    // 不下载图片
        webClient.getOptions().setTimeout(10*1000);//设置超时
        webClient.setAjaxController(new NicelyResynchronizingAjaxController()); // 很重要，设置支持AJAX
        webClient.waitForBackgroundJavaScript(30* 1000);   // 异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束
        HtmlPage page = null;
        try {
            page = webClient.getPage(Url);  // 尝试加载给出的网页
        } catch (Exception e) {
            System.out.println("爬取失败");
            e.printStackTrace();
        } finally {
            webClient.close();
        }

        String pageXml = page.asXml();  // 直接将加载完成的页面转换成xml格式的字符串

        pageCon = pageXml;
    }
}
