package in.flowers.db.service.impl;

import in.flowers.db.dto.ImageDto;
import in.flowers.db.mapper.ImageMapper;
import in.flowers.db.model.Flower;
import in.flowers.db.model.Image;
import in.flowers.db.repository.FlowerRepository;
import in.flowers.db.repository.ImageRepository;
import in.flowers.db.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;
    private final ImageRepository imageRepository;
    private final FlowerRepository flowerRepository;

    public ImageServiceImpl(ImageMapper imageMapper, ImageRepository imageRepository, FlowerRepository flowerRepository) {
        this.imageMapper = imageMapper;
        this.imageRepository = imageRepository;
        this.flowerRepository = flowerRepository;
    }


    @Override
    public List<ImageDto> findAll() {
        return imageMapper.fromImages(imageRepository.findAll());
    }

    @Override
    public ImageDto findById(Long id) {
        if (imageRepository.findById(id).isPresent()) {
            return imageMapper.fromImage(imageRepository.getById(id));
        } else return null;

    }

    @Override
    public ImageDto saveImage(MultipartFile file, Long id) {
        try {
            Flower flower = flowerRepository.findById(id).get();
            Image image = new Image();
            Long pathId = (long) flower.getImages().size() + 1;
            String path = "image/flowers/" + "flower-" + flower.getId() + "_post-" + pathId + ".bin";
            new File(path).createNewFile();


            image.setPath(path);
            flower.addImageToFlower(image);

            flowerRepository.save(flower);

            byte[] bytes = StreamUtils.copyToByteArray(file.getInputStream());
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(bytes);
            oos.close();

//            System.out.println(Arrays.toString(bytes));
            return imageMapper.fromImage(imageRepository.getById(pathId));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
