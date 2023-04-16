package com.example.blog_project.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractFilter implements Filter {
  protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

  @Override
  public void init(FilterConfig filterConfig) { }

  @Override
  public final void doFilter(ServletRequest request, ServletResponse response,
																													FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    doFilter(req, resp, chain);
  }

  public abstract void doFilter(HttpServletRequest req, HttpServletResponse resp,
																																FilterChain chain) throws IOException, ServletException;

  @Override
  public void destroy() { }
}
