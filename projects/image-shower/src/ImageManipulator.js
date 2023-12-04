import React, { useState } from 'react';

const ImageManipulator = (props) => {
  const [scale, setScale] = useState(1);
  const [rotation, setRotation] = useState(0);

  const handleScaleChange = (e) => {
    const newScale = parseFloat(e.target.value);
    setScale(newScale);
    props.applyTransform(newScale, rotation);
  };

  const handleRotationChange = (e) => {
    const newRotation = parseFloat(e.target.value);
    setRotation(newRotation);
    props.applyTransform(scale, newRotation);
  };

  return (
    <div className="image-manipulator">
      <label>
        Scale:
        <input
          type="range"
          min="0.1"
          max="2"
          step="0.1"
          value={scale}
          onChange={handleScaleChange}
        />
        {scale}
      </label>

      <label>
        Rotation:
        <input
          type="range"
          min="0"
          max="360"
          step="1"
          value={rotation}
          onChange={handleRotationChange}
        />
        {rotation}
      </label>
    </div>
  );
};
export default ImageManipulator;
