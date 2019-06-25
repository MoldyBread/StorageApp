package ua.com.ke4a_store.Controller;

import ua.com.ke4a_store.dao.impl.Connector;
import ua.com.ke4a_store.dao.impl.GoodsDaoImpl;
import ua.com.ke4a_store.entity.Good;
import ua.com.ke4a_store.entity.GoodsGroup;
import ua.com.ke4a_store.service.GoodsService;
import ua.com.ke4a_store.service.impl.GoodsServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoodsController extends HttpServlet {
    private static final Connector connector = new Connector();
    private static final GoodsService goodsService = new GoodsServiceImpl(new GoodsDaoImpl(connector));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getSession().getAttribute("isLogged")) {
            resp.sendRedirect("/");
            return;
        }
//

        List<Good> goods = goodsService.findAll();

        req.getSession().setAttribute("goods", goods);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/goods.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        switch (action) {
            case "delete":
                long id = Long.parseLong(req.getParameter("id"));
                goodsService.deleteById(id);
                resp.sendRedirect("/goods");
                break;
            case "add":

                break;
        }
    }
}
