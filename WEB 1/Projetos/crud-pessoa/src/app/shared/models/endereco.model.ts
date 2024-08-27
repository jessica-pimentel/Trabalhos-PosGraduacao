export class Endereco {
    constructor(
        public id?: number,
        public rua?: string,
        public numero?: number,
        public complemento?: string,
        public bairro?: string,
        public cep?: number,
        public cidade?: string,
        public estado?: string,
        public residencial?: boolean
    ) { }
}
