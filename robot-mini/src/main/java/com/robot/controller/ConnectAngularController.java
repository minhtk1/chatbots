package com.robot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ConnectAngularController{

	@RequestMapping("/")
	public ResponseEntity index() {
		return new ResponseEntity(null,HttpStatus.OK);
	}

}