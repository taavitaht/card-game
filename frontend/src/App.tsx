import { useState } from 'react'
import './App.css'

function App() {
  const [card, setCard] = useState<string | null>(null)
  const [gameMessage, setGameMessage] = useState<string | null>(null)
  const [lives, setLives] = useState<number>(0)
  const [score, setScore] = useState<number>(0)
  const [playerName, setPlayerName] = useState<string>('') // ✅ track name input

  const getCard = async () => {
    try {
      const response = await fetch('http://localhost:8080/start', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
      });

      if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);

      const data = await response.json();
      console.log('Server data:', data);

      setCard(data.baseCard);
      setGameMessage(data.message);
      setLives(data.lives);
      setScore(data.score);
      setPlayerName(''); // reset name when starting new game
    } catch (error) {
      console.error('Error fetching card:', error);
    }
  };

  const guessNextCard = async (guess: 'higher' | 'lower' | 'equal') => {
    try {
      const response = await fetch('http://localhost:8080/guess', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ guess }),
      });

      if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);

      const data = await response.json();
      console.log('Server data:', data);

      setCard(data.baseCard);
      setGameMessage(data.message);
      setLives(data.lives);
      setScore(data.score);
    } catch (error) {
      console.error('Error making guess:', error);
    }
  };

  const saveScore = async () => {
    if (!playerName.trim()) {
      alert('Please enter your name before saving your score!');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/scoreboard', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: playerName }),
      });

      if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);

      const data = await response.json();
      console.log('Score saved:', data);
      alert(`Score saved for ${playerName}!`);
    } catch (error) {
      console.error('Error saving score:', error);
    }
  };

  return (
    <>
      <h1>Card Game</h1>
      <div className="card">
        <p>Current Base Card: {card ?? 'None'}</p>
        <p>{gameMessage ?? '-'}</p>
        <p>Lives: {lives}</p>
        <p>Score: {score}</p>
      </div>

      <div className="buttons">
        <button onClick={getCard}>Start New Game</button>
        <p>Make your guess:</p>
        <button onClick={() => guessNextCard('higher')}>Guess Higher</button>
        <button onClick={() => guessNextCard('lower')}>Guess Lower</button>
        <button onClick={() => guessNextCard('equal')}>Guess Equal</button>

        {/* Show name input and save button only when game ends */}
        {gameMessage?.includes('Game') && (
          <>
            <p>Your final score is {score}.</p>
            <input
              type="text"
              placeholder="Enter your name"
              value={playerName}
              onChange={(e) => setPlayerName(e.target.value)}
              required
            />
            <button
              onClick={saveScore}
              disabled={!playerName.trim()}
            >
              Post your score
            </button>
          </>
        )}
      </div>
    </>
  )
}

export default App
