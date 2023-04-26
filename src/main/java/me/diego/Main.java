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
    // Just for change logger pattern
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %n");
    }

    private static final String BASE_URL = "https://webhook.site/3f439cc3-8c3f-4ca8-94e5-f76bd63c9cf4";
    static List<Proxy> proxies = List.of(
            new Proxy("142.132.189.106", 8080),
            new Proxy("202.8.74.9", 8080)
    );
    static AtomicInteger ai = new AtomicInteger();

    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(proxies.size());

        threadPool.runThread(() -> {
            int index = ai.getAndAdd(1);
            Proxy proxy = proxies.get(index);
            GetRequest getRequest = new GetRequest(proxy, BASE_URL);

            getRequest.setRequestHeader(DefaultHeader.DEFAULT_USER_AGENT);

            getRequest.sendHttpRequest();
        });
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