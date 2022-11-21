package com.testgb.utils;
//通过ip查所在城市及相关详细地址


import java.io.IOException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description
 * @Author gb
 * @Data 2022/10/19 11:14
 */
public class QuerySomeByIp {


    public static String queryCityByIp(String ip) {


        HttpClient client = new HttpClient("http://whois.pconline.com.cn/ipJson.jsp?ip="
                                                 +ip+
                                                 "&json=true");

        client.setHttps(true);
        //执行post请求发送
        try {
            client.get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4 得到发送请求返回结果
        //返回内容，是使用xml格式返回
        String xml = null;

        try {
            xml = client.getContent();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        String[] split = xml.split(",");
        String addr = split[3].split(":")[1].substring(1);
        addr = addr.substring(0, addr.length() - 1);

        //返回ip所在市
        return addr;


    }




    public static String queryAddrByIp(String ip) {

        //HttpClient client = new HttpClient("https://www.ipshudi.com/"+ip+".htm");
        //HttpClient client = new HttpClient("https://www.ip.cn/ip/"+ip+".htm");
        HttpClient client = new HttpClient("http://stool.chinaz.com/same?s=117.154.99.132");

        client.setHttps(true);
        //执行post请求发送
        try {
            client.get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4 得到发送请求返回结果
        //返回内容，是使用xml格式返回
        String xml = null;

        try {
            xml = client.getContent();



            //System.out.println(xml);

            String content=xml;

            String regStr="<p class=\"fz14 color-63\" title=\".*\">";
            Pattern pattern=Pattern.compile(regStr);
            Matcher matcher=pattern.matcher(content);



            matcher.find();
            //String addr = matcher.group(0).substring(6).split("<")[0].replace(" ", "");
            String addr = matcher.group(0).substring(32);
            String substring = addr.substring(0, addr.length() - 2);
            System.out.println(substring);



            return addr;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    public static void main(String[] args) throws Exception {



        queryAddrByIp("d");

    }

}
