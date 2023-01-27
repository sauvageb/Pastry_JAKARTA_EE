package com.training.pastries.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.pastries.dao.PastryDao;
import com.training.pastries.dao.entity.Pastry;
import com.training.pastries.dao.factory.DaoFactory;
import com.training.pastries.dto.PastryDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@WebServlet(PopulateDatabaseServlet.URL)
public class PopulateDatabaseServlet extends HttpServlet {

    public static final String URL = "/populate";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String URL = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/pastries.json";

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url(URL)
                    .method("GET", null)
                    .build();

            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = client.newCall(request).execute().body();
            PastryDTO[] pastries = objectMapper.readValue(responseBody.string(), PastryDTO[].class);

            PastryDao dao = DaoFactory.getPastryDao();

            Arrays
                    .stream(pastries)
                    .forEach(p -> {
                        Pastry pastry = Pastry.from(p);
                        pastry.setId(null);
                        pastry.getOrigin().setId(null);
                        pastry.getImages().stream().peek(i -> i.setId(null)).collect(Collectors.toList());

                        dao.create(pastry);
                    });

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/populate-finished.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
