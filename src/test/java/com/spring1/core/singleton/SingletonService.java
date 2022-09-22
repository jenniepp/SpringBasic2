package com.spring1.core.singleton;

public class SingletonService {

    //1. static 영역에 객체를 딱 1개만 생성해두기.
    private static final SingletonService instance = new SingletonService();

    //2. public으로 열어서 객체 인스턴스가 필요하다면 이 static 메서드를 통해서 조회하도록 허용
    public static SingletonService getInstance() {
        return instance;
    }

    //3. 생성자를 private으로 선언해 외부에서의 객체생성을 막음
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
