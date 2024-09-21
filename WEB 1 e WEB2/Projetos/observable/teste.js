"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const rxjs_1 = require("rxjs");
let valores = [5, 10, 15, 20, 25, 30, 35, 40];
const obs1 = (0, rxjs_1.from)(valores).pipe((0, rxjs_1.filter)(valor => valor % 2 == 0), (0, rxjs_1.map)(valor => valor / 10), (0, rxjs_1.take)(2));
obs1.subscribe(valor => console.log(valor));
