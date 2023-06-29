package com.example.web_ai_back.find;

import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.*;

@Service
public class FindServiceImpl implements FindService {
    @Value("${TMAP_APPKEY}")
    private String tmap_apiKey; //티맵 API 앱키 설정

    @Value("${TMAP_URL}")
    private String tmap_url;

    @Value("${TMAP_RG_URL}")
    private String tmap_rg_url;

    @SneakyThrows
    public List<FindDto> findAddressByTmapAPI(String FindName, double longitude, double latitude) { // 티맵 api (통합검색(명칭검색))

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(tmap_url);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        WebClient wc = WebClient.builder().uriBuilderFactory(factory).baseUrl(tmap_url).build();

        String encodedName = URLEncoder.encode(FindName, "UTF-8");

        ResponseEntity<String> result = wc.get()  // HTTP GET
                .uri(uriBuilder -> uriBuilder.path("/tmap/pois")
                        .queryParam("version", 1) //버전
                        .queryParam("searchKeyword", encodedName) // 검색 키워드
                        .queryParam("count", 10) // 개수
                        .queryParam("appKey", tmap_apiKey) // 서비스키
                        .queryParam("searchtypCd", "A") // 거리순, 정확도순 검색(거리순 : R, 정확도순 : A)
                        .queryParam("radius", 0) // 반경( 0: 전국반경)
                        .queryParam("centerLon", longitude) // 중심 좌표의 경도 좌표
                        .queryParam("centerLat", latitude) // 중심 좌표의 위도 좌표
                        .build())

                .retrieve() //response 바로 처리
                .toEntity(String.class)
                .block();

        if (result.getBody() != null) {
            //받아온 JSON 파싱 via json parser
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject)parser.parse(result.getBody());
            //searchPoiInfo의 value들
            JSONObject searchPoiInfo = (JSONObject) object.get("searchPoiInfo");
            //pois의 value들
            JSONObject pois = (JSONObject) searchPoiInfo.get("pois");
            //poi의 value는 배열 -> JSONArray 사용
            JSONArray poiArr = (JSONArray) pois.get("poi");

            List<FindDto> dtos = new ArrayList<>(); //리스트에 담을 dtos 선언

            for (int i = 0; i < poiArr.size(); i++) {
                FindDto findDto = new FindDto();
                object = (JSONObject) poiArr.get(i);
                String middleAddrName = (String) object.get("middleAddrName"); // 도로명주소 ㅇㅇ로
                String roadName = (String) object.get("roadName"); // 도로명주소 ㅇㅇ로
                String firstBuildNo = (String) object.get("firstBuildNo"); //건물번호1
                findDto.setMiddleAddrName(middleAddrName);
                findDto.setRoadName(roadName);
                findDto.setFirstBuildNo(firstBuildNo);

                String addr = middleAddrName + " " + roadName + " " + firstBuildNo;

                String encodedAddr = URLEncoder.encode(addr, "UTF-8");
            }



            return dtos;
        } else {
            return null;
        }
    }

    @SneakyThrows
    public String tMapReverseGeoCoding(String lat, String lon) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders(); //헤더
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8)); // 한글깨짐 방지

        //URI 생성
        UriComponents uri = UriComponentsBuilder
                .fromUriString(tmap_rg_url)
                .queryParam("lon", lon)
                .queryParam("lat", lat)
                .queryParam("version", 1)
                .queryParam("appKey", tmap_apiKey)
                .build(true);

        //response
        ResponseEntity<String> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, new HttpEntity<String>(headers), String.class);

        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject)parser.parse(result.getBody());
        JSONObject addressInfo = (JSONObject) object.get("addressInfo");

        return addressInfo.get("fullAddress").toString();
    }
}
