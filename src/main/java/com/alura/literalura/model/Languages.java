package com.alura.literalura.model;

public enum Languages {
    PORTUGUESE("pt"),
    ESPANISH("es"),
    ENGLISH("en"),
    FRENCH("fr");

    private final String gutendexLanguages;

    Languages(String gutendexLanguages) {
        this.gutendexLanguages = gutendexLanguages;
    }
    public static Languages fromString(String text) {
        for (Languages language : Languages.values()) {
            if (language.gutendexLanguages.equalsIgnoreCase(text)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Idioma " + text + "não encontrado");
    }
}
