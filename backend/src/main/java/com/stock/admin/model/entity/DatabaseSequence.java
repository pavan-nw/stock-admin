package com.stock.admin.model.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Database sequence.
 */
@Document(collection = "database_sequences")
@EntityScan
public class DatabaseSequence {

    @Id
    private String id;
    private long seq;


    /**
     * Instantiates a new Database sequence.
     */
    public DatabaseSequence() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets seq.
     *
     * @return the seq
     */
    public long getSeq() {
        return seq;
    }

    /**
     * Sets seq.
     *
     * @param seq the seq
     */
    public void setSeq(long seq) {
        this.seq = seq;
    }
}
