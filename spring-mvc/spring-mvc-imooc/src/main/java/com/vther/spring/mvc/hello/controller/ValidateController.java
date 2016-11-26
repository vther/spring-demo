package com.vther.spring.mvc.hello.controller;

import com.vther.spring.mvc.hello.model.valid.ValidateModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

/** Created by Wither on 2016/11/26. */
@Controller
@RequestMapping(value = "/validate")
public class ValidateController {

  private static Logger LOG = LoggerFactory.getLogger(CourseController.class);

  @RequestMapping(
    value = "/register",
    method = {RequestMethod.GET}
  )
  public String test(Model model) {

    if (!model.containsAttribute("contentModel")) {
      model.addAttribute("contentModel", new ValidateModel());
    }
    return "validate/register";
  }

  @RequestMapping(
    value = "/register",
    method = {RequestMethod.POST}
  )
  public String test(
      Model model,
      @Valid @ModelAttribute("contentModel") ValidateModel validateModel,
      BindingResult result)
      throws NoSuchAlgorithmException {
    //如果有验证错误 返回到form页面
    if (result.hasErrors()) return test(model);
    return "validate/success";
  }
}
