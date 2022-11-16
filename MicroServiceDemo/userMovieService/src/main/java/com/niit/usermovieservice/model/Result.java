package com.niit.usermovieservice.model;

import lombok.*;
import java.util.List;

@Data
public class Result {
    private int page;
    private List<Movie> results;
    private int total_pages;
    private int total_results;

}
