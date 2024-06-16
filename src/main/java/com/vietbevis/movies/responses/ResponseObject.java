package com.vietbevis.movies.responses;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ResponseObject {
    private final Map<String, Object> map = new HashMap<>();

    private ResponseObject() {
        this.setCode(200);
    }

    public static ResponseObject getBuilder() {
        return new ResponseObject();
    }

    public ResponseObject setCode(final int code) {
        map.put("code", code);
        setSuccess(code < 400);
        return this;
    }

    public ResponseObject setCode(final HttpStatus code) {
        this.setCode(code.value());
        return this;
    }

    public ResponseObject setMessage(final String message) {
        map.put("message", message);
        return this;
    }

    public ResponseObject set(final String key, final Object value) {
        map.put(key, value);
        return this;
    }

    public ResponseObject setSuccess(final boolean isSuccess) {
        map.put("success", isSuccess);
        return this;
    }

    public ResponseObject setData(final Object data) {
        map.put("data", data);
        return this;
    }

    public ResponseEntity<?> build() {
        if (map.get("message") == null && map.get("code") != null)
            map.put("message", HttpStatus.valueOf((Integer) map.get("code")).getReasonPhrase());
        int code = (Integer) map.get("code");
        return ResponseEntity.status(HttpStatus.valueOf(code))
                .body((map));
    }
}
