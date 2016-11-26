package com.vther.spring.mvc.hello.controller;

import com.vther.spring.mvc.hello.model.course.Course;
import com.vther.spring.mvc.hello.service.CourseService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/courses")
// /courses/**
public class CourseController {

  private static Logger LOG = LoggerFactory.getLogger(CourseController.class);

  private CourseService courseService;

  @Autowired
  public void setCourseService(CourseService courseService) {
    this.courseService = courseService;
  }

  //本方法将处理 /courses/view?courseId=123 形式的URL
  // http://localhost:8080/courses/view?courseId=123
  @RequestMapping(value = "/view", method = RequestMethod.GET)
  public String viewCourse(@RequestParam("courseId") Integer courseId, Model model) {
    LOG.debug("In viewCourse, courseId = {}", courseId);
    Course course = courseService.getCourseById(courseId);
    model.addAttribute(course);
    return "course_overview";
  }

  //本方法将处理 /courses/view2/123 形式的URL
  @RequestMapping("/view2/{courseId}")
  public String viewCourse2(@PathVariable("courseId") Integer courseId, Map<String, Object> model) {

    LOG.debug("In viewCourse2, courseId = {}", courseId);
    Course course = courseService.getCourseById(courseId);
    model.put("course", course);
    return "course_overview";
  }

  //本方法将处理 /courses/view3?courseId=123 形式的URL
  @RequestMapping("/view3")
  public String viewCourse3(HttpServletRequest request) {
    Integer courseId = Integer.valueOf(request.getParameter("courseId"));
    Course course = courseService.getCourseById(courseId);
    request.setAttribute("course", course);
    return "course_overview";
  }

  @RequestMapping(value = "/admin", method = RequestMethod.GET, params = "add")
  public String createCourse() {
    return "course_admin/edit";
  }

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public String doSave(@ModelAttribute Course course) {

    LOG.debug("Info of Course:");
    LOG.debug(ReflectionToStringBuilder.toString(course));

    //在此进行业务操作，比如数据库持久化
    course.setCourseId(123);
    return "redirect:view2/" + course.getCourseId();
  }

  @RequestMapping(value = "/upload", method = RequestMethod.GET)
  public String showUploadPage(@RequestParam(value = "multi", required = false) Boolean multi) {
    if (multi != null && multi) {
      return "course_admin/multifile";
    }
    return "course_admin/file";
  }

  @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
  public String doUploadFile(@RequestParam("file") MultipartFile file) throws IOException {

    if (!file.isEmpty()) {
      LOG.debug("Process file: {}", file.getOriginalFilename());
      FileUtils.copyInputStreamToFile(
          file.getInputStream(),
          new File("c:\\temp\\imooc\\", System.currentTimeMillis() + file.getOriginalFilename()));
    }
    return "success";
  }

  @RequestMapping(value = "/doUpload2", method = RequestMethod.POST)
  public String doUploadFile2(MultipartHttpServletRequest multiRequest) throws IOException {

    Iterator<String> filesNames = multiRequest.getFileNames();
    while (filesNames.hasNext()) {
      String fileName = filesNames.next();
      MultipartFile file = multiRequest.getFile(fileName);
      if (!file.isEmpty()) {
        LOG.debug("Process file: {}", file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(
            file.getInputStream(),
            new File("c:\\temp\\imooc\\", System.currentTimeMillis() + file.getOriginalFilename()));
      }
    }
    return "success";
  }

  @RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
  public @ResponseBody Course getCourseInJson(@PathVariable Integer courseId) {
    return courseService.getCourseById(courseId);
  }

  @RequestMapping(value = "/jsontype/{courseId}", method = RequestMethod.GET)
  public ResponseEntity<Course> getCourseInJson2(@PathVariable Integer courseId) {
    Course course = courseService.getCourseById(courseId);
    return new ResponseEntity<Course>(course, HttpStatus.OK);
  }
}
