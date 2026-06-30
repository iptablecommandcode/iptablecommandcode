package me.synology.freash97.category.service.impl;

import me.synology.freash97.category.domain.CategoryDTO;
import me.synology.freash97.category.mapper.CategoryMapper;
import me.synology.freash97.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public List<CategoryDTO> findAllActive() {
        return categoryMapper.findAllActive();
    }

    @Override
    public List<CategoryDTO> findVisible(Integer userId, boolean admin) {
        return categoryMapper.findVisible(userId, admin ? "Y" : "N");
    }

    @Override
    public List<CategoryDTO> findCommonActive() {
        return categoryMapper.findCommonActive();
    }

    @Override
    public List<CategoryDTO> findByOwner(int userId) {
        return categoryMapper.findByOwner(userId);
    }

    @Override
    public CategoryDTO findById(int categorySq) {
        return categoryMapper.findById(categorySq);
    }

    @Override
    public boolean canUseCategory(Integer categorySq, Integer userId, boolean admin) {
        if (categorySq == null) {
            return true;
        }
        CategoryDTO category = categoryMapper.findById(categorySq);
        if (category == null || !"Y".equals(category.getUseYn())) {
            return false;
        }
        return admin
                || "Y".equals(category.getCommonYn())
                || (userId != null && userId.equals(category.getUserId()));
    }

    @Override
    public boolean canManageCategory(int categorySq, Integer userId, boolean admin) {
        CategoryDTO category = categoryMapper.findById(categorySq);
        if (category == null) {
            return false;
        }
        return admin || (userId != null && userId.equals(category.getUserId()));
    }

    @Override
    public void save(CategoryDTO categoryDTO, String username) {
        if (categoryDTO.getCommonYn() == null) {
            categoryDTO.setCommonYn("N");
        }
        if (categoryDTO.getUseYn() == null) {
            categoryDTO.setUseYn("Y");
        }
        if (categoryDTO.getSortOrder() == null) {
            categoryDTO.setSortOrder(0);
        }
        categoryDTO.setCreateUser(username);
        categoryMapper.save(categoryDTO);
    }

    @Override
    public void update(CategoryDTO categoryDTO, String username) {
        if (categoryDTO.getCommonYn() == null) {
            categoryDTO.setCommonYn("N");
        }
        if (categoryDTO.getUseYn() == null) {
            categoryDTO.setUseYn("Y");
        }
        if (categoryDTO.getSortOrder() == null) {
            categoryDTO.setSortOrder(0);
        }
        categoryDTO.setUpdateUser(username);
        categoryMapper.update(categoryDTO);
    }

    @Override
    public void delete(int categorySq) {
        categoryMapper.delete(categorySq);
    }
}
