package com.vietbevis.movies.fetchapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    private String totalItems;
    private Integer totalItemsPerPage;
    private String currentPage;
    private Integer totalPages;
}
