package shop.pingping2.board.dto;

// DTO(Data Transfer Object) : 계층 간에 데이터 교환을 위한 JAVA Bean 이다. 데이터 전달을 목적
// 데이터를 캡슐화한 데이터 전달 객체

// BoardDto //
// 게시판 API 간에 Data를 전달한느 DTO

import lombok.*;
import shop.pingping2.board.domain.Board;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString       // 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴하는 메서드
@NoArgsConstructor      // 인자 없이 객체 생성 가능
public class BoardDto {
    private Long id;
    private String writter;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    public Board toEntity() {
        Board board = Board.builder()
                .id(id)
                .writer(writter)
                .title(title)
                .content(content)
                .build();
        return board;
    }

    @Builder
    public BoardDto(Long id, String title, String content,
                    String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writter = writer;
        this.title = title;
        this.content=content;
        this.createDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

}
