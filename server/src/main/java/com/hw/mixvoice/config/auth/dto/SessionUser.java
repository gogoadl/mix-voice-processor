package com.hw.mixvoice.config.auth.dto;

import com.hw.mixvoice.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { // 세션에 사용자 정보를 저장하기 위한 Dto 클래스
    // 세션에 저장하기 위해 User 클래스를 세션에 저장하려고 하니 User 클래스에 직렬화를 구현하지 않았다는 의미의 에러가 뜬다.
    // 이유는 User 클래스가 엔티티 이기 때문
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user)
    {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
