package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spring.member.Exceptions.DuplicationException;
import spring.member.Exceptions.NotMatchingPWException;
import spring.member.MemberRegisterService;
import spring.member.RegisterRequest;
import spring.member.RegisterRequestValidator;

import javax.validation.Valid;

@Controller
public class MemberController {

    @Autowired
    private MemberRegisterService memberRegisterService;

    @GetMapping("register/regist")
    public String registHandler( RegisterRequest request, Errors errors) {
        // model.addAttribute("registerRequest", new RegisterRequest());
        return "register/regist";
    }

    @PostMapping("register/regist")
    public String done(@Valid RegisterRequest request, Errors errors) {
        // @Valid 사용으로 Validator 코드는 주석
        //new RegisterRequestValidator().validate(request, errors);

        if(errors.hasErrors()) {
            System.out.println("hasErrors");
            return "register/regist";
        }

        try {
            if(request != null) {
                System.out.println("id: " + request.getId() + ", pw: " + request.getPassword() + ", email: " + request.getEmail());
            }
            memberRegisterService.regist(request);
            return "register/done";
        }
        catch (DuplicationException e) {
            System.out.println("============ dup");
            errors.rejectValue("id", "dupId");
            return "register/regist";
        }
        catch (NullPointerException e) {
            System.out.println("============ null");
            return "register/regist";
        }
        catch (NotMatchingPWException e) {

            errors.reject("notMatchingPassword");
            return "register/regist";
        }
    }
}
