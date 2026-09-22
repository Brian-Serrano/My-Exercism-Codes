import ballerina/http;

public type Calculation record {
    float operand1;
    float operand2;
    string operator;
};

public type Response record {
    float result;
    string expression;
};

service / on new http:Listener(9090) {
    resource function post calc(@http:Payload Calculation calculation) returns Response|http:BadRequest {
        return {
            result: getResult(calculation.operand1, calculation.operand2, calculation.operator),
            expression: string `${calculation.operand1}${calculation.operator}${calculation.operand2}`
        };
    }
}

function getResult(float operand1, float operand2, string operator) returns float {
    match operator {
        "+" => {
            return operand1 + operand2;
        }
        "-" => {
            return operand1 - operand2;
        }
        "x" | "*" => {
            return operand1 * operand2;
        }
        "/" => {
            return operand1 / operand2;
        }
        _ => {
            return 0.0;
        }
    }
}
