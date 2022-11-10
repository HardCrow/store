package com.atguigu.ssm.controller;

import com.atguigu.ssm.pojo.Employee;
import com.atguigu.ssm.pojo.User;
import com.atguigu.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class EmployeeController {
    String fileName;

     @Autowired
    private EmployeeService employeeService;
     //第二种登入所要用的起始值
   // Map<String,Object> map =new HashMap<String, Object>();

    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    public String getAllEmployee(Model model){
       List<Employee> list= employeeService.getAllEmployee();
       model.addAttribute("list",list);
       return "book_list";
    }


    //自己写的
    @RequestMapping(value = "/emp")
    public String inssert(Employee employee){
        System.out.println(employee.getEmpName());
        employeeService.insertEmployee(employee);
        return "success";
    }



    @RequestMapping(value = "/register")
    public String register(){
        return "register";
    }
    @RequestMapping(value = "/losePassword")
    public String losepassword(){
        return "losePassword";
    }
    @RequestMapping(value = "/back")
    public String backpage(){
        return "index";
    }

    //登入查询功能
    @RequestMapping(value = "/verificate")
    public String verificate(User user,Model model){
       User Ch=employeeService.check(user);
       //扔给域里面的东西前端都可以拿到   此处相当于把userid封进msg 然后扔给域最后前端用msg拿到userid
        // 下面password一样的  不论前端那个页面都可以获取  
       model.addAttribute("msg",user.getUserid());
       model.addAttribute("pass",user.getPassword());
       if(Ch!=null){
           List<Employee> list= employeeService.getAllEmployee();
           model.addAttribute("list",list);
           return "book_list";}
       else {
           return "test1";
       }
       //登入第二种写法
     /*   @RequestMapping(value = "/verificate")   //因为映射了同一个前端文件所以二者只能开一个不能同时开
        public String verificates(Map<String,Object> map){
            Map<String,Object> maps =employeeService.checks(map);
            System.out.println(maps);
            if(maps!=null){
                return "success";}
            else {
                return "defeat";
            }*/
    }
    @RequestMapping(value = "/test")
    public String test(Model model){
        model.addAttribute("test","1");
        return "success";
    }
  @RequestMapping(value = "/reach",method = RequestMethod.GET) //搜索引擎功能
    public String reach(Model models,String empName){
      List<Employee> lists= employeeService.reach(empName);
      models.addAttribute("list",lists);
      return "reach";
  }



  //上传图片功能
  @Controller
  @RequestMapping("/file_test")
  public class File_uploadtTest {
      @RequestMapping("/add")
      @ResponseBody
      public String addUser(HttpServletRequest request, HttpServletResponse response,
                            MultipartFile file) throws IOException {
//对象点getOriginalFilename 还没有file对象 MultipartFile file 还没有在实体类中创建 上传功能 不能实现 写法就是大概这样写
          String newFileName = "";
          String fileName = file.getOriginalFilename();//获取文件名
          System.out.println("获取文件名"+fileName);
          fileName = getFileName(fileName);          //添加时间戳后的文件名
          System.out.println("获取文件名加时间"+fileName);
          request.getSession().setAttribute("fileImgName",fileName);
          String filepath = getUploadPath();     //获取当前系统路径
          if (!file.isEmpty()) {                  //如果文件不为空
              try (BufferedOutputStream out = new BufferedOutputStream(   //上传
                      new FileOutputStream(new File(filepath + File.separator + fileName)))) {
                  out.write(file.getBytes());
                  out.flush();
              } catch (FileNotFoundException e) {
                  System.out.println("上传文件失败 FileNotFoundException：" + e.getMessage());
              } catch (IOException e) {
                  System.out.println("上传文件失败 IOException：" + e.getMessage());
              }

          } else {
              System.out.println("上传文件失败，文件为空");
          }
          return "ok";
      }

      /**
       * 文件名后缀前添加一个时间戳
       */
      private String getFileName(String fileName) {
          int index = fileName.lastIndexOf(".");
          final SimpleDateFormat sDateFormate = new SimpleDateFormat("yyyymmddHHmmss");  //设置时间格式
          String nowTimeStr = sDateFormate.format(new Date()); // 当前时间
          fileName = fileName.substring(0, index) + "_" + nowTimeStr + fileName.substring(index);
          return fileName;
      }

      /**
       * 获取当前系统路径
       */
      private String getUploadPath() {
          File path = null;
          try {
              path = new File(ResourceUtils.getURL("classpath:").getPath());
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }
          if (!path.exists()) path = new File("");
          File upload = new File(path.getAbsolutePath(), "src/main/resources/static/img/");
          if (!upload.exists()) upload.mkdirs();
          return upload.getAbsolutePath();
      }

  }


}
