import React from "react";
import './quiz.css'

class QuizComp extends React.Component{

    constructor(){
        super();
        this.state = {
            questionCount: 6,
            timeLeft: 6 * 60, // Total time in seconds
            attemptedCount: 0
          };
      
          this.timerInterval = null;
    }
    componentDidMount() {
        this.timerInterval = setInterval(this.updateTimer, 1000);
    }
    componentWillUnmount() {
        clearInterval(this.timerInterval);
    
        const options = document.querySelectorAll('input[type="radio"]');
        options.forEach(option => {
          option.removeEventListener('change', this.handleOptionChange);
        });
      }

      getTime = () =>{
        var time = this.state.timeLeft;
        var min = Math.floor(time/60);
        var sec = time%60;
        return ''+min+':'+sec;
      }
    
      updateTimer = () => {
        if (this.state.timeLeft > 0) {
          this.setState(prevState => ({
            timeLeft: prevState.timeLeft - 1
          }));
        } else {
          clearInterval(this.timerInterval);
        }
      }
      handleOptionChange = event => {
        if (event.target.checked) {
          this.setState(prevState => ({
            attemptedCount: prevState.attemptedCount + 1
          }));
        }
      }
    
      updateTimerDisplay = () => {
        const { timeLeft } = this.state;
        const minutes = Math.floor(timeLeft / 60);
        const seconds = timeLeft % 60;
        return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
      }
    
      updateQuizStats = () => {
        const { attemptedCount, questionCount } = this.state;
        return (
          <div>
            Attempted: <span>{attemptedCount}</span> / Unattempted: <span>{questionCount - attemptedCount}</span>
          </div>
        );
      }    
    render() {
        return(
        <React.Fragment>
            <div>
      <h1>Data Structures Quiz</h1>
      <div className="quiz-container">
        <div className="quiz-stats" id="quizStats">
          Attempted: <span id="attemptedCount">0</span> / Unattempted: <span id="unattemptedCount">6</span>
        </div>
        <div className="timer" id="timer">Time: <span id="time">{this.getTime()}</span></div>
        <form id="quiz-form">
          <div className="question">1. What is a data structure?</div>
          <ul className="options">
            <li className="option"><label><input type="radio" name="q1" value="a" />a) A way to store and organize data</label></li>
            <li className="option"><label><input type="radio" name="q1" value="b" />b) A programming language</label></li>
            <li className="option"><label><input type="radio" name="q1" value="c" />c) A database management system</label></li>
          </ul> 
          <div className="question">2. What is an AVL tree?</div>
        <ul className="options">
        <li className="option"><label><input type="radio" name="q11" value="a" />a) A binary search tree with balanced heights</label></li>
        <li className="option"><label><input type="radio" name="q11" value="b" />b) A tree structure with no nodes</label></li>
        <li className="option"><label><input type="radio" name="q11" value="c" />c) A data structure to store key-value pairs</label></li>
        </ul>

        <div className="question">3. What is a heap?</div>
        <ul className="options">
        <li className="option"><label><input type="radio" name="q12" value="a" />a) A stack data structure</label></li>
        <li className="option"><label><input type="radio" name="q12" value="b" />b) A data structure to store elements in a circular manner</label></li>
        <li className="option"><label><input type="radio" name="q12" value="c" />c) A specialized tree-based data structure with the highest (or lowest) element at the root</label></li>
        </ul>

        <div className="question">4. What is a data structure?</div>
        <ul className="options">
        <li className="option"><label><input type="radio" name="q1" value="a" />a) A way to store and organize data</label></li>
        <li className="option"><label><input type="radio" name="q1" value="b" />b) A programming language</label></li>
        <li className="option"><label><input type="radio" name="q1" value="c" />c) A database management system</label></li>
        </ul>

        <div className="question">5. What is an array?</div>
        <ul className="options">
        <li className="option"><label><input type="radio" name="q2" value="a" />a) A linear data structure to store elements of the same type</label></li>
        <li className="option"><label><input type="radio" name="q2" value="b" />b) A collection of key-value pairs</label></li>
        <li className="option"><label><input type="radio" name="q2" value="c" />c) A data structure to store hierarchical data</label></li>
        </ul>

        <div className="question">6. What is a linked list?</div>
        <ul className="options">
        <li className="option"><label><input type="radio" name="q3" value="a" />a) A linear data structure with First-In-First-Out (FIFO) behavior</label></li>
        <li className="option"><label><input type="radio" name="q3" value="b" />b) A data structure that represents a hierarchical tree</label></li>
        <li className="option"><label><input type="radio" name="q3" value="c" />c) A linear data structure in which elements are connected by pointers</label></li>
        </ul>
       
          <button className="btn-submit" type="submit">Submit</button>
        </form>
      </div>
      <div className="feedback-container" id="feedbackContainer">
        <p>Thanks for attempting the quiz!</p>
        <p>If your score meets our criteria, we'll let you know soon.</p>
        <p>Please provide your feedback:</p>
        <form className="feedback-form" id="feedback-form">
          <textarea rows="4" placeholder="Your feedback"></textarea>
          <button className="btn-submit" type="submit">Submit Feedback</button>
        </form>
      </div>
    </div>
    </React.Fragment>
    )
    }
}

export default QuizComp;