export type RecordsResponse = { //tipo de dado pra tratar o retorno da requisição (GET)
    content: RecordItem[]; //dados do retorno do (GET), que serão exibidos
    totalPages: number;
}

export type RecordItem = {
    id: number;
    moment: string;
    name: string;
    age: number;
    gameTitle: string;
    gamePlatform: Platform;
    genreName: string;
}

export type Platform = 'XBOX' | 'PC' | 'PLAYSTATION';