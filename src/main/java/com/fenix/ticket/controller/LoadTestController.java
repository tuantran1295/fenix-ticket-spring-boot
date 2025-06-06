package com.fenix.ticket.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/loadtest")
public class LoadTestController {

    private static final int TEST_ITERATIONS = 100;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public Map<String, Object> runLoadTest() {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            result.put("login", performLoginTest());
            result.put("createTicket", performCreateTicketTest());
            result.put("listTickets", performListTicketsTest());
            result.put("updateTicket", performUpdateTicketTest());
            result.put("deleteTicket", performDeleteTicketTest());
        } catch (Exception e) {
            result.clear();
            result.put("error", e.getMessage());
        }
        return result;
    }

    // The base URL to use for internal REST API requests. Adjust as necessary.
    private final String API_BASE_URL = "http://localhost:8080/api";

    private HashMap<String, Object> performLoginTest() {
        ArrayList<Long> responseTimes = new ArrayList<>();
        AtomicInteger successCount = new AtomicInteger(0);
        ArrayList<Map<String, Object>> detailsList = new ArrayList<>();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Map<String, Object> loginData = new HashMap<>();
            loginData.put("username", "admin");
            loginData.put("password", "password");
            Map<String, Object> requestDetails = new HashMap<>();
            requestDetails.put("input", new HashMap<>(loginData));
            try {
                long startTime = System.nanoTime();
                ResponseEntity<Map> resp = restTemplate.postForEntity(API_BASE_URL + "/login", loginData, Map.class);
                long endTime = System.nanoTime();
                long execTimeMs = (endTime - startTime) / 1_000_000;
                Map<String, Object> responseMap = resp.getBody();
                if (Boolean.TRUE.equals(responseMap.get("success"))) {
                    successCount.incrementAndGet();
                }
                requestDetails.put("response", responseMap);
                requestDetails.put("executionTime", execTimeMs);
                responseTimes.add(execTimeMs);
            } catch (Exception e) {
                requestDetails.put("response", Map.of("success", false, "error", e.getMessage()));
                requestDetails.put("executionTime", 0);
            }
            detailsList.add(requestDetails);
        }
        HashMap<String, Object> metrics = calculateMetrics(responseTimes, successCount.get());
        metrics.put("details", detailsList);
        return metrics;
    }

    private HashMap<String, Object> performCreateTicketTest() {
        ArrayList<Long> responseTimes = new ArrayList<>();
        AtomicInteger successCount = new AtomicInteger(0);
        ArrayList<Map<String, Object>> detailsList = new ArrayList<>();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Map<String, Object> ticketData = new HashMap<>();
            ticketData.put("title", "負荷テストチケット " + i);
            ticketData.put("description", "これは負荷テスト中に作成されたテストチケットです。");
            ticketData.put("status", "OPEN");
            Map<String, Object> requestDetails = new HashMap<>();
            requestDetails.put("input", new HashMap<>(ticketData));
            try {
                long startTime = System.nanoTime();
                ResponseEntity<Map> resp = restTemplate.postForEntity(API_BASE_URL + "/tickets", ticketData, Map.class);
                long endTime = System.nanoTime();
                long execTimeMs = (endTime - startTime) / 1_000_000;
                Map<String, Object> responseMap = resp.getBody();
                if (Boolean.TRUE.equals(responseMap.get("success"))) {
                    successCount.incrementAndGet();
                }
                requestDetails.put("response", responseMap);
                requestDetails.put("executionTime", execTimeMs);
                responseTimes.add(execTimeMs);
            } catch (Exception e) {
                requestDetails.put("response", Map.of("success", false, "error", e.getMessage()));
                requestDetails.put("executionTime", 0);
            }
            detailsList.add(requestDetails);
        }
        HashMap<String, Object> metrics = calculateMetrics(responseTimes, successCount.get());
        metrics.put("details", detailsList);
        return metrics;
    }

    private HashMap<String, Object> performListTicketsTest() {
        ArrayList<Long> responseTimes = new ArrayList<>();
        AtomicInteger successCount = new AtomicInteger(0);
        ArrayList<Map<String, Object>> detailsList = new ArrayList<>();
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Map<String, Object> requestDetails = new HashMap<>();
            requestDetails.put("input", new HashMap<>());
            try {
                long startTime = System.nanoTime();
                ResponseEntity<Map> resp = restTemplate.getForEntity(API_BASE_URL + "/tickets", Map.class);
                long endTime = System.nanoTime();
                long execTimeMs = (endTime - startTime) / 1_000_000;
                Map<String, Object> responseMap = resp.getBody();
                if (Boolean.TRUE.equals(responseMap.get("success"))) {
                    successCount.incrementAndGet();
                }
                requestDetails.put("response", responseMap);
                requestDetails.put("executionTime", execTimeMs);
                responseTimes.add(execTimeMs);
            } catch (Exception e) {
                requestDetails.put("response", Map.of("success", false, "error", e.getMessage()));
                requestDetails.put("executionTime", 0);
            }
            detailsList.add(requestDetails);
        }
        HashMap<String, Object> metrics = calculateMetrics(responseTimes, successCount.get());
        metrics.put("details", detailsList);
        return metrics;
    }

    private HashMap<String, Object> performUpdateTicketTest() {
        ArrayList<Long> responseTimes = new ArrayList<>();
        AtomicInteger successCount = new AtomicInteger(0);
        ArrayList<Map<String, Object>> detailsList = new ArrayList<>();
        final long fixedTicketId = 1; // You may want to create and track ticketIds dynamically in real tests!
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Map<String, Object> updateData = new HashMap<>();
            updateData.put("id", fixedTicketId);
            updateData.put("title", "更新済み負荷テストチケット " + i);
            updateData.put("description", "このチケットは負荷テスト中に更新されました。");
            updateData.put("status", "IN_PROGRESS");
            Map<String, Object> requestDetails = new HashMap<>();
            requestDetails.put("input", new HashMap<>(updateData));
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(updateData, headers);
                long startTime = System.nanoTime();
                ResponseEntity<Map> resp = restTemplate.exchange(API_BASE_URL + "/tickets", HttpMethod.PUT, entity, Map.class);
                long endTime = System.nanoTime();
                long execTimeMs = (endTime - startTime) / 1_000_000;
                Map<String, Object> responseMap = resp.getBody();
                if (Boolean.TRUE.equals(responseMap.get("success"))) {
                    successCount.incrementAndGet();
                }
                requestDetails.put("response", responseMap);
                requestDetails.put("executionTime", execTimeMs);
                responseTimes.add(execTimeMs);
            } catch (Exception e) {
                requestDetails.put("response", Map.of("success", false, "error", e.getMessage()));
                requestDetails.put("executionTime", 0);
            }
            detailsList.add(requestDetails);
        }
        HashMap<String, Object> metrics = calculateMetrics(responseTimes, successCount.get());
        metrics.put("details", detailsList);
        return metrics;
    }

    private HashMap<String, Object> performDeleteTicketTest() {
        ArrayList<Long> responseTimes = new ArrayList<>();
        AtomicInteger successCount = new AtomicInteger(0);
        ArrayList<Map<String, Object>> detailsList = new ArrayList<>();
        final long fixedTicketId = 1;
        for (int i = 0; i < TEST_ITERATIONS; i++) {
            Map<String, Object> deleteData = new HashMap<>();
            deleteData.put("ticketId", fixedTicketId);
            Map<String, Object> requestDetails = new HashMap<>();
            requestDetails.put("input", new HashMap<>(deleteData));
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(deleteData, headers);
                long startTime = System.nanoTime();
                ResponseEntity<Map> resp = restTemplate.exchange(API_BASE_URL + "/tickets", HttpMethod.DELETE, entity, Map.class);
                long endTime = System.nanoTime();
                long execTimeMs = (endTime - startTime) / 1_000_000;
                Map<String, Object> responseMap = resp.getBody();
                if (Boolean.TRUE.equals(responseMap.get("success"))) {
                    successCount.incrementAndGet();
                }
                requestDetails.put("response", responseMap);
                requestDetails.put("executionTime", execTimeMs);
                responseTimes.add(execTimeMs);
            } catch (Exception e) {
                requestDetails.put("response", Map.of("success", false, "error", e.getMessage()));
                requestDetails.put("executionTime", 0);
            }
            detailsList.add(requestDetails);
        }
        HashMap<String, Object> metrics = calculateMetrics(responseTimes, successCount.get());
        metrics.put("details", detailsList);
        return metrics;
    }

    private HashMap<String, Object> calculateMetrics(ArrayList<Long> responseTimes, int successCount) {
        HashMap<String, Object> metrics = new HashMap<>();
        if (responseTimes.isEmpty()) {
            metrics.put("successRate", 0);
            metrics.put("avgResponseTime", 0);
            metrics.put("minResponseTime", 0);
            metrics.put("maxResponseTime", 0);
            return metrics;
        }
        double successRate = ((double) successCount / responseTimes.size()) * 100;
        metrics.put("successRate", Math.round(successRate * 100) / 100.0);
        long minTime = responseTimes.stream().min(Long::compare).orElse(0L);
        long maxTime = responseTimes.stream().max(Long::compare).orElse(0L);
        double avgTime = responseTimes.stream().mapToLong(v -> v).average().orElse(0);
        metrics.put("avgResponseTime", Math.round(avgTime * 100) / 100.0);
        metrics.put("minResponseTime", minTime);
        metrics.put("maxResponseTime", maxTime);
        return metrics;
    }
}