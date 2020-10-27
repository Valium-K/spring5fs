package controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.AuthInfo;
import spring.AuthService;
import spring.WrongIdPasswordException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 로그인 요청 처리 컨트롤러
@Controller
@RequestMapping("/login")
public class LoginController {
    private AuthService authService;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String form(LoginCommand loginCommand,
                       // 이름이 REMEMBER인 쿠키를 cOOKIE 타입으로 전달받는다.
                       // 이 때 지정한 이름의 쿠키가 존재하지 않을 수 있다면 required = false이다. (기본값은 true)
                       @CookieValue(value = "REMEMBER", required = false) Cookie rCookie) {

        // REMEBER쿠키가 존재하면
        if(rCookie != null) {
            loginCommand.setEmail(rCookie.getValue());
            loginCommand.setRememberEmail(true);
        }
        return "login/loginForm";
    }

    @PostMapping
    public String submit(LoginCommand loginCommand, Errors errors, HttpSession session,
                         // 쿠키를 생성하기 위한 response
                         HttpServletResponse response) {
        new LoginCommandValidator().validate(loginCommand, errors);
        if(errors.hasErrors())
            return "login/loginForm";

        try {
            AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());

            // 로그인 성공 시 HttpSession의 "authInfo"속성에 authInfo객체를 저장.
            session.setAttribute("authInfo", authInfo);


            Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());
            rememberCookie.setPath("/");

            if(loginCommand.isRememberEmail()) {
                // 1분 유지
                rememberCookie.setMaxAge(60);
            }
            else {
                // 파기
                rememberCookie.setMaxAge(0);
            }
            response.addCookie(rememberCookie);

            return "login/loginSuccess";
        } catch (WrongIdPasswordException e) {
            errors.reject("idPasswordNotMatching");
            return "login/loginForm";
        }
    }
}
