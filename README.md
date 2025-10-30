# 🐦 SocialAutomation

An Android app built with **Kotlin** that allows users to **log in with Twitter using Firebase Authentication**, and (in future versions) post tweets directly through the **Twitter API**.

> 🚧 The posting feature is currently under development — login and authentication are functional, while post creation and publishing will be added soon.

---

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Setup & Installation](#setup--installation)
- [Usage](#usage)
- [Future Work](#future-work)
- [Contact](#contact)

---

## 🧠 Overview
**SocialAutomation** is a personal Android project designed to integrate social media automation features.  
The main goal is to simplify connecting and interacting with social platforms — starting with **Twitter**.

This project currently supports **Twitter login with Firebase**, and will soon support **posting tweets directly via the Twitter API**.

---

## ✨ Features
✅ Login with Twitter using Firebase Authentication  
🚧 Create and post tweets (in progress)  
🔐 Secure OAuth 1.0a flow  
📱 Clean and modern Android UI built with Kotlin  
☁️ Firebase integration for user management  

---

## 🛠️ Tech Stack
| Category | Tool / Library |
|-----------|----------------|
| **Language** | Kotlin |
| **Architecture** | MVVM |
| **Networking** | Retrofit + Coroutines |
| **Authentication** | Firebase Auth + Twitter API (OAuth 1.0a) |
| **UI Components** | Jetpack (ViewModel, LiveData, Navigation) |
| **Database** | Room (if needed for storing posts or user info) |
| **Build System** | Gradle |

---

## 🧱 Architecture
The project follows the **MVVM (Model-View-ViewModel)** architecture pattern:
- **Model:** Handles API integration and Firebase communication.
- **ViewModel:** Manages app logic and live data updates.
- **View (Activity/Fragment):** Displays UI and observes data changes.

---

## ⚙️ Setup & Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/fotoh19/SocialAutomation.git

## 📬 Contact
👤 **Developed by:** Fotoh  
📧 Email: (fotoh.moh14@gmail.com)  
🔗 GitHub: [fotoh19](https://github.com/fotoh19)
   
