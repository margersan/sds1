import React, { useEffect, useState } from 'react'; //import necessário qdo usamos o react
import axios from 'axios' //faz uma ponte (conexão) http com o nosso endpoint
import './styles.css'; 
import { RecordsResponse } from './types';
import { formatDate } from './helpers';
import Pagination from './Pagination'
//import { Link } from 'react-router-dom'
import Filters from '../../components/Filters';


const BASE_URL = 'http://localhost:8080' //endereço do nosso site (teste)

const Records = () => {
    const [ recordsResponse, setRecordsResponse ] = useState<RecordsResponse>(); //hook que cria um estado interno pro componente
    const [activePage, setActivePage] = useState(0);

    useEffect(() => { //ReactHook - ele trata o ciclo de vida do componente;
        axios.get(`${BASE_URL}/records?linesPerPage=12&page=${activePage}`) //requisição http (GET)
            .then(response => setRecordsResponse(response.data)) //qdo a requisição terminar, vamos pergar a resposta(response)
    }, [activePage]); //colocando como dependência e será usando pelo Effect

    const handlePageChange = (index: number) => {
        setActivePage(index)
    }
    return (
        <div className='page-container'>
            <Filters link="/charts" linkText="VER GRÁFICO" />
            <table className='records-table' cellPadding ="0" cellSpacing ="0">
                <thead>
                    <tr>
                        <th>INSTANTE</th>
                        <th>NOME</th>
                        <th>IDADE</th>
                        <th>PLATAFORMA</th>
                        <th>GÊNERO</th>
                        <th>TÍTULO DO GAME</th>
                    </tr>
                </thead>
                <tbody>
                    {recordsResponse?.content.map(record => ( // optional chanel - para evitar a quebra do componente e 
                                                              // map - iteração nos registros
                        <tr key={record.id}>
                            <td>{formatDate(record.moment)}</td>
                            <td>{record.name}</td>
                            <td>{record.age}</td>
                            <td className="text-secondary">{record.gamePlatform}</td>
                            <td>{record.genreName}</td>
                            <td className="text-primary">{record.gameTitle}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <Pagination
                activePage = {activePage}
                goToPage = {handlePageChange}
                totalPages = {recordsResponse?.totalPages}
             />
        </div>
    );
}

export default Records;