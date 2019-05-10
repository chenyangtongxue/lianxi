package com.springmvc.logic.file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class FileUpload {

  /**
   * 文件上传功能
   */
  public Map<String, Object> upload(MultipartFile file) throws IllegalStateException, IOException {
    Map<String, Object> resultMap = new HashMap<>();
    // 未上传文件
    if (file == null) {
      resultMap.put("result", false);
      resultMap.put("tips", 2); // 1：上传失败 2：未上传 3：文件格式不符
    } else if (!file.isEmpty()) {
      // 文件不为空
      String myFileName = file.getOriginalFilename();// 文件原名称
      String suffix = myFileName.substring(myFileName.length() - 3, myFileName.length());
      if (!suffix.equals("csv")) {
        // 后缀不为csv
        resultMap.put("result", false);
        resultMap.put("tips", 3);
        return resultMap;
      }
      String fileName = myFileName.substring(0, myFileName.length() - 4) + "_" + Integer.toHexString(new Random().nextInt());
      String path = "D:\\download\\" + fileName + ".csv";
      File localFile = new File(path);
      file.transferTo(localFile);
      resultMap.put("result", true);
      resultMap.put("path", path);
    } else {
      resultMap.put("result", false);
      resultMap.put("tips", 1);
    }
    return resultMap;
  }
}
