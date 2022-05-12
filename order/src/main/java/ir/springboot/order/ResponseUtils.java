package ir.springboot.order;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class ResponseUtils {

    public static Mono<ServerResponse> ok(){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build();
    }

    public static Mono<ServerResponse> ok(String result){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(result), String.class);
    }

    public static <T> Mono<ServerResponse> ok(T data, Class<T> clazz){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(data), clazz);
    }
}
