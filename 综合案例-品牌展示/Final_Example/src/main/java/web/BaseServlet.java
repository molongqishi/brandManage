package web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
*   替换httpServlet
* */
public class BaseServlet extends HttpServlet {
    // 方法分发
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        // 找到最后的方法名称
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1);

        // 执行方法，
        // 获取字节码对象
        Class<? extends BaseServlet> cls = this.getClass();
        try {
            // 获取method方法
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
