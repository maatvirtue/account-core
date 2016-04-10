package net.maatvirtue.accountcore.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter extends OncePerRequestFilter
{
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
	{
		if(request.getHeader("Origin")!=null)
		{
			response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

			String requestHeaders=request.getHeader("Access-Control-Request-Headers");

			if(requestHeaders!=null)
				response.addHeader("Access-Control-Allow-Headers", requestHeaders);

			response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			response.addHeader("Access-Control-Max-Age", "0");
		}

		filterChain.doFilter(request, response);
	}
}
