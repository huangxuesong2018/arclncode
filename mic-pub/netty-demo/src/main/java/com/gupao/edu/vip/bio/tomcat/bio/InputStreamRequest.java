package com.gupao.edu.vip.bio.tomcat.bio;

import com.gupao.edu.vip.bio.tomcat.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author HXS
 * @copyright
 * @since 2019-08-21
 */
public class InputStreamRequest implements Request {
    private String url;
    private String method;
    public InputStreamRequest(InputStream in) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String text = reader.readLine();
            if (text == null || "".equals(text))return;
            System.out.println(text);
            String[] contexts = text.split("\\s");
            method = contexts[0];
            url = contexts[1];
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
