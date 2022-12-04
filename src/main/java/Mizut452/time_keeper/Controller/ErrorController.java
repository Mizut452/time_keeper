/*package Mizut452.time_keeper.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
    @ExceptionHandler(NullPointerException.class)
    public String NullPage() {
        return "error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String IllegalPage() {
        return "error";
    }

    @ExceptionHandler(Throwable.class)
    public String ThrowPage() {
        return "error";
    }

    @RequestMapping("/error")
    public String ErrorPage() throws Exception{
        throw new Exception();
    }
}*/
