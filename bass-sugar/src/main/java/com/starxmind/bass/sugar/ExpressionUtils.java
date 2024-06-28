package com.starxmind.bass.sugar;

import com.starxmind.bass.sugar.beans.Variable;
import com.starxmind.bass.sugar.exceptions.EvaluateExpressionException;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 表达式工具
 *
 * @author pizzalord
 * @since 1.0
 */
public final class ExpressionUtils {
    private static final Pattern PATTERN = Pattern.compile("\\$\\{(.+?)\\}");

    public static String evaluateExpression(String expression, Map<String, String> variables) {
        Matcher matcher = PATTERN.matcher(expression);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            Variable variable = extractVariable(matcher.group(1));
            String variableValue = variables.get(variable.getName());
            if (variableValue == null && variable.getDefaultValue() != null) {
                variableValue = variable.getDefaultValue();
            }
            matcher.appendReplacement(buffer, variableValue != null ? Matcher.quoteReplacement(variableValue) : "");
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }

    public static Set<String> extractVariables(String expression) {
        Set<String> variables = new HashSet<>();
        Pattern pattern = Pattern.compile("\\$\\{([^\\}]+)\\}");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            String variableExpression = matcher.group(1);
            String variableName = extractVariable(variableExpression).getName();
            variables.add(variableName);
        }

        return variables;
    }

    private static Variable extractVariable(String variableExpression) {
        int colonIndex = variableExpression.indexOf(':');
        if (colonIndex != -1) {
            String[] split = variableExpression.split(":");
            return new Variable(split[0].trim(), split[1].trim());
        } else {
            return new Variable(variableExpression.trim());
        }
    }

    public static Object retrieveValue(Object object, String expression) {
        try {
            String[] keys = expression.split("\\.");
            Object result = object;

            for (String key : keys) {
                Field field = result.getClass().getDeclaredField(key);
                field.setAccessible(true);
                result = field.get(result);
            }

            return result;
        } catch (Exception e) {
            throw new EvaluateExpressionException("Fatal: failed to retrieve value from object based on the expression...", e);
        }
    }
}
