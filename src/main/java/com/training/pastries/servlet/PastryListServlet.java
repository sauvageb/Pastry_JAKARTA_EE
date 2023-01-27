package com.training.pastries.servlet;


import com.training.pastries.dao.PastryDao;
import com.training.pastries.dao.entity.Pastry;
import com.training.pastries.dao.factory.DaoFactory;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/pastry-list")
public class PastryListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PastryDao dao = DaoFactory.getPastryDao();
        List<Pastry> pastryList = dao.findAll();
        if (pastryList.isEmpty()) {
            resp.sendRedirect(PopulateDatabaseServlet.URL);

        } else {
            req.setAttribute("pastries", pastryList);

            String searchedPastryName = req.getParameter("searchName");
            if (searchedPastryName != null) {
                List<Pastry> searchedPastries = dao.findPastrysByName(searchedPastryName);
                req.setAttribute("searchedPastries", searchedPastries);
                req.setAttribute("searchedValue", searchedPastryName);
            }

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pastry-list.jsp");
            rd.forward(req, resp);
        }
    }


}
