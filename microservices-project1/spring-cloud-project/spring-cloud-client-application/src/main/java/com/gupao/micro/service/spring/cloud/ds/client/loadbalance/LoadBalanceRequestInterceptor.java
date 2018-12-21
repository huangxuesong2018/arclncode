package com.gupao.micro.service.spring.cloud.ds.client.loadbalance;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 实现一个拦截器用来作负载均衡
 */
public class LoadBalanceRequestInterceptor implements ClientHttpRequestInterceptor {

    private volatile Map<String,Set<String>> targetUrlsCache = new HashMap<String,Set<String>>();
    @Autowired
    private DiscoveryClient discoveryClient;

    @Scheduled(fixedDelay = 10*1000)//10秒钟更新一次
    public void updateTargetUrls(){
        //获取当前应用的机器列表
        //http://${host}:${port}
        Map<String,Set<String>> newTargetUrlsCache = new HashMap<>();
        //discoveryClient.getServices() 获得所有注册实例，并遍历
        discoveryClient.getServices().forEach(serviceName ->{
            //查询每个实例下的所有节点
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
            Set<String> newTargetUrls = serviceInstances
                    .stream()
                    .map(s -> s.isSecure() ?
                            "https://"+s.getHost()+":"+s.getPort():
                            "http://"+s.getHost()+":"+s.getPort()
                    ).collect(Collectors.toSet());
            newTargetUrlsCache.put(serviceName,newTargetUrls);
        });
        this.targetUrlsCache = newTargetUrlsCache;
        System.out.println("服务节点列表-->"+ this.targetUrlsCache);
    }


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        URI requestURI = request.getURI();
        String path = requestURI.getPath();
        String [] paths = StringUtils.split(path.substring(1),"/");
        String serviceName = paths[0];
        String uri = paths[1];

        //服务器列表快照
        List<String> targetUrls = new ArrayList<>(this.targetUrlsCache.get(serviceName));
        int size = targetUrls.size();
        int index = new Random().nextInt(size);
        //选择其中一台服务器
        String targetURL = targetUrls.get(index);

        String actualURL = targetURL +"/"+ uri+"?"+requestURI.getQuery();
        System.out.println("本次请求的URI"+actualURL);
        URL url = new URL(actualURL);
        URLConnection connection = url.openConnection();
        HttpHeaders httpHeaders = new HttpHeaders();
        InputStream responseBody =  connection.getInputStream();
        return new SimpleClientHttpResponse(responseBody,httpHeaders);//默认执行后续的操作
    }

    private static class SimpleClientHttpResponse implements ClientHttpResponse{
        private InputStream body;
        private HttpHeaders head;

        public SimpleClientHttpResponse(InputStream body, HttpHeaders head) {
            this.body = body;
            this.head = head;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.OK;
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return 200;
        }

        @Override
        public String getStatusText() throws IOException {
            return "ok";
        }

        @Override
        public void close() {

        }

        @Override
        public InputStream getBody() throws IOException {
            return body;
        }

        @Override
        public HttpHeaders getHeaders() {
            return head;
        }
    }
}
