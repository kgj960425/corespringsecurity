package io.security.corespringsecurity.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class sessionController {

    @GetMapping("/errorSession")
    public String sessionError(HttpServletRequest request, HttpServletResponse response){
        return "/security/error-session";
    }

    @GetMapping("/errorPermission")
    public String sessionPermission(HttpServletRequest request, HttpServletResponse response){
        return "/security/error-permission";
    }
}
