package com.example;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.InputSource;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class ExtractTextController {
    @RequestMapping(value="/extract-text", produces="text/plain")
    public String extractText(@RequestParam(value="url", defaultValue = "") String urlString) {
        if (urlString.isEmpty()) {
            return "";
        } else {
            try {
                URL url = new URL(urlString);
                InputStream inputStream = url.openStream();
                InputSource inputSource = new InputSource(inputStream);
                String text = ArticleExtractor.getInstance().getText(inputSource);
                return text;
            } catch (MalformedURLException e) {
                System.err.println(e.getMessage());
                return "";
            } catch (BoilerpipeProcessingException e) {
                System.err.println(e.getMessage());
                return "";
            } catch (java.io.IOException e) {
                System.err.println(e.getMessage());
                return "";
            }
        }
    }
}
