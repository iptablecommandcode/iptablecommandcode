package me.synology.freash97.notice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.notice.entity.NoticeEntity;
import me.synology.freash97.notice.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// 게시글의 page 번호 url 컨트롤러 (index) -> 페이징.서비스로 넘김
// 게시글을 클릭 -> 게시글 번호 -> findByNoticeSeq -> 페이지에 해당 게시글의 값 보여줌
// 보드 상세페이지 -> 수정 -> notice_write -> 저장 -> 수정된 값 db 저장 후 -> notice_view
// 보드 상세페이지 -> 삭제 -> 상태코드.update_date -> 인덱스

@Controller
@Slf4j
@RequiredArgsConstructor
// @RequestMapping("notice/notice_view")
public class NoticeController {

    private final NoticeService noticeService;

    // 게시글 전체 조회 => index

    // 게시글 1건 조회
    @GetMapping("/{noticeSeq}")
    public NoticeEntity findByNoticeSeq(@PathVariable int noticeSeq) {
        return noticeService.findByNoticeSeq(noticeSeq);
    }

    // 게시글 작성 => 작성 저장 후 게시글 view

    // 게시글 수정 => 수정된 게시글 view

    // 게시글 삭제 => 삭제 후 index 리턴
}