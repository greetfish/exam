package com.junle.exam.Config;

import com.alibaba.fastjson.JSON;
import com.junle.exam.Entity.AjaxResponeBody;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//用户未登录时返回的数据
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        AjaxResponeBody responeBody = new AjaxResponeBody();
        responeBody.setStatus("000");
        responeBody.setMsg("need Authorities");
        httpServletResponse.getWriter().write(JSON.toJSONString(responeBody));
    }
}
