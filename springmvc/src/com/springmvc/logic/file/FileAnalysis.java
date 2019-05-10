package com.springmvc.logic.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springmvc.dto.T010Dto;
import com.springmvc.dto.T020Dto;
import com.springmvc.entity.CsvConditionEntity;
import com.springmvc.logic.CsvComponent;

@Component
public class FileAnalysis {

  @Autowired
  CsvComponent csvComponent;

  /**
   * 判断文件对应的解析类型
   */
  public List<Object> readCsv(CsvConditionEntity csvCondition) throws Exception {
    String type = csvCondition.getType();
    List<Object> result = new ArrayList<>();
    if (type.equals("1")) {
      result = readCsv(csvCondition.getPath(), T010Dto.class);
    } else if (type.equals("2")) {
      result = readCsv(csvCondition.getPath(), T020Dto.class);
    }
    return result;
  }

  /**
   * 解析文件
   */
  public List<Object> readCsv(String path, Class<?> clazz) throws Exception {
    return csvComponent.readCsv(path, clazz);
  }

  /**
   * 对象转换为list
   */
  public List<List<String>> readCsvToScreen(CsvConditionEntity csvCondition, List<Object> dataList) throws Exception {
    List<List<String>> resultList = new ArrayList<>();
    Map<String, String> map = readCsvMap(csvCondition);// 获取tableHeader的中英文对应关系
    for (Object o : dataList) {
      Map<String, String> keyAvauMap = new LinkedHashMap<>();
      // 通过数组把对象转换为键值对
      for (String m : Arrays.asList(o.toString().split(","))) {
        String[] values = m.split("=");
        keyAvauMap.put(values[0].trim(), values[1].trim());
      }
      // 按照tableHeader的顺序存放list值
      List<String> data = new ArrayList<>();
      for (String k : map.keySet()) {
        data.add(keyAvauMap.get(k));
      }
      resultList.add(data);
    }
    return resultList;
  }

  /**
   * tableHeader的顺序及中英文对应关系
   */
  public Map<String, String> readCsvMap(CsvConditionEntity csvCondition) throws Exception {
    String type = csvCondition.getType();
    Map<String, String> map = new LinkedHashMap<>();
    if (type.equals("1")) {
      map.put("name", "姓名");
      map.put("sex", "性别");
      map.put("age", "年龄");
    } else if (type.equals("2")) {
      map.put("userId", "用户ID");
      map.put("realName", "用户姓名");
      map.put("vName", "用户昵称");
      map.put("time", "登录日期");
      map.put("place", "登录地点");
    }
    return map;
  }

  /**
   * 获取tableHeader
   */
  public List<String> readCsvHeader(CsvConditionEntity csvCondition) throws Exception {
    Map<String, String> map = readCsvMap(csvCondition);
    List<String> headerList = new ArrayList<>();
    map.forEach((k, v) -> {
      headerList.add(v);
    });
    return headerList;
  }
}
