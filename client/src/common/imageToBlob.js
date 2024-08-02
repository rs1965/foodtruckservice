import React, { useState } from 'react'

function ImageToBlob() {
    const [blobImageSrc, setBlobImageSrc] = useState(null);
    const handleImageUpload = (event) => {
        const file = event.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = (e) => {
                convertToBlob(e.target.result);
            };
            reader.readAsDataURL(file);
        }
    };
    const convertToBlob = (dataUrl) => {
        fetch(dataUrl)
            .then((res) => res.blob())
            .then((blob) => {
                convertBlobToImage(blob);
            });
    };

    const convertBlobToImage = (blob) => {
        const url = URL.createObjectURL(blob);
        setBlobImageSrc(url);
    };
    return (
        <div>
            <input type="file" accept="image/*" onChange={handleImageUpload} />
            <div>
                {blobImageSrc && (
                    <div>
                        <h3>Blob Image:</h3>
                        <img src={blobImageSrc} alt="Blob" style={{ width: '250px', height: '250px' }} />
                    </div>
                )}
            </div>
        </div>
    )
}

export default ImageToBlob
