package me.diego;

import me.diego.request.DefaultHeader;
import me.diego.request.type.PostRequest;
import org.apache.commons.httpclient.NameValuePair;

public class Main {
    public static void main(String[] args) {


    }

    public static void createGetRequest() {

    }

    public static void createPostRequest() {
        PostRequest requestSender = new PostRequest("", 80, "");
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