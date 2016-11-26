package com.vther.spring.mvc.hello.model.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Chapter {

  private Integer id;
  private Integer courseId;
  private Integer order;
  private String title;
  private String descr;

  //    private List<Section> sectionList;

}
