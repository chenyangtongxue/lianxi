package com.springmvc.logic.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

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
public class FileDownloadOlder {

  @Autowired
  CsvComponent csvComponent;

  /**
   * 在服务器端生成文件，读取文件再生成文件输出流
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void writeCsvOlder(String type, String[] dataIdList, List<Object> dataList, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    String tempOutPutPath = "D:\\download";
    String fileName = "";
    if (type.equals("1")) {
      List<T010Dto> writeList = new ArrayList<>();
      for (String dataId : dataIdList) {
        writeList.add((T010Dto) dataList.get(Integer.valueOf(dataId)));
      }
      fileName = csvComponent.createCsvOlder((List) test.listToList(writeList), T011Dto.class, tempOutPutPath, "T010DtoCsv");
    } else if (type.equals("2")) {
      List<T020Dto> writeList = new ArrayList<>();
      for (String dataId : dataIdList) {
        writeList.add((T020Dto) dataList.get(Integer.valueOf(dataId)));
      }
      fileName = csvComponent.createCsvOlder((List) test.listToList2(writeList), T021Dto.class, tempOutPutPath, "T020DtoCsv");
    }

    String tempFile = tempOutPutPath + "\\" + fileName;
    File file = new File(tempFile);
    response.reset();
    String userAgent = request.getHeader("User-Agent");
    if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
      response.setHeader("Content-Disposition", "attachment;filename=\"" + URLEncoder.encode(fileName, "utf-8") + "\"");
    } else {
      fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1"); // 下载的文件名显示编码处理
      response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
    }
    response.setContentType("application/x-msdownload;charset=UTF-8");
    FileInputStream fis = new FileInputStream(file);
    BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
    byte[] buffer = new byte[2048];
    int readlength = 0;
    while ((readlength = fis.read(buffer)) != -1) {
      bos.write(buffer, 0, readlength);
    }
    try {
      fis.close();
      bos.flush();
      bos.close();
      file.delete();
    } catch (IOException e) {
    }

  }
}
