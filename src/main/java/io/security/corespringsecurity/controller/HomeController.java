package io.security.corespringsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = {"","/","home"} , method = {RequestMethod.GET,RequestMethod.POST} , produces = "application/json; charset=UTF-8")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "/home";
    }
}
