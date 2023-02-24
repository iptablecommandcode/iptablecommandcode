package me.synology.freash97.paging.mapper;

import me.synology.freash97.notice.entity.NoticeEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PagingRepository {
    List<NoticeEntity> findAllNotice();
}
