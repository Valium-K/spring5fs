package interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// AuthCheck 인터셉터
public class AuthCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // (var1 == (true||공백)) ? "세션이 없을시 새로운 세션생성 후 반환" : "세션이 없을시 null 반환"
        HttpSession session = request.getSession(false);

        if(session != null) {
            Object authInfo = session.getAttribute("authInfo");
            if(authInfo != null) {
                return true;
            }
        }

        // 경로가 http://localhost:8080/sp5-chap13일 경우 getCOntextPaht()는 /sp5-chap13를 리턴.
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
