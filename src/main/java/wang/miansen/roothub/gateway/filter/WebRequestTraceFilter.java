package wang.miansen.roothub.gateway.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import wang.miansen.roothub.common.constant.BaseConstants;

/**
 * Web 过滤器，增加 logger 的 traceId
 *
 * @author miansen.wang
 * @date 2021-05-16 16:56
 */
public class WebRequestTraceFilter extends OncePerRequestFilter implements Ordered {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        // 读取 HTTP 请求头有没有 traceId
        String traceId = request.getHeader(BaseConstants.TRACE_LOG_MDC_KEY);
        if (StringUtils.isEmpty(traceId)) {
            // 没有的话就初始化 traceId
            traceId = UUID.randomUUID().toString().replace("-", "");
        }

        // 塞到 MDC 上下文中
        MDC.put(BaseConstants.TRACE_LOG_MDC_KEY, traceId);

        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    @Override
    public int getOrder() {
        // 把这个 Filter 设置成最高优先级
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
