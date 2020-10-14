package com.stock.admin.service;

import com.stock.admin.model.entity.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * The type Sequence generator service.
 */
@Service
public class SequenceGeneratorService {
    private MongoOperations mongoOperations;

    /**
     * Instantiates a new Sequence generator service.
     *
     * @param mongoOperations the mongo operations
     */
    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    /**
     * Generate sequence string.
     *
     * @param seqName the seq name
     * @return the string
     */
    public String generateSequence(String seqName) {

        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return seqName+"-"+(!Objects.isNull(counter) ? counter.getSeq() : 1);

    }

}
