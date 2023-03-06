package me.synology.freash97.sign.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import me.synology.freash97.common.FinalKey;
import me.synology.freash97.member.entity.MemberEntity;
import me.synology.freash97.sign.service.SignService;
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

    /**
    auth : 박치원
    title : Sign_In 접속
    desc : Sign_In 접속시 사용하는 로직
    */
    @GetMapping(VALUE + "Sign_In")
    public String signIn() throws Exception {
        log.debug("Sign_In 접속");
        return VALUE + "Sign_In";
    }

    /**
     * @desc Sign_Up페이지 이동
     * @return String
     */
    @GetMapping(VALUE + "Sign_Up")
    public String signUp() {
        log.debug("Sign_Up 페이지 접속");
        return VALUE + "Sign_Up";
    }

    /**
    auth : 박치원
    title : Register 시 ID 중복확인
    desc : Register시 아이디 중복확인을 위한 컨트롤러 이다.
    * */
    @PostMapping(VALUE + "id_Chk")
    @ResponseBody
    public String idChk(@RequestBody Map<String, Object> param) throws Exception, NullPointerException {
        FinalKey Key = new FinalKey();

        String result = Key.STRINGFAIL;
        boolean idcheck = false;

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

    /**
     * @param param
     * @param modelAndView
     * @return ModelAndView
     * @throws Exception
     * @throws NullPointerException
     * @desc 로그인시 사용하는 메소드
     */
    @PostMapping(VALUE + "Sign_In.do")
    public ModelAndView signInAction(@RequestParam Map<String, Object> param, ModelAndView modelAndView) throws Exception, NullPointerException {
        log.debug("Sign_In.do 시작");
        MemberEntity memberEntity;

        if (param.isEmpty()) {
            log.debug("param 없음 계정 생성 실패");
            throw new NullPointerException("계정 생성용 값 내용 없음");
        } else {
            memberEntity = signService.sign_In(param);
        }

        log.debug("memberEntity 출력 : " + memberEntity.toString());
        if ("true".equals(memberEntity.getPASSWORD())){

        }

        return modelAndView;
    }

    /**
     * @param param
     * @param modelAndView
     * @return ModelAndView
     * @throws Exception
     * @throws NullPointerException
     * @desc 계정 생성시 사용하는 메소드
     */
    @PostMapping(VALUE + "Sign_Up.do")
    public ModelAndView signUpAction(@RequestParam Map<String, Object> param, ModelAndView modelAndView) throws Exception, NullPointerException {
        log.debug("Sign_Up.do 시작");
        MemberEntity memberEntity;

        try {
            if (param.isEmpty()) {
                log.debug("param 없음 계정 생성 실패");
                throw new NullPointerException("계정 생성용 값 내용 없음");
            } else {
                memberEntity = signService.createMember(param);
            }

            log.debug("memberEntity 출력 : " + memberEntity.toString());

            modelAndView.setViewName("redirect:Sign_In");
        } catch (Exception e){
            log.error("계정생성 오류");
            throw new RuntimeException(e);
        }


        return modelAndView;
    }
}