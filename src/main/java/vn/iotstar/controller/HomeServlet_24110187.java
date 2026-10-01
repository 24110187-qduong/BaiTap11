package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Video_24110187;
import vn.iotstar.service.VideoService_24110187;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet_24110187 extends HttpServlet {

    private final VideoService_24110187 videoService = new VideoService_24110187();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Video_24110187> videos = videoService.findActive();
        req.setAttribute("videos", videos);
        req.getRequestDispatcher("/WEB-INF/views/user/home.jsp").forward(req, resp);
    }
}