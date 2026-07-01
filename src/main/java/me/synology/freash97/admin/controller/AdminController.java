package me.synology.freash97.admin.controller;

import lombok.RequiredArgsConstructor;
import me.synology.freash97.board.domain.BoardDTO;
import me.synology.freash97.board.service.BoardService;
import me.synology.freash97.category.domain.CategoryDTO;
import me.synology.freash97.category.service.CategoryService;
import me.synology.freash97.comment.service.CommentService;
import me.synology.freash97.common.enumconfig.ResultValue;
import me.synology.freash97.emaildomain.domain.EmailDomainDTO;
import me.synology.freash97.emaildomain.service.EmailDomainService;
import me.synology.freash97.sign.domain.SignDTO;
import me.synology.freash97.sign.service.SignService;
import me.synology.freash97.tag.domain.TagDTO;
import me.synology.freash97.tag.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final BoardService boardService;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final SignService signService;
    private final EmailDomainService emailDomainService;
    private final CommentService commentService;

    //URL 상수 지정
    private static final String REDIRECT_SIGN_IN_URL = "redirect:/sign/signIn";

    private boolean isAdmin(HttpSession session) {
        SignDTO loginUser = (SignDTO) session.getAttribute("loginUser");
        return loginUser != null && "Y".equals(loginUser.getAdmin());
    }

    private void assertAdmin(HttpSession session) {
        if (!isAdmin(session)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "관리자 권한이 필요합니다.");
        }
    }

    private String currentUsername(HttpSession session) {
        SignDTO loginUser = (SignDTO) session.getAttribute("loginUser");
        return loginUser != null ? loginUser.getUsername() : "admin";
    }

    // ── 대시보드 ──
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (!isAdmin(session)) return REDIRECT_SIGN_IN_URL;
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
    public String boardList(Model model, HttpSession session) {
        if (!isAdmin(session)) return REDIRECT_SIGN_IN_URL;
        model.addAttribute("boards", boardService.findAllForAdmin());
        return "admin/boardList";
    }

    @PostMapping("/boards/{boardSq}/delete")
    @ResponseBody
    public Map<String, Object> deleteBoard(@PathVariable int boardSq, HttpSession session) {
        assertAdmin(session);
        boardService.delete(boardSq);
        Map<String, Object> result = new HashMap<>();
        result.put(ResultValue.succ.getValue(), true);
        return result;
    }

    @PostMapping("/boards/{boardSq}/notice")
    @ResponseBody
    public Map<String, Object> toggleNotice(@PathVariable int boardSq, @RequestParam String noticeYn, HttpSession session) {
        assertAdmin(session);
        BoardDTO dto = new BoardDTO();
        dto.setBoardSq(boardSq);
        dto.setNoticeYn(noticeYn);
        dto.setUpdateUser(currentUsername(session));
        boardService.updateNotice(dto);
        Map<String, Object> result = new HashMap<>();
        result.put("noticeYn", noticeYn);
        return result;
    }

    // ── 댓글 관리 ──
    @GetMapping("/comments")
    public String commentList(Model model, HttpSession session) {
        if (!isAdmin(session)) return REDIRECT_SIGN_IN_URL;
        model.addAttribute("comments", commentService.findAllForAdmin());
        return "admin/commentList";
    }

    @PostMapping("/comments/{commentSq}/delete")
    @ResponseBody
    public Map<String, Object> deleteComment(@PathVariable long commentSq, HttpSession session) {
        assertAdmin(session);
        commentService.delete(commentSq);
        Map<String, Object> result = new HashMap<>();
        result.put(ResultValue.succ.getValue(), true);
        return result;
    }

    // ── 카테고리 관리 ──
    @GetMapping("/categories")
    public String categoryList(Model model, HttpSession session) {
        if (!isAdmin(session)) return REDIRECT_SIGN_IN_URL;
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("users", signService.findAll());
        return "admin/categoryList";
    }

    @PostMapping("/categories/save")
    @ResponseBody
    public Map<String, Object> saveCategory(@RequestBody CategoryDTO dto, HttpSession session) {
        assertAdmin(session);
        categoryService.save(dto, currentUsername(session));
        Map<String, Object> result = new HashMap<>();
        result.put(ResultValue.succ.getValue(), true);
        return result;
    }

    @PostMapping("/categories/update")
    @ResponseBody
    public Map<String, Object> updateCategory(@RequestBody CategoryDTO dto, HttpSession session) {
        assertAdmin(session);
        categoryService.update(dto, currentUsername(session));
        Map<String, Object> result = new HashMap<>();
        result.put(ResultValue.succ.getValue(), true);
        return result;
    }

    @PostMapping("/categories/{categorySq}/delete")
    @ResponseBody
    public Map<String, Object> deleteCategory(@PathVariable int categorySq, HttpSession session) {
        assertAdmin(session);
        categoryService.delete(categorySq);
        Map<String, Object> result = new HashMap<>();
        result.put(ResultValue.succ.getValue(), true);
        return result;
    }

    // ── 태그 관리 ──
    @GetMapping("/tags")
    public String tagList(Model model, HttpSession session) {
        if (!isAdmin(session)) return REDIRECT_SIGN_IN_URL;
        model.addAttribute("tags", tagService.findAll());
        return "admin/tagList";
    }

    @PostMapping("/tags/save")
    @ResponseBody
    public Map<String, Object> saveTag(@RequestBody TagDTO dto, HttpSession session) {
        assertAdmin(session);
        dto.setCreateUser(currentUsername(session));
        tagService.save(dto);
        Map<String, Object> result = new HashMap<>();
        result.put(ResultValue.succ.getValue(), true);
        return result;
    }

    @PostMapping("/tags/{tagSq}/delete")
    @ResponseBody
    public Map<String, Object> deleteTag(@PathVariable int tagSq, HttpSession session) {
        assertAdmin(session);
        tagService.delete(tagSq);
        Map<String, Object> result = new HashMap<>();
        result.put(ResultValue.succ.getValue(), true);
        return result;
    }

    // ── 회원 관리 ──
    @GetMapping("/users")
    public String userList(Model model, HttpSession session) {
        if (!isAdmin(session)) return REDIRECT_SIGN_IN_URL;
        model.addAttribute("users", signService.findAll());
        return "admin/userList";
    }

    @PostMapping("/users/{userId}/toggleAdmin")
    @ResponseBody
    public Map<String, Object> toggleAdmin(@PathVariable int userId, @RequestParam String adminYn, HttpSession session) {
        assertAdmin(session);
        signService.updateAdmin(userId, adminYn);
        Map<String, Object> result = new HashMap<>();
        result.put(ResultValue.succ.getValue(), true);
        return result;
    }

    @PostMapping("/users/{userId}/delete")
    @ResponseBody
    public Map<String, Object> deleteUser(@PathVariable int userId, HttpSession session) {
        assertAdmin(session);
        signService.deleteUser(userId);
        Map<String, Object> result = new HashMap<>();
        result.put(ResultValue.succ.getValue(), true);
        return result;
    }

    // ── 이메일 도메인 관리 ──
    @GetMapping("/email-domains")
    public String emailDomainList(Model model, HttpSession session) {
        if (!isAdmin(session)) return REDIRECT_SIGN_IN_URL;
        model.addAttribute("domains", emailDomainService.findAll());
        return "admin/emailDomainList";
    }

    @PostMapping("/email-domains/save")
    @ResponseBody
    public Map<String, Object> saveDomain(@RequestBody EmailDomainDTO dto, HttpSession session) {
        assertAdmin(session);
        dto.setCreateUser(currentUsername(session));
        emailDomainService.save(dto);
        Map<String, Object> result = new HashMap<>();
        result.put(ResultValue.succ.getValue(), true);
        return result;
    }

    @PostMapping("/email-domains/{domainSq}/toggle")
    @ResponseBody
    public Map<String, Object> toggleDomain(@PathVariable int domainSq, @RequestParam String useYn, HttpSession session) {
        assertAdmin(session);
        emailDomainService.updateUseYn(domainSq, useYn);
        Map<String, Object> result = new HashMap<>();
        result.put(ResultValue.succ.getValue(), true);
        return result;
    }

    @PostMapping("/email-domains/{domainSq}/delete")
    @ResponseBody
    public Map<String, Object> deleteDomain(@PathVariable int domainSq, HttpSession session) {
        assertAdmin(session);
        emailDomainService.delete(domainSq);
        Map<String, Object> result = new HashMap<>();
        result.put(ResultValue.succ.getValue(), true);
        return result;
    }
}
