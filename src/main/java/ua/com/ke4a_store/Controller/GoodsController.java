package ua.com.ke4a_store.Controller;

import ua.com.ke4a_store.dao.impl.Connector;
import ua.com.ke4a_store.dao.impl.GoodsDaoImpl;
import ua.com.ke4a_store.entity.Good;
import ua.com.ke4a_store.service.impl.GoodsServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoodsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getSession().getAttribute("isLogged")){
            resp.sendRedirect("/");
            return;
        }

        Connector connector = new Connector();

        List<Good> goods = new GoodsServiceImpl(new GoodsDaoImpl(connector)).findAll();

        req.getSession().setAttribute("goods",goods);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/goods.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
