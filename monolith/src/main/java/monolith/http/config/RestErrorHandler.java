package monolith.http.config;

import monolith.application.impl.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@ControllerAdvice
public final class RestErrorHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestErrorHandler.class);

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logError(((ServletWebRequest) request).getRequest(), ex);
        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }

    @ExceptionHandler(HibernateOptimisticLockingFailureException.class)
    public ResponseEntity<String> handleHibernateOptimisticLockingFailureException(HibernateOptimisticLockingFailureException ex, HttpServletRequest httpRequest) {
        logWarn(httpRequest, ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex, HttpServletRequest httpRequest) {
        logWarn(httpRequest, ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception exception, HttpServletRequest httpRequest) {
        logError(httpRequest, getCause(exception));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private static void logInfo(HttpServletRequest httpRequest, Throwable throwable) {
        String restRequest = buildRequestString(httpRequest);
        Throwable cause = Optional
                .ofNullable(throwable.getCause())
                .orElse(throwable);

        logger.info(restRequest, cause.getClass().getSimpleName(), cause.getMessage());
    }

    private static void logWarn(HttpServletRequest httpRequest, Throwable throwable) {
        String restRequest = buildRequestString(httpRequest);
        Throwable cause = Optional
                .ofNullable(throwable.getCause())
                .orElse(throwable);

        logger.warn(restRequest, cause.getClass().getSimpleName(), cause.getMessage());
    }

    private static void logError(HttpServletRequest httpRequest, Throwable throwable) {
        String restRequest = buildRequestString(httpRequest);

        Throwable cause = Optional
                .ofNullable(throwable.getCause())
                .orElse(throwable);

        logger.error(restRequest, cause);
    }

    private static String buildRequestString(HttpServletRequest httpRequest) {
        StringBuilder errorMessageBuilder = new StringBuilder("\n"+httpRequest.getRemoteAddr());
        errorMessageBuilder.append(" ");
        errorMessageBuilder.append(httpRequest.getMethod());
        errorMessageBuilder.append(" ");
        errorMessageBuilder.append(httpRequest.getRequestURI());
        errorMessageBuilder.append(" failed:");
        return errorMessageBuilder.toString();
    }

    private static Throwable getCause(Throwable throwable) {
        Throwable cause = throwable.getCause();
        if (cause == null) {
            return throwable;
        }
        return cause;
    }
}
