package in.flowers.api.test;


import in.flowers.db.dto.ImageDto;
import in.flowers.db.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    private final ImageService imageService;

    public TestController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<ImageDto>> getAll() {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageService.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDto> getById(@PathVariable Long id) {
        return ResponseEntity
                .ok()
//                .contentType(MediaType.IMAGE_JPEG)
                .body(imageService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ImageDto> saveImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam("id") Long id) {
        ImageDto imageDto = imageService.saveImage(file, id);
        return ResponseEntity
                .ok()
//                .contentType(MediaType.IMAGE_JPEG)
                .body(imageDto);

    }

}
