package vn.iotstar.service;

import vn.iotstar.dao.VideoDAO_24110187;
import vn.iotstar.entity.Video_24110187;

import java.util.List;

public class VideoService_24110187 {

    private final VideoDAO_24110187 videoDAO = new VideoDAO_24110187();

    public List<Video_24110187> findActive() { return videoDAO.findByActive(true); }
    public Video_24110187 findById(String id) { return videoDAO.findById(id); }

    public Video_24110187 findDetailAndCountView(String id) {
        videoDAO.incrementViews(id);
        return videoDAO.findByIdWithDetail(id);
    }

    public List<Video_24110187> findByCategory(int categoryId, int page, int pageSize) {
        return videoDAO.findByCategory(categoryId, page, pageSize);
    }

    public int countByCategory(int categoryId) {
        return videoDAO.countByCategory(categoryId);
    }
}