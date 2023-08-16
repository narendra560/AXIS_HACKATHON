import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import NavBarComp from './NavBarComponent'
import { Home } from './routes';
import JobsComp from './JobsComponent';
import ApplicationsComp from './ApplicationsComp';
import QuizComp from './QuizComponent';

function App() {
  return (
    <Router>
      <NavBarComp></NavBarComp>
      <Routes>
        <Route exact path='/' element={<Home/>}></Route>
        <Route  path='/applications/' element={<  ApplicationsComp job_id = '10000'/>}></Route>
        <Route exact path='/jobs' element = {<JobsComp></JobsComp>}></Route>
        <Route exact path='/quiz' element = {<QuizComp></QuizComp>}></Route>
      </Routes>
    </Router>
  );
}

export default App;
