package me.diego.request.type;

import me.diego.domain.Proxy;
import me.diego.request.RequestSender;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class PostRequest extends RequestSender {
    private final PostMethod post;

    public PostRequest(Proxy proxy, String uri) {
        super(proxy);
        this.post = new PostMethod(uri);
        super.httpMethod = this.post;
    }

    public void setRequestBody(NameValuePair... requestBody) {
        this.post.setRequestBody(requestBody);
    }
}
