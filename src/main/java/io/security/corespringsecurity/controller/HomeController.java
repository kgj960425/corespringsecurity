package io.security.corespringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

//    @GetMapping(value = "/")
//    public String home() throws Exception {
//        return "admin/config";
//    }
      @RequestMapping(value="/", method = RequestMethod.GET)
      public ModelAndView create() {
          return new ModelAndView("home");
      }
}
