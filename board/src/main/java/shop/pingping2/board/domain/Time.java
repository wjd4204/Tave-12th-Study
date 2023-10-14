package shop.pingping2.board.domain;

// 테이블 조작 시 자동으로 날짜를 수정해주는 JPA의 Audting 기능을 사용한다.
// 이 time entity 를 만들어 놓고 다른 entity(board .. ) 로 부터 상속받아서 사용한다.

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value = (AuditingEntityListener.class))
public abstract class Time {    // 상속 클래스

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column
    private LocalDateTime modifiedDate;
}
