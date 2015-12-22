package tiger.common.dal.query;

/**
 * The Class AttachQuery.
 */
public class AttachQuery extends BaseQuery {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3586222894525254049L;

    /** The customer id. */
    private long customerId;

    /**
     * Gets the customer id.
     *
     * @return the customer id
     */
    public long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer id.
     *
     * @param customerId the new customer id
     */
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

}
