package in.flowers.db.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL
            ,mappedBy = "category"
            ,fetch = FetchType.LAZY
    )
    private List<Flower> flowers;
}
