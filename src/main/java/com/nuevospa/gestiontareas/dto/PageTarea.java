package com.nuevospa.gestiontareas.dto;

import com.nuevospa.gestiontareas.model.Tarea;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageTarea {
    private List<Tarea> content;
    private Object pageable;
    private boolean last;
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;
    private Object sort;
    private boolean first;
    private int numberOfElements;
    private boolean empty;
} 