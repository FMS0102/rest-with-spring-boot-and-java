package br.com.fms.model;

import br.com.fms.exceptions.UnsupportedMathOperationException;
import br.com.fms.math.SimpleMath;
import br.com.fms.request.converters.NumberConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    private final SimpleMath simpleMath = new SimpleMath();

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable(name = "numberOne") String numberOne,
            @PathVariable(name = "numberTwo") String numberTwo
    ) throws Exception {
        if (NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return simpleMath.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("/sub/{numberOne}/{numberTwo}")
    public Double sub(
            @PathVariable(name = "numberOne") String numberOne,
            @PathVariable(name = "numberTwo") String numberTwo
    ) throws Exception {
        if (NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return simpleMath.sub(NumberConverter.convertToDouble(numberOne),NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("/mul/{numberOne}/{numberTwo}")
    public Double mul(
            @PathVariable(name = "numberOne") String numberOne,
            @PathVariable(name = "numberTwo") String numberTwo
    ) throws Exception {
        if (NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return simpleMath.mul(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("/div/{numberOne}/{numberTwo}")
    public Double div(
            @PathVariable(name = "numberOne") String numberOne,
            @PathVariable(name = "numberTwo") String numberTwo
    ) throws Exception {
        if (NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return simpleMath.div(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("/avg/{numberOne}/{numberTwo}")
    public Double avg(
            @PathVariable(name = "numberOne") String numberOne,
            @PathVariable(name = "numberTwo") String numberTwo
    ) throws Exception {
        if (NumberConverter.isNumeric(numberOne) || NumberConverter.isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return simpleMath.avg(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
    }

    @RequestMapping("/sqr/{number}")
    public Double sqr(
            @PathVariable(name = "number") String number
    ) throws Exception {
        if (NumberConverter.isNumeric(number))
            throw new UnsupportedMathOperationException("Please set a numeric value!");

        return simpleMath.sqr(NumberConverter.convertToDouble(number));
    }
}
