package in.flowers.db.mapper;

import in.flowers.db.dto.CategoryDto;
import in.flowers.db.dto.FlowerDto;
import in.flowers.db.model.Category;
import in.flowers.db.model.Flower;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FLowerMapper {

    private final ImageMapper imageMapper;

    public FLowerMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    public Flower toFlower(FlowerDto flowerDto) {
        Flower flower = new Flower();
        flower.setId(flowerDto.getId());
        flower.setName(flowerDto.getName());

        return null;
    }

    public FlowerDto fromFLower(Flower flower) {
        FlowerDto flowerDto = new FlowerDto();
        flowerDto.setId(flower.getId());
        flowerDto.setName(flower.getName());
        flowerDto.setDescription(flower.getDescription());
        flowerDto.setPrice(flower.getPrice());
        flowerDto.setCategory(fromCategory(flower.getCategory()));
        flowerDto.setImages(imageMapper.fromImages(flower.getImages()));

        return flowerDto;
    }

    public CategoryDto fromCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public List<FlowerDto> fromFlowers(List<Flower> flowers) {
        List<FlowerDto> flowerDtoList = new ArrayList<>();
        flowers.forEach(f -> flowerDtoList.add(fromFLower(f)));
        return flowerDtoList;
    }

}
