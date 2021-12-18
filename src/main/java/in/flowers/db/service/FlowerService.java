package in.flowers.db.service;



import in.flowers.db.dto.FlowerDto;
import in.flowers.db.model.Flower;

import java.util.List;

public interface FlowerService {

    Flower addNewFlower(Flower flower);

    List<FlowerDto> getAll();

    Flower findByUsername(String name);

    Flower findById(Long id);

    void delete(Long id);

}
