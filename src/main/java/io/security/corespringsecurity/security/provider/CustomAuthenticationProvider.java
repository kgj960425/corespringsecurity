package io.security.corespringsecurity.security.provider;

import groovy.util.logging.Slf4j;
import io.security.corespringsecurity.security.service.AccountContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
    Logger logger = LoggerFactory.getLogger(LoggerFactory.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //검증을 위한 구현
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        AccountContext accountContext = (AccountContext)userDetailsService.loadUserByUsername(username);

        if(!passwordEncoder.matches(password, accountContext.getAccount().getPassword())){
            logger.info("BadCredentialsException : password가 매치하지 않습니다.");
            logger.info("password : "+password);
            logger.info("accountContext.getAccount().getPassword() : "+accountContext.getAccount().getPassword());
            throw new BadCredentialsException("BadCredentialsException");
            //이 시2발 if문의 조건문에서 다르면(!) 작동하게 해야하는데 같으면 작동되게 만들어서 이 개고생을했네 시2발 진짜 하루 죙일 찾았았는데 썅
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(accountContext.getAccount(),null,accountContext.getAuthorities());

        return authenticationToken;
    }

    //토큰의 타입과 동일할시 반환
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
