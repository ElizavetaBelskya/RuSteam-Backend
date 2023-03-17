package ru.itis.rusteam.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import ru.itis.rusteam.models.account.Token;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.repositories.AccountsRepository;
import ru.itis.rusteam.repositories.TokensRepository;


import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static ru.itis.rusteam.security.config.TokenSecurityConfig.AUTHENTICATION_URL;

@Component
public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String USERNAME_PARAMETER = "email";

    private final ObjectMapper objectMapper;

    private final TokensRepository tokensRepository;

    private final AccountsRepository accountsRepository;


    public TokenAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration,
                                     ObjectMapper objectMapper,
                                     TokensRepository tokensRepository,
                                     AccountsRepository accountsRepository) throws Exception {
        super(authenticationConfiguration.getAuthenticationManager());
        this.setUsernameParameter(USERNAME_PARAMETER);
        this.setFilterProcessesUrl(AUTHENTICATION_URL);
        this.objectMapper = objectMapper;
        this.tokensRepository = tokensRepository;
        this.accountsRepository = accountsRepository;
    }

    //TODO из json
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        String value = UUID.randomUUID().toString();

        Optional<Account> account = accountsRepository.findByEmail(authResult.getName());

        if (account.isPresent()) {
            Token token = getToken(account.get());
            token.setValue(value);
            tokensRepository.save(token);
        } else {
            throw new RuntimeException("Account not found");
        }

        response.setContentType("application/json");
        objectMapper.writeValue(response.getOutputStream(), Collections.singletonMap("token", value));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }


    protected Token getToken(@NonNull Account account) {
        return tokensRepository.findById(account.getId()).orElseGet(() -> tokensRepository.save(
                Token.builder()
                        .id(account.getId())
                        .account(account)
                        .build()
        ));
    }
}
