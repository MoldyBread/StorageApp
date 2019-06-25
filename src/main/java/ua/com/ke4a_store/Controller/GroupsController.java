package ua.com.ke4a_store.Controller;

import ua.com.ke4a_store.dao.impl.Connector;
import ua.com.ke4a_store.dao.impl.GroupsDaoImpl;
import ua.com.ke4a_store.entity.GoodsGroup;
import ua.com.ke4a_store.service.impl.GroupsServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GroupsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getSession().getAttribute("isLogged")){
            resp.sendRedirect("/");
            return;
        }

        Connector connector = new Connector();

        List<GoodsGroup> groups = new GroupsServiceImpl(new GroupsDaoImpl(connector)).findAll();

        req.getSession().setAttribute("groups",groups);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/main.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
