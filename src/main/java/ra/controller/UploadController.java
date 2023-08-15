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
    public String handleUpload( Model model,@RequestParam("image") MultipartFile multipartFile) {
        // tiến hành upload vào thu mục chỉ định
        File file = new File(uploadPath);
        if (!file.exists()){
            //chua ton tai folder , khoi dao mot folder moi
            file.mkdirs();
        }
        String fileName = multipartFile.getOriginalFilename();
        //upload anh
        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(uploadPath+fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("image",fileName);
        return "home";
    }
}
