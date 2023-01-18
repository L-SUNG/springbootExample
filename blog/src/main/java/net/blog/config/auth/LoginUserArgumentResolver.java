package net.blog.config.auth;

import lombok.RequiredArgsConstructor;
import net.blog.config.auth.dto.SessionUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 파라미터에 LoginUser 어노테이션이 붙어있는것을 판정
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        // 파라미터의 글래스타입이 SessionUser 인것을 판정
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 세션에서 user를 취득
        return httpSession.getAttribute("user");
    }
}
