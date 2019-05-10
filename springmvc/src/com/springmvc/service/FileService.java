package com.springmvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springmvc.entity.CsvConditionEntity;
import com.springmvc.logic.file.FileAnalysis;
import com.springmvc.logic.file.FileDownload;
import com.springmvc.logic.file.FileDownloadOlder;
import com.springmvc.logic.file.FileUpload;

@Service
public class FileService {

  @Autowired
  FileUpload fileUpload;

  @Autowired
  FileAnalysis fileAnalysis;

  @Autowired
  FileDownload fileDownload;

  @Autowired
  FileDownloadOlder fileDownloadOlder;

  /**
   * 上传文件用Service
   */
  public Map<String, Object> upload(MultipartFile file, HttpServletRequest request) throws Exception {
    HttpSession session = request.getSession();
    // 清空session
    session.setAttribute("_LISTBHEADER_", null);
    session.setAttribute("_LISTBODYFORSESSION_", null);
    session.setAttribute("_LISTBODY_", null);
    session.setAttribute("_TYPE_", null);
    return fileUpload.upload(file);
  }

  /**
   * 读取文件用Service
   */
  public Map<String, Object> readCsv(CsvConditionEntity csvCondition, HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, Object> map = new HashMap<>();
    HttpSession session = request.getSession();
    List<Object> dataList = fileAnalysis.readCsv(csvCondition); // 获取dataList
    if (dataList != null && !dataList.isEmpty()) {
      map.put("result", "success");
      session.setAttribute("_LISTBHEADER_", fileAnalysis.readCsvHeader(csvCondition)); // 设置table的header
      session.setAttribute("_LISTBODYFORSESSION_", dataList); // session保存ObjectDataList
      session.setAttribute("_LISTBODY_", fileAnalysis.readCsvToScreen(csvCondition, dataList)); // 设置table的body,把对象转化为list
      session.setAttribute("_TYPE_", csvCondition.getType()); // session保存数据对象的类型
    } else {
      map.put("result", "failure");
    }
    return map;
  }

  /**
   * 生成csv用Service
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void writeCsv(HttpServletRequest request, HttpServletResponse response) throws Exception {
    HttpSession session = request.getSession(); // 创建session
    List<Object> dataList = (List) session.getAttribute("_LISTBODYFORSESSION_");// session获取ObjectDataList
    String type = (String) session.getAttribute("_TYPE_");
    String dataIdList = request.getParameter("dataIdList");
    if (dataIdList == null || dataIdList == "") {
      return;
    }
    String[] selectedDataId = dataIdList.split(",");
    fileDownload.writeCsv(type, selectedDataId, dataList, request, response);
    // fileDownloadOlder.writeCsv(type, selectedDataId, dataList, request, response);
  }
}
