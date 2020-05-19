package com.junle.exam.Config;

import com.alibaba.fastjson.JSON;
import com.junle.exam.Entity.AjaxResponeBody;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//访问无权访问资源时返回的数据
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        AjaxResponeBody responeBody = new AjaxResponeBody();
        responeBody.setStatus("300");
        responeBody.setMsg("need Authorities");
        httpServletResponse.getWriter().write(JSON.toJSONString(responeBody));
    }
}
