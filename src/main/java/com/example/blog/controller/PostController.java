package com.example.blog.controller;

import com.example.blog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class PostController {

    // GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT 총 9가지의 HttpMethod


    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate params, BindingResult result) throws Exception {
        //데이터를 검증하는 이유
        // 1. Client 개발자가 깜빡할 수 있다. 실수로 값을 안보낼 수 있다.
        // 2. Client bug
        // 3. 외부에 해커가 값을 임의로 조작해서 보낼수 있다.
        // 4. DB에 값을 저장할 때 의도치 않은 오류가 발생가능하다.
        // 5. 서버 개발자의 편안함을 위해서
        if (result.hasErrors()) {
            List<FieldError> fieldErrorList = result.getFieldErrors();
            FieldError firstFieldError = fieldErrorList.get(0);
            String fieldName = firstFieldError.getField();
            String errorMessage = firstFieldError.getDefaultMessage();

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;
        }
        return  Map.of();
    }
}
