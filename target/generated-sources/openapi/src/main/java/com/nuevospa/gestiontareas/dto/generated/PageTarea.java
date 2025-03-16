package com.nuevospa.gestiontareas.dto.generated;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.nuevospa.gestiontareas.dto.generated.Tarea;
import java.util.ArrayList;
import java.util.List;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * PageTarea
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-16T14:52:53.339869700-03:00[America/Santiago]")
public class PageTarea {

  @Valid
  private List<@Valid Tarea> content;

  private Object pageable;

  private Boolean last;

  private Integer totalPages;

  private Long totalElements;

  private Integer size;

  private Integer number;

  private Object sort;

  private Boolean first;

  private Integer numberOfElements;

  private Boolean empty;

  public PageTarea content(List<@Valid Tarea> content) {
    this.content = content;
    return this;
  }

  public PageTarea addContentItem(Tarea contentItem) {
    if (this.content == null) {
      this.content = new ArrayList<>();
    }
    this.content.add(contentItem);
    return this;
  }

  /**
   * Get content
   * @return content
  */
  @Valid 
  @Schema(name = "content", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("content")
  public List<@Valid Tarea> getContent() {
    return content;
  }

  public void setContent(List<@Valid Tarea> content) {
    this.content = content;
  }

  public PageTarea pageable(Object pageable) {
    this.pageable = pageable;
    return this;
  }

  /**
   * Get pageable
   * @return pageable
  */
  
  @Schema(name = "pageable", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pageable")
  public Object getPageable() {
    return pageable;
  }

  public void setPageable(Object pageable) {
    this.pageable = pageable;
  }

  public PageTarea last(Boolean last) {
    this.last = last;
    return this;
  }

  /**
   * Get last
   * @return last
  */
  
  @Schema(name = "last", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("last")
  public Boolean getLast() {
    return last;
  }

  public void setLast(Boolean last) {
    this.last = last;
  }

  public PageTarea totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Get totalPages
   * @return totalPages
  */
  
  @Schema(name = "totalPages", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalPages")
  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public PageTarea totalElements(Long totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * Get totalElements
   * @return totalElements
  */
  
  @Schema(name = "totalElements", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalElements")
  public Long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }

  public PageTarea size(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
  */
  
  @Schema(name = "size", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("size")
  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public PageTarea number(Integer number) {
    this.number = number;
    return this;
  }

  /**
   * Get number
   * @return number
  */
  
  @Schema(name = "number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("number")
  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public PageTarea sort(Object sort) {
    this.sort = sort;
    return this;
  }

  /**
   * Get sort
   * @return sort
  */
  
  @Schema(name = "sort", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sort")
  public Object getSort() {
    return sort;
  }

  public void setSort(Object sort) {
    this.sort = sort;
  }

  public PageTarea first(Boolean first) {
    this.first = first;
    return this;
  }

  /**
   * Get first
   * @return first
  */
  
  @Schema(name = "first", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("first")
  public Boolean getFirst() {
    return first;
  }

  public void setFirst(Boolean first) {
    this.first = first;
  }

  public PageTarea numberOfElements(Integer numberOfElements) {
    this.numberOfElements = numberOfElements;
    return this;
  }

  /**
   * Get numberOfElements
   * @return numberOfElements
  */
  
  @Schema(name = "numberOfElements", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberOfElements")
  public Integer getNumberOfElements() {
    return numberOfElements;
  }

  public void setNumberOfElements(Integer numberOfElements) {
    this.numberOfElements = numberOfElements;
  }

  public PageTarea empty(Boolean empty) {
    this.empty = empty;
    return this;
  }

  /**
   * Get empty
   * @return empty
  */
  
  @Schema(name = "empty", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("empty")
  public Boolean getEmpty() {
    return empty;
  }

  public void setEmpty(Boolean empty) {
    this.empty = empty;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageTarea pageTarea = (PageTarea) o;
    return Objects.equals(this.content, pageTarea.content) &&
        Objects.equals(this.pageable, pageTarea.pageable) &&
        Objects.equals(this.last, pageTarea.last) &&
        Objects.equals(this.totalPages, pageTarea.totalPages) &&
        Objects.equals(this.totalElements, pageTarea.totalElements) &&
        Objects.equals(this.size, pageTarea.size) &&
        Objects.equals(this.number, pageTarea.number) &&
        Objects.equals(this.sort, pageTarea.sort) &&
        Objects.equals(this.first, pageTarea.first) &&
        Objects.equals(this.numberOfElements, pageTarea.numberOfElements) &&
        Objects.equals(this.empty, pageTarea.empty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, pageable, last, totalPages, totalElements, size, number, sort, first, numberOfElements, empty);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageTarea {\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    pageable: ").append(toIndentedString(pageable)).append("\n");
    sb.append("    last: ").append(toIndentedString(last)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    sort: ").append(toIndentedString(sort)).append("\n");
    sb.append("    first: ").append(toIndentedString(first)).append("\n");
    sb.append("    numberOfElements: ").append(toIndentedString(numberOfElements)).append("\n");
    sb.append("    empty: ").append(toIndentedString(empty)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

