package shop.pingping2.board.domain;


// spring security 중 사용자의 권한을 enum 클래스로 만들어 관리한다.
// (enum : 열거형 타입임을 의미하며 집합이 갖는 값이 한정되어 있고, 다른 값을 허용하지 않도록 하기 위해 사용 )

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST","손님"),
    USER("ROLE_USER","일반 사용자");

    private final String key;
    private final String title;

}
