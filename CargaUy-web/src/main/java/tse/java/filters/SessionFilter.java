package tse.java.filters;

import tse.java.entity.Administrador;
import tse.java.entity.Autoridad;
import tse.java.entity.Usuario;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/autoridad/*", "/"})
public class SessionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        if (servletContext != null)
            System.out.println("Info: filtro de sesion inicializado");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        boolean success = false;

        HttpSession session = request.getSession();

        Usuario currentUser = (Usuario) session.getAttribute("currentUser");
        if (currentUser != null) {
            System.out.println("Existe un usuario loggeado");
            if(currentUser instanceof Administrador && (uri.contains("admin") || uri.endsWith("login.xhtml")  || !uri.endsWith("html"))) {
                success = true;
                filterChain.doFilter(request, response);
            } else {
                if(currentUser instanceof Autoridad && (uri.contains("autoridad") || uri.endsWith("login.xhtml")  || !uri.endsWith("html"))) {
                    success = true;
                    filterChain.doFilter(request, response);
                }
            }

            if (!success) {
                response.sendRedirect((uri.contains("admin") || uri.contains("autoridad")) ? "../noAutorizado.xhtml" : "noAutorizado.xhtml");
            }
        } else {
            System.out.println("No existe un usuario loggeado, te tiene que mandar a login");
            response.sendRedirect((uri.contains("admin") || uri.contains("autoridad")) ? "../login.xhtml" : "login.xhtml");
        }

    }
}
