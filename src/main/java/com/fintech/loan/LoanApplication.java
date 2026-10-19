package com.fintech.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fintech.loan.application.LoanApplicationService;
import com.fintech.loan.domain.service.LoanApprovalService;
import com.fintech.loan.domain.service.impl.LoanApprovalServiceImpl;
import com.fintech.loan.infrastructure.repository.LoanApplicationRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.fintech.loan.infrastructure.repository")
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableAsync
public class LoanApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(LoanApplication.class, args);
    }
    
    @Configuration
    static class AppConfig {
        
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
        
        @Bean
        public LoanApprovalService loanApprovalService(LoanApplicationRepository loanApplicationRepository) {
            return new LoanApprovalServiceImpl(loanApplicationRepository);
        }
        
        @Bean
        public LoanApplicationService loanApplicationService(LoanApprovalService loanApprovalService,
                                                          LoanApplicationRepository loanApplicationRepository) {
            return new LoanApplicationService(loanApprovalService, loanApplicationRepository);
        }
    }
    
    @Bean
    public CommandLineRunner initData(LoanApplicationRepository repository) {
        return args -> {
            // Inicialización de datos de prueba si es necesario
            if (repository.count() == 0) {
                // Crear algunas solicitudes de préstamo de ejemplo
                // Esto es solo para fines de demostración y pruebas
            }
        };
    }
}