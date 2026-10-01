package vn.iotstar.service;

import vn.iotstar.dao.CategoryDAO_24110187;
import vn.iotstar.entity.Category_24110187;

import java.util.List;

public class CategoryService_24110187 {

    private final CategoryDAO_24110187 categoryDAO = new CategoryDAO_24110187();

    public List<Category_24110187> findAll() { return categoryDAO.findAll(); }
    public Category_24110187 findById(int id) { return categoryDAO.findById(id); }
    public List<Category_24110187> findAllWithVideoCount() {
        return categoryDAO.findAllWithVideoCount();
    }
}