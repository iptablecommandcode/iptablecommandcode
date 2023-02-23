package me.synology.freash97.paging.entity;

import lombok.Getter;
import lombok.Setter;
import me.synology.freash97.notice.entity.NoticeEntity;

import java.util.List;

// 한 페이지의 게시글 개수 : 10개
// 보여줄 페이지 번호 개수 : 5개 ( 1 ~ 5)
@Getter @Setter
public class PagingEntity {

    private int startNoticeNo;                      // 게시판 시작 번호
    private int endNoticeNo;                        // 게시판 끝 번호

    private int currentPageNo;                      // 보여줄 페이지 번호
    private int startPageNo;                        // 페이지 시작 번호
    private int endPageNo;                          // 페이지 끝 번호
    private final int PAGE_SIZE = 5;                // 보여줄 페이지 번호 개수
    private final int NOTICE_SIZE = 10;             // 보여줄 게시글 개수
    private boolean prev;                           // 이전 버튼 활성화 여부
    private boolean next;                           // 다음 버튼 활성화 여부
    private List<NoticeEntity> Notices;             // 조회하는 게시글 리스트
}
