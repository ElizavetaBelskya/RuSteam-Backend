package ru.itis.rusteam.security.filters;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.rusteam.models.account.Token;
import ru.itis.rusteam.repositories.TokensRepository;
import ru.itis.rusteam.security.details.UserDetailsImpl;
import ru.itis.rusteam.security.utils.AuthorizationHeaderUtil;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static ru.itis.rusteam.security.filters.TokenAuthenticationFilter.AUTHENTICATION_URL;


@Component
@RequiredArgsConstructor
public class TokenAuthorizationFilter extends OncePerRequestFilter {

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private final TokensRepository tokensRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals(AUTHENTICATION_URL)) {
            filterChain.doFilter(request, response);
        } else {
            if (authorizationHeaderUtil.hasAuthorizationToken(request)) {
                String token = authorizationHeaderUtil.getToken(request);

                try {
                    Token userToken = tokensRepository.findByValue(token).orElseThrow();
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            new UserDetailsImpl(userToken.getAccount()),
                            null,
                            Collections.singleton(new SimpleGrantedAuthority(userToken.getAccount().getRole().name()))
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }

            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
