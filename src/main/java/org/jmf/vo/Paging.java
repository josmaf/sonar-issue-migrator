package org.jmf.vo;

public final class Paging {

    private Integer pageIndex;
    private Integer pageSize;
    private Integer total;
    private String fTotal;
    private Integer pages;

    public String getfTotal() {
        return fTotal;
    }

    public void setfTotal(String fTotal) {
        this.fTotal = fTotal;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

}
