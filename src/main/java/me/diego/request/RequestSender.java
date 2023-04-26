package me.diego.request;

import me.diego.Main;
import me.diego.domain.Proxy;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class RequestSender {
    private final Proxy proxy;
    protected HttpMethodBase httpMethod;

    private final Logger logger = Logger.getLogger(Main.class.getName());;

    public RequestSender(Proxy proxy) {
        this.proxy = proxy;
    }

    public void setRequestHeader(Header... headers) {
        Arrays.stream(headers)
                .forEach(this.httpMethod::addRequestHeader);
    }

    public void sendHttpRequest() {
        HttpClient httpClient = new HttpClient();
        HostConfiguration hostConfiguration = httpClient.getHostConfiguration();
        hostConfiguration.setProxy(proxy.url(), proxy.port());

        try {
            httpClient.executeMethod(httpMethod);
            logger.log(Level.INFO, hostConfiguration.getProxyHost() + ":" + hostConfiguration.getPort());
            logger.log(Level.INFO, httpMethod.getStatusLine().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            httpMethod.releaseConnection();
        }
    }
}
