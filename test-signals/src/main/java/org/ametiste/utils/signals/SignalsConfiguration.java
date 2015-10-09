package org.ametiste.utils.signals;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.http.HttpMethod;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.http.inbound.HttpRequestHandlingMessagingGateway;
import org.springframework.integration.http.inbound.RequestMapping;

import java.util.Collections;


/**
 * Created by atlantis on 10/9/15.
 */
@Configuration
@EnableIntegration
@IntegrationComponentScan
public class SignalsConfiguration {


    @Bean
    public HttpRequestHandlingMessagingGateway inbound() {
        HttpRequestHandlingMessagingGateway gateway = new HttpRequestHandlingMessagingGateway(true);
        RequestMapping requestMapping = new RequestMapping();
        requestMapping.setMethods(HttpMethod.POST, HttpMethod.DELETE, HttpMethod.GET, HttpMethod.OPTIONS,
                HttpMethod.HEAD, HttpMethod.PUT);
        requestMapping.setPathPatterns("{code:\\d\\d\\d}");
        gateway.setRequestMapping(requestMapping);
        gateway.setRequestChannel(requestChannel());
        gateway.setHeaderExpressions(Collections.singletonMap("http_statusCode",
                new SpelExpressionParser().parseExpression("#pathVariables.code")));
        return gateway;
    }

    @Bean
    public DirectChannel requestChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow flow() {
        return IntegrationFlows.from(requestChannel()).transform((MultiMap) -> "").get();
    }


}
