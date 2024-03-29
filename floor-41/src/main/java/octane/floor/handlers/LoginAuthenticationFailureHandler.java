package octane.floor.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import octane.floor.filters.FlashMap;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler{

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		FlashMap.getCurrent(request).put("signinErrorMessage", exception.getMessage());
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/login"));
	}

}
