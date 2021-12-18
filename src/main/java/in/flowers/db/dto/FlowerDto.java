package in.flowers.db.dto;

import in.flowers.db.model.Category;
import in.flowers.db.model.Flower;
import lombok.Data;

import java.util.List;

@Data
public class FlowerDto {

    private Long id;
    private String name;
    private String description;
    private double price;
    private CategoryDto category;
    private List<ImageDto> images;


}
