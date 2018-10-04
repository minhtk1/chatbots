package com.robot.controller;

import com.robot.common.interface_common.ICMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WelcomeController {

    @Autowired
    @Qualifier("methodHandling")
    private ICMethod method;

    @RequestMapping(value = "/hello")
    public ResponseEntity welcome(){
        try {
            System.out.println(method.removePointCodeUtf8("Trần Khánh Minh Trong bảng chữ cái tiếng việt có tất cả 29 chữ cái, mới đây có 1 số đề xuất thêm 4 chữ cái"));
            return new ResponseEntity(null,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
