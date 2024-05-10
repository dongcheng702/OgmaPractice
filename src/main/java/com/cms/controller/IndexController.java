package com.cms.controller;
 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
@RestController
public class IndexController {
  @RequestMapping(value = "/", method = RequestMethod.GET)
  String index() {
    return "index";
  }
  @RequestMapping(value = "/company/", method = RequestMethod.GET)
  String comapny() {
    return "contents/company";
  }
  @RequestMapping(value = "/service/", method = RequestMethod.GET)
  String service() {
    return "contents/service";
  }
  @RequestMapping(value = "/recruit/", method = RequestMethod.GET)
  String recruit() {
    return "contents/recruit";
  }
  @RequestMapping(value = "/contact/", method = RequestMethod.GET)
  String contact() {
    return "contents/contact";
  }
}