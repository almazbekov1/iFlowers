package in.flowers.api;

import in.flowers.db.model.Flower;
import in.flowers.db.model.Image;
import in.flowers.db.repository.FlowerRepository;
import in.flowers.db.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/images")
public class ImageApi {

    private final FlowerRepository flowerRepository;

    public ImageApi(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }
    @Autowired
    private ImageRepository imageRepository;


    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getById(@PathVariable("id") Long id) throws IOException, ClassNotFoundException {
        Image image = imageRepository.findById(id).get();
        FileInputStream fls = new FileInputStream(image.getPath());
        ObjectInputStream ois = new ObjectInputStream(fls);

        byte[] byteTest = (byte[]) ois.readObject();

        ois.close();
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(byteTest);
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<List<byte[]>>getAll(){
//
//        return null;
//    }

    @PostMapping()
    public ResponseEntity<byte[]> addImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
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

            System.out.println(Arrays.toString(bytes));
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
