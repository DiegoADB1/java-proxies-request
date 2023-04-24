package me.diego.request.type;

import me.diego.request.RequestSender;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class PostRequest extends RequestSender {
    private final PostMethod post;

    public PostRequest(String proxyUrl, int proxyPort, String uri) {
        super(proxyUrl, proxyPort);
        this.post = new PostMethod(uri);
        super.httpMethod = post;
    }

    public void setRequestBody(NameValuePair... requestBody) {
        this.post.setRequestBody(requestBody);
    }
}
