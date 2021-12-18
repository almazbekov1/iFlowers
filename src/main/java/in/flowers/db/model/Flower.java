package in.flowers.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.flowers.db.model.security.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "flowers")
public class Flower extends BaseEntity {

    private String name;



    private String description;

    private double price;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @OneToMany(cascade = CascadeType.ALL
            ,mappedBy = "flower"
            ,fetch = FetchType.LAZY
    )
    private List<Image> images;

    public void addImageToFlower(Image image){
        if(images == null){
            images = new ArrayList<>();
        }
        images.add(image);
        image.setFlower(this);
    }




}
