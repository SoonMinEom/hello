package com.springboot.hello.controller;

import com.springboot.hello.domain.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//dispatcherServlet이 Mapping해줄 Controller를 등록
@RestController
@RequestMapping("/api/v1/get-api")
public class HelloController {

    // controller가 할당 된 후 어떤 method를 실행할지 연결해주는 어노테이션
    // hello를 get으로 지정
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello World";
    }

    @GetMapping(value = "/name")
    public String getName() {
        return "soonmin";
    }

    @GetMapping(value = "/variable1/{variable}")
    // path parm = pathVariable ->주소를 통해 정보를 넘김
    // 주로, id 와 같이 꼭 입력되어야 하는 parm을 이것으로 설정하는 경우가 많음
    public String getNamVariable1(@PathVariable String variable) {
        return variable;
    }


    // Query parms -> 주소의 ? 뒤에 입력하는 것을 pram으로 받아옴
    // 선택적으로 입력되는 parm은 요것으로 설정하는 편
    @GetMapping(value = "/request1")
    public String getNamVariable2(@RequestParam String name,@RequestParam String email,@RequestParam String organization ) {
        return String.format("%s %s %s", name, email, organization);
    }

    @GetMapping(value = "/request2")
    public String getNamVariable3(@RequestParam Map<String, String> param) {
        param.entrySet().forEach((map)->{
            System.out.printf("Key : %s value : %s\n",map.getKey(),map.getValue());
        });
        return "request2 호출 완료!!";
    }

    @GetMapping(value = "/request3")
    public String getRequestParam(MemberDto memberDto) {
        return memberDto.toString();
    }
}
