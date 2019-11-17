package muei.riws.tropespace.tropespace;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import muei.riws.tropespace.tropespace.service.TropeService;
import muei.riws.tropespace.tropespace.service.TropeServiceImpl;

import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackages = "muei.riws.tropespace.tropespace.repository")
public class EsConfig {

    @Bean
    public Client client() throws Exception {

        Settings esSettings = Settings.builder()
                .put("node.name", "kiiKFUc")
                .build();

        @SuppressWarnings("resource")
        TransportClient client = new PreBuiltTransportClient(esSettings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), Integer.valueOf("9300")));
        
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }
    
    @Bean
    public TropeService tropeService() {
        TropeService tropeService = new TropeServiceImpl();
        return tropeService;
        
    }

    //Embedded Elasticsearch Server
    /*@Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(nodeBuilder().local(true).node().client());
    }*/

}