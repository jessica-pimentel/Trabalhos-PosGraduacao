export class Pessoa {
    constructor(
        public id?: number,
        public nome?: string,
        public idade?: number,
        public dataNascimento?: string,
        public motorista? : string
    ) { }

    dateToRest() {
        if (this.dataNascimento) {
            let dia, mes, ano;
            if (this.dataNascimento.indexOf("/")==-1) {
                dia = this.dataNascimento.substring(0,2);
                mes = this.dataNascimento.substring(2,4);
                ano = this.dataNascimento.substring(4);
            }
            else {
                [dia, mes, ano] = this.dataNascimento.split("/");
            }
            this.dataNascimento = `${ano}-${mes}-${dia}`
        }
    }

    dateFromRest() {
        if (this.dataNascimento) {
            const [ano, mes, dia] = this.dataNascimento.split("-");
            this.dataNascimento = `${dia}/${mes}/${ano}`
        }
    }
}
