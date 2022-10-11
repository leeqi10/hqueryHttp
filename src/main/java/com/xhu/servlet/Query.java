package com.xhu.servlet;

import com.xhu.utils.SpiderUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.security.provider.ConfigFile;

import javax.net.ssl.SSLContext;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class Query extends HttpServlet {
    public HttpClient SSLSkip() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        }).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String param1=request.getParameter("param1");
        String param11="";
        String url1=null;
      /*  try {
            HttpClient httpClient = this.SSLSkip();
            String path = "https://so.eastmoney.com/web/s?keyword="+param1;
            HttpGet httpGet=new HttpGet(path);
            HttpResponse httpResponse= httpClient.execute(httpGet);
            int stu=httpResponse.getStatusLine().getStatusCode();
            if (stu==200){
                HttpEntity httpEntity= httpResponse.getEntity();
                String content= EntityUtils.toString(httpEntity,"utf-8");*/
        String content=null;
        SpiderUtils.spiderRun(param1);
        content =SpiderUtils.pageCon;
        url1= SpiderUtils.Url;
        System.out.println(content);
        Document doc=Jsoup.parse(content);

        Elements elements= doc.select("div .title-wrapper.ellipsis-2 .article-item-title.weight-bold");
        for(Element element:elements){
            param11=param11+element.text()+"<br/>"+"\n";
        }
        System.out.println(param11);
        request.setAttribute("param11",param11);
        request.setAttribute("url1",url1);
        request.setAttribute("param1",param1);
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
