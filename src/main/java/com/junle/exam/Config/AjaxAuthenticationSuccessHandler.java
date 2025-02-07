package com.junle.exam.Config;

import com.alibaba.fastjson.JSON;
import com.junle.exam.Entity.AjaxResponeBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//登录成功返回的数据
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponeBody responeBody = new AjaxResponeBody();
        responeBody.setStatus("200");
        responeBody.setMsg("login Success");
        httpServletResponse.getWriter().write(JSON.toJSONString(responeBody));
    }
}
