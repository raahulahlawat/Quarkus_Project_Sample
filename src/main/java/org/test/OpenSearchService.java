package org.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import jakarta.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.Base64;

@ApplicationScoped
public class OpenSearchService {

    private final CloseableHttpClient httpClient;
    private final String username = "admin";
    private final String password = "mrJaat@1222";
    private final String authHeader;

    public OpenSearchService() {
        httpClient = HttpClients.createDefault();
        authHeader = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
    }

    public String checkHealth() {
        HttpGet request = new HttpGet("http://localhost:9200/_cluster/health");
        request.setHeader("Authorization", authHeader);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            return null;
        }
    }
}
