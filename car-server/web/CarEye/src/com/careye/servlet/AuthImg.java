/**    
 * Description: car-eye车辆管理平台    
 * 文件名：AuthImg.java   
 * 版本信息：    
 * 日期：2013-7-20  
 * Copyright car-eye 车辆管理平台 Copyright (c) 2013     
 * 版权所有    
 *    
 */
package com.careye.servlet;

/**    
 *     
 * 项目名称：car-eye    
 * 类名称：AuthImg    
 * 类描述：验证码   
 * 创建人：zr    
 * 创建时间：2013-7-20 下午02:19:57    
 * 修改人：zr    
 * 修改时间：2013-7-20 下午02:19:57    
 * 修改备注：    
 * @version 1.0  
 *     
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthImg extends HttpServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = 4975974534946437434L;

    // 设置图形验证码字符串的字体和大小
    private Font mFont = new Font("微软雅黑", Font.ITALIC, 18);

    private Random random = new Random();

    public void init() throws ServletException {
        super.init();
    }

    // 生成服务器响应的Service方法
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 阻止生成的页面被缓存，保证每次都生成新的验证码
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 指定验证码图片的大小
        int width = 80, height = 24;
        // 生成一张新的图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        // 在图片中绘制内容
        Graphics g = image.getGraphics();
        g.setColor(new Color(252, 252, 252));
        g.fillRect(1, 1, width, height);
        g.setColor(new Color(252, 252, 252));
        g.drawRect(0, 0, width, height);
        g.setFont(mFont);
        g.setColor(new Color(252, 252, 252));

        // 该变量用于保存系统生成的随机变量字符串
        String sRand = "";
        int colorR = random.nextInt(200);
        int colorG = random.nextInt(200);
        int colorB = random.nextInt(200);
        for (int i = 0; i < 4; i++) {
            // 取得一个随机字符串
            String tmp = getRandomChar();
            sRand += tmp;
            // 将系统生成的随机字符串添加到图片上
            g.setColor(new Color(colorR, colorG, colorB));
            g.drawString(tmp, 15 * i + 10, 20);
        }
        // 取得用户的Session
        HttpSession session = request.getSession(true);
        // 将系统生成的随机验证码添加到用户Session中
        session.setAttribute("rand", sRand);
        g.dispose();
        // 输出验证码图片
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    // 生成随机字符串的方法
    private String getRandomChar() {
        int itmp = random.nextInt(10);
        return String.valueOf(itmp);
    }

}
