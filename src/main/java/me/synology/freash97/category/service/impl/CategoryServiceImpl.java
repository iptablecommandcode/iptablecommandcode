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
    public CategoryDTO findById(int categorySq) {
        return categoryMapper.findById(categorySq);
    }

    @Override
    public void save(CategoryDTO categoryDTO, String username) {
        categoryDTO.setCreateUser(username);
        categoryMapper.save(categoryDTO);
    }

    @Override
    public void update(CategoryDTO categoryDTO, String username) {
        categoryDTO.setUpdateUser(username);
        categoryMapper.update(categoryDTO);
    }

    @Override
    public void delete(int categorySq) {
        categoryMapper.delete(categorySq);
    }
}
