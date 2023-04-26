package me.diego.request.type;

import me.diego.domain.Proxy;
import me.diego.request.RequestSender;
import org.apache.commons.httpclient.methods.GetMethod;

public class GetRequest extends RequestSender {
    private final GetMethod get;

    public GetRequest(Proxy proxy, String uri) {
        super(proxy);
        this.get = new GetMethod(uri);
        super.httpMethod = this.get;
    }
}
