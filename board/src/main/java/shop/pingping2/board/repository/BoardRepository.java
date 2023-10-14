package shop.pingping2.board.repository;

// Repository : Database에 CRUD 명령을 실행하게 만드는 인터페이스
// CRUD : 데이터 생명주기에서 가장 기본적이고 중요한 작업들을 나타내는 약어로, create, read, update, delelte 의 약자이다.
/*
CRUD in Repository : Repository는 데이터에 접근하고 관리하기 위한 메커니즘이며, 주로 CRUD 작업을 수행한다.
여기서 Repository는 CRUD 명령을 데이터베이스에 실행하게 하는 인터페이스로 동작한다.
예를 들어 스프링 프레임워크에서 JPA를 사용하여 Repository 인터페이스를 정의하면 , 개발자는 데이터베이스에 대한 CRUD 연산을 직접 구현할 필요없이
Repository 인터페이스를 통해 데이터에 접근하고 조작할 수 있다.
*/


import org.springframework.data.jpa.repository.JpaRepository;
import shop.pingping2.board.domain.Board;
import shop.pingping2.board.domain.User;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByTitleContaining(String keyword);

    Board findByUser(User user);
}
