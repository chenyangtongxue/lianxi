package com.springmvc.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.springmvc.utils.FormatUtils;
import com.springmvc.utils.annontation.AnnotateUtils;
import com.springmvc.utils.annontation.CsvColumn;

@Component
public class CsvComponent {

  /**
   * 以List形式输出要生成的文件数据
   */
  public List<List<String>> createCsv(List<Object> inputDtoList, Class<?> clazz) throws Exception {
    System.out.println("createCsv start...");
    List<List<String>> resultList = new ArrayList<>();
    // header
    Map<Integer, Field> map = AnnotateUtils.getFieldsWithAnnotationOneToOne(clazz);
    List<String> line = new ArrayList<>();
    map.forEach((k, v) -> {
      line.add("\"" + v.getName().replaceAll("[A-Z]", "_$0").toUpperCase() + "\"");
    });
    resultList.add(line);
    // body
    map.forEach((k, v) -> {
      v.setAccessible(true);
    });
    for (Object dto : inputDtoList) {
      List<String> newline = new ArrayList<>();
      for (Field v : map.values()) {
        String targetFormat = v.getAnnotation(CsvColumn.class).targetFormat();
        if (targetFormat.equals("null")) {
          newline.add(v.get(dto).toString());
        } else {
          newline.add(FormatUtils.format(v.get(dto), v.getType().toString(), targetFormat));
        }
      }
      resultList.add(newline);
    }
    return resultList;
  }

  /**
   * 生成csv文件并返回文件名
   */
  public String createCsvOlder(List<Object> inputDtoList, Class<?> clazz, String outPutPath, String fileName) throws Exception {
    System.out.println("createCsv start...");
    File file = new File(outPutPath);
    if (!file.exists()) {
      file.mkdirs();
    }
    if (inputDtoList.isEmpty()) {
      throw new Exception("please transfer dtoList!");
    }
    if (fileName == null || fileName == "") {
      throw new Exception("please transfer fileName!");
    }
    File csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
    try (BufferedWriter csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8"), 1024)) {
      // header
      Map<Integer, Field> map = AnnotateUtils.getFieldsWithAnnotationOneToOne(clazz);
      List<String> line = new ArrayList<>();
      map.forEach((k, v) -> {
        line.add("\"" + v.getName().replaceAll("[A-Z]", "_$0").toUpperCase() + "\"");
      });
      csvFileOutputStream.write(String.join(",", line));
      csvFileOutputStream.newLine();

      // body
      map.forEach((k, v) -> {
        v.setAccessible(true);
      });
      for (Object dto : inputDtoList) {
        line.clear();
        for (Field v : map.values()) {
          String targetFormat = v.getAnnotation(CsvColumn.class).targetFormat();
          if (targetFormat.equals("null")) {
            line.add(v.get(dto).toString());
          } else {
            line.add(FormatUtils.format(v.get(dto), v.getType().toString(), targetFormat));
          }
        }
        csvFileOutputStream.write(String.join(",", line));
        csvFileOutputStream.newLine();
      }
      csvFileOutputStream.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return csvFile.getName();
  }

  /**
   * 解析csv文件
   */
  public List<Object> readCsv(String fileFullPath, Class<?> clazz) throws Exception {
    System.out.println("readCsv start...");
    List<Object> csvDatalist = new ArrayList<>();
    // 检查文件是否存在
    File file = new File(fileFullPath);
    if (file.isFile() && !file.exists()) {
      throw new Exception("Can't find this file.File path:" + fileFullPath);
    }
    // 检查是否传入类对象
    if (clazz == null) {
      throw new Exception("Please transfer dto-class!");
    }
    // 读文件
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath), "UTF-8"))) {
      boolean isbody = false;
      Map<Integer, List<Field>> map = AnnotateUtils.getFieldsWithAnnotationOneToMore(clazz); // 获取csv对应关系
      Integer maxKey = 0;
      for (Integer key : map.keySet()) {
        maxKey = key;
        map.get(key).forEach(f -> {
          f.setAccessible(true); // 开启访问权限
        });
      }
      while (reader.ready()) {
        String line = reader.readLine();
        String[] datas = line.split(","); // 通过","获取数据数组
        // 判断是数据还是header
        if (isbody) {
          Object newDto = clazz.newInstance();
          for (Integer key : map.keySet()) {
            List<Field> fields = map.get(key);
            for (Field field : fields) {
              field.set(newDto, datas[key - 1].replace("\"", "")); // 去掉String两边的引号并放入object
            }
          }
          csvDatalist.add(newDto);
        } else {
          // 注解的值超过csv的位置数量
          if (maxKey > datas.length) {
            // annotation'index > Fields in file
            throw new Exception("Annotation overflow!");
          }
          isbody = true;
        }
      }
      file.delete();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    return csvDatalist;
  }

  /**
   * 解析csv文件(Java8)
   */
  public List<Object> readCsvByJava8(String fileFullPath, Class<?> clazz) throws Exception {
    System.out.println("readCsv start...");
    List<Object> csvDatalist = new ArrayList<>();
    // 文件存在check
    File file = new File(fileFullPath);
    if (file.isFile() && !file.exists()) {
      throw new Exception("Can't find this file.File path:" + fileFullPath);
    }
    // DTO必须check
    if (clazz == null) {
      throw new Exception("Please transfer dto-class!");
    }
    // 文件读取
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileFullPath), "UTF-8"))) {
      Map<Integer, List<Field>> map = AnnotateUtils.getFieldsWithAnnotationByJava8(clazz);
      map.forEach((k, v) -> {
        v.forEach(f -> {
          f.setAccessible(true);
        });
      });
      String header = reader.readLine();
      if (map.keySet().stream().anyMatch(key -> key > header.split(",").length)) {
        // annotation'index > Fields in file
        throw new Exception(clazz.getName() + ": Annotation overflow!");
      }
      reader.lines().forEach(line -> {
        String[] datas = line.split(",");
        try {
          Object newDto = clazz.newInstance();
          map.forEach((k, v) -> {
            v.forEach(f -> {
              try {
                f.set(newDto, datas[k - 1]);
              } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException();
              }
            });
          });
          csvDatalist.add(newDto);
        } catch (Exception e1) {
          e1.printStackTrace();
          throw new RuntimeException();
        }
      });
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    return csvDatalist;
  }
}
