package ru.itis.rusteam.security.utils;

import javax.servlet.http.HttpServletRequest;

public interface AuthorizationHeaderUtil {
    boolean hasAuthorizationToken(HttpServletRequest request);

    String getToken(HttpServletRequest request);
}
