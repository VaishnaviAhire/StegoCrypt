# StegoCrypt 🔐  
*A Java-based steganography tool for secure and inconspicuous data transmission*  

## 📌 Problem Statement  
In large corporations, maintaining secrecy is crucial, but traditional cryptographic methods can raise suspicion among attackers. There is a need for a secure yet inconspicuous way to transmit sensitive information without alerting unintended users.  

**StegoCrypt** addresses this challenge by utilizing steganography to embed secret data within multimedia files (images), ensuring covert and secure communication.  

## ✅ Solution  
StegoCrypt provides a secure and hidden method for transmitting sensitive information using **steganography**. Instead of relying solely on cryptography, which may attract attention, StegoCrypt embeds secret data within images, making it indistinguishable from regular media. This ensures safe transmission of confidential data without suspicion, making it ideal for corporate security.  

## 🛠 Tech Stack  
- **Programming Language:** Java (Core Java for logic implementation)  
- **GUI Frameworks:**  
  - JavaFX – For modern and interactive UI  
  - Swing & AWT – For additional GUI components and legacy support  
- **Steganography Algorithms:**  
  - **LSB (Least Significant Bit)** – Embedding secret data at the pixel level  
  - **DCT (Discrete Cosine Transform)** – For frequency-based data hiding  
- **File Handling:** Java I/O for reading/writing multimedia and secret data  
- **Image Processing:** Java ImageIO for handling image files  

## 🏗 Architecture & Design  

![Screenshot 2025-02-08 173555](https://github.com/user-attachments/assets/e51df850-2c1f-425e-8b93-5198648eaf33)

### 🔹 **Algorithm for Hiding Data**  
1️⃣ Start the process  
2️⃣ Enter the Secret Information  
3️⃣ Enter the User Code  
4️⃣ Load an image (multimedia data)  
5️⃣ Generate Secret Code using **User Code + Secret Information**  
6️⃣ Hide the secret information securely within the image  
7️⃣ Display the generated secret key  
8️⃣ Stop the process  

### 🔹 **Algorithm for Extracting Data**  
1️⃣ Start the process  
2️⃣ Enter the Secret Code  
3️⃣ Upload the Stegano Medium (Image)  
4️⃣ Extract secret information using the Secret Code  
5️⃣ Stop the process  

## Results

![Screenshot 2025-02-08 173950](https://github.com/user-attachments/assets/f6d2b187-202e-4475-9c3b-46256e6fc6a2)

![Screenshot 2025-02-08 174051](https://github.com/user-attachments/assets/05678bf2-3510-41a8-b867-fa91312c228f)

![Screenshot 2025-02-08 174100](https://github.com/user-attachments/assets/a5a09697-c252-49d1-8227-0355800c4e62)

![Screenshot 2025-02-08 174146](https://github.com/user-attachments/assets/4513209b-f385-460c-904d-0710eb1864ce)

![Screenshot 2025-02-08 174502](https://github.com/user-attachments/assets/72e09420-be04-4886-94d8-ef63e6abcbec)

## 🚀 Getting Started  
### 🔹 Prerequisites  
- Java Development Kit (JDK 8 or later)  
- JavaFX and Swing/AWT libraries  

### 🔹 How to Run  
1. Clone the repository:  
   ```bash
   git clone https://github.com/yourusername/StegoCrypt.git
   cd StegoCrypt
