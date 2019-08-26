package com.gupao.edu.vip.bio.tomcat.bio;

import com.gupao.edu.vip.bio.tomcat.Response;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-21
 */
public class OutputStreamResponse implements Response{
    private OutputStream out;

    public OutputStreamResponse(OutputStream out) {
        this.out = out;
    }

   public void writer(String context) throws IOException {
        StringBuilder text = new StringBuilder();
        text.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(context);
        out.write(text.toString().getBytes());

    }

}
