package me.synology.freash97.category.mapper;

import me.synology.freash97.category.domain.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDTO> findAll();
    List<CategoryDTO> findAllActive();
    CategoryDTO findById(int categorySq);
    void save(CategoryDTO categoryDTO);
    void update(CategoryDTO categoryDTO);
    void delete(int categorySq);
}
