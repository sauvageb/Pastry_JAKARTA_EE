package com.training.pastries.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@WebServlet(urlPatterns = "/thumb/*")
public class LoadThumbServlet extends HttpServlet {

    private static final String THUMBS_FOLDER = "thumbs";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resourceName = req.getPathInfo();

        String fullPath = getServletContext().getRealPath(THUMBS_FOLDER) + resourceName;

        File thumbFile = new File(fullPath);
        if (!thumbFile.exists() || !thumbFile.isFile()) {
            resp.sendError(resp.SC_NOT_FOUND);
        } else {
            Path path = thumbFile.toPath();
            String mimeType = Files.probeContentType(path);
            resp.setContentType(mimeType);
            try (OutputStream out = resp.getOutputStream()) {
                Files.copy(path, out);
                out.flush();
            } catch (IOException e) {
                resp.sendError(resp.SC_INTERNAL_SERVER_ERROR, "IO Exception image");
            }
        }
    }
}
