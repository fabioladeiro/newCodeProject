import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080'
});

const cadastrarAutores = (nome, email, descricao) => api.post('/autores', { nome, email, descricao });

const cadastrarCategorias = (nome) => api.post('/categorias', { nome });

export default {
  cadastrarAutores,
  cadastrarCategorias
};