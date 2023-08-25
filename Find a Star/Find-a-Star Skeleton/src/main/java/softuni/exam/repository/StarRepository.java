package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;

import java.util.List;
import java.util.Optional;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {

    Optional<Object> findFirstByName(String name);

    @Query("select s from Star s where s.starType = 'RED_GIANT' order by s.lightYears")
    List<Star> allStars ();
}
