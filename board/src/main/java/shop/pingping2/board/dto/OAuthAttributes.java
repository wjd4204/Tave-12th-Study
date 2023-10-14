package shop.pingping2.board.dto;


import lombok.Builder;
import lombok.Getter;
import shop.pingping2.board.domain.Role;
import shop.pingping2.board.domain.User;

import java.util.Map;

// OAuthAttributes //
// OAuth2 Login 을 통해 가져온 OAuth2User 의 정보를 담아 주기 위한 DTO
@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey,
                           String name,
                           String email,
                           String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // 'OAuthAttributes' Class 의 static Method 임을 의미 하며 static Method 는 별도 객체의 호출 없이 사용 가능 하도록 하기 위한 목적을 지니고 있다.
    // of 란 Custom method 를 정의 하며, Arguments 로 3개를 받는다.
    // Return value 로는 ofGoogle Method 를 호출한 값을 받게 된다
    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // toEntity 메서드를 통해 Service -> Database(Entity)로 Data 를 전달할 때 Dto를 통해서 전달한 다
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
