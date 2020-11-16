package pl.bak.plantation.station.exception;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(Class clazz, Long id) {
        super("Could not find " + clazz.getSimpleName() + " with ID: " + id);
    }
}
