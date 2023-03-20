package ru.itis.rusteam.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import ru.itis.rusteam.security.filters.TokenAuthenticationFilter;
import ru.itis.rusteam.security.filters.TokenAuthorizationFilter;


@RequiredArgsConstructor
@EnableWebSecurity
public class TokenSecurityConfig {

    public static final String AUTHENTICATION_URL = "/auth/login";
    public static final String LOGOUT_URL = "/auth/logout";


    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsServiceImpl;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   TokenAuthenticationFilter tokenAuthenticationFilter,
                                                   TokenAuthorizationFilter tokenAuthorizationFilter,
                                                   LogoutHandler tokenLogoutHandler) throws Exception {

        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authorizeRequests()
                //.antMatchers("/applications/**", "/auth/logout").authenticated()
                .antMatchers("/swagger-ui.html/**").permitAll()
                .and()
                .logout(logout -> logout
                        .logoutUrl(LOGOUT_URL)
                        .addLogoutHandler(tokenLogoutHandler)
                );

        httpSecurity.addFilterBefore(tokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilter(tokenAuthenticationFilter);


        return httpSecurity.build();
    }

    @Autowired
    public void bindUserDetailsServiceAndPasswordEncoder(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    }
}
