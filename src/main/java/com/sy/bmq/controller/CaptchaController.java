package com.sy.bmq.controller;

import com.sy.bmq.common.util.ImageVerificationCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * 生成图片验证码
 */
@Controller
public class CaptchaController {

    @RequestMapping("/captcha.do")
    public void writeImg(HttpServletResponse response, HttpSession session) throws Exception{
        //1.获得验证图片
        ImageVerificationCode helper = new ImageVerificationCode();
        BufferedImage image = helper.getImage();

        //2.获取文本,保存在session中
        String text = helper.getText();
        session.setAttribute("captcha", text);

        //3.向浏览器写图片
        helper.output(image, response.getOutputStream());
    }
}
