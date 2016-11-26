package com.vther.spring.mvc.hello.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course2 {

  // 课程Id
  private Integer courseId;
  // 课程名称
  private String title;
  // 图片路径
  private String imgPath;
  // 学习人数
  private Integer learningNum;
  // 课程时长
  private Long duration;
  // 课程难度
  private Integer level;
  // 课程难度描述
  private String levelDesc;
  // 课程介绍
  private String descr;
  // 课程提纲
  //	private List<Chapter> chapterList;

}
