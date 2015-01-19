package idoall.cloud.flume.sink;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Partitionertest implements Partitioner<String> {
	// - [ constant fields ] ----------------------------------------

    /**
     * The constant LOGGER.
     */
    @SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(Partitionertest.class);

    // - [ variable fields ] ----------------------------------------
    // - [ constructor methods ] ------------------------------------

    /**
     * Instantiates a new Single partition.
     *
     * @param props the props
     */
    public Partitionertest(VerifiableProperties props) {
    }

    // - [ interface methods ] ------------------------------------

    /**
     * choose only one partition.
     *
     * @param key partition key
     * @param numberOfPartions number of partitions
     * @return the int
     */
    @Override
    public int partition(String key, int numberOfPartions) {
        return 0;
    }

    // - [ protected methods ] --------------------------------------
    // - [ public methods ] -----------------------------------------
    // - [ private methods ] ----------------------------------------
    // - [ static methods ] -----------------------------------------
    // - [ getter/setter methods ] ----------------------------------
    // - [ main methods ] -------------------------------------------
}
