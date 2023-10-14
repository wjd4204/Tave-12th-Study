package shop.pingping2.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.pingping2.board.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    // 이미 email을 통해 생성된 사용자인지 체크
    Optional<User> findBuEmail(String email);

}
