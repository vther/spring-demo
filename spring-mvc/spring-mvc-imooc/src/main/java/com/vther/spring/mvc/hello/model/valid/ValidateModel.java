package com.vther.spring.mvc.hello.model.valid;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/** Created by Wither on 2016/11/26. */
@Getter
@Setter
public class ValidateModel {
  @NotEmpty()
  private String name;

  @Range(min = 0, max = 150)
  private String age;

  @NotEmpty()
  @Email()
  private String email;
}
