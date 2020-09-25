package config;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import spring.RegisterRequest;



public class RegisterRequestValidator implements Validator {
    private static final String emailRegExp =
            "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
    private Pattern pattern;

    public RegisterRequestValidator() {
        pattern = Pattern.compile(emailRegExp);
    }

    // 받은 객체가 RegisterRequest 클래스로 타입변환 가능한지 확인
    // 글로벌 범위의 Validator
    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterRequest.class.isAssignableFrom(aClass);
    }

    // Object o 는 검사 대상 객체, errors는 검사 결과 밑 에러 코드를 위한 객체
    @Override
    public void validate(Object o, Errors errors) {
        RegisterRequest regReq = (RegisterRequest) o;
        if(regReq.getEmail() == null || regReq.getEmail().trim().isEmpty())
            // arg0의 값이 존재하지 않으면, arg0의 프로퍼티의 에러값으로 arg1을 추가한다.
            errors.rejectValue("email", "required");
        else {
            Matcher matcher = pattern.matcher(regReq.getEmail());
            if(!matcher.matches())
                errors.rejectValue("email", "bad");
        }


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        // 위의 코드는
        // if(regReq.getName() == null || regReq.getName().trim().isEmpty())
        //    errors.rejectValue("name", "required");
        // 의 경량화 코드이다.
        // Object o를 지정하지 않아도 되는 이유는
        // error의 getFieldIdValue("name") 메서드를 이용해
        // 커맨드 객체의 name 프로퍼티 값을 구함
        // 따라서 커맨드 객체를 직접 전달하지 않아도 값 검증을 할 수 있음
        // 쉽게 생각하면 컨트롤러단에서 커맨드 객체를 전달 받을 때 어차피 알 수 있다는 의미.

        ValidationUtils.rejectIfEmpty(errors, "password", "required");
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");

        if(!regReq.isPasswordEqualToConfirmPassword())
            errors.rejectValue("confirmPassword", "nomatch");

    }
}
