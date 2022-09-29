package com.spring1.core.lifecycle;

//빈 생명주기
public class NetworkClient {

    private String url;

    public NetworkClient(){ //생성할때 생성자 호출
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) { //url은 외부에서 넣을 수 있게
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + "message : " + message);
    }

    //서비스 종료시 호출 : 안전하게 연결 끊기
    public void disconnect() {
        System.out.println("close: " + url);
    }

}
