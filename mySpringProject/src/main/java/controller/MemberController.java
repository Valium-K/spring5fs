package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.member.Exceptions.DuplicationException;
import spring.member.MemberRegisterService;
import spring.member.RegisterRequest;

@Controller
public class MemberController {

    @Autowired
    private MemberRegisterService memberRegisterService;

    @GetMapping("register/regist")
    public String registHandler(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register/regist";
    }

    @PostMapping("register/done")
    public String done(RegisterRequest request) {
        try {
            if(request != null) {
                System.out.println("id: " + request.getId() + ", pw: " + request.getPassword() + ", email: " + request.getEmail());
            }
            memberRegisterService.regist(request);
            return "register/done";
        }
        catch (DuplicationException e) {
            System.out.println("============ dup");
            return "register/regist";
        }
        catch (NullPointerException e) {
            System.out.println("============ null");
            return "register/regist";
        }
    }
}