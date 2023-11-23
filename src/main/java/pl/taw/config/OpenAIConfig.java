/**
 * Created by tomasz_taw
 * Date: 23.11.2023
 * Time: 19:39
 * Project Name: wordGeneratorV2
 * Description:
 */
package pl.taw.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIConfig {

    @Value("${spring.ai.openai.api-key}")
    String openaiApiKey;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + openaiApiKey);
            return execution.execute(request, body);
        }));

        return restTemplate;
    }
}
