package com.shop.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomResponseFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        CustomResponseWrapper responseWrapper = new CustomResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseWrapper);
        
        String responseData = responseWrapper.getResponseData();

        String processedData = processSpecialCharacters(responseData);

        byte[] responseBytes = processedData.getBytes(StandardCharsets.UTF_8);
        response.setContentLength(responseBytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(responseBytes);
        outputStream.flush();
        outputStream.close();

        chain.doFilter(request, response);
    }

    private String processSpecialCharacters(String data) {
        if (data == null) return null;
        
        return data.replaceAll("@", "&amp;")
                    .replaceAll("<", "<교채후")
                    .replaceAll(">", ">교채후")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#x27;")
                    .replaceAll("/", "&#x2F;");
    }
}
