package me.diego.request;

import org.apache.commons.httpclient.Header;

public final class DefaultHeader extends Header {
    public final static DefaultHeader DEFAULT_USER_AGENT = new DefaultHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 Edg/112.0.1722.58");
    public final static DefaultHeader JSON_CONTENT_TYPE = new DefaultHeader("Content-Type", "application/json");

    public DefaultHeader(String name, String value) {
        super(name, value);
    }
}
