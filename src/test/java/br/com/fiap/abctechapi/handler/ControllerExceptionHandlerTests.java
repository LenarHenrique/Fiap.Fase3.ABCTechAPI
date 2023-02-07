package br.com.fiap.abctechapi.handler;

import br.com.fiap.abctechapi.handler.exception.MaxAssistsException;
import br.com.fiap.abctechapi.handler.exception.MinAssistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControllerExceptionHandlerTests {

    public ControllerExceptionHandler controllerExceptionHandler;

    @BeforeEach
    public void setUp() {
        controllerExceptionHandler = new ControllerExceptionHandler();
    }

    @Test
    public void whenErrorMinAssistRequiredMethodInvokedThenReturnCorrectlyError() {
        String message = "Invalid assists";
        String description = "Adicione ao menos uma assistencia";

        MinAssistsException minAssistsException = new MinAssistsException(message, description);

        ResponseEntity<ErrorMessageResponse> responseEntity = controllerExceptionHandler.errorMinAssistRequired(minAssistsException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(message, responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getTimestamp());
        assertEquals(description, responseEntity.getBody().getDescription());
        assertEquals(Integer.valueOf(HttpStatus.BAD_REQUEST.value()), responseEntity.getBody().getStatusCode());
    }

    @Test
    public void whenErrorMaxAssistRequiredMethodInvokedThenReturnCorrectlyError() {
        String message = "Invalid assists";
        String description = "Numero maximo de assistencias excedido!";

        MaxAssistsException maxAssistsException = new MaxAssistsException(message, description);

        ResponseEntity<ErrorMessageResponse> responseEntity = controllerExceptionHandler.errorMaxAssistRequired(maxAssistsException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(message, responseEntity.getBody().getMessage());
        assertEquals(description, responseEntity.getBody().getDescription());
        assertEquals(Integer.valueOf(HttpStatus.BAD_REQUEST.value()), responseEntity.getBody().getStatusCode());
    }

    @Test
    public void whenRequestArgumentNotValidMethodInvokedThenReturnCorrectlyError() {

        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);

        ObjectError objectError = new ObjectError("objectName", "defaultMessage");
        List<ObjectError> allErrorList = new ArrayList<>();
        allErrorList.add(objectError);

        when(methodArgumentNotValidException.getAllErrors()).thenReturn(allErrorList);

        ResponseEntity<ErrorMessageResponse> responseEntity = controllerExceptionHandler.requestArgumentNotValid(methodArgumentNotValidException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Objeto passado não é valido!", responseEntity.getBody().getMessage());
        assertEquals(" | defaultMessage", responseEntity.getBody().getDescription());
        assertEquals(Integer.valueOf(HttpStatus.BAD_REQUEST.value()), responseEntity.getBody().getStatusCode());
    }
}