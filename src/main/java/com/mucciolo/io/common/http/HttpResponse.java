package com.mucciolo.io.common.http;

public record HttpResponse(int code, String body) {
  public static final HttpResponse NOT_FOUND = new HttpResponse(404, "Not found");
}
