package com.junle.exam.Config;

import com.alibaba.fastjson.JSON;
import com.junle.exam.Entity.AjaxResponeBody;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//成功登出返回数据
@Component
public class AJaxLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponeBody responeBody = new AjaxResponeBody();
        responeBody.setStatus("300");
        responeBody.setMsg("logOut Success");
        try{
            httpServletResponse.getWriter().write(JSON.toJSONString(responeBody));
        } catch (Exception e){
            System.out.println("error to get HttpWriter");
        }
    }
}
