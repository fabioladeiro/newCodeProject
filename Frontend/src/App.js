import { Switch, Route, BrowserRouter } from 'react-router-dom';
import Home from './Pages/home';
import Autor from './Pages/autor';
import Categoria from './Pages/categoria';
import Livro from './Pages/livro';

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route path="/home" component = { Home }/>
        <Route path="/cadastro-autores" component = { Autor }/>
        <Route path="/cadastro-categorias" component = { Categoria }/>
        <Route path="/livros" component = { Livro }/>
      </Switch>
    </BrowserRouter>    
  );
}

export default App;
