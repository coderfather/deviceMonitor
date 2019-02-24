package com.deviceMonitor.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deviceMonitor.constant.Const;
import com.deviceMonitor.intf.service.IUserService;
import com.deviceMonitor.model.ResultMessage;
import com.deviceMonitor.model.User;
import com.deviceMonitor.model.syn.CboUser;
import com.deviceMonitor.util.ListRange;
import com.deviceMonitor.util.Md5;
import com.deviceMonitor.util.StringUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;
	
    /**
     * 定义图片宽度
     */
    private int width = 60;
    /**
     * 定义图片高度
     */
    private int heigth = 20;
    /**
     * 定义字体大小
     */
    private int font_size = 18;
    /**
     * 定义随机数对象
     */
    private Random random = new Random();

	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) {
		int start = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("start")));
		int limit = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("limit")));
		ListRange listRange = new ListRange();
		listRange.setStart(start);
		listRange.setLimit(limit);
		userService.list(listRange);
		printWriterFromObject(response, listRange);
	}
	
	@RequestMapping("/cboList")
	public void cboList(HttpServletRequest request, HttpServletResponse response) {
		int start = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("start")));
		int limit = StringUtil.str2Integer(StringUtil.strRemoveNullAndBlank(request.getParameter("limit")));
		ListRange listRange = new ListRange();
		listRange.setStart(start);
		listRange.setLimit(limit);
		userService.list(listRange);
		List<User> listUser = listRange.getList();
		List<CboUser> listCboUser = new ArrayList<CboUser>();
		if (!CollectionUtils.isEmpty(listUser)) {
			for (User tmpUser : listUser) {
				CboUser cboUser = new CboUser();
				cboUser.setId(tmpUser.getId());
				cboUser.setUser_name(tmpUser.getUser_name());
				listCboUser.add(cboUser);
			}
		}
		
		listRange.setList(listCboUser);
		printWriterFromObject(response, listRange);
	}

	@RequestMapping("/isExists")
	public void isExists(HttpServletRequest request, HttpServletResponse response) {
		String key = StringUtil.strRemoveNullAndBlank(request.getParameter("key"));
		String id = StringUtil.strRemoveNullAndBlank(request.getParameter("id"));
		Map params = new HashMap();
		params.put("key", key);
		params.put("id", id);
		int result = userService.isExists(params);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/insert")
	public void insert(@ModelAttribute(value = "user") User user,
			HttpServletRequest request, HttpServletResponse response) {
		int result = userService.insert(user);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result == 1);
		resMsg.setResult(result == 1);
		resMsg.setMessage(result == 1 ? "新增成功。" : "新增失败。");
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/update")
	public void update(@ModelAttribute(value = "user") User user,
			HttpServletRequest request, HttpServletResponse response) {
		int result = userService.update(user);
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result == 1);
		resMsg.setResult(result == 1);
		resMsg.setMessage(result == 1 ? "更新成功。" : "更新失败。");
		printWriterFromObject(response, resMsg);
	}

	@RequestMapping("/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		String delIds = StringUtil.strRemoveNullAndBlank(request.getParameter("delIds"));
		int result = userService.batchDelete(delIds.split(Const.SPLIT));
		ResultMessage resMsg = new ResultMessage();
		resMsg.setSuccess(result >= 1);
		resMsg.setResult(result >= 1);
		resMsg.setMessage(result == 1 ? "删除成功。" : "删除失败。");
		printWriterFromObject(response, resMsg);
	}
	
	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String verifyCode = request.getParameter("verifyCode");
	    
        if (!verifyCode.toUpperCase().equals(
                request.getSession().getAttribute("verifyCode"))) {
            
            ResultMessage resMsg = new ResultMessage(false, false, "验证码错误!");
            printWriterFromObject(response, resMsg);
            return;
        }
        
        ResultMessage resMsg = null;
        String dlm = request.getParameter("userName");
        String mm = Md5.getMD5Str(request.getParameter("userPwd"));
        User user = userService.login(dlm, mm);
        if (user != null) {
            request.getSession().setAttribute("uid", String.valueOf(user.getId()));
            request.getSession().setAttribute("yhm", user.getUser_name());
            request.getSession().setAttribute("dlm", user.getLogin_name());
            request.getSession().setAttribute("roleType", String.valueOf(user.getRole_id()));
            resMsg = new ResultMessage(true, true, "登录成功。");
        } else {
            resMsg = new ResultMessage(false, false, "用户名或密码错误！");
        }
        
        printWriterFromObject(response, resMsg);
	}
	
    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        String content = this.getRandomStr();
        // 将验证码保存在session中
        request.getSession().setAttribute("verifyCode", content);
        ServletOutputStream out = response.getOutputStream();
        // 构建缓冲图像
        BufferedImage bi = new BufferedImage(width, heigth,
                BufferedImage.TYPE_INT_BGR);
        // 设置绘图上下文
        Graphics2D g = bi.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, width, heigth);
        // 设置字体
        g.setFont(new Font("Times New Roman", Font.BOLD, font_size));
        g.setColor(Color.black);

        // 绘制干扰线
        for (int i = 0; i < 4; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(heigth);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(heigth);
            g.setColor(this.getRandomColor());
            g.drawLine(x1, y1, x2, y2);
        }
        // 绘制干扰点
        for (int i = 0; i < 15; i++) {
            g.setColor(this.getRandomColor());
            g.drawOval(random.nextInt(width), random.nextInt(heigth), 0, 0);
        }

        // 确定验证码尺寸，使其居中显示
        FontMetrics fontMetrics = g.getFontMetrics();
        int strWidth = fontMetrics.stringWidth(content);
        int as = fontMetrics.getAscent();
        int ds = fontMetrics.getDescent();
        
        // 绘制验证码图片
        g.drawString(content, (width - strWidth) / 2, (heigth - as - ds) / 2 + as);
        JPEGImageEncoder coder = JPEGCodec.createJPEGEncoder(out);
        coder.encode(bi);

        out.flush();
        out.close();
    }
    
    /**
     * @function 获取四位验证码
     * @return String
     */
    private String getRandomStr() {
        String str = "";
        // char[] chr =
        // {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        char[] chr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        for (int i = 0; i < 4; i++) {
            int charIndex = (int) Math.round(Math.random() * 9);
            str += chr[charIndex];
        }
        return str;
    }

    /**
     * @function 获取随机颜色
     * @return Color
     */
    private Color getRandomColor() {
        int R = random.nextInt(125);
        int G = random.nextInt(125);
        int B = random.nextInt(125);
        Color color = new Color(R, G, B);
        return color;
    }
}