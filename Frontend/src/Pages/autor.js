import React, { useState } from 'react';
import api from '../services/libraryApi'

const Autor = () => {
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [descricao, setDescricao] = useState('');

  const validaCadastro = () => {
    api.cadastrarAutores(nome, email, descricao).then((response) => {
      console.log(response)
    })
      .catch(() => console.log('ERRO'));
  };

  return (
    <div>
      <h2>Formulário Autor</h2>
      <div>
        <label htmlFor="nome">Nome:</label>
        <input name="nome" data-testid="nome-input" onChange={(e) => setNome(e.target.value)} />
        <label htmlFor="email">Email:</label>
        <input
          name="email"
          type="email"
          data-testid="email-input"
          onChange={(e) => setEmail(e.target.value)}
        />
        <label htmlFor="descricao">Descrição:</label>
        <input
          name="descricao"
          data-testid="descricao-input"
          onChange={(e) => setDescricao(e.target.value)}
        />
        <button
          type="Button"
          data-testid="signin-btn"
          onClick={() => validaCadastro()}
        >
          CADASTRAR
        </button>
      </div>
    </div>
  );
};

export default Autor;
