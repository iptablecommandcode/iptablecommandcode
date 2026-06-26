package me.synology.freash97.category.service;

import me.synology.freash97.category.domain.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
    List<CategoryDTO> findAllActive();
    CategoryDTO findById(int categorySq);
    void save(CategoryDTO categoryDTO, String username);
    void update(CategoryDTO categoryDTO, String username);
    void delete(int categorySq);
}
