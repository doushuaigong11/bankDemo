package com.zz.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import com.mysql.cj.Session;
import com.zz.common.JsonResult;
import com.zz.pojo.User;
import com.zz.service.UserService;
import com.zz.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("/login")
    public JsonResult getLoginInfo(User user,HttpServletRequest request){
        User login = userService.login(user);
        System.out.println(login+"这是user下面login方法的login值");
        JsonResult jsonResult =null;
        if (login !=null){
            request.getSession().setAttribute(StrUtils.LOGIN_USER,login);
//            request.getServletContext().setAttribute(StrUtils.LOGIN_USER,login);
            jsonResult = new JsonResult(1,login);
        } else {
            jsonResult = new JsonResult(0,"用户未登录");
        }
        return jsonResult;
    }
    @ResponseBody
    @RequestMapping("/query")
    public JsonResult checkLogin(User user, HttpServletRequest request){

        user = (User) request.getSession().getAttribute(StrUtils.LOGIN_USER);
        System.out.println(user + "这是query里面的user");
//        user = (User) request.getServletContext().getAttribute(StrUtils.LOGIN_USER);


        User login = userService.login(user);

        JsonResult jsonResult = null;
        if (login != null){
            jsonResult = new JsonResult(1,login);
        } else {
            jsonResult = new JsonResult(0,"用户未登录");
        }
        return jsonResult;
    }
    @ResponseBody
    @RequestMapping("/findAll")
    public Map<String,Object> findAll(Integer page,Integer limit,HttpServletRequest request){
//        User user = (User) request.getServletContext().getAttribute(StrUtils.LOGIN_USER);
        User user = (User) request.getSession().getAttribute(StrUtils.LOGIN_USER);
        PageHelper.startPage(page,limit);
        List<User> userList = userService.findAll(user.getUid());
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",pageInfo.getList());
        return map;
    }

    @ResponseBody
    @RequestMapping("/findBalance")
    public JsonResult findBalance(User user,HttpServletRequest request){
        user = (User) request.getSession().getAttribute(StrUtils.LOGIN_USER);
//        user = (User) request.getServletContext().getAttribute(StrUtils.LOGIN_USER);
        System.out.println(user + "这是findBalance里面的user");
        System.out.println(user.getUid()+"这是findBalance里面的uid");
        user = userService.findBalance(user.getUid());
        JsonResult jsonResult = new JsonResult(1, user);
        return jsonResult;
    }
    @ResponseBody
    @RequestMapping("/upload")
    public JsonResult uploadFile(@RequestParam MultipartFile upfile,HttpServletRequest request){
//        User user = (User) request.getServletContext().getAttribute(StrUtils.LOGIN_USER);
        User user = (User) request.getSession().getAttribute(StrUtils.LOGIN_USER);
        //1.上传文件的目录
        String path = "F:/upload";
        //2.获取上传文件的名字
        String filename = upfile.getOriginalFilename();
        //3.判断目录是否存在
        File file = new File(path);
        if (!file.exists()){
            file.mkdir();
        }
        //4.要保存文件的file对象
        File newFile = new File(path, filename);
        try {
            upfile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //并且写入数据库，要把图片的路径存到数据库中
        userService.updateHeadImg(user.getUid(),"/upload/"+ filename);
        return new JsonResult(1,"上传成功");
    }

    @ResponseBody
    @RequestMapping("/updatePassword")
    public JsonResult updatePassword(User user,HttpServletRequest request){
//        user = (User) request.getServletContext().getAttribute(StrUtils.LOGIN_USER);
         user = (User) request.getSession().getAttribute(StrUtils.LOGIN_USER);
        System.out.println(user+"这是user下面的updatePassword方法");
        userService.update(user);
        JsonResult jsonResult = new JsonResult(1, user);
        return jsonResult;
    }

}
