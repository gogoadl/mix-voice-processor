package com.hw.mixvoice.web;

import com.hw.mixvoice.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*  RestController
    컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 준다.
    얘전에는 @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해준다고 생각하면 된다.

    GetMapping
    HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어준다.
*/
@RestController // 컨트롤러를 Json을 반환하는 컨트롤러로 만들어 줍니다.
public class HelloController {

    @GetMapping("/hello") // Http Method인 Get의 요청을 받을 수 있는 Api를 만들어 줌
    public String hello()
    {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount)
    {   // RequestParam 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
        // @RequestParam("가져올 데이터 이름") [데이터 타입] [가져온 데이터를 담을 변수]
        return new HelloResponseDto(name,amount);
    }
}
