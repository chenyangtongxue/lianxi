package com.springmvc.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.springmvc.dto.T010Dto;
import com.springmvc.dto.T011Dto;
import com.springmvc.dto.T020Dto;
import com.springmvc.dto.T021Dto;

public class test {

  public static List<T011Dto> listToList(List<T010Dto> list) throws Exception {
    List<T011Dto> newList = new ArrayList<>();
    list.forEach(l -> {
      T011Dto dto = new T011Dto();
      dto.setName(l.getName());
      dto.setSex(l.getSex());
      dto.setAge(Integer.parseInt(l.getAge()));
      newList.add(dto);
    });
    return newList;
  }

  public static List<T021Dto> listToList2(List<T020Dto> list) throws Exception {
    List<T021Dto> newList = new ArrayList<>();
    list.forEach(l -> {
      T021Dto dto = new T021Dto();
      dto.setUserId(l.getUserId());
      dto.setRealName(l.getLastName()+l.getFirstName());
      dto.setvName(l.getName());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
      try {
        dto.setTime(sdf.parse(l.getTime()));
      } catch (ParseException e) {
        e.printStackTrace();
      }
      newList.add(dto);
    });
    return newList;
  }
}
