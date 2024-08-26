package org.test;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorResponse {

    private static final Logger logger = LoggerFactory.getLogger(DoctorResponse.class);

    private Hits hits;

    public Hits getHits() {
        logger.info("Getting Hits: {}", hits);
        return hits;
    }

    public void setHits(Hits hits) {
        logger.info("Setting Hits: {}", hits);
        this.hits = hits;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Hits {

        private static final Logger logger = LoggerFactory.getLogger(Hits.class);

        private Total total;
        private List<Hit> hits;

        public Total getTotal() {
            logger.info("Getting Total: {}", total);
            return total;
        }

        public void setTotal(Total total) {
            logger.info("Setting Total: {}", total);
            this.total = total;
        }

        public List<Hit> getHits() {
            logger.info("Getting Hits List: {}", hits);
            return hits;
        }

        public void setHits(List<Hit> hits) {
            logger.info("Setting Hits List: {}", hits);
            this.hits = hits;
        }

        public List<Doctor> getDoctors() {
            List<Doctor> doctors = hits.stream().map(Hit::getSource).collect(Collectors.toList());
            logger.info("Getting Doctors from Hits: {}", doctors);
            return doctors;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Total {

        private static final Logger logger = LoggerFactory.getLogger(Total.class);

        private int value;
        private String relation;

        public int getValue() {
            logger.info("Getting Total Value: {}", value);
            return value;
        }

        public void setValue(int value) {
            logger.info("Setting Total Value: {}", value);
            this.value = value;
        }

        public String getRelation() {
            logger.info("Getting Relation: {}", relation);
            return relation;
        }

        public void setRelation(String relation) {
            logger.info("Setting Relation: {}", relation);
            this.relation = relation;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Hit {

        private static final Logger logger = LoggerFactory.getLogger(Hit.class);

        private Doctor _source;

        public Doctor getSource() {
            logger.info("Getting Doctor Source: {}", _source);
            return _source;
        }

        public void setSource(Doctor _source) {
            logger.info("Setting Doctor Source: {}", _source);
            this._source = _source;
        }
    }
}
