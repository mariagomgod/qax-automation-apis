package com.ninja4testing.api.models;

import java.util.List;

public class Response {

    private Integer count;
    private String next;
    private String prev;
    private Integer pages;
    private List<Character> results;


    public Response(Integer count, String next, String prev, Integer pages, List<Character> results) {
        this.count = count;
        this.next = next;
        this.prev = prev;
        this.pages = pages;
        this.results = results;
    }

    public Response() {
    }

    public Integer getCount() { return count; }
    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }
    public void setEmail(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }
    public void setPrev(String prev) {
        this.prev = prev;
    }

    public Integer getPages() { return pages; }
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<Character> getResults() { return results; }
    public void setResults(List<Character> results) {
        this.results = results;
    }
}
