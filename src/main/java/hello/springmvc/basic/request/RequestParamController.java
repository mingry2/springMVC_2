package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        Integer age = Integer.parseInt(request.getParameter("age"));
        log.info("username:{}, age:{}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request/param/v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") Integer memberAge) {
        log.info("memberName:{}, memberAge:{}", memberName, memberAge);

        return "ok";
    }

    // 변수명이 동일하면 ("키")생략 가능
    @ResponseBody
    @RequestMapping("/request/param/v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam Integer age) {
        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    // 변수명이 동일하면 @RequestParam도 생략 가능
    @ResponseBody
    @RequestMapping("/request/param/v4")
    public String requestParamV4(String username, Integer age) {
        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    // required로 필수 값, 선택 값 선택가능
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username:{}, age:{}", username, age);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap) {
        log.info("username:{}, age:{}", paramMap.get("username"), paramMap.get("age"));

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(
            @RequestParam String username,
            @RequestParam Integer age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("helloData.getUserName:{}, helloData.getAge:{}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(
            @ModelAttribute HelloData helloData) {
        log.info("helloData.getUserName:{}, helloData.getAge:{}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v3") // 생략 시 단순 타입은 @RequestParam, 그 외 객체들은 @ModelAttribute 반영
    public String modelAttributeV3(HelloData helloData) {
        log.info("helloData.getUserName:{}, helloData.getAge:{}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

}
