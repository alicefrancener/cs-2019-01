const funcao = require("../Matematica.js");

test("satisfaz (caso classico)", () => {
    expect(funcao.f10(1, 3)).toBe(2.5);
});

test("expoente fora da faixa", () => {
    expect(() => {
        funcao.f10(-1, 10);
    }).toThrow(RangeError);
});

test("precisao fora da faixa", () => {
    expect(() => {
        funcao.f10(5, 1);
    }).toThrow(RangeError);
});