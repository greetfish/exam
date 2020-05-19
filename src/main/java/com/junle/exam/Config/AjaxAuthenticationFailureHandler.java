package com.junle.exam.Config;

import com.alibaba.fastjson.JSON;
import com.junle.exam.Entity.AjaxResponeBody;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//用户登录失败时返回的数据
@Component
public class AjaxAuthenticationFailureHandler  implements AuthenticationFailureHandler{
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        AjaxResponeBody responeBody = new AjaxResponeBody();
        responeBody.setStatus("400");
        responeBody.setMsg("logon Failure");
        httpServletResponse.getWriter().write(JSON.toJSONString(responeBody));
    }
}
