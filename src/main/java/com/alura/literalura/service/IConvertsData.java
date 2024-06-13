package com.alura.literalura.service;

public interface IConvertsData {
    <T> T  obterDados(String json, Class<T> classe);

}
