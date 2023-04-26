package me.diego;

import me.diego.domain.Proxy;
import me.diego.request.DefaultHeader;
import me.diego.request.type.GetRequest;
import me.diego.request.type.PostRequest;
import me.diego.thread.ThreadPool;
import org.apache.commons.httpclient.NameValuePair;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final String BASE_URL = "";
    static List<Proxy> proxies = List.of();
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(proxies.size());

        threadPool.runThread(() -> {
            Proxy proxy = proxies.get(atomicInteger.getAndAccumulate(1, Integer::sum));
            GetRequest getRequest = new GetRequest(proxy, BASE_URL);

            getRequest.sendHttpRequest();
        });
    }

    public static void createGetRequest() {
        GetRequest get = new GetRequest(proxies.get(0), BASE_URL);
        get.setRequestHeader(
                DefaultHeader.DEFAULT_USER_AGENT
        );

        get.sendHttpRequest();
    }

    public static void createPostRequest() {
        PostRequest requestSender = new PostRequest(proxies.get(0), BASE_URL);
        requestSender.setRequestHeader(
                DefaultHeader.DEFAULT_USER_AGENT,
                DefaultHeader.JSON_CONTENT_TYPE
        );
        requestSender.setRequestBody(
                new NameValuePair("fodase", "fodase"),
                new NameValuePair("fodase", "fodase")
        );

        requestSender.sendHttpRequest();
    }
}