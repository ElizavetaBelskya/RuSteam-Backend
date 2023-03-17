package ru.itis.rusteam.security.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import ru.itis.rusteam.repositories.TokensRepository;
import ru.itis.rusteam.security.utils.AuthorizationHeaderUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@RequiredArgsConstructor
public class TokenLogoutHandler implements LogoutHandler {

    private final AuthorizationHeaderUtil authorizationHeaderUtil;

    private final TokensRepository tokensRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authorizationHeaderUtil.hasAuthorizationToken(request)) {
            String tokenValue = authorizationHeaderUtil.getToken(request);

            tokensRepository.findByValue(tokenValue).ifPresent(token -> {
                token.setValue(null);
                tokensRepository.save(token);
            });
        }
    }
}
