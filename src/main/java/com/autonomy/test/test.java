package com.autonomy.test;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class test {

	//private Logger log = LoggerFactory.getLogger(test.class);

	@RequestMapping(value={"/hello"})
	public String hello() {
		log.info("test logger!");
		System.out.println("hello word!");
		return "index";
	}
}
