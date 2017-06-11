package monolith.application.impl;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> what, String criteria) {
        super(what.getSimpleName() + " not found by " + criteria);
    }
}
