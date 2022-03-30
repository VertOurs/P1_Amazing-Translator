package fr.vertours.translator.core.exception;

public class InaccurateNumberOrLangException extends RuntimeException {



    @Override
    public String getMessage() {
        return "this application only works for integers between 1 and 30. " +
                "For the moment it only supports English, French and German using their iso code." +
                " please check the validity of your request.";
    }
}
