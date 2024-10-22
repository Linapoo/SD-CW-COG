package com.group13.cog.model;

import java.util.List;

/**
 * Created by Qizhen on 2020/3/10.
 */

public class Page<T> {

    private List<T> data;
    
    private int pageSize;

    private int pageNo;
    
    private int totalPage;
    
    public Page(List<T> data, int pageSize, int pageNo, int totalPage){
        this.setData(data);
        this.setPageSize(pageSize);
        this.setPageNo(pageNo);
        this.setTotalPage(totalPage);
    }

    public Page(List<T> data, int pageSize, int pageNo){
        int totalPage = (data == null)? 0 : (int) Math.ceil((double) data.size()/pageSize);
        int start = (data == null)? 0 : (pageSize*(pageNo-1)>=data.size()? data.size():pageSize*(pageNo-1));
        int end = (data == null)? 0 : (pageSize*pageNo >= data.size()? data.size():pageSize*pageNo);
        this.setData((data == null)? null : data.subList(start, end));
        this.setPageSize(pageSize);
        this.setPageNo(pageNo);
        this.setTotalPage(totalPage);
    }

    public void setData(List<T> data){
        this.data = data;
    }

    public List<T> getData(){
        return data;
    }

    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    public int getPageSize(){
        return pageSize;
    }

    public void setPageNo(int pageNo){
        this.pageNo = pageNo;
    }

    public int getPageNo(){
        return pageNo;
    }

    public void setTotalPage(int totalPage){
        this.totalPage = totalPage;
    }

    public int getTotalPage(){
        return totalPage;
    }
}