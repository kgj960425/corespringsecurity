//package io.security.corespringsecurity.security.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.security.corespringsecurity.domain.AccountDto;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    public AjaxLoginProcessingFilter() {
//        super(new AntPathRequestMatcher("/api/login"));
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//
//        if(!isAjax(request)){
//            throw new IllegalStateException("Authentication is not supported");
//        }
//
//        objectMapper.readValue(request.getReader(), AccountDto.class);
////        if (StringUtils.)//
//        return null;
//    }
//
//    private boolean isAjax(HttpServletRequest request){
//        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-with"))){
//            return true;
//        }
//        return false;
//    }
//}
