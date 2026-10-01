package vn.iotstar.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Category_24110187;
import vn.iotstar.entity.Video_24110187;
import vn.iotstar.service.CategoryService_24110187;
import vn.iotstar.service.VideoService_24110187;

import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet_24110187 extends HttpServlet {

    private final CategoryService_24110187 categoryService = new CategoryService_24110187();
    private final VideoService_24110187 videoService = new VideoService_24110187();

    private static final int PAGE_SIZE = 3;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Category_24110187> categories = categoryService.findAllWithVideoCount();

        String idStr = req.getParameter("id");
        int categoryId = -1;

        if (idStr != null && !idStr.isBlank()) {
            try { categoryId = Integer.parseInt(idStr); }
            catch (NumberFormatException e) { categoryId = -1; }
        }

        if (categoryId == -1 && !categories.isEmpty()) {
            categoryId = categories.get(0).getCategoryId();
        }

        if (categoryId != -1) {
            int page = 1;
            try { page = Integer.parseInt(req.getParameter("page")); } catch (Exception ignored) {}
            if (page < 1) page = 1;

            Category_24110187 current = categoryService.findById(categoryId);
            List<Video_24110187> videos = videoService.findByCategory(categoryId, page, PAGE_SIZE);
            int total = videoService.countByCategory(categoryId);
            int totalPages = (int) Math.ceil((double) total / PAGE_SIZE);

            req.setAttribute("currentCategory", current);
            req.setAttribute("videos", videos);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("totalVideos", total);
        }

        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/views/user/video-by-category.jsp").forward(req, resp);
    }
}