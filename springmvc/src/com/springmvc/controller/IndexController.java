package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

  @RequestMapping("/index")
  public String IndexView() {
    return "index";
    }

  @RequestMapping("/main")
  public ModelAndView view(HttpServletRequest request) {
    String path = "main/" + request.getParameter("path");
    ModelAndView mav = new ModelAndView();
    mav.setViewName(path);
    return mav;
  }
}