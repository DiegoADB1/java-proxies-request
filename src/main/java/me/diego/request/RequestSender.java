package me.diego.request;

import me.diego.Main;
import me.diego.domain.Proxy;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.params.HttpClientParams;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class RequestSender {
    private final Proxy proxy;
    private final HttpClientParams httpClientParams;
    protected HttpMethodBase httpMethod;

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public RequestSender(Proxy proxy) {
        this.proxy = proxy;
        this.httpClientParams = new HttpClientParams();
        httpClientParams.setSoTimeout(10000);
    }

    public void setRequestHeader(Header... headers) {
        Arrays.stream(headers)
                .forEach(this.httpMethod::addRequestHeader);
    }

    public void sendHttpRequest() {
        HttpClient httpClient = new HttpClient();
        HostConfiguration hostConfiguration = httpClient.getHostConfiguration();
        hostConfiguration.setProxy(proxy.url(), proxy.port());
        httpClient.setParams(httpClientParams);

        try {
            int statusCode = httpClient.executeMethod(httpMethod);
            if (statusCode == 200) {
                logger.log(Level.INFO, "Request sent");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            httpMethod.releaseConnection();
            if (!httpMethod.isRequestSent()) {
                logger.log(Level.WARNING, "Proxy cannot send a request " + proxy);
            }
        }
    }
}
