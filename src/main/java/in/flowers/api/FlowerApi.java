package in.flowers.api;

import in.flowers.db.dto.FlowerDto;
import in.flowers.db.model.Flower;
import in.flowers.db.service.FlowerService;
import in.flowers.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/api/flowers")
public class FlowerApi {


    private final FlowerService flowerService;

    public FlowerApi(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping()
    public ResponseEntity<List<FlowerDto>> getFlowers(){
        return new ResponseEntity<>(flowerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlowerDto> getById(@PathVariable("id") Long id){
        FlowerDto flower = flowerService.findById(id);
        return ResponseEntity
                .ok()
//                .contentType(MediaType.IMAGE_JPEG)
                .body(flower);
    }

}
