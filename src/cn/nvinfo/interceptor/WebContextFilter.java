package cn.nvinfo.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
/**
 *解决js跨域响应
 * @author 杨立	2017-10-10
 *
 */
public class WebContextFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletResponse httpServletResponse=(HttpServletResponse)arg1;	
		httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
		//httpServletResponse.setHeader("Access-Control-Allow-Headers","Authentication");
		httpServletResponse.setHeader("Content-Type", "text/json; charset=UTF-8");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		arg2.doFilter(arg0, httpServletResponse);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
