Real-time Speech Emotion Recognition Application

This project is a real-time speech emotion recognition application built using Streamlit, TensorFlow, and Librosa. It allows users to record their speech through a microphone, and the system predicts the emotional tone from the audio input.

Prerequisites
Before running this application, ensure you have Python installed on your system. The application has been tested with Python 3.8. It may work with other versions but compatibility is not guaranteed.

Installation
To set up your environment and run the application, follow these steps:

Navigate to the project directory:

Open a command prompt or terminal and change directory to the project:

cd path/to/project

Install required Python packages:

Ensure pip is up to date:

python -m pip install --upgrade pip

Install the necessary packages using the requirements.txt file:

pip install -r requirements.txt

Running the Application
Once all dependencies are installed, you can run the application using the following command:

streamlit run streamlit_app.py

This will start the Streamlit server, and your default web browser should automatically open to the application's URL, typically http://localhost:8501. If it doesn't open automatically, you can manually enter this URL into your browser to access the application.

Usage
Click the Record Speech button in the Streamlit application to start recording your speech.
Speak clearly into your microphone for the duration specified (default is 5 seconds).
Once the recording is complete, the application will automatically predict and display the emotional tone of your speech along with the accuracy of the prediction.