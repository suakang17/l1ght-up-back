package com.example.web_ai_back.find;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@ComponentScan
public class FindApi{

    @Autowired
    private FindServiceImpl findService;

    //TMap 명칭(POI) 통합 검색용 API 컨트롤러
    @RequestMapping(value="/find/address", method = {RequestMethod.POST})
    public List<FindDto> FindByAPI(String keyword, double longitude, double latitude) {
        return findService.findAddressByTmapAPI(keyword, longitude, latitude);
    }

    // image 등록된 danger factor TMap Api marker 표시 위함
    @RequestMapping(value="/find/reverseGeo", method = {RequestMethod.POST})
    public String reverseGeocoding(String lat, String lon) {
        return findService.tMapReverseGeoCoding(lat, lon);
    }
}
