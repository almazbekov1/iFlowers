package in.flowers.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.flowers.db.model.security.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "image")
public class Image  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created")
    private Date created;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "flower_id")
    @JsonIgnore
    private Flower flower;
}
