package com.study.filter;

import com.study.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * ClassName: TransactionFilter
 * Description: 事务过滤器，过滤所有请求，以完成事务处理
 * date: 2021/8/7 11:33
 * author Quensty
 * since JDK 1.8
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();
        } catch (IOException e) {
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);//包异常抛给Tomcat管理，展示友好的错误页面
        }
    }

    @Override
    public void destroy() {

    }
}
