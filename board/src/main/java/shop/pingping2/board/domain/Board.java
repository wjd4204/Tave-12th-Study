package shop.pingping2.board.domain;

// 게시판 글 정보들을 모아놓은 보드 테이블 //

import lombok.AccessLevel;
import lombok.*;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 외부에서의 생성을 열어 둘 필요가 없을 때 / 보안적으로 권장된다.
@Getter
@Entity
@Table(name = "board")  // 이거 보고 테이블 생성
public class Board extends Time {
    // Board : 실제 DB와 연결될 클래스 (Entity Class)
    // JPA에서는 프록시 생성을 위해 기본 생성자를 반드시 하나 생성해야 한다/
    // 생성자 자동 생성 : NoArgsConstructor, AllArgsConstructor
    // NoArgsConstructor : 객체 생성 시 초기 인자 없이 객체를 생성할 수 있다.

    @Id     // 해당 Property가 Primary 키의 역할을 한다는 것을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // Primary Key의 값을 위한 자동 생성 전략을 명시하는데 사용한다.
    private Long id;
    @Column(length = 10,nullable = false)
    private String writer;
    @Column(length = 100,nullable = false)
    private String title;
    @Column(columnDefinition = "Text", nullable = false)
    private String content;

    // @OneToOne :  Board가 필드값으로 갖고 있는 User 도메인을 1:1 관계로 설정하는 Annotation 이다.
    //lazy : User 객체를 조회하는 시점이 아닌 객체가 실제 사용될 때 조회한다는 뜻이다. lazy와 eager 가 있다.
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    // Java 디자인 패턴, 생성 시점에 값을 채워줌
    @Builder
    public Board(Long id, String title, String content, String writer, User user) {
        // Assert 구문으로 안전한 객체 생성 패턴을 구현
        Assert.hasText(writer, "writer must not be empty");
        Assert.hasText(title, "title must not be empty");
        Assert.hasText(content, "content must not be empty");

        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
