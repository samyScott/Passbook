package cn.edu.hdu.passbook.controller;

import cn.edu.hdu.passbook.constant.Constans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>PassTemplate Token Upload</h1>
 * @author samy
 * @date 2019/12/3 19:27
 */
@Slf4j
@Controller
public class TokenUploadController {

    /** redis 客户端 */
    private final StringRedisTemplate redisTemplate;

    public TokenUploadController(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus(){
        return "uploadStatus";
    }

    @PostMapping("/token")
    public String tokenFileUpload(@RequestParam("merchantsId") String merchantsId,
                                  @RequestParam("passTemplateId") String passTemplateId,
                                  @RequestParam("file")MultipartFile file,
                                  RedirectAttributes redirectAttributes){

        if (null == passTemplateId || file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","passTemplateId is null or file is empty");
            return "redirect:/uploadStatus";
        }

        try {
            File cur = new File(Constans.TOKEN_DIR ,merchantsId);
            if (!cur.exists()){
                log.info("Create File: {}",cur.mkdirs());
            }
            Path path = Paths.get(Constans.TOKEN_DIR,merchantsId,passTemplateId);
            Files.write(path,file.getBytes());

            if (!writeTokenToRedis(path,passTemplateId)){
                redirectAttributes.addFlashAttribute("message","write token error");
            }else {
                redirectAttributes.addFlashAttribute("message","You successfully upload '"
                        + file.getOriginalFilename() + "'");
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return "redirect:/uploadStatus";
    }

    /**
     * <h2>将token写入redis</h2>
     * @param path {@link Path}
     * @param key redis key
     * @return true/false
     */
    private boolean writeTokenToRedis(Path path,String key){

        Set<String> tokens;

        try(Stream<String> stream = Files.lines(path)) {
            tokens = stream.collect(Collectors.toSet());
        }catch (IOException ex){
            ex.printStackTrace();
            return false;
        }

        if (!CollectionUtils.isEmpty(tokens)){
            redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
                for (String token : tokens){
                    connection.sAdd(key.getBytes(),token.getBytes());
                }
                return null;
            });
            return true;
        }

        return false;
    }


}
