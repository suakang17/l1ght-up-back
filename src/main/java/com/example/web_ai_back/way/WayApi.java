package com.example.web_ai_back.way;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WayApi {
    @Autowired
    WayServiceImpl wayService;

    @PostMapping("/way")
    public List<WayDto> FindWay(Double startX, Double startY, Double endX, Double endY, String startName, String endName, Number option) {
        return wayService.findWay(startX, startY, endX, endY, startName, endName, option);
    }

}
