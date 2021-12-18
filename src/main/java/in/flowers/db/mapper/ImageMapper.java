package in.flowers.db.mapper;

import in.flowers.db.dto.ImageDto;
import in.flowers.db.model.Image;
import in.flowers.db.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ImageMapper {

    private final ImageRepository imageRepository;

    public ImageMapper(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageDto fromImage(Image image) {
        ImageDto imageDto = new ImageDto();
        byte[] bytes = null;

        FileInputStream fls = null;
        try {
            fls = new FileInputStream(image.getPath());
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(fls);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bytes = (byte[]) ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println(Arrays.toString(bytes));
        imageDto.setImage(bytes);
        imageDto.setId(image.getId());
        return imageDto;
    }

    public List<ImageDto> fromImages(List<Image> images) {
        List<ImageDto> imageDtoList = new ArrayList<>();
        images.forEach(i -> imageDtoList.add(fromImage(i)));
        return imageDtoList;
    }

}
