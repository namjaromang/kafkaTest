package com.onemedics.pushapi.filter;

import com.onemedics.pushapi.client.UserClient;
import com.onemedics.pushapi.payload.response.UserRoleResponse;
import com.onemedics.pushapi.service.JwtService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
@Component
@Order(Ordered.LOWEST_PRECEDENCE - 7)
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private final JwtService jwtService;

    private final UserClient userClient;

    @SneakyThrows(GeneralSecurityException.class)
    @Override
    protected void doFilterInternal(
            HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication();

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication()
            throws IOException, GeneralSecurityException {
        Map<String, Object> jwtMap = jwtService.getJwtInfo();
        Long userId = Long.valueOf((Integer) jwtMap.get("uid"));

        if (null != userId) {

            UserRoleResponse userRoleResponse = userClient.findUserRole(userId);

            List<String> rs = userRoleResponse.getRoleEngNames();
            Collection<GrantedAuthority> tmp = new ArrayList<>();
            for (String a : rs) {
                tmp.add(new SimpleGrantedAuthority(a));
            }

            UserDetails user =
                    User.builder()
                            .username(String.valueOf(userId))
                            .authorities(tmp)
                            .password(" ")
                            .build();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            }
            return null;
        }
        return null;
    }
}
