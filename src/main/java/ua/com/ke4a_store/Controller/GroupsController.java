package ua.com.ke4a_store.Controller;

import ua.com.ke4a_store.dao.impl.Connector;
import ua.com.ke4a_store.dao.impl.GroupsDaoImpl;
import ua.com.ke4a_store.entity.GoodsGroup;
import ua.com.ke4a_store.service.GroupsService;
import ua.com.ke4a_store.service.impl.GroupsServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GroupsController extends HttpServlet {
    private static final Connector connector = new Connector();
    private static final GroupsService groupsService = new GroupsServiceImpl(new GroupsDaoImpl(connector));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getSession().getAttribute("isLogged")) {
            resp.sendRedirect("/");
            return;
        }


        List<GoodsGroup> groups = groupsService.findAll();

        req.getSession().setAttribute("groups", groups);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/groups.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String name;
        long id;

        switch (action) {
            case "delete":
                id = Long.parseLong(req.getParameter("id"));
                groupsService.deleteById(id);
                resp.sendRedirect("/groups");
                break;
            case "add":
                name = req.getParameter("name");
                groupsService.insert(new GoodsGroup(name));
                resp.sendRedirect("/groups");
                break;
            case "edit":
                name = req.getParameter("name");
                id = Long.parseLong(req.getParameter("id"));
                groupsService.updateById(new GoodsGroup(name));
                resp.sendRedirect("/groups");
                break;
            default:
                    resp.sendRedirect("/goods");
        }
    }
}
