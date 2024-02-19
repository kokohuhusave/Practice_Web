package com.shop.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import java.io.IOException;

@Configuration
public class FilterConfig {
	
	//Jackson2Object objectMapper객체 커스터마이징
	@Bean
    public ObjectMapper objectMapper() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        SimpleModule module = new SimpleModule();
        module.addSerializer(String.class, new HtmlEscapingSerializer());
        builder.modules(module); // 생성한 모듈을 빌더에 추가
        return builder.build(); // 커스터마이징된 ObjectMapper 인스턴스 반환
    }
	
	public static class HtmlEscapingSerializer extends StdSerializer<String> {

        public HtmlEscapingSerializer() {
            super(String.class);
        }
        
        //문자열 타입의 필드를 직렬화
        //문자열 데이터를 json으로 변환할때 특수문자 excape
        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            if (value != null) { //HTML 이스케이프 수행후 JSON출력
                String escapedValue = org.apache.commons.text.StringEscapeUtils.escapeHtml4(value);
                gen.writeString(escapedValue); // Json출력작성
            }
        }
	}  
}

