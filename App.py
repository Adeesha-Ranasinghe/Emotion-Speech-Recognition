import streamlit as st
import numpy as np
import sounddevice as sd
import librosa
from tensorflow.keras.models import load_model
from sklearn.preprocessing import LabelEncoder

# Load trained model
model = load_model('emotion_recognition_model.h5')

# Load or define your label encoder
emotions = ['neutral', 'calm', 'happy', 'sad', 'angry', 'fearful', 'disgust', 'surprised']
label_encoder = LabelEncoder()
label_encoder.fit(emotions)

# Color mapping for emotions
emotion_colors = {
    'neutral': 'gray',
    'calm': '#ADD8E6',  # light blue
    'happy': 'yellow',
    'sad': 'blue',
    'angry': 'red',
    'fearful': 'purple',
    'disgust': 'green',
    'surprised': 'orange'
}

# Constants
SAMPLE_RATE = 22050
DURATION = 5  # seconds

# Function to record audio from microphone
def record_audio(duration):
    recording = sd.rec(int(duration * SAMPLE_RATE), samplerate=SAMPLE_RATE, channels=1, dtype='float32')
    sd.wait()  # Wait until recording is finished
    return recording.flatten()

# Function to extract features
def extract_features(audio):
    mfcc = librosa.feature.mfcc(y=audio, sr=SAMPLE_RATE, n_mfcc=13)
    mfcc_scaled = np.mean(mfcc.T, axis=0)
    return mfcc_scaled

# Function to predict emotion
def predict_emotion(audio):
    feature = extract_features(audio)
    feature = np.expand_dims(np.expand_dims(feature, axis=0), axis=2)  # Reshape for model
    predictions = model.predict(feature)
    emotion = label_encoder.inverse_transform([np.argmax(predictions)])
    accuracy = np.max(predictions)
    return emotion[0], accuracy

st.title('Real-time Speech Emotion Recognition')

# Button to start recording
if st.button('Record Speech'):
    with st.spinner(f'Recording for {DURATION} seconds...'):
        audio = record_audio(DURATION)
        st.success("Recording complete")

        # Predict emotion
        prediction, accuracy = predict_emotion(audio)
        # Display the prediction with color and larger text
        color = emotion_colors.get(prediction, 'black')  # Default to black if no color is found
        st.markdown(f"<h1 style='color:{color}; font-size:30px;'>{prediction}</h1>", unsafe_allow_html=True)
        st.write(f"Accuracy: {accuracy * 100:.2f}%")
