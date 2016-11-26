package com.vther.spring.mvc.hello.controller;

import com.vther.spring.mvc.hello.model.i18n.I18nModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/** Created by Wither on 2016/11/26. */
@Controller
@RequestMapping("/i18n")
public class I18NController {

  private static Logger LOG = LoggerFactory.getLogger(CourseController.class);

  @RequestMapping(
    value = "/test",
    method = {RequestMethod.GET}
  )
  public String test(HttpServletRequest request, Model model) {
    if (!model.containsAttribute("i18nModel")) {

      //从后台代码获取国际化信息
      RequestContext requestContext = new RequestContext(request);

      LOG.debug(
          "requestContext.getLocale()={},requestContext.getTheme()={},requestContext.getTimeZone()={}",
          requestContext.getLocale(),
          requestContext.getTheme(),
          requestContext.getTimeZone());

      model.addAttribute("money", requestContext.getMessage("money"));
      model.addAttribute("time", requestContext.getMessage("time"));

      I18nModel formatModel = new I18nModel();
      formatModel.setMoney(12345.678);
      formatModel.setTime(new Date());
      model.addAttribute("i18nModel", formatModel);
    }
    return "internationalization/i18ntest";
  }
}
