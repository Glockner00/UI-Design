import React, { useState } from 'react';
import ImageManipulator from './ImageManipulator';
import logo from './logo.svg';
import './App.css';

function App() {
  const [transform, setTransform] = useState({
    scale: 1,
    rotation: 0,
  });

  const applyTransform = (scale, rotation) => {
    setTransform({ scale, rotation });
  };

  const { scale, rotation } = transform;
  const imageStyle = {
    transform: `scale(${scale}) rotate(${rotation}deg)`,
  };

  return (
    <div className="App">
      <header className="App-header">
        <ImageManipulator applyTransform={applyTransform} />
        <img src={logo} className="App-logo" alt="logo" style={imageStyle} />
      </header>
    </div>
  );
}

export default App;

