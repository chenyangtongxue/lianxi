package com.springmvc.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtils {

  /** yyyy/MM/dd */
  public static final String FORMAT_DATE_1 = "yyyy/MM/dd";

  /** yyyyMMdd */
  public static final String FORMAT_DATE_2 = "yyyyMMdd";

  /** yyyy-MM-dd */
  public static final String FORMAT_DATE_3 = "yyyy-MM-dd";

  /** yyyy-MM-dd,HH:mm:ss.000 */
  public static final String FORMAT_DATE_4 = "yyyy-MM-dd HH:mm:ss.SSSSSS";

  /** 00Z */
  public static final String FORMAT_INT_1 = "00Z";

  /** 0000Z */
  public static final String FORMAT_INT_2 = "0000Z";

  /** 00000000Z */
  public static final String FORMAT_INT_3 = "00000000Z";

  /** ZZ-XXXXX */
  public static final String FORMAT_STRINGT_1 = "ZZ-XXXXX";

  /** XXXXXXX-ZZ */
  public static final String FORMAT_STRINGT_2 = "XXXXXXX-ZZ";

  /**
   * 根据数据类型进行format
   */
  public static String format(Object value, String type, String targetFormat) throws Exception {
    if (type.equals("class java.util.Date")) {
      return dateFormat((Date) value, targetFormat);
    } else if (type.equals("int")) {
      return intFormat((Integer) value, targetFormat);
    } else if (type.equals("class java.lang.String")) {
      return stringFormat((String) value, targetFormat);
    }
    return "";
  }

  /**
   * 日期format
   */
  public static String dateFormat(Date value, String targetFormat) throws Exception {
    SimpleDateFormat formatter = new SimpleDateFormat(targetFormat);
    String dateString = formatter.format(value);
    return dateString;
  }

  /**
   * int型format
   */
  public static String intFormat(Integer value, String targetFormat) throws Exception {
    String formatValue = value.toString();
    if (formatValue.length() > targetFormat.length()) {
      throw new Exception("value:" + value + "is illegal!");
    }
    while (formatValue.length() < targetFormat.length()) {
      formatValue = "0" + formatValue;
    }
    return formatValue;
  }

  /**
   * String型format
   */
  public static String stringFormat(String value, String targetFormat) throws Exception {
    String[] members = targetFormat.split("-");
    if (members[0].contains("Z")) {
      value = value.substring(0, members[0].length()) + "-" + value.substring(members[0].length(), value.length() - 1);
    } else if (members[1].contains("Z")) {
      value = value.substring(0, value.length() - members[1].length()) + "-" + value.substring(value.length() - members[1].length(), value.length());
    } else {
      throw new Exception("targetFormat:" + targetFormat + "is illegal!");
    }
    return value;
  }

  /**
   * 判断要format的数据类型
   */
  public static Boolean checkformat(String type, String targetFormat) throws Exception {
    if (type.equals("class java.util.Date")) {
      return isDateFormat(targetFormat);
    } else if (type.equals("int")) {
      return isIntFormat(targetFormat);
    } else if (type.equals("class java.lang.String")) {
      return isStringFormat(targetFormat);
    }
    return false;
  }

  /**
   * 是否符合data转换格式要求
   */
  public static Boolean isDateFormat(String targetFormat) {
    return targetFormat.equals(FORMAT_DATE_1) || targetFormat.equals(FORMAT_DATE_2) || targetFormat.equals(FORMAT_DATE_3)
        || targetFormat.equals(FORMAT_DATE_4);
  }

  /**
   * 是否符合int转换格式要求
   */
  public static Boolean isIntFormat(String targetFormat) {
    return targetFormat.equals(FORMAT_INT_1) || targetFormat.equals(FORMAT_INT_2) || targetFormat.equals(FORMAT_INT_3);
  }

  /**
   * 是否符合String转换格式要求
   */
  public static Boolean isStringFormat(String targetFormat) {
    return targetFormat.equals(FORMAT_STRINGT_1) || targetFormat.equals(FORMAT_STRINGT_2);
  }
}
