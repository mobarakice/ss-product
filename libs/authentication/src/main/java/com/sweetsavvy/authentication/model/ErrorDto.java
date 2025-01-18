package com.sweetsavvy.authentication.model;

public record ErrorDto(int status, String message, String error, String path) {
}
