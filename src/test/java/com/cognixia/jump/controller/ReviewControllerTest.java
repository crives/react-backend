package com.cognixia.jump.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import com.cognixia.jump.config.MongoConfig;

@ContextConfiguration(classes={MongoConfig.class/*, WebConfig.class*/})
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

}
