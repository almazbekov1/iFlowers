package in.flowers.db.service;

import in.flowers.db.dto.ImageDto;
import in.flowers.db.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    List<ImageDto> findAll();
    ImageDto findById(Long id);
    ImageDto saveImage(MultipartFile file, Long id);
}
