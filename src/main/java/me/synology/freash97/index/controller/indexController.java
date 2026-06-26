package me.synology.freash97.index.controller;

import lombok.RequiredArgsConstructor;
import me.synology.freash97.board.service.BoardService;
import me.synology.freash97.category.service.CategoryService;
import me.synology.freash97.tag.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class indexController {

    private final BoardService    boardService;
    private final CategoryService categoryService;
    private final TagService      tagService;

    @GetMapping({"/", "/index"})
    public String index(Model model) throws Exception {
        model.addAttribute("recentBoards", boardService.findAll().stream().limit(6).toList());
        model.addAttribute("categories",   categoryService.findAllActive());
        model.addAttribute("allTags",      tagService.findAllActive());
        return "index";
    }
}
