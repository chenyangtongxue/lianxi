package com.springmvc.logic.file;

import java.io.BufferedWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springmvc.dto.T010Dto;
import com.springmvc.dto.T011Dto;
import com.springmvc.dto.T020Dto;
import com.springmvc.dto.T021Dto;
import com.springmvc.logic.CsvComponent;
import com.springmvc.logic.test;

@Component
public class FileDownload {

  @Autowired
  CsvComponent csvComponent;

  /**
   * 生成文件输出流
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void writeCsv(String type, String[] dataIdList, List<Object> dataList, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    List<List<String>> contentList = new ArrayList<>();
    String fileName = "";
    if (type.equals("1")) {
      List<T010Dto> writeList = new ArrayList<>();
      // 获取选中的data
      for (String dataId : dataIdList) {
        writeList.add((T010Dto) dataList.get(Integer.valueOf(dataId)));
      }
      contentList = csvComponent.createCsv((List) test.listToList(writeList), T011Dto.class); // 获取文件输出dataList
      fileName = "T010_" + Integer.toHexString(new Random().nextInt());
    } else if (type.equals("2")) {
      List<T020Dto> writeList = new ArrayList<>();
      for (String dataId : dataIdList) {
        writeList.add((T020Dto) dataList.get(Integer.valueOf(dataId)));
      }
      contentList = csvComponent.createCsv((List) test.listToList2(writeList), T021Dto.class); // 获取文件输出dataList
      fileName = "T020_" + Integer.toHexString(new Random().nextInt());
    }

    response.reset(); // 清空输出流
    // 设置响应头和下载保存的文件名
    String userAgent = request.getHeader("User-Agent");
    if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
      response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "utf-8") + ".csv" + "\"");
    } else {
      fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1"); // 下载的文件名显示编码处理
      response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + ".csv" + "\"");
    }
    response.setContentType("application/x-msdownload;charset=UTF-8");// 定义输出类型
    try (BufferedWriter csvFileOutputStream = new BufferedWriter(response.getWriter(), 1024)) {
      // 生成流
      for (List<String> line : contentList) {
        csvFileOutputStream.write(String.join(",", line));
        csvFileOutputStream.newLine();
      }
      csvFileOutputStream.flush();
    }
  }
}
