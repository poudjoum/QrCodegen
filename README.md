# 🧠 QR Code Generator Backend – Spring Boot

Ce projet est un backend REST développé avec **Spring Boot** qui permet de générer des QR codes personnalisés à partir d’un texte, d’une URL ou d’un email. Les QR codes sont générés à différentes tailles, avec un niveau de correction configurable, et sont stockés localement pour être servis au frontend.

---

## 🚀 Fonctionnalités

- Génération de QR codes à partir d’un payload (texte, URL, email…)
- Support des tailles multiples (ex: 300, 500, 1024)
- Personnalisation du préfixe de fichier
- Choix du niveau de correction : `L`, `M`, `Q`, `H`
- Sauvegarde des images en `.png` dans un dossier configurable
- Exposition des fichiers via un endpoint REST

---

## 🧱 Technologies utilisées

- Java 17
- Spring Boot 3.x
- ZXing (QR code generation)
- Maven

---

## ⚙️ Installation

```bash
git clone https://github.com/ton-utilisateur/qr-code-backend.git
cd qr-code-backend
./mvnw clean package
java -jar target/qr-code-backend.jar
