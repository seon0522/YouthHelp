package com.cookandroid.youthhelp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

//Thread를 사용할 시 오류남 -> 그래서 AsyncTask를 사용하기로 함.
public class NetworkThread extends Thread {
    @Override
    public void run() {
        try{
            StringBuilder urlBuilder = new StringBuilder("https://openapi.gg.go.kr/YoungBoyAndGirsRestArea");
            Log.e("Test_url", String.valueOf(urlBuilder));

            urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=4d4e4a13b44341e3aad282014464e8d6");
            urlBuilder.append("&" + URLEncoder.encode("busRouteId", "UTF-8") + "=" + URLEncoder.encode("100100016", "UTF-8"));

            URL url = new URL(urlBuilder.toString());

//            url 연결
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
//            타입설정 application/json 형식으로 전송 ()
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd; //실제 서버로 Request 요청 하는 부분
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            Log.e("BUS_API_TEST",sb.toString());
            rd.close();
            conn.disconnect();


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
