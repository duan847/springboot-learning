package com.duan.springboot.learning.oauth;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Slf4j
public class OauthApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * 没有登录直接访问受保护的资源
     */
    @Test
    public void test01show() {
        //get
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:8013/show", String.class);
        //状态码是否为401
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));

        log.info("没有登录直接访问受保护的资源，返回内容：{}", responseEntity.getBody());
    }

    /**
     * 获取token
     */
    @Test
    public void test02token() {
        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "Basic Y2xpZW50aWQ6c2VjcmV0");
        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(requestHeaders);
        //post
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8013/oauth/token?scope=read&grant_type=password&usrname=1&password=1", requestEntity, String.class);

        String responseBody = responseEntity.getBody();
        log.info("获取jwt格式的token：{}", responseBody);

    }

    /**
     * 登录后访问受保护的资源
     */
    @Test
    public void test03show() throws JSONException {
        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "Basic Y2xpZW50aWQ6c2VjcmV0");
        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(requestHeaders);
        //post
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8013/oauth/token?scope=read&grant_type=password&usrname=1&password=1", requestEntity, String.class);

        String responseEntityBody = responseEntity.getBody();

        JSONObject jsonObject = new JSONObject(responseEntityBody);

        String accessToken = jsonObject.get("access_token").toString();
        //headers
        requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "Bearer " + accessToken);
        //HttpEntity
        requestEntity = new HttpEntity<>(requestHeaders);

        //get
        responseEntity = restTemplate.exchange("http://localhost:8013/show", HttpMethod.GET, requestEntity, String.class);

        //状态码是否为401
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));

        log.info("登录后访问受保护的资源，返回内容：{}", responseEntity.getBody());
    }

    /**
     * 刷新token
     */
    @Test
    public void refreshToken() throws JSONException {

        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "Basic Y2xpZW50aWQ6c2VjcmV0");
        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<>(requestHeaders);
        //post
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8013/oauth/token?scope=read&grant_type=password&usrname=1&password=1", requestEntity, String.class);

        String responseEntityBody = responseEntity.getBody();

        JSONObject jsonObject = new JSONObject(responseEntityBody);

        String refreshToken = jsonObject.get("refresh_token").toString();
        log.info("refresh_token：{}", refreshToken);

//headers
        requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "Basic Y2xpZW50aWQ6c2VjcmV0");
        //HttpEntity
        requestEntity = new HttpEntity<>(requestHeaders);
        //post
        responseEntity = restTemplate.postForEntity("http://localhost:8013/oauth/token?grant_type=refresh_token&refresh_token=" + refreshToken + "&client_id=clientid&client_secret=secret", requestEntity, String.class);
        System.out.println(responseEntity.getBody());

    }
}
