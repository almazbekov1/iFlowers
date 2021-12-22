package in.flowers.db.repository;

import in.flowers.db.model.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlowerRepository extends JpaRepository<Flower,Long> {

    Flower findByName(String name);

//    Flower findFlowerBy
}
