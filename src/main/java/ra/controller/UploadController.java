package ra.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/upload")
@PropertySource("classpath:upload.properties")
public class UploadController {
    @Value("${upload-path}")
    private String uploadPath;

    @GetMapping
    public String upload() {
        return "form";
    }

    @PostMapping("/image")
    public String handleUpload(Model model, @RequestParam("image") List<MultipartFile> images){
        //tiến hành up load vào thư mục chỉ định
        File file =new File(uploadPath);
        if(!file.exists()){
            // chưa tồn tại folder , khởi tạo 1 folder mới
            file.mkdirs();
        }
        List<String> listImageUrls = new ArrayList<>();
        for (MultipartFile multipartFile: images
        ) {
            String fileName = multipartFile.getOriginalFilename();
            listImageUrls.add(fileName);
            // upload ảnh
            try {
                FileCopyUtils.copy(multipartFile.getBytes(),new File(uploadPath+fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        model.addAttribute("image",listImageUrls);
        return "home";
    }
}
