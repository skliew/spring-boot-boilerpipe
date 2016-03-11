package com.example;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class ExtractTextController {
    @RequestMapping(value="/extract-text")
    public ExtractTextResult extractText(@RequestParam(value="url", defaultValue = "") String urlString) {
        if (urlString.isEmpty()) {
            return ExtractTextResult.getDefaultResult();
        } else {
            try {
                URL url = new URL(urlString);
                InputStream inputStream = url.openStream();
                InputSource inputSource = new InputSource(inputStream);
                String text = ArticleExtractor.getInstance().getText(inputSource);
                return new ExtractTextResult(true, text);
            } catch (BoilerpipeProcessingException|IOException e) {
                // MalformedURLException is a subclass of IOException
                System.err.println(e.getMessage());
            }
            // Default result
            return ExtractTextResult.getDefaultResult();
        }
    }
}
