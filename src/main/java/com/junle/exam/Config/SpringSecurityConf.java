package com.junle.exam.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConf extends WebSecurityConfigurerAdapter {
    @Autowired
    AjaxAuthenticationEntryPoint authenticationEntryPoint;//未登录时返回JSON数据给前端（否则为HTML）
    @Autowired
    AjaxAuthenticationSuccessHandler authenticationSuccessHandler;//登录成功
    @Autowired
    AjaxAuthenticationFailureHandler authenticationFailureHandler;//登录失败
    @Autowired
    AJaxLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    AjaxAccessDeniedHandler accessDeniedHandler;//无权访问
    @Autowired
    SelfAuthenticationProvider provider;//自定义安全认证

    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
        //加入自定义安全认证
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()

                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)

                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/user_*","/","/assets/js/*.*.js","/assets/js/*.*.js.map",
                "/assets/css/*.*.css","/assets/fonts/*","/img/*","/admin","/admin.html","/can.html");
    }
}
