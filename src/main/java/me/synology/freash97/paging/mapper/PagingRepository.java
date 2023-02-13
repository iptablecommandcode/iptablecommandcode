package me.synology.freash97.paging.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PagingRepository {
    int getToTalCount();
}
