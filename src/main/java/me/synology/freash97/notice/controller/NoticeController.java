package me.synology.freash97.notice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.notice.entity.NoticeEntity;
import me.synology.freash97.notice.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// 게시글을 클릭 -> 게시글 번호 -> findByNoticeSeq -> 페이지에 해당 게시글의 값 보여줌
// 보드 상세페이지 -> 수정 -> notice_write -> 저장 -> 수정된 값 db 저장 후 -> notice_view
// 보드 상세페이지 -> 삭제 -> 상태코드.update_date -> 인덱스

@Controller
@Slf4j
@RequiredArgsConstructor
//@RequestMapping("/notice/*")
public class NoticeController {

    private final NoticeService noticeService;

    // 게시글 전체 조회 => index => index 페이지가 결국 notice_list ..
    @GetMapping
    public String noticeEntityList(Model model) {
        model.addAttribute("noticeEntityList", noticeService.noticeEntityList());
        return "/index";
    }

    // 게시글 1건 조회
    @GetMapping("/notice/notice_view")
    public String findByNoticeSeq(@PathVariable int noticeSeq, Model model) {
        model.addAttribute("noticeView", noticeService.findByNoticeSeq(noticeSeq));
        return "notice/notice_view";
    }

    // 게시글 작성 => notice_write 로 접속 후 게시글 작성
    @PostMapping("/notice/notice_write")
    public String insertNotice(@RequestParam String title, @RequestParam String content, Model model) {
        NoticeEntity newNotice = new NoticeEntity(title, content);
        int noticeSeq = noticeService.insertNotice(newNotice);

        model.addAttribute("noticeSeq", noticeSeq);

        return "redirect:/notice/notice_view/{noticeSeq}";
    }

    // 게시글 수정 url1 => ex.1번 게시글에서 '수정'버튼 눌렀을때 notice_write 로 이동
    @GetMapping("/notice/notice_view")
    public String updateNoticeGet(@RequestParam int noticeSeq, Model model) {
        if(noticeSeq != 0) {
            model.addAttribute("noticeView", noticeService.findByNoticeSeq(noticeSeq));
        }
        return "notice/notice_view/{noticeSeq}";
    }

    // 게시글 수정 url2?(requestparam으로 write와 수정 구분??)
    // => 수정 컬럼 (title, content, notice_stts_cd, update_date) '저장(수정완료)' 클릭 후 notice_view 로 이동
    @PostMapping("/notice/notice_write")
    public String updateNoticeSave(NoticeEntity noticeEntity, RedirectAttributes redirectAttributes) {
        noticeService.updateNotice(noticeEntity);
        return "redirect:/notice/notice_view/{noticeSeq}";
    }


    // 게시글 삭제 => 삭제 event 업데이트 후 (update_date, notice_stts_cd) index 페이지로 리턴

}