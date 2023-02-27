package me.synology.freash97.paging.controller;


import lombok.AllArgsConstructor;
import me.synology.freash97.paging.service.PagingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@AllArgsConstructor
public class PagingController {

    private final PagingService pagingService;

    @GetMapping("/list")
    public String getNoticeListyPageNo(@ModelAttribute("currentPageNo") int currentPageNo) {

        // Notice Controller에서 Notice Service 호출하도록 수정 예정
        // (currentpageNo만 넘긴 후 Notice 서비스 단에서 Paging 로직 추가)
        pagingService.getPageInfoByPageNo(currentPageNo, 0);

        return "";
    }

    @GetMapping("/list/prev")
    public String getNoticeListByPrev() {

        return "";
    }

    @GetMapping("/list/next")
    public String getNoticeListByNext() {

        return "";
    }

}
