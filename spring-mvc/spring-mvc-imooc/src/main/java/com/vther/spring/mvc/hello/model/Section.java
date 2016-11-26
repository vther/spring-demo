package com.vther.spring.mvc.hello.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Section {

  private Integer id;
  private Integer courseId;
  private Integer chapterId;
  private Integer order;
  private String title;
  private Integer type;
  private String duration;
  private String filePath;
}
