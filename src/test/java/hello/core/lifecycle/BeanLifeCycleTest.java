package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);

        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        //sol1. @Bean(initMethod = "init", destroyMethod = "close")
        //sol2. 외부라이브러리에서 적용되지 않는 것 말고는 이 방법으로 사용.
        @Bean
        public NetworkClient networkClient() {
            System.out.println("qqqqqq");
            NetworkClient networkClient = new NetworkClient();  // 객체 생성에만 집중, 매개변수로 넘겨주지 않는다.
            System.out.println("zzzzzz");
            networkClient.setUrl("http://hello-spring.dev");    // DI 에 집중(수정자 주입).
            return networkClient;
        }
    }
}
