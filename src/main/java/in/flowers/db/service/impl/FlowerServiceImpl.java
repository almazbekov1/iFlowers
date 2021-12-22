package in.flowers.db.service.impl;

import in.flowers.db.dto.FlowerDto;
import in.flowers.db.mapper.FLowerMapper;
import in.flowers.db.model.Flower;
import in.flowers.db.model.security.Status;
import in.flowers.db.repository.FlowerRepository;
import in.flowers.db.service.FlowerService;
import in.flowers.db.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FlowerServiceImpl implements FlowerService {

    private final FlowerRepository flowerRepository;

    private final FLowerMapper fLowerMapper;

    private final ImageService imageService;

    public FlowerServiceImpl(FlowerRepository flowerRepository, FLowerMapper fLowerMapper, ImageService imageService) {
        this.flowerRepository = flowerRepository;
        this.fLowerMapper = fLowerMapper;
        this.imageService = imageService;
    }

    @Override
    public Flower addNewFlower(Flower flower) {

        flower.setStatus(Status.ACTIVE);

        return flowerRepository.save(flower);
    }

    @Override
    public List<FlowerDto> getAll() {
        List<FlowerDto> flowerDtoList = fLowerMapper.fromFlowers(flowerRepository.findAll());
        return flowerDtoList;
    }

    @Override
    public Flower findByUsername(String name) {
        return flowerRepository.findByName(name);
    }

    @Override
    public FlowerDto findById(Long id) {
        FlowerDto result = fLowerMapper.fromFLower(flowerRepository.findById(id).get());

        if (result == null) {
//            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }
//        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        flowerRepository.deleteById(id);
    }
}
