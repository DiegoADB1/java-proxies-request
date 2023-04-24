package me.diego.request.type;

import me.diego.request.RequestSender;
import org.apache.commons.httpclient.methods.GetMethod;

public class GetRequest extends RequestSender {
    private final GetMethod get;

    public GetRequest(String proxyUrl, int proxyPort, String uri) {
        super(proxyUrl, proxyPort);
        this.get = new GetMethod(uri);
        super.httpMethod = this.get;
    }
}
