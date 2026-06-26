package me.synology.freash97.admin.controller;

import me.synology.freash97.board.domain.BoardDTO;
import me.synology.freash97.board.service.BoardService;
import me.synology.freash97.category.domain.CategoryDTO;
import me.synology.freash97.category.service.CategoryService;
import me.synology.freash97.comment.service.CommentService;
import me.synology.freash97.emaildomain.domain.EmailDomainDTO;
import me.synology.freash97.emaildomain.service.EmailDomainService;
import me.synology.freash97.sign.domain.SignDTO;
import me.synology.freash97.sign.service.SignService;
import me.synology.freash97.tag.domain.TagDTO;
import me.synology.freash97.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private BoardService boardService;
    @Autowired private CategoryService categoryService;
    @Autowired private TagService tagService;
    @Autowired private SignService signService;
    @Autowired private EmailDomainService emailDomainService;
    @Autowired private CommentService commentService;

    // ── 대시보드 ──
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<BoardDTO> recentBoards = boardService.findAllForAdmin();
        model.addAttribute("recentBoards", recentBoards.size() > 10 ? recentBoards.subList(0, 10) : recentBoards);
        model.addAttribute("totalBoards", boardService.countAll());
        model.addAttribute("totalUsers", signService.countAll());
        model.addAttribute("totalComments", commentService.countAll());
        model.addAttribute("totalCategories", categoryService.findAll().size());
        return "admin/dashboard";
    }

    // ── 게시글 관리 ──
    @GetMapping("/boards")
    public String boardList(Model model) {
        model.addAttribute("boards", boardService.findAllForAdmin());
        return "admin/boardList";
    }

    @PostMapping("/boards/{boardSq}/delete")
    @ResponseBody
    public Map<String, Object> deleteBoard(@PathVariable int boardSq) {
        boardService.delete(boardSq);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @PostMapping("/boards/{boardSq}/notice")
    @ResponseBody
    public Map<String, Object> toggleNotice(@PathVariable int boardSq, @RequestParam String noticeYn, HttpSession session) {
        SignDTO loginUser = (SignDTO) session.getAttribute("loginUser");
        BoardDTO dto = new BoardDTO();
        dto.setBoardSq(boardSq);
        dto.setNoticeYn(noticeYn);
        dto.setUpdateUser(loginUser != null ? loginUser.getUsername() : "admin");
        boardService.updateNotice(dto);
        Map<String, Object> result = new HashMap<>();
        result.put("noticeYn", noticeYn);
        return result;
    }

    // ── 댓글 관리 ──
    @GetMapping("/comments")
    public String commentList(Model model) {
        model.addAttribute("comments", commentService.findAllForAdmin());
        return "admin/commentList";
    }

    @PostMapping("/comments/{commentSq}/delete")
    @ResponseBody
    public Map<String, Object> deleteComment(@PathVariable long commentSq) {
        commentService.delete(commentSq);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    // ── 카테고리 관리 ──
    @GetMapping("/categories")
    public String categoryList(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/categoryList";
    }

    @PostMapping("/categories/save")
    @ResponseBody
    public Map<String, Object> saveCategory(@RequestBody CategoryDTO dto, HttpSession session) {
        SignDTO loginUser = (SignDTO) session.getAttribute("loginUser");
        String username = loginUser != null ? loginUser.getUsername() : "admin";
        categoryService.save(dto, username);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @PostMapping("/categories/update")
    @ResponseBody
    public Map<String, Object> updateCategory(@RequestBody CategoryDTO dto, HttpSession session) {
        SignDTO loginUser = (SignDTO) session.getAttribute("loginUser");
        String username = loginUser != null ? loginUser.getUsername() : "admin";
        categoryService.update(dto, username);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @PostMapping("/categories/{categorySq}/delete")
    @ResponseBody
    public Map<String, Object> deleteCategory(@PathVariable int categorySq) {
        categoryService.delete(categorySq);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    // ── 태그 관리 ──
    @GetMapping("/tags")
    public String tagList(Model model) {
        model.addAttribute("tags", tagService.findAll());
        return "admin/tagList";
    }

    @PostMapping("/tags/save")
    @ResponseBody
    public Map<String, Object> saveTag(@RequestBody TagDTO dto, HttpSession session) {
        SignDTO loginUser = (SignDTO) session.getAttribute("loginUser");
        dto.setCreateUser(loginUser != null ? loginUser.getUsername() : "admin");
        tagService.save(dto);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @PostMapping("/tags/{tagSq}/delete")
    @ResponseBody
    public Map<String, Object> deleteTag(@PathVariable int tagSq) {
        tagService.delete(tagSq);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    // ── 회원 관리 ──
    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", signService.findAll());
        return "admin/userList";
    }

    @PostMapping("/users/{userId}/toggleAdmin")
    @ResponseBody
    public Map<String, Object> toggleAdmin(@PathVariable int userId, @RequestParam String adminYn) {
        signService.updateAdmin(userId, adminYn);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @PostMapping("/users/{userId}/delete")
    @ResponseBody
    public Map<String, Object> deleteUser(@PathVariable int userId) {
        signService.deleteUser(userId);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    // ── 이메일 도메인 관리 ──
    @GetMapping("/email-domains")
    public String emailDomainList(Model model) {
        model.addAttribute("domains", emailDomainService.findAll());
        return "admin/emailDomainList";
    }

    @PostMapping("/email-domains/save")
    @ResponseBody
    public Map<String, Object> saveDomain(@RequestBody EmailDomainDTO dto, HttpSession session) {
        SignDTO loginUser = (SignDTO) session.getAttribute("loginUser");
        dto.setCreateUser(loginUser != null ? loginUser.getUsername() : "admin");
        emailDomainService.save(dto);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @PostMapping("/email-domains/{domainSq}/toggle")
    @ResponseBody
    public Map<String, Object> toggleDomain(@PathVariable int domainSq, @RequestParam String useYn) {
        emailDomainService.updateUseYn(domainSq, useYn);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }

    @PostMapping("/email-domains/{domainSq}/delete")
    @ResponseBody
    public Map<String, Object> deleteDomain(@PathVariable int domainSq) {
        emailDomainService.delete(domainSq);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }
}
