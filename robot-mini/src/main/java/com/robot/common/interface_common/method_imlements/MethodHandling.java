package com.robot.common.interface_common.method_imlements;

import com.robot.common.interface_common.ICMethod;
import com.robot.common.veriables.Veriable;
import org.springframework.stereotype.Component;


@Component(value = "methodHandling")
public class MethodHandling implements ICMethod {

    @Override
    public int factorial(int n) {
        int factorial = 1;
        if (n == 0 || n == 1) {
            return factorial;
        } else {
            for (int i = 2; i <= n; i++) {
                factorial *= i;
            }
            return factorial;
        }
    }

    @Override
    public String removePointCodeUtf8(String value) {
        if(!value.isEmpty() && value != null){
            value = value.toLowerCase();
        }
        String splitString[] = value.split("(?!^)");
        String utf8 = Veriable.CHARACTER_UTF8;
        int codePoint;
        String result = "";
        for(String e:splitString){
            if(!e.isEmpty()){
                codePoint = utf8.indexOf(e);
                if(codePoint != -1){
                    result += this.resultCharacter(codePoint);
                }else{
                    result+=e;
                }
            }else{
                result+=" ";
            }
        }
        return result;
    }

    private String resultCharacter(int codePoint){
        String result = "";
        if(codePoint < 18){
            result = "a";
        }else if(18 <= codePoint && codePoint < 20){
            result = "d";
        }else if(20 <= codePoint && codePoint < 32){
            result = "e";
        }else if(32 <= codePoint && codePoint < 38){
            result = "i";
        }else if(38 <= codePoint && codePoint < 56){
            result = "o";
        }else if(56 <= codePoint && codePoint < 68){
            result = "u";
        }else if(68 <= codePoint && codePoint <= 74){
            result = "y";
        }
        return result;
    }
}
