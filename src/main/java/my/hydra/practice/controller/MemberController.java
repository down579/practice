package my.hydra.practice.controller;

import lombok.RequiredArgsConstructor;
import my.hydra.practice.models.member.MemberRegist;
import my.hydra.practice.service.UserServiceDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final UserServiceDetailsImpl userServiceDetails;
    /**
     * 로그인 뷰 페이지 이동
     * @return 로그인 페이지 뷰 주소
     */
    @GetMapping(value = "/login")
    public String showLoginPage() {
        return "/login";
    }

    /**
     * 로그아웃
     * @param request
     * @param response
     * @return 로그인 페이지로 리다이렉트
     */
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    /**
     * 회원 가입 페이지 뷰 컨트롤러
     * @return
     */
    @GetMapping(value = "/member/new")
    public String showMemberRegistPage() {
        return "/memberRegist";
    }

    @PostMapping(value = "/member/signup")
    public String memberRegist(@RequestParam String username, @RequestParam String password, @RequestParam String membername) {
        MemberRegist regist = new MemberRegist();
        regist.setMembername(membername);
        regist.setUsername(username);
        regist.setPassword(password);
        userServiceDetails.registMember(regist);
        return "redirect:/login";
    }
}
