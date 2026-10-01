package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Video_24110187;
import vn.iotstar.service.FavoriteService_24110187;
import vn.iotstar.service.ShareService_24110187;
import vn.iotstar.service.VideoService_24110187;

import java.io.IOException;

@WebServlet("/video/*")
public class VideoServlet_24110187 extends HttpServlet {

    private final VideoService_24110187 videoService = new VideoService_24110187();
    private final FavoriteService_24110187 favoriteService = new FavoriteService_24110187();
    private final ShareService_24110187 shareService = new ShareService_24110187();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getPathInfo();
        if (path == null) path = "";

        if (path.equals("/detail")) {
            String id = req.getParameter("id");
            if (id == null || id.isBlank()) {
                resp.sendRedirect(req.getContextPath() + "/home");
                return;
            }

            Video_24110187 video = videoService.findDetailAndCountView(id);

            if (video == null) {
                resp.sendError(404, "Không tìm thấy video!");
                return;
            }

            if (video.getShareCount() == null) video.setShareCount(shareService.countByVideo(id));
            if (video.getLikeCount()  == null) video.setLikeCount(favoriteService.countByVideo(id));

            req.setAttribute("video", video);
            req.getRequestDispatcher("/WEB-INF/views/user/video-detail.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/home");
    }
}