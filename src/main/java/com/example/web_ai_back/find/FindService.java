package com.example.web_ai_back.find;

import java.util.List;

public interface FindService {
    List<FindDto> findAddressByTmapAPI(String name, double longitude, double latitude); // 티맵 API로 명칭 검색
    String tMapReverseGeoCoding(String lat, String lon); // 위도경도 좌표를 주소로 변환
}
