package net.maatvirtue.accountcore.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestLogFilter extends OncePerRequestFilter
{
	private Logger logger=LoggerFactory.getLogger(RequestLogFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
	{
		logger.debug("Client request: "+request.getMethod()+" "+request.getRequestURI());
		
		filterChain.doFilter(request, response);
	}
}
