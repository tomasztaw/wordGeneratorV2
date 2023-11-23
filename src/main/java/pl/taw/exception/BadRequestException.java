/**
 * Created by tomasz_taw
 * Date: 02.11.2023
 * Time: 20:21
 * Project Name: wordGenerator
 * Description:
 */
package pl.taw.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
