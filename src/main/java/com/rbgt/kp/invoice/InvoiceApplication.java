package com.rbgt.kp.invoice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 开票系统
 * @author 俞春旺
 */
@Slf4j
@SpringBootApplication
public class InvoiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvoiceApplication.class, args);
        log.info("===========================================");
        log.info("======== RBGT - 开票服务系统启动成功 ========");
        log.info("===========================================");
    }

}
