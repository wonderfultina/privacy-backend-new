package idata.platform.privacy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;
import java.io.IOException;


@SpringBootApplication(scanBasePackages="idata.platform.privacy")
@EnableTransactionManagement
@MapperScan("idata.platform.*.mapper")
@EnableDiscoveryClient
public class ServicePrivacyApplication {
    public static void main(String[] args) throws IOException {
        //启动主程序
        SpringApplication.run(ServicePrivacyApplication.class, args);
    }
}
