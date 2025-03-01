package com.saggu.cdc.contract;

import com.saggu.cdc.OrderServiceConsumer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;


/***
 *  This test can be run in offline mode without the actual server. 
 *  It uses the stub jar provided by the service provider and runs on 
 *  the specific port in AutoConfigureStubRunner
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
        stubsMode = LOCAL,
        ids = "com.saggu:service-provider:+:8090")  
              //format: groupid:latestversion:port
			  //groupid:artifactid from POM of provider
			  //+ latest version of it 
			  //8090 can be any port but  match with the port in below code
public class ContractConsumerStubTest {

    @Test
    void test() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<OrderServiceConsumer.Order> response = restTemplate.
                getForEntity("http://localhost:8090/orders/1",
                        OrderServiceConsumer.Order.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(response.getBody());
        assertThat(requireNonNull(response.getBody()).getOrderId()).isEqualTo("1");
    }
}
