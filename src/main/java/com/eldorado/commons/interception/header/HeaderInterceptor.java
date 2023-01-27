package com.eldorado.commons.interception.header;

import com.eldorado.commons.security.AuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;


@Component
@Order(HeaderInterceptor.ORDER)
@Slf4j
@RequiredArgsConstructor
public class HeaderInterceptor implements HandlerInterceptor {

    public static final int ORDER = Ordered.HIGHEST_PRECEDENCE + 3;

    private final AuthUtils authUtils;

    @Override
    @SneakyThrows
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        var authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        return Objects.equals("/login", request.getRequestURI()) || authUtils.validateJwtToken(authorization, response);
    }

    @Bean
    public AuthUtils authUtils() {
        return new AuthUtils();
    }

}
