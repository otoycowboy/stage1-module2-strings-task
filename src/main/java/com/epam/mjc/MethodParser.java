package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        System.out.println(signatureString);

        StringTokenizer st1 = new StringTokenizer(signatureString.substring(0, signatureString.indexOf('(')), " ");

        String accessModifier = null;
        if (st1.countTokens() > 2) {
            accessModifier = st1.nextToken();
        }
        String returnType = st1.nextToken();
        String methodName = st1.nextToken();

        String arguments = signatureString.substring(signatureString.indexOf('(') + 1, signatureString.lastIndexOf(')'));
        MethodSignature methodSignature = new MethodSignature(methodName, parseArguments(arguments));
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }

    private List<MethodSignature.Argument> parseArguments(String arguments) {
        List<MethodSignature.Argument> argumentList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(arguments, ",");
        while (stringTokenizer.hasMoreTokens()) {
            StringTokenizer stringTokenizer1 = new StringTokenizer(stringTokenizer.nextToken(), " ");
            argumentList.add(new MethodSignature.Argument(stringTokenizer1.nextToken(), stringTokenizer1.nextToken()));
        }

        return argumentList;
    }
}
