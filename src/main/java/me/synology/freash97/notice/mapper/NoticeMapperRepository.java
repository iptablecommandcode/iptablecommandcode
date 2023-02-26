package me.synology.freash97.notice.mapper;

import me.synology.freash97.notice.entity.NoticeEntity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * date: 2023.02.07
 * name: NoticeMapperRepository.java
 * desc: 게시글 작성, 조회, 수정, 삭제 repository
 */
@Mapper
public interface NoticeMapperRepository {
    // 게시글 작성
    int insertNotice(NoticeEntity noticeEntity);
    // 게시글 전체 조회
    List<NoticeEntity> findAllNotice();
    // 특정 게시글 view
    NoticeEntity findByNoticeSeq(int noticeSeq);
    // 게시글 수정
    int updateNotice(NoticeEntity noticeEntity);
    // 게시글 삭제
    int deleteNotice(int noticeSeq);
}
