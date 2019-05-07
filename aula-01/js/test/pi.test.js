const funcao = require("../Matematica.js");

test("satisfaz (caso classico)", () => {
    expect(funcao.f9(1)).toBe(4);
});

test("valor fora da faixa", () => {
    expect(() => {
        funcao.f9(-1);
    }).toThrow(RangeError);
});

test("argumento null/undefined", () => {
    expect(() => {
        funcao.f9();
    }).toThrow(TypeError);
});

test("argumento nao numerico", () => {
    expect(() => {
        funcao.f9("abcd");
    }).toThrow(TypeError);
});