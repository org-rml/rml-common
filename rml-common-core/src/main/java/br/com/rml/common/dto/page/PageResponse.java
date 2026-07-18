package br.com.rml.common.dto.page;

import java.util.List;

/**
 * Resposta paginada genérica — use em qualquer endpoint que retorne listas com paginação.
 *
 * @param <T> tipo do item na página
 */
public record PageResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last
) {
}

