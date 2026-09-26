class CalculatorConundrum {
    public String calculate(int operand1, int operand2, String operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }
        if (operation.isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be empty");
        }
        String expression = operand1 + " " + operation + " " + operand2 + " = ";
        switch (operation) {
            case "+" -> {
                return expression + (operand1 + operand2);
            }
            case "*" -> {
                return expression + (operand1 * operand2);
            }
            case "/" -> {
                if (operand2 == 0) {
                    throw new IllegalOperationException("Division by zero is not allowed", new ArithmeticException());
                }
                return expression + (operand1 / operand2);
            }
            default -> throw new IllegalOperationException("Operation '" + operation + "' does not exist");
        }
    }
}