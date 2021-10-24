package model.status;

public interface StatusFactory {

    /**
     * Get a new Status.
     * 
     * @param type.
     * @return a specific status to be created.
     */
    Status createStatus(StatusEnum type);
}
