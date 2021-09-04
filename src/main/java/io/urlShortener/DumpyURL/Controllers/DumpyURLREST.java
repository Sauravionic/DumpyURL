package io.urlShortener.DumpyURL.Controllers;

import com.google.common.hash.Hashing;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RequestMapping("/rest/url")
@RestController
public class DumpyURLREST {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id) throws RuntimeException {
        String url = stringRedisTemplate.opsForValue().get(id);
//        System.out.println("URL retrived " + url);
        try {
            if (url == null) {
                throw new RuntimeException("There is no Shorter Url for " + id);
            }
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return url;
    }

    @PostMapping
    public String createUrl(@RequestBody String url) {

        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http","https"}
        );

        if (urlValidator.isValid(url)) {
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();

//            System.out.println("URL ID Generated: " + id);
            stringRedisTemplate.opsForValue().set(id,url); // Populating Redis with key-value pair
            return id;
        }
        throw new RuntimeException("URL Invalid " + url);
    }
}
