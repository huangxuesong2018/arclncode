package com.gupao.edu.vip.bio.tomcat;

import java.io.IOException;

public interface Response {
    public void writer(String context) throws IOException;
}
