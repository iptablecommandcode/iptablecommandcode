package me.synology.freash97.category.service;

import me.synology.freash97.category.domain.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
    List<CategoryDTO> findAllActive();
    List<CategoryDTO> findVisible(Integer userId, boolean admin);
    List<CategoryDTO> findCommonActive();
    List<CategoryDTO> findByOwner(int userId);
    CategoryDTO findById(int categorySq);
    boolean canUseCategory(Integer categorySq, Integer userId, boolean admin);
    boolean canManageCategory(int categorySq, Integer userId, boolean admin);
    void save(CategoryDTO categoryDTO, String username);
    void update(CategoryDTO categoryDTO, String username);
    void delete(int categorySq);
}
