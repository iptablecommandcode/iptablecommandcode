package me.synology.freash97.category.mapper;

import me.synology.freash97.category.domain.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDTO> findAll();
    List<CategoryDTO> findAllActive();
    List<CategoryDTO> findVisible(@Param("userId") Integer userId, @Param("adminYn") String adminYn);
    List<CategoryDTO> findCommonActive();
    List<CategoryDTO> findByOwner(@Param("userId") int userId);
    CategoryDTO findById(int categorySq);
    void save(CategoryDTO categoryDTO);
    void update(CategoryDTO categoryDTO);
    void delete(int categorySq);
}
