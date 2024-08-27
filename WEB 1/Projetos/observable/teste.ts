import { from, of , filter, map, take} from 'rxjs';

let valores = [5, 10, 15, 20, 25, 30, 35, 40];
const obs1 = from(valores).pipe(
    filter(valor => valor % 2 == 0),
    map(valor => valor /10),
    take(2)
)

obs1.subscribe(
    valor => console.log(valor)
)