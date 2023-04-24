package me.diego.request;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;

import java.io.IOException;
import java.util.Arrays;

public abstract class RequestSender {
    private final String proxyUrl;
    private final int proxyPort;
    protected HttpMethodBase httpMethod;

    public RequestSender(String proxyUrl, int proxyPort) {
        this.proxyUrl = proxyUrl;
        this.proxyPort = proxyPort;
    }

    public void setRequestHeader(Header... headers) {
        Arrays.stream(headers)
                .forEach(this.httpMethod::addRequestHeader);
    }

    public void sendHttpRequest() {
        HttpClient httpClient = new HttpClient();
        httpClient.getHostConfiguration().setProxy(proxyUrl, proxyPort);

        try {
            httpClient.executeMethod(httpMethod);
            System.out.println(httpMethod.getStatusLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            httpMethod.releaseConnection();
        }
    }
}
