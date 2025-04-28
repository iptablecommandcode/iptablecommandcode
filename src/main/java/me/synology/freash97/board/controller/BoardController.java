package me.synology.freash97.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.synology.freash97.board.service.BoardService;
import me.synology.freash97.board.vo.entity.BoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String boardList(Model model) throws Exception {
        List<BoardDTO> posts = boardService.findAll();
        model.addAttribute("posts", posts);
        return "board-list";  // board-list.html
    }

    @GetMapping("/{id}")
    public String boardDetail(@PathVariable Long id, Model model) throws Exception {
        BoardDTO post = boardService.findById(id);
        model.addAttribute("post", post);
        return "board-detail";  // board-detail.html
    }

    @GetMapping("/new")
    public String createForm() {
        return "board-new";  // board-new.html
    }

    @PostMapping("/new")
    public String createBoard(@ModelAttribute BoardDTO board, Principal principal) throws Exception {
        boardService.save(board, principal.getName());
        return "redirect:/board";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) throws Exception {
        BoardDTO post = boardService.findById(id);
        model.addAttribute("post", post);
        return "board-edit";  // board-edit.html
    }

    @PostMapping("/{id}/edit")
    public String editBoard(@ModelAttribute BoardDTO boardDTO) throws Exception {
        boardService.update(boardDTO);
        return "redirect:/board/{id}";
    }

    @PostMapping("/{id}/delete")
    public String deleteBoard(@PathVariable Long id) throws Exception {
        boardService.delete(id);
        return "redirect:/board";
    }
}
