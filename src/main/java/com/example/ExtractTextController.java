package com.example;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class ExtractTextController {
    @RequestMapping("/extract-text")
    public String extractText(@RequestParam(value="url", defaultValue = "") String urlString) {
        if (urlString.isEmpty()) {
            return "";
        } else {
            try {
                URL url = new URL(urlString);
                String text = ArticleExtractor.getInstance().getText(url);
                return text;
            } catch (MalformedURLException e) {
                System.err.println(e.getMessage());
                return "";
            } catch (BoilerpipeProcessingException e) {
                System.err.println(e.getMessage());
                return "";
            }
        }
    }
}
