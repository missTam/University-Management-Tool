package de.academy.config;

import de.academy.dto.ProfessorDTO;
import de.academy.dto.StudentDTO;
import de.academy.entities.User;
import de.academy.services.LoginService;
import de.academy.services.ProfessorService;
import de.academy.services.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class DashboardRedirectAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final LoginService loginService;
    private final StudentService studentService;
    private final ProfessorService professorService;

	public DashboardRedirectAuthenticationSuccessHandler(LoginService userService, StudentService studentService, ProfessorService professorService) {
		this.loginService = userService;
		this.studentService = studentService;
		this.professorService = professorService;
	}

	@Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication
	) throws IOException {

		HttpSession session = request.getSession();
        User authenticatedUser = loginService.findByUsername(authentication.getName());

        if(authenticatedUser.getRole().getName().equals("ROLE_STUDENT")) {
			StudentDTO studentDTO = studentService.getStudentUnderGivenUserAccount(authenticatedUser);
			session.setAttribute("student", studentDTO);
		} else {
			ProfessorDTO professorDTO = professorService.getProfessorUnderGivenUserAccount(authenticatedUser);
			session.setAttribute("professor", professorDTO);
		}

        // Forward user to the correct dashboard
		handleRedirect(request, response, authentication);
    }

    protected void handleRedirect(HttpServletRequest request,
                                  HttpServletResponse response,
                                  Authentication authentication
	) throws IOException {

    	// determine url based on user authority
		String targetUrl = determineTargetUrl(authentication);

		// engage different controller based on user authority
		redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    // Extract authority (role) information for the current user via Authentication object
	protected String determineTargetUrl(final Authentication authentication) {

		Map<String, String> roleTargetUrlMap = new HashMap<>();
		roleTargetUrlMap.put("ROLE_STUDENT", "/student-dashboard/");
		roleTargetUrlMap.put("ROLE_PROFESSOR", "/professor-dashboard/");

		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			String authorityName = grantedAuthority.getAuthority();
			if(roleTargetUrlMap.containsKey(authorityName)) {
				return roleTargetUrlMap.get(authorityName);
			}
		}

		throw new IllegalStateException();
	}

}
