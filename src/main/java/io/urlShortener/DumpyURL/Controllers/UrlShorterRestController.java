package io.urlShortener.DumpyURL.Controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import com.google.common.hash.Hashing;
import io.urlShortener.DumpyURL.Model.ShortenUrl;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlShorterRestController {

    private Map<String, ShortenUrl> shortenUrlList = new HashMap<>();

    @PostMapping("/shortenurl")
    public ResponseEntity<ShortenUrl> getShortenUrl(@RequestBody ShortenUrl shortenUrl) throws MalformedURLException {
        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http","https"}
        );
        if (urlValidator.isValid(shortenUrl.getFull_url())) {
            String randomChar = getRandomChars(shortenUrl.getFull_url());
            setShortUrl(randomChar, shortenUrl);
            return ResponseEntity.of(Optional.of(shortenUrl));
        }
        shortenUrl.setError("Please enter valid URl - starting from http or https (Tip:- Copy the url)");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(shortenUrl);
    }

    @GetMapping("/s/{randomstring}")
    public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString) throws IOException {
        response.sendRedirect(shortenUrlList.get(randomString).getFull_url());
    }

    private void setShortUrl(String randomChar, ShortenUrl shortenUrl) throws MalformedURLException {
        shortenUrl.setShort_url("http://localhost:8080/s/"+randomChar);
        shortenUrlList.put(randomChar, shortenUrl);
    }

    private String getRandomChars(String url) {
        String randomStr = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
        return randomStr;
    }

}