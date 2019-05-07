const funcao = require("../Matematica.js");

test("satisfaz (caso classico)", () => {
    expect(funcao.f19(6)).toBe(8);
});

test("argumento fora da faixa", () => {
    expect(() => {
        funcao.f19(-1);
    }).toThrow(RangeError);
});

test("argumento null/undefined", () => {
    expect(() => {
        funcao.f19();
    }).toThrow(TypeError);
});

test("argumento nao numerico", () => {
    expect(() => {
        funcao.f19("abcd");
    }).toThrow(TypeError);
});