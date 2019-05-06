//package com.duan.video.service.impl;
//
//import cn.wanghaomiao.seimi.annotation.Crawler;
//import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
//import cn.wanghaomiao.seimi.struct.Request;
//import cn.wanghaomiao.seimi.struct.Response;
//import org.seimicrawler.xpath.JXDocument;
//
//import java.util.List;
//
//@Crawler(name = "basic")
//public class VideoCrawler  extends BaseSeimiCrawler {
//    @Override
//    public String[] startUrls() {
//        //两个是测试去重的
//        return new String[]{"http://www.cnblogs.com/", "http://www.cnblogs.com/"};
//    }
//
//    @Override
//    public void start(Response response) {
//        JXDocument doc = response.document();
//        try {
//            List<Object> urls = doc.sel("//a[@class='titlelnk']/@href");
//            logger.info("{}", urls.size());
//            for (Object s : urls) {
//                push(Request.build(s.toString(), VideoCrawler::getTitle));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getTitle(Response response) {
//        JXDocument doc = response.document();
//        try {
//            logger.info("url:{} {}", response.getUrl(), doc.sel("//h1[@class='postTitle']/a/text()|//a[@id='cb_post_title_url']/text()"));
//            //do something
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//package com.duan.video.service.impl;
//
//import cn.wanghaomiao.seimi.annotation.Crawler;
//import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
//import cn.wanghaomiao.seimi.struct.Request;
//import cn.wanghaomiao.seimi.struct.Response;
//import org.seimicrawler.xpath.JXDocument;
//
//import java.util.List;
//
//@Crawler(name = "basic")
//public class VideoCrawler  extends BaseSeimiCrawler {
//    @Override
//    public String[] startUrls() {
//        //两个是测试去重的
//        return new String[]{"http://www.cnblogs.com/", "http://www.cnblogs.com/"};
//    }
//
//    @Override
//    public void start(Response response) {
//        JXDocument doc = response.document();
//        try {
//            List<Object> urls = doc.sel("//a[@class='titlelnk']/@href");
//            logger.info("{}", urls.size());
//            for (Object s : urls) {
//                push(Request.build(s.toString(), VideoCrawler::getTitle));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getTitle(Response response) {
//        JXDocument doc = response.document();
//        try {
//            logger.info("url:{} {}", response.getUrl(), doc.sel("//h1[@class='postTitle']/a/text()|//a[@id='cb_post_title_url']/text()"));
//            //do something
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
