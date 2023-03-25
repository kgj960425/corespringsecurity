package io.security.corespringsecurity.security.handler;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(LoggerFactory.class);
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,/*아 이건 원래 없는거네요 parameter도 똑같아야 이걸 타는데 이거 때문에 안 타고 있음 FilterChain chain,*/ Authentication authentication) throws IOException, ServletException {
        //session을 가져와서 세션 작업을 해도 되고 authentication을 사용해서 인증관련 데이터를 만져도 됌
        setDefaultTargetUrl("/");

        SavedRequest saveRequest = requestCache.getRequest(request, response);

        if(saveRequest != null){
            String targetUrl = saveRequest.getRedirectUrl();
            logger.info("saveRequest is! targetUrl : " + targetUrl);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }else {
            logger.info("no targetUrl...");
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }
    }
}
