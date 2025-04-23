package me.synology.freash97.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * packageName   : me.synology.freash97.board.controller
 * fileName      : boardController
 * author        : iptable
 * date          : 2025-04-23
 * time          : 오후 10:38
 * description   :
 * ====================================================
 * DATE                  AUTHOR              NOTE
 * ----------------------------------------------------
 * 2025-04-23               iptab             최초 생성
 */
@Slf4j
@Controller
@RequestMapping("board")
public class boardController {

    @GetMapping("/boardList")
    public String boardList() {
        log.debug("boardList Controller Start !!!");

        return "board/boardList";
    }
}
