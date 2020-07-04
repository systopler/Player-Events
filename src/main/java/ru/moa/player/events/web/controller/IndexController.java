package ru.moa.player.events.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.Charset;

@Controller
@RequiredArgsConstructor
public class IndexController {
    @Value("classpath:static/index.html")
    private Resource indexHtml;

    @RequestMapping(value = {"/", "/index.html"}, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String index() throws IOException {
        return StreamUtils.copyToString(indexHtml.getInputStream(), Charset.defaultCharset());
    }
}
