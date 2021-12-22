package in.flowers.api.test;


import in.flowers.db.dto.FlowerDto;
import in.flowers.db.dto.ImageDto;
import in.flowers.db.model.Flower;
import in.flowers.db.service.FlowerService;
import in.flowers.db.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private  FlowerService flowerService;



    @GetMapping("image/{id}")
    public ResponseEntity<byte[]> getById(@PathVariable("id") Long id) throws IOException, ClassNotFoundException {
        FlowerDto flower = flowerService.findById(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(flower.getImages().get(0).getImage());
    }
}
