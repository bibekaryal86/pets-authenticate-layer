package pets.authenticate.config;

import static java.util.Arrays.asList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import pets.authenticate.utils.BasicAuthInterceptor;
import pets.authenticate.utils.RestTemplateLoggingInterceptor;

@Configuration
public class RestTemplateConfig {

  @Bean("restTemplate")
  public RestTemplate restTemplate() {
    RestTemplate restTemplate =
        new RestTemplate(new BufferingClientHttpRequestFactory(clientHttpRequestFactory()));
    restTemplate
        .getInterceptors()
        .addAll(asList(new RestTemplateLoggingInterceptor(), new BasicAuthInterceptor()));
    return restTemplate;
  }

  private ClientHttpRequestFactory clientHttpRequestFactory() {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setConnectTimeout(15000);
    return factory;
  }
}
