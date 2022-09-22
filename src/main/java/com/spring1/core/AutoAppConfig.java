package com.spring1.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( //@Component가 붙은 설정 정보 자동 등록
        basePackages = "com.spring1.core.member", //탐색할 패키지의 시작 위치 설정
        //하나의 AppConfig만 작동해야하니까 제외필터 걸어주기
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)

)
public class AutoAppConfig {
}
