package com.springmvc.utils.annontation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.springmvc.utils.FormatUtils;

public class AnnotateUtils {

  /**
   * 通过反射注解获取每个Field对应的位置
   */
  public static Map<Integer, Field> getFieldsWithAnnotationOneToOne(Class<?> clazz) throws Exception {
    Map<Integer, Field> map = new TreeMap<>();
    for (Field f : clazz.getDeclaredFields()) {
      if (f.isAnnotationPresent(CsvColumn.class)) {
        Integer index = f.getAnnotation(CsvColumn.class).index();
        String targetFormat = f.getAnnotation(CsvColumn.class).targetFormat();
        if (index < 1) {
          throw new Exception(clazz.getName() + ": Error annotation! [" + f.getName() + " -index: " + index + "]");
        }
        if (map.containsKey(index)) {
          throw new Exception(
              clazz.getName() + ": Repeat annotation! [" + f.getName() + "and" + map.get(index).getName() + " -index: " + index + "]");
        } else if (!targetFormat.equals("null") && !FormatUtils.checkformat(f.getType().toString(), targetFormat)) {
          throw new Exception(
              clazz.getName() + ": Error format! [" + f.getName() + "and" + map.get(index).getName() + " -targetFormat: " + targetFormat + "]");
        } else {
          map.put(index, f);
        }
      }
    }
    if (map.isEmpty()) {
      throw new Exception(clazz.getName() + ": There is no annotation in all fields!");
    }
    return map;
  }

  /**
   * 通过反射注解获取每个位置对应的Field（一个位置可对应多个Field）
   */
  public static Map<Integer, List<Field>> getFieldsWithAnnotationOneToMore(Class<?> clazz) throws Exception {
    Map<Integer, List<Field>> map = new TreeMap<>();
    // 获取类对象中所有成员变量
    for (Field f : clazz.getDeclaredFields()) {
      // 获得有注解的成员变量
      if (f.isAnnotationPresent(CsvColumn.class)) {
        Integer index = f.getAnnotation(CsvColumn.class).index(); // 获取注解的值
        // 注解的值异常
        if (index < 1) {
          throw new Exception(clazz.getName() + ": Error annotation! [" + f.getName() + " -index: " + index + "]");
        }
        // 位置已有对应的成员变量
        if (map.containsKey(index)) {
          map.get(index).add(f);
        } else {
          List<Field> fields = new ArrayList<>();
          fields.add(f);
          map.put(index, fields);
        }
      }
    }
    // 没有得到有注解的成员变量
    if (map.isEmpty()) {
      throw new Exception(clazz.getName() + ": There is no annotation in all fields!");
    }
    return map;
  }

  /**
   * 通过反射注解获取每个位置对应的Field(java8)
   */
  public static Map<Integer, List<Field>> getFieldsWithAnnotationByJava8(Class<?> clazz) throws Exception {
    Map<Integer, List<Field>> map = new TreeMap<>();
    List<String> errorList = new ArrayList<>();
    Arrays.stream(clazz.getDeclaredFields()).filter(f -> f.isAnnotationPresent(CsvColumn.class)).forEach(f -> {
      Integer index = f.getAnnotation(CsvColumn.class).index();
      if (index < 1) {
        errorList.add(clazz.getName() + ": Error annotation! [" + f.getName() + " (index: " + index + ")]");
      } else if (!map.containsKey(index)) {
        List<Field> fields = new ArrayList<>();
        fields.add(f);
        map.put(index, fields);
      } else {
        map.get(index).add(f);
      }
    });
    if (errorList.size() > 0) {
      throw new Exception(errorList.toString());
    } else if (map.isEmpty()) {
      throw new Exception(clazz.getName() + ": There is no annotation in any fields!");
    }
    return map;
  }
}
