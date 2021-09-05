package io.urlShortener.DumpyURL.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class shortenURLController {

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String loadIndex() {
        return "index";
    }
}
