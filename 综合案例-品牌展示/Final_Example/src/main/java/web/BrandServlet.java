package web;

import com.alibaba.fastjson.JSON;
import proj.Brand;
import proj.PageBean;
import service.BrandService;
import service.impl.BrandServiceImpl;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private BrandService service = new BrandServiceImpl();
    private int id = 0;
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Brand> brands = service.selectAll();

        // 将集合转化为json数据， 序列化
        String jsonString = JSON.toJSONString(brands);

        // 响应数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码问题：POST
        req.setCharacterEncoding("UTF-8");  // 设置字符输入流的编码

        // 接收数据
        BufferedReader br = req.getReader();
        String params = br.readLine();

        // 转换为brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        System.out.println(brand);
        service.add(brand);

        // 响应成功标识
        resp.getWriter().write("success!");
    }

    public void getIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 获取具体的编码
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        String _index = req.getParameter("index");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        int index = Integer.parseInt(_index);

        this.id = (currentPage - 1) * pageSize + index + 1;
        System.out.println(this.id);

        resp.getWriter().write("success!");
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // 接收数据
        BufferedReader br = req.getReader();
        String params = br.readLine();

        //
        Brand brand = JSON.parseObject(params, Brand.class);

        System.out.println(id);
        System.out.println(brand);

        //
        service.update(id, brand);

        resp.getWriter().write("success!");
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码问题：POST
        req.setCharacterEncoding("UTF-8");  // 设置字符输入流的编码

        // 接收数据
        BufferedReader br = req.getReader();
        String params = br.readLine();

        Brand brand = JSON.parseObject(params, Brand.class);

        service.delete(brand);

        resp.getWriter().write("success!");
    }

    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码问题：POST
        req.setCharacterEncoding("UTF-8");  // 设置字符输入流的编码

        // 接收数据
        BufferedReader br = req.getReader();
        String params = br.readLine();

        // 转换为brand对象
        int[] ids = JSON.parseObject(params, int[].class);

        service.deleteByIds(ids);

        // 响应成功标识
        resp.getWriter().write("success!");
    }

    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码问题：POST
        req.setCharacterEncoding("UTF-8");  // 设置字符输入流的编码
        // 接收当前页码和每页展示条数
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 调用方法
        PageBean<Brand> pageBean = service.selectByPage(currentPage, pageSize);
        // 将集合转化为json数据， 序列化
        String jsonString = JSON.toJSONString(pageBean);

        // 响应数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码问题：POST
        req.setCharacterEncoding("UTF-8");  // 设置字符输入流的编码
        // 接收当前页码和每页展示条数
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件
        BufferedReader br = req.getReader();
        String params = br.readLine();

        Brand brand = JSON.parseObject(params, Brand.class);
        // 调用方法
        PageBean<Brand> pageBean = service.selectByPageAndCondition(currentPage, pageSize, brand);
        // 将集合转化为json数据， 序列化
        String jsonString = JSON.toJSONString(pageBean);

        // 响应数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
}
