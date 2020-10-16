package com.stock.admin.model.response;

import org.springframework.data.domain.Page;

/**
 * The type Paged response.
 */
public class PagedResponse extends Response {
    private int page;
    private int size;
    private int totalPages;
    private boolean isLast;
    private long totalCount;

    /**
     * Instantiates a new Response.
     *
     * @param type           the type
     * @param payload        the payload
     * @param responseStatus the response status
     * @param page           the page
     * @param size           the size
     * @param totalPages     the total pages
     * @param isLast         the is last
     * @param totalCount     the total count
     */
    public PagedResponse(String type, Object payload,
                         ResponseStatus responseStatus,
                         int page, int size,
                         int totalPages, boolean isLast,
                         long totalCount) {
        super(type, payload, responseStatus);
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.isLast = isLast;
        this.totalCount = totalCount;
    }

    /**
     * Build paged response paged response.
     *
     * @param type       the type
     * @param payload    the payload
     * @param success    the success
     * @param page       the page
     * @param size       the size
     * @param totalPages the total pages
     * @param isLast     the is last
     * @param totalCount the total count
     * @return the paged response
     */
    public static PagedResponse buildPagedResponse(String type, Object payload, boolean success,
                                                   int page, int size, int totalPages, boolean isLast,
                                                   long totalCount) {
        return new PagedResponse(type,
                payload,
                success ? ResponseStatus.Success : ResponseStatus.Failure,
                page + 1,
                size,
                totalPages,
                isLast,
                totalCount
        );
    }

    /**
     * Build paged response paged response.
     *
     * @param type the type
     * @param page the page
     * @return the paged response
     */
    public static PagedResponse buildPagedResponse(String type, Page page) {
        return buildPagedResponse(type,
                page.getContent(),
                true,
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.isLast(),
                page.getTotalElements());
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets total pages.
     *
     * @return the total pages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Sets total pages.
     *
     * @param totalPages the total pages
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * Is last boolean.
     *
     * @return the boolean
     */
    public boolean isLast() {
        return isLast;
    }

    /**
     * Sets last.
     *
     * @param last the last
     */
    public void setLast(boolean last) {
        isLast = last;
    }

    /**
     * Gets total count.
     *
     * @return the total count
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * Sets total count.
     *
     * @param totalCount the total count
     */
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
