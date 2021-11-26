package com.example.entrevueSpringBoot.exception;
import javax.servlet.http.HttpServletRequest;

import com.example.entrevueSpringBoot.util.Const;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = {Const.PACKAGE} )
public class GestionnaireException extends ResponseEntityExceptionHandler{

    @ModelAttribute
    public void attributs(Model model) {
        model.addAttribute(Const.CODE_ERREUR, Const.MESSAGE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<FilmResourceExceptionDTO> erreurInconnue(HttpServletRequest req, Exception ex) {
        FilmResourceExceptionDTO response = new FilmResourceExceptionDTO();
        response.setErrorCode(Const.CODE_ERREUR);
        response.setErrorMessage(ex.getMessage());
        response.setRequestURL(req.getRequestURL().toString());
        return new ResponseEntity<FilmResourceExceptionDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FilmResourceException.class)
    public ResponseEntity<FilmResourceExceptionDTO> filmResourceError(HttpServletRequest req, FilmResourceException ex) {
        FilmResourceExceptionDTO filmResourceExceptionDTO = new FilmResourceExceptionDTO();
        filmResourceExceptionDTO.setStatus(ex.getStatus());
        filmResourceExceptionDTO.setErrorCode(ex.getErrorCode());
        filmResourceExceptionDTO.setErrorMessage(ex.getMessage());
        filmResourceExceptionDTO.setRequestURL(req.getRequestURL().toString());
        return new ResponseEntity<FilmResourceExceptionDTO>(filmResourceExceptionDTO, ex.getStatus());
    }
}