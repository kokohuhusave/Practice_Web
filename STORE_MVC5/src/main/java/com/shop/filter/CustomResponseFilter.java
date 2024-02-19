//package com.shop.filter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//
//public class CustomResponseFilter implements Filter {
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        if (response.getContentType() != null && response.getContentType().contains("application/json")) {
//            CustomResponseWrapper responseWrapper = new CustomResponseWrapper((HttpServletResponse) response);
//            chain.doFilter(request, responseWrapper);
//
//            String responseData = responseWrapper.getResponseData();
//            JsonNode rootNode = objectMapper.readTree(responseData);
//
//            // JSON 데이터 처리
//            if (rootNode.isObject()) {
//                processObjectNode((ObjectNode) rootNode);
//            } else if (rootNode.isArray()) {
//                for (JsonNode arrayItem : rootNode) {
//                    if (arrayItem.isObject()) {
//                        processObjectNode((ObjectNode) arrayItem);
//                    }
//                }
//            }
//
//            String processedData = objectMapper.writeValueAsString(rootNode);
//            byte[] responseBytes = processedData.getBytes(StandardCharsets.UTF_8);
//
//            response.setContentLength(responseBytes.length);
//            ServletOutputStream outputStream = response.getOutputStream();
//            outputStream.write(responseBytes);
//            outputStream.flush();
//            outputStream.close();
//        } else {
//            chain.doFilter(request, response);
//        }
//    }
//
//    private void processObjectNode(ObjectNode objectNode) {
//        objectNode.fields().forEachRemaining(entry -> {
//            JsonNode value = entry.getValue();
//            if (value.isTextual()) {
//                // value 값이 텍스트인 경우만 필터링 로직 적용
//                String modifiedValue = processSpecialCharacters(value.textValue());
//                objectNode.put(entry.getKey(), modifiedValue);
//            }
//        });
//    }
//
//    private String processSpecialCharacters(String data) {
//      if (data == null) return null;
//
//      return data.replaceAll("@", "&amp;")
//                  .replaceAll("<", "&lt;")
//                  .replaceAll(">", "&gt;")
//                  .replaceAll("\"", "&quot;")
//                  .replaceAll("'", "&#x27;")
//                  .replaceAll("123","(123)filter" )
//                  .replaceAll("/", "&#x2F;");
//  }
//
//}
//
////public class CustomResponseFilter implements Filter {
////    
////    @Override
////    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
////            throws IOException, ServletException {
////        // MIME 타입을 확인하여 특정 타입에만 처리 적용
////        if (response.getContentType() != null && response.getContentType().contains("application/json")) {
////            CustomResponseWrapper responseWrapper = new CustomResponseWrapper((HttpServletResponse) response);
////            chain.doFilter(request, responseWrapper);
////
////            String responseData = responseWrapper.getResponseData();
////            String processedData = processSpecialCharacters(responseData);
////
////            byte[] responseBytes = processedData.getBytes(StandardCharsets.UTF_8);
////
////            response.setContentLength(responseBytes.length);
////            ServletOutputStream outputStream = response.getOutputStream();
////            outputStream.write(responseBytes);
////            outputStream.flush();
////            outputStream.close();
////        } else {
////            // 그 외의 경우, 변환 없이 진행
////            chain.doFilter(request, response);
////        }
////    }
////
////    private String processSpecialCharacters(String data) {
////        if (data == null) return null;
////
////        // 정규 표현식 대신 단순 문자열 처리로 변경
////        data = data.replace("@", "&amp;");
////        data = data.replace("<", "&lt;");
////        data = data.replace(">", "&gt;");
////        data = data.replace("\"", "&quot;");
////        data = data.replace("'", "&#x27;");
////        data = data.replace("123", "(123)filter");
////        data = data.replace("/", "&#x2F;");
////        return data;
////    }
////}
////public class CustomResponseFilter implements Filter {
////    
////    @Override
////    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
////            throws IOException, ServletException {
////
////        CustomResponseWrapper responseWrapper = new CustomResponseWrapper((HttpServletResponse) response);
////        chain.doFilter(request, responseWrapper);
////        
////        String responseData = responseWrapper.getResponseData();
////
////        String processedData = processSpecialCharacters(responseData);
////
////        byte[] responseBytes = processedData.getBytes(StandardCharsets.UTF_8);
////        
////        response.setContentLength(responseBytes.length);
////        ServletOutputStream outputStream = response.getOutputStream();
////        outputStream.write(responseBytes);
////        outputStream.flush();
////        outputStream.close();
////    }
////
////    private String processSpecialCharacters(String data) {
////        if (data == null) return null;
////
////        return data.replaceAll("@", "&amp;")
////                    .replaceAll("<", "&lt;")
////                    .replaceAll(">", "&gt;")
////                    .replaceAll("\"", "&quot;")
////                    .replaceAll("'", "&#x27;")
////                    .replaceAll("123","(123)filter" )
////                    .replaceAll("/", "&#x2F;");
////    }
////
////}
