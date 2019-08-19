package org.springframework.arcln;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Enumeration;

/**
 * @author HXS
 * @copyright
 * @since 2019-07-30
 */
public class MyServletConfig implements ServletConfig {
	@Override
	public String getServletName() {
		System.out.println("1");
		return "arcln-servletName";
	}

	@Override
	public ServletContext getServletContext() {
		System.out.println("2");
		return null;
	}

	@Override
	public String getInitParameter(String name) {
		System.out.println("3");
		return null;
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		System.out.println("4");
		return null;
	}
}
