package com.springmvc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.entity.CsvConditionEntity;
import com.springmvc.service.FileService;

@Controller
public class FileController {

  @Autowired
  FileService fileService;

  /**
   * 上传文件用Controller
   */
  @ResponseBody
  @RequestMapping(value = "/upload")
  public Map<String, Object> upload(MultipartFile file, HttpServletRequest request) throws Exception {
    return fileService.upload(file, request);
  }

  /**
   * 读取文件用Controller
   */
  @ResponseBody
  @RequestMapping(value = "/readCsv", method = RequestMethod.POST)
  public Map<String, Object> readCsv(@RequestBody CsvConditionEntity csvCondition, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return fileService.readCsv(csvCondition, request, response);
  }

  /**
   * 显示table用Controller
   */
  @RequestMapping(value = "/readTable")
  public ModelAndView readTable(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("basicFunction/" + request.getParameter("path"));
    return mav;
  }

  /**
   * 生成csv用Controller
   */
  @RequestMapping(value = "/writeCsv")
  public void writeCsv(HttpServletRequest request, HttpServletResponse response) throws Exception {
    fileService.writeCsv(request, response);
  }
}
