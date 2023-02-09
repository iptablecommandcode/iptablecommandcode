package me.synology.freash97.Paging.Entity;

import lombok.Getter;

@Getter
public class PagingEntity {

    private int pageNum;
    private int size;
    private int startPageNo;
    private int endPageNo;
    private boolean prev;
    private boolean next;
}
