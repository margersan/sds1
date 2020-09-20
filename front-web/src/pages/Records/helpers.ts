import moment from 'moment' //arquivo destinado a devolução de campos formatados

export const formatDate = (date: string) => {
    return moment(date).format('DD/MM/YYYY HH:mm');
}
