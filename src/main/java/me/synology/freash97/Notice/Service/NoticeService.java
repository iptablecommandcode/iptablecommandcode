package me.synology.freash97.Notice.Service;

import lombok.RequiredArgsConstructor;
import me.synology.freash97.Notice.Entity.NoticeEntity;
import me.synology.freash97.Notice.Mapper.NoticeMapperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * date: 2023.02.11
 * name: NoticeService.java
 * desc: 게시글 작성, 조회, 수정, 삭제 service
 */
@Service
@RequiredArgsConstructor
public class NoticeService {

    @Autowired
    private final NoticeMapperRepository noticeMapperRepository;

    // 게시글 작성
    public int insertNotice(NoticeEntity noticeEntity) {
        noticeMapperRepository.insertNotice(noticeEntity);
        return noticeEntity.getNoticeSeq();
    }

    // 게시글 전체 조회 (index 페이지)
    @Transactional(readOnly = true)
    public List<NoticeEntity> noticeEntityList() {
        return noticeMapperRepository.findAllNotice();
    }

    // 특정 게시글 1건 조회
    @Transactional(readOnly = true)
    public NoticeEntity findByNoticeSeq(int noticeSeq) {
        return noticeMapperRepository.findByNoticeSeq(noticeSeq);
    }

    // 게시글 수정
    public NoticeEntity updateNotice(NoticeEntity noticeEntity) {
        return noticeMapperRepository.updateNotice(noticeEntity);
    }

    // 게시글 삭제
    public int deleteNotice(int noticeSeq) {
        return noticeMapperRepository.deleteNotice(noticeSeq);
    }

}
