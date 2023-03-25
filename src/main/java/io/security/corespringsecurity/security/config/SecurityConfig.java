package io.security.corespringsecurity.security.config;

import io.security.corespringsecurity.security.handler.CustomAuthenticationSuccessHandler;
import io.security.corespringsecurity.security.provider.CustomAuthenticationProvider;
import io.security.corespringsecurity.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationDetailsSource authenticationDetailsSource;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/","/home","/user/login/**","/login","/users","/error").permitAll()   //permitAll은 보안 필터에 들어오긴 함. 익명 사용자라도 통과가 되게끔 한다.
                .antMatchers("/mypage").hasRole("USER")
                .antMatchers("/messages").hasRole("MANAGER")
                .antMatchers("/config").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
        .formLogin()
                .loginPage("/login")
                .authenticationDetailsSource(authenticationDetailsSource)//<input th:type="hidden" th:value="secret" name="secret_key" /> 에서 type을 th:type="hidden"이 아니라 그냥 type히든 갈기면 데이터 못들고옴. 이새끼는 용도가 뭐지...? html에 묻어 놓은 비밀 키 가져오는거...?
                .loginProcessingUrl("/login_proc")
                .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
                .successHandler(customAuthenticationSuccessHandler)
                .permitAll()
                .and()

//        .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/home")
//                .deleteCookies("JSESSIONID", "lime-research-remember-me")
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .and()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new CustomAuthenticationProvider();
    }

    //실전프로젝트 -인증 프로세스 Form 인증 구현 - 2)정적 자원 관리
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    //실전프로젝트 -인증 프로세스 Form 인증 구현 - 3)사용자 DB등록 및 PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}
