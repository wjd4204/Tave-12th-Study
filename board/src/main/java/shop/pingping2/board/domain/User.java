package shop.pingping2.board.domain;

// Google OAuth2 로그인 한 사용자들에게 대한 정보를 저장하기 위한 User 테이블

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
//@Entity(name = "USER_TABLE")
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)      // 외부에서의 생성을 열어 둘 필요가 없을 때 / 보안적으로 권장된다.
public class User extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column     //  @Column 기본값 true
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }




}


