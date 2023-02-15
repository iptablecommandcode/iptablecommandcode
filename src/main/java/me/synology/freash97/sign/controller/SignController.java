package me.synology.freash97.Sign.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import me.synology.freash97.Common.FinalKey;
import me.synology.freash97.Member.Entity.MemberEntity;
import me.synology.freash97.Sign.Service.SignService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@AllArgsConstructor
public class SignController {
    public SignService signService;
    private static final String VALUE = "Sign/";

    @GetMapping(VALUE + "Sign_In")
    public String signIn() throws Exception {
        try {
            List<MemberEntity> test = signService.selectMember();
            log.debug("Sign_In");
            log.debug(test.toString());
        } catch (Exception e) {
            log.error("Sign_In 페이지 접속 오류");
            e.getMessage();
        }

        return VALUE + "Sign_In";
    }

    @GetMapping(VALUE + "Sign_Up")
    public String signUp() {
        log.debug("Sign_Up");
        return VALUE + "Sign_Up";
    }

    /*
    auth : 박치원
    title : Register 시 ID 중복확인
    desc : Register시 아이디 중복확인을 위한 컨트롤러 이다.
    * */
    @PostMapping(VALUE + "id_Chk")
    @ResponseBody
    public String idChk(@RequestBody Map<String, Object> param) throws Exception, NullPointerException {
        FinalKey Key = new FinalKey();

        String result = Key.STRINGFAIL;
        boolean idcheck = Key.BOOLFAIL;

        log.debug("아이디 중복확인 실행");
        log.debug("아이디 중복확인 param 값 확인" + param.toString());
        try {
            idcheck = signService.checkId(param);
            log.debug("idcheck 결과 : " + idcheck);
        } catch (NullPointerException e) {
            log.error("아이디 체크 오류 NullPointerException");
            e.getMessage();
        } catch (Exception e) {
            log.error("아이디 체크 오류 Exception");
            e.getMessage();
        }

        //아이디가 중복되지 않으면 "success"리턴
        if (idcheck) {
            result = Key.STRINGSUCCESS;
            log.debug("아이디 중복 없음");
        } else {
            result = Key.STRINGFAIL;
            log.debug("아이디 중복");
        }

        return result;
    }
    @PostMapping(VALUE + "Register")
    public String register(@RequestParam Map<String, Object> param) {
        log.debug("success");
        return "redirect:Sign_In";
    }
    @PostMapping(VALUE + "Sign_In.do")
    public ModelAndView signInAction(@RequestParam Map<String, Object> param, ModelAndView modelAndView) throws Exception, NullPointerException {
        log.debug("Sign_In.do 시작");
        MemberEntity memberEntity;

        if (param.isEmpty()) {
            log.debug("param 없음 계정 생성 실패");
            throw new NullPointerException("계정 생성용 값 내용 없음");
        } else {
            memberEntity = signService.createMember(param);
        }

        log.debug("memberEntity 출력 : " + memberEntity.toString());

        return modelAndView;
    }
}