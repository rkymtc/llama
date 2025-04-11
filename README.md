# LLaMA 3.1 Projesi

## Proje Bileşenleri
1. Python Servisi
2. Java API

## Kurulum

### 1. Ollama Kurulumu
```bash
ollama pull llama3:8b
```

### 2. Python Servisini Başlatma
```bash
cd llama
pip install -r requirements.txt
python app.py
```

### 3. Java API'yi Başlatma
```bash
cd java_spring_boot_api
./mvnw spring-boot:run   # Linux/Mac
mvnw.cmd spring-boot:run # Windows
```

### 4. Ollama API Erişimi ve Doğrulama
- Bu projede yerel olarak çalışan Ollama'ya (`http://localhost:11434/api/generate`) doğrudan erişim kullanılmaktadır.
- Ollama'nın yerel kurulumunda varsayılan olarak doğrulama gerekmez.
- Uzak bir Ollama sunucusuna bağlanmak için:
  ```python
  OLLAMA_API_URL = "https://example.com/api/generate" 
  ```
- Eğer Ollama Cloud veya korumalı bir Ollama API uç noktası kullanılıyorsa, API anahtarı şu şekilde eklenebilir:
  ```python
  headers = {
      "Content-Type": "application/json",
      "Authorization": "Bearer OLLAMA_API_KEY"
  }
  ```
- API anahtarınızı güvenli bir şekilde yönetmek için ortam değişkenleri kullanabilirsiniz:
  ```python
  import os
  OLLAMA_API_KEY = os.environ.get("OLLAMA_API_KEY")
  ```

## Kullanım
```json
{
  "message": "Merhaba, nasılsın?"
}
``` 